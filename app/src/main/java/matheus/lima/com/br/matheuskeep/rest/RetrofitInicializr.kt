package matheus.lima.com.br.matheuskeep.rest

import matheus.lima.com.br.matheuskeep.rest.service.NoteService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInicializr {

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(5,TimeUnit.SECONDS)
        .build()

    private val retrofit =     Retrofit.Builder()
            .baseUrl("http://192.168.0.84:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    fun noteService():NoteService{
        return retrofit.create(NoteService::class.java)
    }
}