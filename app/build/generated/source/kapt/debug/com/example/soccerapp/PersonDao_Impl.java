package com.example.soccerapp;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PersonDao_Impl implements PersonDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Jugador> __insertionAdapterOfJugador;

  private final EntityDeletionOrUpdateAdapter<Jugador> __deletionAdapterOfJugador;

  private final EntityDeletionOrUpdateAdapter<Jugador> __updateAdapterOfJugador;

  public PersonDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfJugador = new EntityInsertionAdapter<Jugador>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Jugador` (`id`,`nombre`,`telefono`,`equipo`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Jugador value) {
        stmt.bindLong(1, value.getId());
        if (value.getNombre() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNombre());
        }
        stmt.bindLong(3, value.getTelefono());
        if (value.getEquipo() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEquipo());
        }
      }
    };
    this.__deletionAdapterOfJugador = new EntityDeletionOrUpdateAdapter<Jugador>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Jugador` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Jugador value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfJugador = new EntityDeletionOrUpdateAdapter<Jugador>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Jugador` SET `id` = ?,`nombre` = ?,`telefono` = ?,`equipo` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Jugador value) {
        stmt.bindLong(1, value.getId());
        if (value.getNombre() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNombre());
        }
        stmt.bindLong(3, value.getTelefono());
        if (value.getEquipo() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEquipo());
        }
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public Object insert(final List<Jugador> jugadores,
      final Continuation<? super Unit> $completion) {
    __db.assertNotSuspendingTransaction();
  }

  @Override
  public Object delete(final Jugador jugador, final Continuation<? super Unit> $completion) {
    __db.assertNotSuspendingTransaction();
  }

  @Override
  public Object update(final Jugador jugador, final Continuation<? super Unit> $completion) {
    __db.assertNotSuspendingTransaction();
  }

  @Override
  public Object getAll(final Continuation<? super LiveData<List<Jugador>>> $completion) {
    final String _sql = "SELECT * FROM Jugador";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    int _argIndex = 1;
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final Object _result;
      if(_cursor.moveToFirst()) {
        _result = new Object();
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Object getByNombre(final String nombre, final Continuation<? super Jugador> $completion) {
    final String _sql = "SELECT * FROM Jugador WHERE nombre = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (nombre == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nombre);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final Object _result;
      if(_cursor.moveToFirst()) {
        _result = new Object();
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
