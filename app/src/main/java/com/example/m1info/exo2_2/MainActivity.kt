package com.example.m1info.exo2_2

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.angersuniv.mob.tp01.createlayoutandmenu.FakeData



class MainActivity : AppCompatActivity() {

    lateinit var recycleradapter: RecyclerViewAdapter
    lateinit var RecyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleradapter = RecyclerViewAdapter(this)
        RecyclerView = findViewById<RecyclerView>(R.id.rv_mon_recyclerview)

        RecyclerView.adapter = recycleradapter

        RecyclerView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        RecyclerView.addItemDecoration(dividerItemDecoration)

        val tasks = FakeData.get_tasks()

        for (task in tasks) {
            recycleradapter.ajouter("$task")
        }

    }


    private val startAddMenuActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val tache = data?.getStringExtra("tache")
            val prio = data?.getStringExtra("prio")
            Toast.makeText(this, "Tâche reçue: $tache, Priorité: $prio", Toast.LENGTH_SHORT).show()

            recycleradapter.ajouter("<$prio> $tache")

            println("bien ajoué")

        } else {
            Toast.makeText(this, "Tâche invalide", Toast.LENGTH_SHORT).show()

        }
    }

    // Sert à afficher le menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Sert à lancer les actions suite au déclenchement d'un menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu2 -> {
                showNumberInputDialog()
                Toast.makeText(this,"Enclenché", Toast.LENGTH_LONG).show()
                true
            }

            R.id.menu1 -> {
                val intent = Intent(this, addmenu::class.java)
                startAddMenuActivity.launch(intent)
                //startActivity(intent)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun showNumberInputDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Position à retirer")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_NUMBER
        builder.setView(input)

        builder.setPositiveButton("OK") { _, _ ->
            val position = input.text.toString().toIntOrNull()
            if (position != null) {
                val adapter = findViewById<RecyclerView>(R.id.rv_mon_recyclerview).adapter as RecyclerViewAdapter
                adapter.remove(position)
            } else {
                Toast.makeText(this, "Entrez une position valide", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Annulé") { dialog, _ -> dialog.cancel() }

        builder.show()
    }




}
