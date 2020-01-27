package matheus.lima.com.br.matheuskeep.rest

interface CallBackResponse<T> {

    fun sucess(response: T)
}