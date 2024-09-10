package com.example.sabadoletivonativo

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sabadoletivonativo.domain.entities.Vertebrado
import com.example.sabadoletivonativo.domain.interfaces.IVertebradoDao
import com.example.sabadoletivonativo.infrastructure.database.VertebradoAdapter
import com.example.sabadoletivonativo.infrastructure.database.VertebradoDaoImpl
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VertebradoAdapter
    private lateinit var vertebradoDao: IVertebradoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vertebradoDao = VertebradoDaoImpl(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = VertebradoAdapter(mutableListOf()) { vertebrado ->
            vertebradoDao.deletarPorId(vertebrado.id)
            refreshList()
        }

        recyclerView.adapter = adapter

        val addButton: FloatingActionButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            showAddDialog()
        }

        refreshList()
    }

    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_vertebrado, null)
        val nomeInput = dialogView.findViewById<EditText>(R.id.nomeInput)
        val idadeInput = dialogView.findViewById<EditText>(R.id.idadeInput)
        val pesoInput = dialogView.findViewById<EditText>(R.id.pesoInput)
        val sangueQuenteInput = dialogView.findViewById<EditText>(R.id.sangueQuenteInput)
        val tipoEsqueletoInput = dialogView.findViewById<EditText>(R.id.tipoEsqueletoInput)
        val numeroMembrosInput = dialogView.findViewById<EditText>(R.id.numeroMembrosInput)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Add Vertebrado")
            .setPositiveButton("Save") { _, _ ->
                val nome = nomeInput.text.toString()
                val idade = idadeInput.text.toString().toInt()
                val peso = pesoInput.text.toString().toDouble()
                val sangueQuente = sangueQuenteInput.text.toString() == "true"
                val tipoEsqueleto = tipoEsqueletoInput.text.toString()
                val numeroMembros = numeroMembrosInput.text.toString().toInt()


                val vertebrado = Vertebrado(nome, idade, peso, tipoEsqueleto, sangueQuente, numeroMembros)
                vertebradoDao.adicionar(vertebrado)
                refreshList()
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

    private fun refreshList() {
        val vertebrados = vertebradoDao.ListarTodos()
        val nonNullVertebrados = vertebrados?.filterNotNull() ?: emptyList()
        adapter.updateList(nonNullVertebrados)
    }
}
