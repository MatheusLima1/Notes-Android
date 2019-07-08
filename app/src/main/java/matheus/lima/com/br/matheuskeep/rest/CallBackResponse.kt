package matheus.lima.com.br.matheuskeep.rest

import matheus.lima.com.br.matheuskeep.entity.Notes

interface CallBackResponse<T> {

    fun sucess(response: T)
}