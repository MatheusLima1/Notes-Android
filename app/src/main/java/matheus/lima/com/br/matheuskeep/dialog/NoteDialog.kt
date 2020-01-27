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

class NoteDialog(
    private val context: Context,
    private val viewGroup: ViewGroup
) {

    val createdView = LayoutInflater.from(context)
        .inflate(R.layout.form_note, viewGroup, false)
    val titleField = createdView.form_note_title
    val descriptionField = createdView.form_note_description

    fun add(
        preExecute: () -> Unit = {},
        finished: () -> Unit = {},
        created: (createdNotes: Notes) -> Unit = {}
    ) {

        AlertDialog.Builder(context)
            .setTitle("Add Note")
            .setView(createdView)
            .setPositiveButton("Save") { _, _ ->
                val title = createdView.form_note_title.text.toString()
                val description = createdView.form_note_description.text.toString()
                val note = Notes(title = title, description = description)
                preExecute()
                NoteWebClient().insert(
                    note,
                    sucess = { created(it) },
                    failure = {
                        Toast.makeText(
                            context,
                            "Falha ao salvar nota!",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    preExecute = preExecute,
                    finished = finished
                )
            }.show()
    }

    fun alter(
        note: Notes,
        preExecute: () -> Unit = {},
        finished: () -> Unit = {},
        altered: (alteredNote: Notes) -> Unit
    ) {
        titleField.setText(note.title)
        descriptionField.setText(note.description)
        AlertDialog.Builder(context)
            .setTitle("Alterar Nota")
            .setView(createdView)
            .setPositiveButton("Save") { _, _ ->
                val title = titleField.text.toString()
                val description = descriptionField.text.toString()
                val alteredNote = note.copy(title = title, description = description)
                NoteWebClient().alter(alteredNote, sucess = { altered(it) }, failure = {
                    Toast.makeText(context, "Falha ao alterar nota", Toast.LENGTH_LONG).show()
                }, preExecute = preExecute,
                    finished = finished
                )
            }
            .show()
    }
}
