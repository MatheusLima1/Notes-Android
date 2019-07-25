package matheus.lima.com.br.matheuskeep.rest.webclient

import matheus.lima.com.br.matheuskeep.entity.Notes
import matheus.lima.com.br.matheuskeep.rest.RetrofitInicializr
import matheus.lima.com.br.matheuskeep.rest.callback.callback
import matheus.lima.com.br.matheuskeep.util.defaultFailure
import matheus.lima.com.br.matheuskeep.util.defaultResponse

class NoteWebClient {


    fun list(
        preExecute:()->Unit = {},
        finished:()->Unit = {},
        sucess: (note: List<Notes>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        val call = RetrofitInicializr().noteService().list()
        call.enqueue(callback(
            sucess = {it.defaultResponse(sucess)},
            failure = { it.defaultFailure(failure) },
            preExecute = preExecute,
            finished = finished))
    }

    fun insert(note: Notes,
               preExecute: () -> Unit,
               finished:() -> Unit,
               sucess: (note: Notes) -> Unit,
               failure: (throwable: Throwable) -> Unit) {
        val call = RetrofitInicializr().noteService().insert(note)
        call.enqueue(callback(
            sucess = {it.defaultResponse(sucess)},
            failure = { it.defaultFailure(failure) },
            preExecute = preExecute,
            finished = finished))
    }

    fun alter(note: Notes,
              preExecute: () -> Unit,
              finished: () -> Unit,
              sucess: (note: Notes) -> Unit,
              failure: (throwable: Throwable) -> Unit){
        val call = RetrofitInicializr().noteService().alter(note,note.id)
        call.enqueue(callback(
            sucess = {it.defaultResponse(sucess)},
            failure = { it.defaultFailure(failure) },
            preExecute = preExecute,
            finished = finished))
    }

}