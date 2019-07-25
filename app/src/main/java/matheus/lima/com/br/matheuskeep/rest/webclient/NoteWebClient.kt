package matheus.lima.com.br.matheuskeep.rest.webclient

import matheus.lima.com.br.matheuskeep.entity.Notes
import matheus.lima.com.br.matheuskeep.rest.RetrofitInicializr
import matheus.lima.com.br.matheuskeep.rest.callback.callback

class NoteWebClient {


    fun list(
        sucess: (note: List<Notes>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        val call = RetrofitInicializr().noteService().list()
        call.enqueue(callback({ response ->
            response?.body()?.let {
                sucess(it)
            }
        }, { throwable ->
            throwable?.let { (failure(it)) }
        }))
    }

    fun insert(note: Notes, finished:() -> Unit,sucess: (note: Notes) -> Unit,
               failure: (throwable: Throwable) -> Unit) {
        val call = RetrofitInicializr().noteService().insert(note)
        call.enqueue(callback({ response ->
            response?.body()?.let {
                sucess(it)
                finished()
            }
        }, { throwable ->
            throwable?.let {
                failure(it)
                finished()
            }
        }))
    }

    fun alter(note: Notes, sucess: (note: Notes) -> Unit,
              failure: (throwable: Throwable) -> Unit){
        val call = RetrofitInicializr().noteService().alter(note,note.id)
        call.enqueue(callback({response ->
            response?.body()?.let {
                sucess(it)
            }
        },{
            throwable ->
            throwable?.let { failure }
        }))
    }

}