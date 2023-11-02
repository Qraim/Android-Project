package com.example.m1info.exo2_2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast

class Adapter(private val context: Context) : BaseAdapter()  {

    private var ma_liste: ArrayList<String> = ArrayList<String>()

    override fun getCount(): Int {
        return ma_liste.size
    }

    override fun getItem(p0: Int): Any {
        return ma_liste[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    fun GetString(p0 : Int) : String {
        return ma_liste[p0]
    }

    override fun getView(p0: Int, convertView: View?, viewGroup:
    ViewGroup?): View {
        var maView: View? = convertView

        if (maView == null) {
            maView = LayoutInflater.from(context).inflate(R.layout.liste,
                viewGroup, false)
        }

        val colorsquare = maView!!.findViewById<View>(R.id.tv_champ1)
        val champ2 = maView.findViewById<TextView>(R.id.tv_champ2)
        val parts = GetString(p0).split(">", limit = 2)


        when (parts[0]) {
            "<1" -> colorsquare.setBackgroundColor(Color.GREEN)
            "<2" -> colorsquare.setBackgroundColor(Color.YELLOW)
            "<3" -> colorsquare.setBackgroundColor(Color.RED)
        }
        champ2.text = parts[1]

        maView.setOnClickListener {
            Toast.makeText(context, parts[1] + " de priorité : " + parts[0].replace("<",""), Toast.LENGTH_LONG).show()
        }

        return maView
    }

    fun ajoute(item: String) {
        ma_liste.add(item)
        notifyDataSetChanged()
    }
}