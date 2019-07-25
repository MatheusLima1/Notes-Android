package matheus.lima.com.br.matheuskeep.util

import retrofit2.Response

fun <T> Response<T>?.defaultResponse(sucess: (t: T) -> Unit){
    this?.body()?.let { sucess(it) }
}

fun Throwable?.defaultFailure(failure: (throwable: Throwable) -> Unit){
    this?.let{
        failure(it)
    }
}