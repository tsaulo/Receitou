package com.example.trabalho.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.trabalho.R
import com.example.trabalho.model.Receita

class DetalheFragment : Fragment() {

    private var receita: Receita? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            receita = it.getSerializable(ARG_RECEITA) as? Receita
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detalhe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val imgReceita = view.findViewById<ImageView>(R.id.imgReceitaDetalhe)
        val txtNome = view.findViewById<TextView>(R.id.txtNomeReceita)
        val txtDificuldade = view.findViewById<TextView>(R.id.txtDificuldade)
        val txtUtensilios = view.findViewById<TextView>(R.id.txtUtensilios)
        val txtIngredientes = view.findViewById<TextView>(R.id.txtIngredientes)
        val txtModoPreparo = view.findViewById<TextView>(R.id.txtModoPreparo)

        receita?.let {
            imgReceita.setImageResource(it.imagemResId)
            txtNome.text = it.nome
            txtDificuldade.text = "Dificuldade: ${it.dificuldade}"
            txtUtensilios.text = it.utensilios
            txtIngredientes.text = it.ingredientes
            txtModoPreparo.text = it.modoPreparo
        }
    }

    companion object {
        private const val ARG_RECEITA = "receita"

        @JvmStatic
        fun newInstance(receita: Receita) =
            DetalheFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_RECEITA, receita)
                }
            }
    }
}