package com.example.trabalho.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho.R
import com.example.trabalho.adapter.TarefaAdapter
import com.example.trabalho.model.Receita

class ListaFragment : Fragment() {

    private var mostrarAprendidas: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mostrarAprendidas = it.getBoolean(ARG_APRENDIDAS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // Lista de receitas com Utensílios e Imagem (usando ícone padrão como exemplo)
        val todasReceitas = listOf(
            Receita(
                1, "Bolo de Cenoura", "Média", 
                "Cenoura, Ovos, Farinha, Açúcar, Óleo",
                "1. Bata a cenoura, ovos e óleo no liquidificador.\n2. Misture a farinha e o açúcar.\n3. Asse por 40 min.",
                "Liquidificador, Forma de Bolo, Tigela grande",
                android.R.drawable.ic_menu_gallery, true
            ),
            Receita(
                2, "Omelete de Queijo", "Fácil", 
                "2 Ovos, Sal, Queijo Muçarela", 
                "1. Bata os ovos com sal.\n2. Despeje na frigideira.\n3. Adicione o queijo e dobre.",
                "Frigideira antiaderente, Garfo, Tigela",
                android.R.drawable.ic_menu_gallery, true
            ),
            Receita(
                3, "Lasanha à Bolonhesa", "Difícil", 
                "Massa de Lasanha, Molho à Bolonhesa, Queijo, Presunto", 
                "1. Prepare o molho.\n2. Monte as camadas em um refratário.\n3. Leve ao forno até gratinar.",
                "Refratário de vidro, Panela, Forno",
                android.R.drawable.ic_menu_gallery, false
            ),
            Receita(
                4, "Suco de Laranja", "Fácil", 
                "4 Laranjas, Gelo", 
                "1. Esprema as laranjas.\n2. Coe se desejar.\n3. Sirva com gelo.",
                "Espremedor de frutas, Jarra, Faca",
                android.R.drawable.ic_menu_gallery, false
            )
        )

        val listaFiltrada = todasReceitas.filter { it.aprendida == mostrarAprendidas }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = TarefaAdapter(listaFiltrada) { receita ->
            val fragmentDetalhe = DetalheFragment.newInstance(receita)
            
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, fragmentDetalhe)
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        private const val ARG_APRENDIDAS = "aprendidas"

        @JvmStatic
        fun newInstance(aprendidas: Boolean) =
            ListaFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_APRENDIDAS, aprendidas)
                }
            }
    }
}