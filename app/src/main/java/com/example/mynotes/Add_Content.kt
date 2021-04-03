package com.example.mynotes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Add_Content : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__content)

        val noteTitle=findViewById<EditText>(R.id.add_title)
        val noteContent=findViewById<EditText>(R.id.add_content)
        val addNote=findViewById<Button>(R.id.add_note_button)

        addNote.setOnClickListener { createNote(noteTitle,noteContent) }
    }


    fun createNote(etTitle:EditText,etContent:EditText){
        val title=etTitle.text.toString()
        val content=etContent.text.toString()
        etTitle.text.clear()
        etContent.text.clear()

        if(title.isNotEmpty() && content.isNotEmpty()){
            var getNoteData= Intent(this@Add_Content,MainActivity::class.java)
            getNoteData.putExtra("title",title)
            getNoteData.putExtra("content",content)
            setResult(RESULT_OK,getNoteData)
            finish()
        }
    }
}