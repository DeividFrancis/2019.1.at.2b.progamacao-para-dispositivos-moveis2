package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String banco = "APP_PONTO";
    private static final Integer versao = 1;

    public DatabaseHelper(Context context){
        super(context, banco, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PESSOA ( _ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NOME TEXT, CPF TEXT, IDADE TEXT, LOGRADOURO TEXT, TELEFONE TEXT, EMAIL TEXT, SENHA TEXT," +
                " NUMERO TEXT, CIDADE TEXT, ESTADO TEXT)");
        db.execSQL("CREATE TABLE PONTO ( _ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " PES_ID INTEGER, CONSTRAINT FK_PESSOA FOREIGN KEY(PES_ID) REFERENCES PESSOA;" +
                " DATA TEXT, HORA01 TEXT, HORA02 TEXT, HORA03 TEXT, HORA04 TEXT, HORA05 TEXT, HORA06 TEXT," +
                " HORA07 TEXT, HORA08 TEXT, HORA09 TEXT, HORA10 TEXT)");
        db.execSQL("CREATE TABLE PESSOAPAPEL ( _ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " PES_ID INTEGER, CONSTRAINT FK_PESSOA FOREIGN KEY(PES_ID) REFERENCES PESSOA)");
        db.execSQL("CREATE TABLE PAPEL ( _ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " DESCRICAO TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
