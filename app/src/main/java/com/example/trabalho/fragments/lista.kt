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

        // Simulação de banco de dados de receitas
        val todasReceitas = listOf(
            Receita(1, "Bolo de Cenoura", "Média", "Cenoura, Ovos, Farinha, Açúcar", "Bata tudo no liquidificador e asse.", true),
            Receita(2, "Omelete", "Fácil", "Ovos, Sal, Queijo", "Bata os ovos e frite na frigideira.", true),
            Receita(3, "Lasanha", "Difícil", "Massa, Molho, Queijo, Carne", "Monte as camadas e leve ao forno por 40 min.", false),
            Receita(4, "Suco de Laranja", "Fácil", "Laranjas", "Esprema as laranjas e sirva com gelo.", false)
        )

        // Filtra as receitas de acordo com o parâmetro recebido
        val listaFiltrada = todasReceitas.filter { it.aprendida == mostrarAprendidas }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = TarefaAdapter(listaFiltrada) { receita ->
            // Abre o detalhe da receita
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