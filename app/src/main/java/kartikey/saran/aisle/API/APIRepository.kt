package kartikey.saran.aisle.API

import kartikey.saran.aisle.Model.Note
import kartikey.saran.aisle.Model.Status
import kartikey.saran.aisle.Model.Token
import retrofit2.Response
import javax.inject.Inject

class APIRepository @Inject constructor(private val apiService: APIService){

    suspend fun phoneNumberLogin(number: String): Status? {
        return apiService.phoneNumberLogin(number)
    }

    suspend fun verifyOtp(number: String, otp: String): Token? {
        return apiService.verifyOTP(number, otp)
    }

    suspend fun getProfileList(authKey: String): Note? {
        return apiService.getProfileList(authKey)
    }

}