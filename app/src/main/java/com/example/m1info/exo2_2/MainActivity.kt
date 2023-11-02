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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.angersuniv.mob.tp01.createlayoutandmenu.FakeData



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycleradapter = RecyclerViewAdapter(this)

        //val listadapter = Adapter(this);

        val RecyclerView = findViewById<RecyclerView>(R.id.rv_mon_recyclerview)

        //val listView = findViewById<ListView>(R.id.lv_ma_list_view)


        RecyclerView.adapter = recycleradapter

        //listView.adapter = listadapter

        RecyclerView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        RecyclerView.addItemDecoration(dividerItemDecoration)


        val tasks = FakeData.get_tasks()

        for (task in tasks) {
            recycleradapter.ajouter("$task")
            //listadapter.ajoute("$task")
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu2 -> {
                showNumberInputDialog()
                Toast.makeText(this,"Enclenché", Toast.LENGTH_LONG).show()
                true
            }

            R.id.menu1 -> {
                val intent = Intent(this, addmenu::class.java)
                startActivity(intent)
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
