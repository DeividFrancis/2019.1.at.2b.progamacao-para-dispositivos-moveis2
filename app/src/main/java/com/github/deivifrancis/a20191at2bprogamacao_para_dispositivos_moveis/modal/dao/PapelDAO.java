package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.CondicaoEnum;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.ConnectionDB;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.Filtro;

import java.util.ArrayList;
import java.util.List;

public class PapelDAO extends ConnectionDB {
    SQLiteDatabase db;
    ContentValues dados;

    private static final String TABELA = "PAPEL";

    public PapelDAO(Context context){
        super(context);
        db = getWritableDatabase();
    }

    public PapelBean inserir(PapelBean papelBean) throws ErrorException {
        Long papelId = null;

        dados = new ContentValues();
        dados.put("descricao",papelBean.getDescricao());
        try {
            papelId = db.insertOrThrow(TABELA,null,dados);
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
            db.update(TABELA,dados, "_id "+CondicaoEnum.EQUALS.get()+" ?", new String[] {String.valueOf(papelBean.getId())});
        }catch (SQLException e){
            throw new ErrorException("Erro ao atualizar o papel.",e);
        }

        return  papelBean;
    }

    public PapelBean buscarId(Integer id) throws ErrorException {

        PapelBean papelBean = null;
        Filtro filtro = new Filtro();
        filtro.adicionar("_id", CondicaoEnum.EQUALS,id);
        List<PapelBean> lista = buscar(filtro);
        papelBean = lista.get(0);
        return papelBean;
    }

    public List<PapelBean> buscar(Filtro filtro) throws ErrorException {
        List<PapelBean> papelBeans = new ArrayList<PapelBean>();

        String condicoes = "";
        String [] parametros = null;

        if (filtro != null){
            condicoes = filtro.criarCondicao();
            parametros = filtro.criarParametros();
        }

        StringBuilder papel = new StringBuilder();
        papel.append("    select                                                         ");
        papel.append("  		 pap._id,                                                    ");
        papel.append("  		 pap.descricao                                               ");
        papel.append("      from "+TABELA+" pap                                              ");
        papel.append("     where 1 = 1 "+condicoes                                        );

        Cursor cursor = db.rawQuery(papel.toString(), parametros);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                PapelBean papelBean = new PapelBean();
                papelBean.setId(cursor.getInt(0));
                papelBean.setDescricao(cursor.getString(1));
                papelBeans.add(papelBean);
            }while (cursor.moveToNext());
        }else{
            throw new ErrorException("Papéis não encontrados");
        }

        return papelBeans;
    }

    public void deletar(Integer id){
       db.delete(TABELA, "_id "+CondicaoEnum.EQUALS.get()+" ?", new String[] {String.valueOf(id)});
    }

    public void fundir(PapelBean papelBean) throws ErrorException {
        try {
            buscarId(papelBean.getId());
            atualizar(papelBean);
        } catch (ErrorException e) {
            inserir(papelBean);
        }
    }
}
