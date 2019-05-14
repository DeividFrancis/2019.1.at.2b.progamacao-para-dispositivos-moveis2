package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PessoaPapelHelper extends DatabaseHelper {
    SQLiteDatabase db;

    public PessoaPapelHelper(Context context){
        super(context);
        db = getWritableDatabase();
    }
}
