package com.example.sabadoletivonativo.infrastructure.database.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VertebradoDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object Atr {
        const val DATABASE_NAME: String = "sabadoLetivoNativo.db"
        const val DATABASE_VERSION: Int = 2
        const val TABLE_NAME: String = "vertebrado"
        const val COLUMN_ID: String = "id"
        const val COLUMN_NOME: String = "nome"
        const val COLUMN_IDADE: String = "idade"
        const val COLUMN_PESO: String = "peso"
        const val COLUMN_SANGUE_QUENTE: String = "sangueQuente"
        const val COLUMN_TIPO_ESQUELETO: String = "tipoEsqueleto"
        const val COLUMN_NUMERO_DE_MEMBROS: String = "numeroMembros"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("DROP TABLE IF EXISTS vertebrado")
        db?.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_IDADE + " INTEGER, " +
                COLUMN_PESO + " INTEGER, " +
                COLUMN_TIPO_ESQUELETO + " TEXT, " +
                COLUMN_SANGUE_QUENTE + " BOOLEAN, " +
                COLUMN_NUMERO_DE_MEMBROS + " INTEGER)");
    }


    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME");
        onCreate(db);
    }
}