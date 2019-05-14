package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.Papel;

public class PapelHelper extends DatabaseHelper {
    SQLiteDatabase db;
    ContentValues dados;

    public PapelHelper(Context context){
        super(context);
        db = getWritableDatabase();
    }

    public long insere(Papel papel){
        long retorno = 0;

        dados = new ContentValues();
        dados.put("descricao",papel.getDescricao());

        retorno = db.insertOrThrow("PAPEL",null,dados);

        return  retorno;
    }
}
