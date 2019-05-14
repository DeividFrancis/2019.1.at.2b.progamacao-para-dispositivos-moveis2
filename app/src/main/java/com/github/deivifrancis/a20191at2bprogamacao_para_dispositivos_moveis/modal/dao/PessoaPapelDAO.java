package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.ConnectionDB;

public class PessoaPapelDAO extends ConnectionDB {
    SQLiteDatabase db;

    public PessoaPapelDAO(Context context){
        super(context);
        db = getWritableDatabase();
    }
}
