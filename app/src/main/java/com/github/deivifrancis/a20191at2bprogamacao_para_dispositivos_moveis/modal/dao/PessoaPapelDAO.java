package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.ConnectionDB;

import java.util.ArrayList;
import java.util.List;

public class PessoaPapelDAO extends ConnectionDB {
    SQLiteDatabase db;
    ContentValues dados;
    private static final String TABELA = "PESSOAPAPEL";

    public PessoaPapelDAO(Context context){
        super(context);
        db = getWritableDatabase();
    }

    public PessoaPapelBean inserir (PessoaPapelBean pessoaPapelBean) throws ErrorException {
        Long pessoaPapelId = null;

        dados = new ContentValues();
        dados.put("pessoaId",pessoaPapelBean.getPessoaId());
        dados.put("papelId",pessoaPapelBean.getPapelId());
        try {
            pessoaPapelId = db.insertOrThrow(TABELA,null,dados);
        }catch (SQLException e){
            throw new ErrorException("Erro ao definir um papel para essa pessoa.",e);
        }
         pessoaPapelBean.setId(pessoaPapelId.intValue());

        return pessoaPapelBean;
    }

    public PessoaPapelBean atualizar(PessoaPapelBean pessoaPapelBean) throws ErrorException {
        dados = new ContentValues();
        dados.put("pessoaId",pessoaPapelBean.getPessoaId());
        dados.put("papelId",pessoaPapelBean.getPapelId());
        try {
            db.update(TABELA,dados," _id = ?",new String[] {String.valueOf(pessoaPapelBean.getId())});
        }catch (SQLException e){
            throw new ErrorException("Erro ao atualizar o papel da pessoa.",e);
        }

        return  pessoaPapelBean;
    }

    public PessoaPapelBean buscar(Integer id) throws ErrorException {
        PessoaPapelBean pessoaPapelBean = null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABELA+" WHERE _id = ?",new String[] {String.valueOf(id)});

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                pessoaPapelBean = new PessoaPapelBean();
                pessoaPapelBean.setId(cursor.getInt(0));
                pessoaPapelBean.setPessoaId(cursor.getInt(1));
                pessoaPapelBean.setPapelId(cursor.getInt(2));
            }while (cursor.moveToNext());
        }else {
            throw new ErrorException("Não foi encontrado nenhum papel");
        }

        return pessoaPapelBean;
    }

    public List<PessoaPapelBean> listarTodos() throws ErrorException {
        List<PessoaPapelBean> pessoaPapelBeans = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT *FROM "+TABELA,null);

        if (cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                PessoaPapelBean pessoaPapelBean = new PessoaPapelBean();
                pessoaPapelBean.setId(cursor.getInt(0));
                pessoaPapelBean.setPessoaId(cursor.getInt(1));
                pessoaPapelBean.setPapelId(cursor.getInt(2));
                pessoaPapelBeans.add(pessoaPapelBean);
            }while (cursor.moveToNext());
        }else {
            throw new ErrorException("Papéis não encontrados.");
        }

        return pessoaPapelBeans;
    }

    public void deletar(Integer id){
        db.delete(TABELA," _id = ?",new String[] {String.valueOf(id)});
    }


}
