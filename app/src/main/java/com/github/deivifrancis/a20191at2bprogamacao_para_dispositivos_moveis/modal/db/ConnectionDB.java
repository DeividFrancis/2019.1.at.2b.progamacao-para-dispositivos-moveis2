package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnectionDB extends SQLiteOpenHelper {

    private static final String banco = "DIRETOAOPONOTO";
    private static final Integer versao = 1;

    public ConnectionDB(Context context){
        super(context, banco, null, versao);
    }

    //ver pra criar um crud abstract, para as filhas serem obrigadas a ter.
    //também ver pra por as tabelas no metódo.
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder pessoa = new StringBuilder();
        pessoa.append("  		CREATE TABLE PESSOA(                                                           ");
        pessoa.append("  				_ID INTEGER PRIMARY KEY AUTOINCREMENT,                                 ");
        pessoa.append("  				NOME TEXT,                                                             ");
        pessoa.append("  				CPF TEXT,                                                              ");
        pessoa.append("  				ANIVERSARIO TEXT,                                                      ");
        pessoa.append("  				LOGRADOURO TEXT,                                                       ");
        pessoa.append("  				TELEFONE TEXT,                                                         ");
        pessoa.append("  				EMAIL TEXT,                                                            ");
        pessoa.append("  				SENHA TEXT,                                                            ");
        pessoa.append("  				NUMERO TEXT,                                                           ");
        pessoa.append("  				CIDADE TEXT,                                                           ");
        pessoa.append("  				ESTADO TEXT                                                            ");
        pessoa.append("  				);                                                                     ");
        db.execSQL(pessoa.toString());

        StringBuilder ponto = new StringBuilder();
        ponto.append("  		CREATE TABLE PONTO(                                                            ");
        ponto.append("  				_ID INTEGER PRIMARY KEY AUTOINCREMENT,                                 ");
        ponto.append("  				PESSOA_ID INTEGER,                                                     ");
        ponto.append("  				DATA TEXT,                                                             ");
        ponto.append("  				HORA01 TEXT,                                                           ");
        ponto.append("  				HORA02 TEXT,                                                           ");
        ponto.append("  				HORA03 TEXT,                                                           ");
        ponto.append("  				HORA04 TEXT,                                                           ");
        ponto.append("  				HORA05 TEXT,                                                           ");
        ponto.append("  				HORA06 TEXT,                                                           ");
        ponto.append("  				HORA07 TEXT,                                                           ");
        ponto.append("  				HORA08 TEXT,                                                           ");
        ponto.append("  				HORA09 TEXT,                                                           ");
        ponto.append("  				HORA10 TEXT,                                                           ");
        ponto.append("  				FOREIGN KEY(PESSOA_ID) REFERENCES PESSOA(_ID)                          ");
        ponto.append("  				);                                                                     ");
        db.execSQL(ponto.toString());

        StringBuilder pessoaPapel = new StringBuilder();
        pessoaPapel.append("        CREATE TABLE PESSOAPAPEL (                                                 ");
        pessoaPapel.append("        				_ID INTEGER PRIMARY KEY AUTOINCREMENT,                     ");
        pessoaPapel.append("                        PESSOA_ID INTEGER,                                         ");
        pessoaPapel.append("                        PAPEL_ID INTEGER,                                          ");
        pessoaPapel.append("                        FOREIGN KEY(PESSOA_ID) REFERENCES PESSOA(_ID),             ");
        pessoaPapel.append("                        FOREIGN KEY(PAPEL_ID) REFERENCES PAPEL(_ID)                ");
        pessoaPapel.append("                        );                                                         ");
        db.execSQL(pessoaPapel.toString());

        StringBuilder papel = new StringBuilder();
        papel.append("              CREATE TABLE PAPEL (                                                       ");
        papel.append("        				_ID INTEGER PRIMARY KEY AUTOINCREMENT,                             ");
        papel.append("                      DESCRICAO TEXT                                                     ");
        papel.append("                        );                                                               ");
        db.execSQL(papel.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
