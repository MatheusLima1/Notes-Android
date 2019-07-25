package matheus.lima.com.br.matheuskeep

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_note_list.*
import matheus.lima.com.br.matheuskeep.adapters.NoteListAdapter
import matheus.lima.com.br.matheuskeep.dialog.NoteDialog
import matheus.lima.com.br.matheuskeep.entity.Notes
import matheus.lima.com.br.matheuskeep.rest.webclient.NoteWebClient

class NoteListActivity : AppCompatActivity() {
    private val notes: MutableList<Notes> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        NoteWebClient().list ({
            notes.addAll(it)
            configureList()
        },{
            Toast.makeText(this, "Falha ao buscar as notas", Toast.LENGTH_LONG).show()
        })

        fab_add_note.setOnClickListener{
            NoteDialog(this,
                    window.decorView as ViewGroup)
                    .add ({
                        note_list_progress.visibility = ProgressBar.VISIBLE
                    },{
                        note_list_progress.visibility = ProgressBar.GONE
                    }){
                        notes.add(it)
                        configureList()
                    }
        }
    }

    private fun configureList() {
        val recyclerView = note_list_recyclerview
        recyclerView.adapter = NoteListAdapter(notes, this) { note, position ->
            NoteDialog(this,window.decorView as ViewGroup).alter(note){
                notes[position] = it
                configureList()
            }
        }
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }
}
