package com.example.trabalho.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho.R
import com.example.trabalho.model.Receita

class InstrucoesAdapter(
    private val lista: List<Receita>,
    private val onClick: (Receita) -> Unit
) : RecyclerView.Adapter<InstrucoesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView = view.findViewById(R.id.txtNome)
        val dificuldade: TextView = view.findViewById(R.id.txtDificuldadeItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarefa, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val receita = lista[position]
        holder.nome.text = receita.nome
        holder.dificuldade.text = "Dificuldade: ${receita.dificuldade}"
        
        holder.itemView.setOnClickListener {
            onClick(receita)
        }
    }
}
