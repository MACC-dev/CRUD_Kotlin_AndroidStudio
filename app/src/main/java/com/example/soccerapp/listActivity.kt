package com.example.soccerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class listActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val dbHelper = DatabaseHelper(this, "soccerapp.db", null, 1)

        val btnBuscar = findViewById<Button>(R.id.btnBuscar)
        val txtNombre = findViewById<EditText>(R.id.txtNombre2)
        val txtTelefono = findViewById<EditText>(R.id.txtTelefono2)
        val txtPosicion = findViewById<EditText>(R.id.txtPosicion2)

        btnBuscar.setOnClickListener {
            val nombre = txtNombre.text.toString()
            val jugador = dbHelper.obtenerJugador(nombre)

            if (jugador != null) {
                txtNombre.setText(jugador.nombre)
                txtTelefono.setText(jugador.telefono.toString())
                txtPosicion.setText(jugador.posicion)
            } else {
                Toast.makeText(this, "No se encontró un jugador con ese nombre", Toast.LENGTH_SHORT).show()
            }
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val cursor = dbHelper.obtenerJugadores()
        val jugadores = mutableListOf<Jugador>()

        val idIndex = cursor.getColumnIndex("id")
        val nombreIndex = cursor.getColumnIndex("nombre")
        val telefonoIndex = cursor.getColumnIndex("telefono")
        val posicionIndex = cursor.getColumnIndex("posicion")

        if (idIndex != -1 && nombreIndex != -1 && telefonoIndex != -1 && posicionIndex != -1) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(idIndex)
                val nombre = cursor.getString(nombreIndex)
                val telefono = cursor.getInt(telefonoIndex)
                val posicion = cursor.getString(posicionIndex)
                jugadores.add(Jugador(id, nombre, telefono, posicion))
            }
        } else {
            Toast.makeText(this, "Error al obtener los jugadores", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = JugadorAdapter(jugadores)
 }
}
