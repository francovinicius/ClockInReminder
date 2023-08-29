package com.alerta.clockinreminder

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btIniciar = findViewById<Button>(R.id.iniciar)


        btIniciar.setOnClickListener {

            val txtHrInicio = findViewById<EditText>(R.id.txtHrInicio)
            val txtMnInicio = findViewById<EditText>(R.id.txtMnInicio)

            val hrInicioText = txtHrInicio.text.toString()
            val mnInicioText = txtMnInicio.text.toString()

            if (hrInicioText.isNotBlank() && mnInicioText.isNotBlank()) {
                val hrInicio = hrInicioText.toInt()
                val mnInicio = mnInicioText.toInt()

                if (hrInicio in 0..23 && mnInicio in 0..59) {
                    // Criar um objeto de hora
                    val horaMinuto = "%02d:%02d".format(hrInicio, mnInicio)

                    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(5000, VibrationEffect.DEFAULT_AMPLITUDE))
                    } else {
                        vibrator.vibrate(500)
                    }

                } else {
                    // Mostrar uma mensagem de erro se os valores de hora/minuto forem inválidos
                    Toast.makeText(this, "Valores de hora/minuto inválidos", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Mostrar uma mensagem de erro se algum campo estiver vazio
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }




        }


    }


}
