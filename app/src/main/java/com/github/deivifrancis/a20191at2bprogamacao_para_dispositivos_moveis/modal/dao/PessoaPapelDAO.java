package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.CondicaoEnum;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.ConnectionDB;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.Filtro;

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
        dados.put("pessoa_id",pessoaPapelBean.getPessoaId());
        dados.put("papel_id",pessoaPapelBean.getPapelId());
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
        dados.put("pessoa_id",pessoaPapelBean.getPessoaId());
        dados.put("papel_id",pessoaPapelBean.getPapelId());
        try {
            db.update(TABELA,dados," _id "+CondicaoEnum.EQUALS.get()+" ?",new String[] {String.valueOf(pessoaPapelBean.getId())});
        }catch (SQLException e){
            throw new ErrorException("Erro ao atualizar o papel da pessoa.",e);
        }

        return  pessoaPapelBean;
    }

    public PessoaPapelBean buscarId(Integer id) throws ErrorException {
        Filtro filtro = new Filtro();
        filtro.adicionar("_id", CondicaoEnum.EQUALS, id);
        return buscar(filtro).get(0);
    }

    public List<PessoaPapelBean> buscar(Filtro filtro) throws ErrorException {
        List<PessoaPapelBean> pessoaPapelBeans = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT _id, pessoa_id, papel_id FROM " + TABELA + " WHERE 1 "+ CondicaoEnum.EQUALS.get()+ " 1 " + filtro.criarCondicao(), filtro.criarParametros());

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
        db.delete(TABELA," _id "+CondicaoEnum.EQUALS.get()+" ?",new String[] {String.valueOf(id)});
    }

    public void fundir(PessoaPapelBean pessoaPapelBean) throws ErrorException {
        try {
            buscarId(pessoaPapelBean.getId());
            atualizar(pessoaPapelBean);
        }catch (ErrorException e){
            inserir(pessoaPapelBean);
        }
    }
}
