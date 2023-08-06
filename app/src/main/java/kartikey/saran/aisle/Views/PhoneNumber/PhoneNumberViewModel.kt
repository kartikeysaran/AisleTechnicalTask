package kartikey.saran.aisle.Views.PhoneNumber

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kartikey.saran.aisle.API.ResultData
import kartikey.saran.aisle.API.UseCase
import kartikey.saran.aisle.Model.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneNumberViewModel @Inject constructor(private val useCase: UseCase): ViewModel() {
    private val _performPhoneNumberLogin = MutableLiveData<ResultData<Status>>()
    val performPhoneNumberLogin: LiveData<ResultData<Status>> = _performPhoneNumberLogin
    fun performPhoneNumberLogin(number: String) {
            viewModelScope.launch {
                _performPhoneNumberLogin.postValue(ResultData.Loading)
                try {
                    useCase.phoneNumberLogin(number).onEach {
                        _performPhoneNumberLogin.postValue(it)
                    }.collect()
                } catch (exception: Exception) {
                    _performPhoneNumberLogin.postValue(ResultData.Failed(exception.message))
                }
            }
    }
}