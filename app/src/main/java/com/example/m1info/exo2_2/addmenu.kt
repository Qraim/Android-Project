package com.example.m1info.exo2_2

import android.content.Intent
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
        var prio = radioButton.text.toString()

        if(prio == "Élevée")
            prio = 3.toString()
        else if(prio == "Moyenne")
            prio = 2.toString()
        else if(prio =="Basse")
            prio = 1.toString()

        Toast.makeText(this, "Tâche: $tache, Priorité: $prio", Toast.LENGTH_SHORT).show()

        val returnIntent = Intent()
        returnIntent.putExtra("tache", tache)

        returnIntent.putExtra("prio", prio)

        setResult(RESULT_OK, returnIntent)

        println("return$tache$prio")

        finish()

    }




}