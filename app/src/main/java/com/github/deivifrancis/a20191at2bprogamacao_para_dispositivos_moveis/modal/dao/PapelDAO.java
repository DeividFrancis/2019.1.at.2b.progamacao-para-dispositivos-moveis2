package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.ConnectionDB;

import java.util.ArrayList;
import java.util.List;

public class PapelDAO extends ConnectionDB {
    SQLiteDatabase db;
    ContentValues dados;

    private static final String TABLE = "PAPEL";

    public PapelDAO(Context context){
        super(context);
        db = getWritableDatabase();
    }

    public PapelBean inserir(PapelBean papelBean) throws ErrorException {
        Long papelId = null;

        dados = new ContentValues();
        dados.put("descricao",papelBean.getDescricao());
        try {
            papelId = db.insertOrThrow(TABLE,null,dados);
        }catch (SQLException e){
            throw new ErrorException("Erro ao cadastrar o papel.",e);
        }
        papelBean.setId(papelId.intValue());

        return  papelBean;
    }

    public PapelBean atualizar(PapelBean papelBean) throws ErrorException {

        dados = new ContentValues();
        dados.put("descricao",papelBean.getDescricao());
        try {
            db.update(TABLE,dados, "_id = ?", new String[] {String.valueOf(papelBean.getId())});
        }catch (SQLException e){
            throw new ErrorException("Erro ao atualizar o papel.",e);
        }

        return  papelBean;
    }

    public PapelBean buscar(Integer id) throws ErrorException {

        PapelBean papelBean = null;

        Cursor cursor = db.rawQuery("SELECT _id, descricao FROM " + TABLE + " WHERE _id = ?", new String[] {String.valueOf(id)});

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                papelBean = new PapelBean();
                papelBean.setId(cursor.getInt(0));
                papelBean.setDescricao(cursor.getString(1));
            }while (cursor.moveToNext());
        }else{
            throw new ErrorException("Papel não encontrado");
        }

        return papelBean;
    }

    public List<PapelBean> listarTodos() throws ErrorException {

        List<PapelBean> lista = new ArrayList<PapelBean>();

        Cursor cursor = db.rawQuery("SELECT _id, descricao FROM " + TABLE, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                PapelBean papelBean = new PapelBean();
                papelBean.setId(cursor.getInt(0));
                papelBean.setDescricao(cursor.getString(1));
                lista.add(papelBean);
            }while (cursor.moveToNext());
        }else{
            throw new ErrorException("Papéis não encontrados");
        }

        return lista;
    }

    public void deletar(Integer id){
       db.delete(TABLE, "_id = ?", new String[] {String.valueOf(id)});
    }

}