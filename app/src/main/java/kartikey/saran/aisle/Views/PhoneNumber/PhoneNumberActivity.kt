package kartikey.saran.aisle.Views.PhoneNumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kartikey.saran.aisle.API.ResultData
import kartikey.saran.aisle.R
import kartikey.saran.aisle.databinding.ActivityPhoneNumberBinding
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import kartikey.saran.aisle.Utils.PrefUtil
import kartikey.saran.aisle.Views.Dashboard.DashboardActivity
import kartikey.saran.aisle.Views.OtpVerify.OtpVerify

@AndroidEntryPoint
class PhoneNumberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneNumberBinding
    private val viewModel: PhoneNumberViewModel by viewModels()
    private lateinit var phoneNumber: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        setUpViews()
    }

    private fun setUpObserver() {
        viewModel.performPhoneNumberLogin.observe(this@PhoneNumberActivity) {
            when(it) {
                is ResultData.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ResultData.Failed ->{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@PhoneNumberActivity, getString(R.string.please_try_again_later), Toast.LENGTH_SHORT).show()
                }
                is ResultData.Success ->{
                    binding.progressBar.visibility = View.GONE
                    if(it.data?.status == true) {
                        val intent = Intent(this@PhoneNumberActivity, OtpVerify::class.java)
                        intent.putExtra("phoneNumber", phoneNumber)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@PhoneNumberActivity, getString(R.string.please_try_again_later), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setUpViews() {

        PrefUtil(this).getToken.asLiveData().observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
        })

        binding.eTPhoneNumber.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val number = p0.toString()
                if(number.length != 10){
                    binding.btnContinue.isEnabled = false
                    binding.eTPhoneNumber.error = getString(R.string.enter_10_digit_phone_number)
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
            val countryCode = binding.eTCountryCode.text.toString()
            val phoneNumber = binding.eTPhoneNumber.text.toString()
            this.phoneNumber = countryCode+phoneNumber
            viewModel.performPhoneNumberLogin(phoneNumber)
        }
    }

    fun hideKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}