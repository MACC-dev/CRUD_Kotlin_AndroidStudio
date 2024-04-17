package com.example.soccerapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context?, name:String?, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE jugadores (id INTEGER PRIMARY KEY, nombre varchar, telefono int, posicion varchar)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS jugadores")
        onCreate(db)
    }

    fun agregarJugador(nombre: String, telefono: Int, posicion: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("nombre", nombre)
        contentValues.put("telefono", telefono)
        contentValues.put("posicion", posicion)
        db.insert("jugadores", null, contentValues)
        db.close()
    }

    fun actualizarJugador(nombre: String, telefono: Int, posicion: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("nombre", nombre)
        contentValues.put("telefono", telefono)
        contentValues.put("posicion", posicion)
        db.update("jugadores", contentValues, "nombre = ?", arrayOf(nombre))
        db.close()
    }

    fun eliminarJugador(nombre: String) {
        val db = this.writableDatabase
        db.delete("jugadores", "nombre = ?", arrayOf(nombre))
        db.close()
    }

    fun obtenerJugadores(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM jugadores", null)
    }

    fun obtenerJugador(nombre: String): Jugador? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM jugadores WHERE nombre = ?", arrayOf(nombre))
        return if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val telefono = cursor.getInt(cursor.getColumnIndex("telefono"))
            val posicion = cursor.getString(cursor.getColumnIndex("posicion"))
            Jugador(id, nombre, telefono, posicion)
        } else {
            null
        }.also {
            cursor.close()
            db.close()
        }
    }



}