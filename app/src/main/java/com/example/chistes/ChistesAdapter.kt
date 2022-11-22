package com.example.chistes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chistes.databinding.ItemChisteBinding

class ChistesAdapter(
    val context: Context,
    var chisteslist: MutableList<Chiste>,
    var listener: OnclickListener
) : RecyclerView.Adapter<ChistesAdapter.VieHolder>() {
    inner class VieHolder(view: View) : RecyclerView.ViewHolder(view) {
        val Bindind = ItemChisteBinding.bind(view)
        fun setListener(chiste: Chiste) {
            Bindind.root.setOnClickListener { listener.onClick(chiste) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_chiste, parent, false)
        return VieHolder(view)
    }

    override fun onBindViewHolder(holder: VieHolder, position: Int) {
        holder.setListener(chisteslist[position])
        holder.Bindind.textChiste.text = chisteslist[position].setup

    }

    override fun getItemCount(): Int {
        return chisteslist.size
    }

    fun upDateList(lista:MutableList<Chiste>){
        chisteslist=lista
        notifyDataSetChanged()
    }
}