package com.example.sabadoletivonativo.infrastructure.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.sabadoletivonativo.domain.entities.Vertebrado
import com.example.sabadoletivonativo.domain.interfaces.IVertebradoDao
import com.example.sabadoletivonativo.infrastructure.database.helper.VertebradoDatabaseHelper
import com.example.sabadoletivonativo.infrastructure.database.helper.VertebradoDatabaseHelper.Atr.COLUMN_ID
import com.example.sabadoletivonativo.infrastructure.database.helper.VertebradoDatabaseHelper.Atr.COLUMN_IDADE
import com.example.sabadoletivonativo.infrastructure.database.helper.VertebradoDatabaseHelper.Atr.COLUMN_NOME
import com.example.sabadoletivonativo.infrastructure.database.helper.VertebradoDatabaseHelper.Atr.COLUMN_NUMERO_DE_MEMBROS
import com.example.sabadoletivonativo.infrastructure.database.helper.VertebradoDatabaseHelper.Atr.COLUMN_PESO
import com.example.sabadoletivonativo.infrastructure.database.helper.VertebradoDatabaseHelper.Atr.COLUMN_SANGUE_QUENTE
import com.example.sabadoletivonativo.infrastructure.database.helper.VertebradoDatabaseHelper.Atr.COLUMN_TIPO_ESQUELETO
import com.example.sabadoletivonativo.infrastructure.database.helper.VertebradoDatabaseHelper.Atr.TABLE_NAME


class VertebradoDaoImpl(context: Context) : IVertebradoDao{

    private var databaseHelper: VertebradoDatabaseHelper = VertebradoDatabaseHelper(context);

    override fun adicionar(vertebrado: Vertebrado?) {
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, vertebrado!!.nome)
            put(COLUMN_IDADE, vertebrado.idade)
            put(COLUMN_PESO, vertebrado.peso)
            put(COLUMN_TIPO_ESQUELETO, vertebrado.tipoEsqueleto)
            put(COLUMN_SANGUE_QUENTE, vertebrado.sangueQuente)
            put(COLUMN_NUMERO_DE_MEMBROS, vertebrado.numeroDeMembros)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    override fun ListarTodos(): List<Vertebrado?>? {
        val db = databaseHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val vertebrados = mutableListOf<Vertebrado?>()

        if (cursor.moveToFirst()) {
            do {
                val vertebrado = Vertebrado(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IDADE)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PESO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO_ESQUELETO)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SANGUE_QUENTE)) > 0,
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUMERO_DE_MEMBROS))
                )
                vertebrados.add(vertebrado)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return vertebrados
    }


    override fun update(vertebrado: Vertebrado?) {
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, vertebrado!!.nome)
            put(COLUMN_IDADE, vertebrado.idade)
            put(COLUMN_PESO, vertebrado.peso)
            put(COLUMN_SANGUE_QUENTE, vertebrado.sangueQuente)
            put(COLUMN_TIPO_ESQUELETO, vertebrado.tipoEsqueleto)
            put(COLUMN_NUMERO_DE_MEMBROS, vertebrado.numeroDeMembros)
        }
        db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(vertebrado?.id.toString()))
        db.close()
    }

    override fun deletarPorId(id: Int?) {
        val db = databaseHelper.writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
    }


    override fun recuperarPorId(id: Int?): Vertebrado? {
        val db = databaseHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = ?", arrayOf(id.toString()))
        if (cursor.moveToFirst()) {
            val vertebrado = Vertebrado(
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME)),
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IDADE)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PESO)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO_ESQUELETO)),
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SANGUE_QUENTE)) > 0,
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUMERO_DE_MEMBROS))
            )
            cursor.close()
            return vertebrado
        }
        cursor.close()
        return null
    }
}