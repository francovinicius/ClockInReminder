package com.alerta.clockinreminder

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referenciando os Spinners do XML
        val hourInitialSpinner = findViewById(R.id.hourInitial) as Spinner
        val hourIntervalSpinner = findViewById(R.id.hourInterval) as Spinner
        val hourReturnSpinner = findViewById(R.id.hourReturn) as Spinner
        val hourExitSpinner = findViewById(R.id.hourExit) as Spinner

        // Criando um ArrayAdapter para o array time_array
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.time_array,
            android.R.layout.simple_spinner_item
        )

        // Definindo o layout dos itens do dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Associando os adapters aos Spinners
        hourInitialSpinner.adapter = adapter
        hourIntervalSpinner.adapter = adapter
        hourReturnSpinner.adapter = adapter
        hourExitSpinner.adapter = adapter

        // Definindo um listener de seleção para os Spinners
        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Aqui você pode tratar a seleção do usuário
                val selectedItem = parent.getItemAtPosition(position).toString()
                // Exemplo de exibição do item selecionado
                println("Item selecionado: $selectedItem")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Este método é chamado quando nada é selecionado
            }
        }

        // Definindo o mesmo listener para todos os Spinners
        hourInitialSpinner.onItemSelectedListener = listener
        hourIntervalSpinner.onItemSelectedListener = listener
        hourReturnSpinner.onItemSelectedListener = listener
        hourExitSpinner.onItemSelectedListener = listener
    }
}
