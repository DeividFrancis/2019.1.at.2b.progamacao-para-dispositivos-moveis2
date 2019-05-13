package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PapelHelper extends DatabaseHelper {
    SQLiteDatabase db;

    public PapelHelper(Context context){
        super(context);
        db = getWritableDatabase();
    }
//
//    public insere(){
//
//    }
}
