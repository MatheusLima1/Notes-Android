package matheus.lima.com.br.matheuskeep.rest

import matheus.lima.com.br.matheuskeep.rest.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInicializr {


    private val retrofit =     Retrofit.Builder()
            .baseUrl("http://192.168.0.57:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun noteService():NoteService{
        return retrofit.create(NoteService::class.java)
    }
}