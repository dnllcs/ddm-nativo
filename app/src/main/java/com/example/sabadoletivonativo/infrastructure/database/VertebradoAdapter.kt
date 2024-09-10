package com.example.sabadoletivonativo.infrastructure.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sabadoletivonativo.R
import com.example.sabadoletivonativo.domain.entities.Vertebrado

class VertebradoAdapter(private var vertebradoList: MutableList<Vertebrado>, private val onDelete: (Vertebrado) -> Unit) :
    RecyclerView.Adapter<VertebradoAdapter.VertebradoViewHolder>() {

    inner class VertebradoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeTextView: TextView = view.findViewById(R.id.nomeTextView)
        val idadeTextView: TextView = view.findViewById(R.id.idadeTextView)
        val pesoTextView: TextView = view.findViewById(R.id.pesoTextView)
        val deleteButton: Button = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VertebradoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vertebrado, parent, false)
        return VertebradoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VertebradoViewHolder, position: Int) {
        val vertebrado = vertebradoList[position]
        holder.nomeTextView.text = vertebrado.nome
        holder.idadeTextView.text = vertebrado.idade.toString()
        holder.pesoTextView.text = vertebrado.peso.toString()

        holder.deleteButton.setOnClickListener {
            onDelete(vertebrado)
        }
    }

    override fun getItemCount(): Int = vertebradoList.size

    fun updateList(newList: List<Vertebrado>) {
        vertebradoList.clear()
        vertebradoList.addAll(newList)
        notifyDataSetChanged()
    }
}
