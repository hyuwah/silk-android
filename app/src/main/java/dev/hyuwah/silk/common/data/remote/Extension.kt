package dev.hyuwah.silk.common.data.remote

import dev.hyuwah.silk.common.domain.model.CallResult
import org.json.JSONObject
import retrofit2.Response

class ServerException(override val message: String?): Exception(message)

fun <In, Out> Response<In>.toCallResult(
    mapData: (response: In) -> Out
): CallResult<Out> {
    return try {
        if (this.isSuccessful) {
            val data = this.body()
            if (data is Unit) {
                CallResult.Success(mapData(data))
            } else if (data != null) {
                CallResult.Success(mapData(data))
            } else {
                CallResult.Failed(httpCode = this.code(), exception = ServerException("Invalid Response Body"))
            }
        } else {
            val message = this.errorBody()?.string()?.let {
                JSONObject(it).getString("error")
            }
            CallResult.Failed(httpCode = this.code(), exception = ServerException(message))
        }
    } catch (e: Exception) {
        CallResult.Failed(exception = e)
    }
}