package com.example.noteapptd.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapptd.R
import com.example.noteapptd.entitites.Notes

class NotesAdapter(val arrayList: List<Notes>):
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(view: View):RecyclerView.ViewHolder(view){
        val titleTV = itemView.findViewById<TextView>(R.id.tvTitle)
        val noteTV = itemView.findViewById<TextView>(R.id.tvDesc)
        val dateTimeTV = itemView.findViewById<TextView>(R.id.tvDateTime)
        val cardView = itemView.findViewById<CardView>(R.id.cardView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
       holder.titleTV.text = arrayList[position].title
        holder.noteTV.text = arrayList[position].subTitle
        holder.dateTimeTV.text = arrayList[position].dateTime

        if (arrayList[position].color != null){
            holder.cardView.setCardBackgroundColor(Color.parseColor(arrayList[position].color))
        }else{
            holder.cardView.setCardBackgroundColor(Color.parseColor(R.color.ColorLightBlack.toString()))
        }
    }


}