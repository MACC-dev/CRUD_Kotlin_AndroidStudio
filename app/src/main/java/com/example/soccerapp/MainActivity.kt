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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dbHelper = DatabaseHelper(this, "soccerapp.db", null, 1)

        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtTelefono = findViewById<EditText>(R.id.txtTelefono)
        val txtPosicion = findViewById<EditText>(R.id.txtPosicion)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnModificar = findViewById<Button>(R.id.btnModificar)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)
        val btnListar = findViewById<Button>(R.id.btnListar)

        btnAgregar.setOnClickListener {
            val nombre = txtNombre.text.toString()
            val telefono = txtTelefono.text.toString().toInt()
            val posicion = txtPosicion.text.toString()

            dbHelper.agregarJugador(nombre, telefono, posicion)
            Toast.makeText(this, "Se agregó un jugador", Toast.LENGTH_SHORT).show()
        }

        btnModificar.setOnClickListener {
            val nombre = txtNombre.text.toString()
            val telefono = txtTelefono.text.toString().toInt()
            val posicion = txtPosicion.text.toString()

            dbHelper.actualizarJugador(nombre, telefono, posicion)
            Toast.makeText(this, "Se modificó un jugador", Toast.LENGTH_SHORT).show()
        }

        btnEliminar.setOnClickListener {
            val nombre = txtNombre.text.toString()

            dbHelper.eliminarJugador(nombre)
            Toast.makeText(this, "Se eliminó un jugador", Toast.LENGTH_SHORT).show()
        }

        btnListar.setOnClickListener {
            val intent = Intent(this, listActivity::class.java)
            startActivity(intent)
        }
    }
}