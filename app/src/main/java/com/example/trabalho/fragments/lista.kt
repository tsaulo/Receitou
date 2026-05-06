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
                "1/2 Xícara (chá) de Óleo\n3 Cenouras Médias Raladas\n4 ovos\n2 xícaras (chá) de açúcar\n1/2 xícaras (chá) de farinha de trigo\n1 colher (sopa) de fermento em pó",
                "1. Em um liquidificador, adicione a cenoura, os ovos e o óleo, depois misture.\n2. Acrescente o açúcar e bata novamente por 5 minutos.\n3. Em uma tigela ou na batedeira, adicione a farinha de trigo e depois misture novamente.\n4. Acrescente o fermento e misture lentamente com uma colher.\n5. Asse em um forno preaquecido a 180° C por aproximadamente 40 minutos.\n 6.Sirva!",
                "Liquidificador, Forma de Bolo, Batedeira",
                R.drawable.bolocenoura, true
            ),
            Receita(
                2, "Omelete de Queijo", "Fácil", 
                "3 ovos\n3 colheres de farinha de trigo\nSal a gosto\nFolhas de coento verde\nQueijo a gosto ou peito de frango cozido em molho e desfiado",
                "1. Bata os ovos, acrescente a farinha de trigo, o sal e tempero verde e bata bem.\n2. Coloque em uma frigideira que já deve estar quente.\n3. Logo em seguida coloque o recheio (e enrole se quiser).\n4. Frite dos dois lados e sirva quente.",
                "Frigideira antiaderente, Garfo, Tigela",
                R.drawable.omelete, true
            ),
            Receita(
                3, "Lasanha à Bolonhesa", "Difícil", 
                "500 g de massa de lasanha\n500 g de carne moída\n2 caixas de creme de leite\n3 colheres de manteiga\n3 colheres de farinha de trigo\n500 g de presunto\n500 g de mussarela\nSal a gosto\n2 copos de leite\n1 cebola ralada\n3 colheres de óleo\n1 caixa de molho de tomate\n3 dentes de alho amassados\n1 pacote de queijo ralado",
                "1. Cozinhe a massa segundo as orientações do fabricante, despeje em um refratário com água gelada para não grudar e reserve.\n2. Refogue o alho, a cebola, a carne moída, o molho de tomate, deixe cozinhar por 3 minutos e reserve.\n3. Derreta a margarina, coloque as 3 colheres de farinha de trigo e mexa.\nDespeje o leite aos poucos e continue mexendo.\nPor último, coloque o creme de leite, mexa por 1 minuto e desligue o fogo.\nDespeje uma parte do molho à bolonhesa em um refratário, a metade da massa, a metade do presunto, a metade da mussarela, todo o molho branco e o restante da massa.\nRepita as camadas até a borda do recipiente.\nFinalize com o queijo ralado e leve ao forno alto (220° C), preaquecido, por cerca de 20 minutos.",
                "Panela, Concha, Espátula, Travessa de Vidro",
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