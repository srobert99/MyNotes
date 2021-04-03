package com.example.mynotes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.NoteAdapter

class MainActivity : AppCompatActivity() {

    lateinit var notesAdapter:NoteAdapter
    lateinit var deleteNote:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesAdapter= NoteAdapter(mutableListOf())
        val rvNotes=findViewById<RecyclerView>(R.id.rvNotes)
        rvNotes.adapter=notesAdapter
        rvNotes.layoutManager=LinearLayoutManager(this)

        val addNote=findViewById<Button>(R.id.add_button)
        addNote.setOnClickListener { showAddContentActivity()
        }
        deleteNote=findViewById(R.id.delete_button)

        val showCheckBox=findViewById<Button>(R.id.show_checkbox_button)
        showCheckBox.setOnClickListener { showCheckBoxes() }

        deleteNote.setOnClickListener {
            notesAdapter.deleteNotes()
            deleteNote.visibility=View.GONE
        }


    }

    fun showCheckBoxes(){
        notesAdapter.showCheckBoxes()
        deleteNote.visibility= View.VISIBLE

    }

    fun showAddContentActivity(){
        val intent= Intent(this@MainActivity,Add_Content::class.java)
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==1){
            if(resultCode== RESULT_OK){
                val title=data?.getStringExtra("title")
                val content=data?.getStringExtra("content")
                if(title!=null && content!=null){
                    val Note=note(content,title)
                    notesAdapter.addNote(Note)
                }
            }
        }
    }
}