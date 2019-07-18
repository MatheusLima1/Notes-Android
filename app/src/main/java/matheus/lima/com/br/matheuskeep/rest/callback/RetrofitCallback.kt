package matheus.lima.com.br.matheuskeep.rest.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> callback(sucess: (response: Response<T>?) -> Unit,
                 failure: (throwable: Throwable?) -> Unit): Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            sucess(response)
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
           failure(t)
        }
    }
}
