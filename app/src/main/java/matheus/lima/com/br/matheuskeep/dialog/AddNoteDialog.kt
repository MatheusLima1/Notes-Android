package matheus.lima.com.br.matheuskeep.dialog

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.form_note.view.*
import matheus.lima.com.br.matheuskeep.R
import matheus.lima.com.br.matheuskeep.entity.Notes
import matheus.lima.com.br.matheuskeep.rest.webclient.NoteWebClient

class AddNoteDialog(private val context: Context,
                    private val viewGroup: ViewGroup) {

    fun show(created: (createdNotes: Notes) -> Unit){

        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.form_note, viewGroup, false)

        AlertDialog.Builder(context)
            .setTitle("Add Note")
            .setView(createdView)
            .setPositiveButton("Save") { _, _ ->
                val title = createdView.form_note_title.text.toString()
                val description = createdView.form_note_description.text.toString()
                val note = Notes(title = title, description = description)
                NoteWebClient().insert(note, {
                    created(it)
                },{
                    Toast.makeText(context, "Falha ao salvar nota", Toast.LENGTH_LONG).show()
                })
            }
            .show()
    }
}

