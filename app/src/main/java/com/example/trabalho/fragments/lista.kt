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

        // Lista de receitas utilizando as imagens específicas da pasta drawable
        val todasReceitas = listOf(
            Receita(
                1, "Bolo de Cenoura", "Média", 
                "1/2 Xícara (chá) de Óleo\n3 Cenouras Médias Raladas\n4 ovos\n2 xícaras (chá) de açúcar\n1/2 xícaras (chá) de farinha de trigo, 1 colher (sopa) de fermento em pó",
                "1. Em um liquidificador, adicione a cenoura, os ovos e o óleo, depois misture.\n2. Acrescente o açúcar e bata novamente por 5 minutos.\n3. Em uma tigela ou na batedeira, adicione a farinha de trigo e depois misture novamente.\n4. Acrescente o fermento e misture lentamente com uma colher.\n5. Asse em um forno preaquecido a 180° C por aproximadamente 40 minutos.\n 6.Sirva!",
                "Liquidificador, Forma de Bolo, Batedeira",
                R.drawable.bolocenoura, true
            ),
            Receita(
                2, "Omelete de Queijo", "Fácil", 
                "2 Ovos, Sal, Queijo Muçarela", 
                "1. Bata os ovos com sal.\n2. Despeje na frigideira.\n3. Adicione o queijo e dobre.",
                "Frigideira antiaderente, Garfo, Tigela",
                R.drawable.omelete, true
            ),
            Receita(
                3, "Lasanha à Bolonhesa", "Difícil", 
                "Massa de Lasanha, Molho à Bolonhesa, Queijo, Presunto", 
                "1. Prepare o molho.\n2. Monte as camadas em um refratário.\n3. Leve ao forno até gratinar.",
                "Refratário de vidro, Panela, Forno",
                R.drawable.lasanha, false
            ),
            Receita(
                4, "Suco de Laranja", "Fácil", 
                "4 Laranjas, Gelo", 
                "1. Esprema as laranjas.\n2. Coe se desejar.\n3. Sirva com gelo.",
                "Espremedor de frutas, Jarra, Faca",
                R.drawable.suco, false
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