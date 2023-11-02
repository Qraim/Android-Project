package com.example.m1info.exo2_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class addmenu : AppCompatActivity() {
    var edittext: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmenu)
        edittext = findViewById<EditText>(R.id.et)
    }

    fun on_btn_valider(view: View) {

        val tache = edittext?.text.toString()

        val radioGroup = findViewById<RadioGroup>(R.id.groupradio)
        val selectedId = radioGroup.checkedRadioButtonId

        val radioButton = findViewById<RadioButton>(selectedId)

        val prio = radioButton.text.toString()



        Toast.makeText(this, "Tâche: $tache, Priorité: $prio", Toast.LENGTH_SHORT).show()
    }




}