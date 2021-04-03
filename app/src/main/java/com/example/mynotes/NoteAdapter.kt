package com.example.todolist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R
import com.example.mynotes.note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(
    private val notes: MutableList<note>
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,
                parent,
                false
            )
        )
    }

    fun addNote(note: note) {
        notes.add(note)
        notifyItemInserted(notes.size - 1)
    }



    fun showCheckBoxes(){
        for(item in notes){
            item.isVisible=true;
        }
        notifyDataSetChanged()
    }

    fun deleteNotes(){
        notes.removeAll { note->
            note.isChecked
        }
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val curNote = notes[position]
        holder.itemView.apply {
            tv_content.text = curNote.content
            tv_title.text=curNote.title
            cb_isChecked.isChecked = curNote.isChecked
            cb_isChecked.setOnCheckedChangeListener { _, isChecked ->
                curNote.isChecked=!curNote.isChecked
            }
            if(curNote.isVisible){
                cb_isChecked.visibility=View.VISIBLE
            }
            if(position%2==0){
                holder.itemView.setBackgroundColor(Color.parseColor("#ffcc99"))
            }
            if(curNote.isVisible){
                cb_isChecked.visibility=View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}
