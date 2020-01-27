package matheus.lima.com.br.matheuskeep.rest.service

import matheus.lima.com.br.matheuskeep.entity.Notes
import retrofit2.Call
import retrofit2.http.*

interface NoteService {

    @GET("notes")
    fun list():Call<List<Notes>>

    @POST("notes")
    fun insert(@Body note: Notes) : Call<Notes>

    @PUT("notes/{id}")
    fun alter(@Body note: Notes, @Path("id") id: Int) : Call<Notes>
}