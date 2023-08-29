package com.alerta.clockinreminder

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Locale
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.core.app.NotificationCompat



class MainActivity : AppCompatActivity() {

    class AlarmReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // Crie uma notificação
            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelId = "MyChannelId"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, "My Channel", NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(channel)
            }

            val notificationBuilder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Lembrete")
                .setContentText("Hora de marcar o ponto")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)

            // Disparar a notificação
            val notificationId = 1
            notificationManager.notify(notificationId, notificationBuilder.build())

            // Fazer o dispositivo vibrar
            val pattern = longArrayOf(0, 1000, 500, 1000) // Padrão de vibração: espera, vibra, espera, vibra...
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                vibrator.vibrate(pattern, -1)
            }
        }
    }


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
