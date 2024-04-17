package com.example.soccerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JugadorAdapter(private val jugadores: List<Jugador>) : RecyclerView.Adapter<JugadorAdapter.JugadorViewHolder>() {

    class JugadorViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.nombre)
        val telefono: TextView = view.findViewById(R.id.telefono)
        val posicion: TextView = view.findViewById(R.id.posicion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JugadorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.jugador_item, parent, false)
        return JugadorViewHolder(view)
    }

    override fun onBindViewHolder(holder: JugadorViewHolder, position: Int) {
        val jugador = jugadores[position]
        holder.nombre.text = jugador.nombre
        holder.telefono.text = jugador.telefono.toString()
        holder.posicion.text = jugador.posicion
    }

    override fun getItemCount() = jugadores.size
}