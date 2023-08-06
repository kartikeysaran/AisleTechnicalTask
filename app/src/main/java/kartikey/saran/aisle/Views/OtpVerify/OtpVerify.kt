package kartikey.saran.aisle.Views.OtpVerify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kartikey.saran.aisle.API.ResultData
import kartikey.saran.aisle.R
import kartikey.saran.aisle.Utils.PrefUtil
import kartikey.saran.aisle.Views.Dashboard.DashboardActivity
import kartikey.saran.aisle.databinding.ActivityOtpVerifyBinding
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class OtpVerify : AppCompatActivity() {

    private lateinit var binding: ActivityOtpVerifyBinding
    private val viewModel: OtpVerifyViewModel by viewModels()
    private lateinit var phoneNumber: String
    private lateinit var otp: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        setUpViews()
        timer()
    }

    private fun setUpObserver() {
        viewModel.performOTPLogin.observe(this@OtpVerify) {
            when(it) {
                is ResultData.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ResultData.Failed ->{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@OtpVerify, getString(R.string.please_try_again_later), Toast.LENGTH_SHORT).show()
                    finish()
                }
                is ResultData.Success ->{
                    binding.progressBar.visibility = View.GONE
                    if(!it.data?.token.isNullOrEmpty()) {
                        val intent = Intent(this@OtpVerify, DashboardActivity::class.java)
                        intent.flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        runBlocking {
                            PrefUtil(this@OtpVerify).saveToken(it.data?.token ?: "")
                        }
                        finish()
                    } else {
                        Toast.makeText(this@OtpVerify, getString(R.string.please_try_again_later), Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }

    private fun setUpViews() {
        phoneNumber = intent.getStringExtra("phoneNumber").toString()
        if(!phoneNumber.isNullOrEmpty()) binding.tVPhone.text = phoneNumber
        else {
            Toast.makeText(this@OtpVerify, getString(R.string.please_try_again_later), Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.eTOtp.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val otp = p0.toString()
                if(otp.length != 4){
                    binding.btnContinue.isEnabled = false
                    binding.eTOtp.error = getString(R.string.enter_4_digit_otp)
                }
                else {
                    hideKeyboard()
                    binding.btnContinue.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.btnContinue.setOnClickListener {
            otp = binding.eTOtp.text.toString()
            viewModel.performOTPLogin(phoneNumber, otp)
        }
    }

    fun hideKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun timer() {
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tVTimer.text = "00:"+(millisUntilFinished / 1000).toString()
            }
            override fun onFinish() {
                binding.tVTimer.text = getString(R.string._0_00)
            }
        }.start()
    }

}