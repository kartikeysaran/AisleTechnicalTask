package kartikey.saran.aisle.API

import kartikey.saran.aisle.API.APIRepository
import kartikey.saran.aisle.API.ResultData
import kartikey.saran.aisle.Model.Note
import kartikey.saran.aisle.Model.Status
import kartikey.saran.aisle.Model.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UseCase @Inject constructor( private val apiRepository: APIRepository) {

    suspend fun phoneNumberLogin(number: String): Flow<ResultData<Status>> {
        return flow {
            emit(ResultData.Loading)

            val statusModel = apiRepository.phoneNumberLogin(number)

            val resultData = if (statusModel == null) {
                ResultData.Failed()
            } else {
                ResultData.Success(statusModel)
            }

            emit(resultData)
        }.catch {
            emit(ResultData.Failed())
        }
    }

    suspend fun verifyOtp(number: String, otp: String): Flow<ResultData<Token>> {
        return flow {
            emit(ResultData.Loading)

            val tokenModel = apiRepository.verifyOtp(number, otp)

            val resultData = if (tokenModel == null) {
                ResultData.Failed()
            } else {
                ResultData.Success(tokenModel)
            }

            emit(resultData)
        }.catch {
            emit(ResultData.Failed())
        }
    }

    suspend fun getProfileList(authKey: String): Flow<ResultData<Note>> {
        return flow {
            emit(ResultData.Loading)

            val profiles = apiRepository.getProfileList(authKey)

            val resultData = if (profiles == null) {
                ResultData.Failed()
            } else {
                ResultData.Success(profiles)
            }

            emit(resultData)
        }.catch {
            emit(ResultData.Failed())
        }
    }
}