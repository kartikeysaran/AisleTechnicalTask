package kartikey.saran.aisle.Views.OtpVerify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kartikey.saran.aisle.API.ResultData
import kartikey.saran.aisle.API.UseCase
import kartikey.saran.aisle.Model.Token
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpVerifyViewModel @Inject constructor(private val useCase: UseCase): ViewModel(){
    private val _performOTPLogin = MutableLiveData<ResultData<Token>>()
    val performOTPLogin: LiveData<ResultData<Token>> = _performOTPLogin
    fun performOTPLogin(phoneNumber: String, otp: String) {
        viewModelScope.launch {
            _performOTPLogin.postValue(ResultData.Loading)
            try {
                useCase.verifyOtp(phoneNumber, otp).onEach {
                    _performOTPLogin.postValue(it)
                }.collect()
            } catch (exception: Exception) {
                _performOTPLogin.postValue(ResultData.Failed(exception.message))
            }
        }
    }
}