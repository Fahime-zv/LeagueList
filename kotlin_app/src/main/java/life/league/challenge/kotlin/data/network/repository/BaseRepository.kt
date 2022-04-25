package life.league.challenge.kotlin.data.network.repository


import android.util.Log
import life.league.challenge.kotlin.presentation.extenstions.TAG
import life.league.challenge.kotlin.data.network.model.Result

abstract class BaseRepository {

    inline fun <reified M : Any> safeApiCall(apiCall: () -> M): Result<M> {
        // Calling the apiCall method that passed as a higher order function
        val result = try {
            val response = apiCall.invoke()
            Result.Data(response)
        } catch (e: Throwable) { // If response code is not 200 then an exception will be thrown
            Log.e(TAG, "${e.message}")
            Result.NetworkError(e)
        }
        return result
    }

}