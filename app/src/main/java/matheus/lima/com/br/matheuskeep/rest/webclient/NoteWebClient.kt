package matheus.lima.com.br.matheuskeep.rest.webclient

import android.util.Log
import matheus.lima.com.br.matheuskeep.entity.Notes
import matheus.lima.com.br.matheuskeep.rest.CallBackResponse
import matheus.lima.com.br.matheuskeep.rest.RetrofitInicializr
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteWebClient {

    fun list(callBackResponse: CallBackResponse<List<Notes>>){
        val call = RetrofitInicializr().noteService().list()
        call.enqueue(object: Callback<List<Notes>?> {
            override fun onResponse(call: Call<List<Notes>?>?,
                                    response: Response<List<Notes>?>?) {

                response?.body()?.let {
                    val notes: List<Notes> = it
                    callBackResponse.sucess(notes)
                }
            }

            override fun onFailure(call: Call<List<Notes>?>?,
                                   t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }


    fun insert(note: Notes, callBackResponse: CallBackResponse<Notes>){
        val call = RetrofitInicializr().noteService().insert(note)
        call.enqueue(object: Callback<Notes?> {
            override fun onResponse(call: Call<Notes?>?,
                                    response: Response<Notes?>?) {

                callBackResponse.sucess(note)
           }

            override fun onFailure(call: Call<Notes?>?,
                                   t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

}