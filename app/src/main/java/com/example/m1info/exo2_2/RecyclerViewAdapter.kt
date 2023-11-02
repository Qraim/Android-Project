package com.example.m1info.exo2_2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.
MonViewHolder>() {

    class MonViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)
    {
        val colorsquare = itemView.findViewById<View>(R.id.tv_champ1)
        val tv_champ2 = itemView.findViewById<TextView>(R.id.tv_champ2)


    }

    private var ma_liste: ArrayList<String> = ArrayList<String>()

    override fun getItemCount() = ma_liste.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MonViewHolder {
        // Gonfler la vue
        val view = LayoutInflater.from(context).inflate(R.layout.liste,
            parent, false)
        return MonViewHolder(view)
    }

    fun GetString(p0 : Int) : String {
        return ma_liste[p0]
    }

    override fun onBindViewHolder(holder: MonViewHolder, p0: Int) {

        val parts = GetString(p0).split(">", limit = 2)


        when (parts[0]) {
            "<1" -> holder.colorsquare.setBackgroundColor(Color.GREEN)
            "<2" -> holder.colorsquare.setBackgroundColor(Color.YELLOW)
            "<3" -> holder.colorsquare.setBackgroundColor(Color.RED)
        }
        holder.tv_champ2.text = p0.toString() + " : " + parts[1]

        holder.itemView.setOnClickListener {
            Toast.makeText(context,   parts[1] + " de priorité : " + parts[0].replace("<",""), Toast.LENGTH_LONG).show()
        }
    }

    fun ajouter(str: String) {
        ma_liste.add(str)
        notifyItemInserted(ma_liste.size - 1)
        println("test")
    }


    fun remove(p0 : Int) {
        if (p0 < 0 || p0 >= ma_liste.size) {
            Toast.makeText(context, "Position invalide", Toast.LENGTH_SHORT).show()
        } else {
            ma_liste.removeAt(p0)
            notifyItemRemoved(p0)
        }
    }


    fun insertion(p0 : Int, str : String) {

        if(ma_liste.size < p0) {
            return
        }

        ma_liste.add(p0, str)
        notifyItemInserted(p0)
    }

}