package matheus.lima.com.br.matheuskeep

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.form_note.view.*
import matheus.lima.com.br.matheuskeep.adapters.NoteListAdapter
import matheus.lima.com.br.matheuskeep.entity.Notes
import matheus.lima.com.br.matheuskeep.rest.CallBackResponse
import matheus.lima.com.br.matheuskeep.rest.webclient.NoteWebClient

class NoteListActivity : AppCompatActivity() {
    private val notes: MutableList<Notes> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        NoteWebClient().list(object : CallBackResponse<List<Notes>> {
            override fun sucess(notes: List<Notes>) {
                this@NoteListActivity.notes.addAll(notes)
                configureList()
            }
        })

        fab_add_note.setOnClickListener {

            val createdView = LayoutInflater.from(this@NoteListActivity)
                .inflate(R.layout.form_note, window.decorView as ViewGroup, false)

            AlertDialog.Builder(this@NoteListActivity)
                .setTitle("Add Note")
                .setView(createdView)
                .setPositiveButton("Save", object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        val title = createdView.form_note_title.text.toString()
                        val description = createdView.form_note_description.text.toString()
                        val note = Notes(title, description)
                        NoteWebClient().insert(note, object: CallBackResponse<Notes>{
                            override fun sucess(note: Notes){
                                this@NoteListActivity.notes.add(note)
                                configureList()
                            }
                        })
                    }
                })
                .show()
        }

    }

    private fun configureList() {
        val recyclerView = note_list_recyclerview
        recyclerView.adapter = NoteListAdapter(notes, this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }
}
