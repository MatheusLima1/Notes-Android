package matheus.lima.com.br.matheuskeep.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.note_item.view.*
import matheus.lima.com.br.matheuskeep.R
import matheus.lima.com.br.matheuskeep.entity.Notes

class NoteListAdapter(private val notes:List<Notes>,
                      private val context:Context) : Adapter<NoteListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]

        holder.let {
            it.title.text = note.title
            it.description.text = note.description
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.note_item_title
        val description = itemView.note_item_description
    }

}

