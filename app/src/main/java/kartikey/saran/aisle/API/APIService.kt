package kartikey.saran.aisle.API

import kartikey.saran.aisle.Model.Note
import kartikey.saran.aisle.Model.Status
import kartikey.saran.aisle.Model.Token
import kartikey.saran.aisle.Utils.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {
    @POST(Constants.PHONE_NUMBER_LOGIN)
    suspend fun phoneNumberLogin(@Query("number") phoneNumber: String): Status?

    @POST(Constants.VERIFY_OTP)
    suspend fun verifyOTP(@Query("number") phoneNumber: String, @Query("otp") otp: String): Token?

    @GET(Constants.TEST_PROFILE_LIST)
    suspend fun getProfileList(@Header("Authorization") token: String): Note?
}