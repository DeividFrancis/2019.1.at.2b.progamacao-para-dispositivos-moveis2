package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PontoBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.ConnectionDB;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PontoDAO extends ConnectionDB {

    SQLiteDatabase db;
    ContentValues dados;
    private static final String TABELA = "PONTO";

    public PontoDAO(Context context){
        super(context);
        db = getWritableDatabase();
    }

    public PontoBean inserir(PontoBean pontoBean) throws ErrorException {
        Long pontoId = null;

        dados = new ContentValues();
        dados.put("pessoaId",pontoBean.getId());
        dados.put("data", DateUtils.format(pontoBean.getData()));
        dados.put("hora01",pontoBean.getHora01());
        dados.put("hora02",pontoBean.getHora02());
        dados.put("hora03",pontoBean.getHora03());
        dados.put("hora04",pontoBean.getHora04());
        dados.put("hora05",pontoBean.getHora05());
        dados.put("hora06",pontoBean.getHora06());
        dados.put("hora07",pontoBean.getHora07());
        dados.put("hora08",pontoBean.getHora08());
        dados.put("hora09",pontoBean.getHora09());
        dados.put("hora10",pontoBean.getHora10());
        try {
            pontoId = db.insertOrThrow(TABELA,null,dados);
        }catch (SQLException e){
            throw new ErrorException("Erro ao cadastrar um novo ponto.", e);
        }
        pontoBean.setId(pontoId.intValue());

        return pontoBean;
    }

    public PontoBean atualizar(PontoBean pontoBean) throws ErrorException {
        dados = new ContentValues();
        dados.put("pessoaId",pontoBean.getId());
        dados.put("data", DateUtils.format(pontoBean.getData()));
        dados.put("hora01",pontoBean.getHora01());
        dados.put("hora02",pontoBean.getHora02());
        dados.put("hora03",pontoBean.getHora03());
        dados.put("hora04",pontoBean.getHora04());
        dados.put("hora05",pontoBean.getHora05());
        dados.put("hora06",pontoBean.getHora06());
        dados.put("hora07",pontoBean.getHora07());
        dados.put("hora08",pontoBean.getHora08());
        dados.put("hora09",pontoBean.getHora09());
        dados.put("hora10",pontoBean.getHora10());
        try {
            db.update(TABELA,dados, "_id = ?", new String[] {String.valueOf(pontoBean.getId())});
        }catch (SQLException e){
            throw new ErrorException("Erro ao atualizar o seu ponto.",e);
        }

        return pontoBean;
    }

    public PontoBean buscar(Integer id) throws ParseException, ErrorException {
        PontoBean pontoBean = null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABELA+" WHERE _id = ?",new String[] {String.valueOf(id)});

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                pontoBean = new PontoBean();
                pontoBean.setId(cursor.getInt(0));
                pontoBean.setPessoaId(cursor.getInt(1));
                pontoBean.setData(DateUtils.parse(cursor.getString(2)));
                pontoBean.setHora01(cursor.getString(3));
                pontoBean.setHora02(cursor.getString(4));
                pontoBean.setHora03(cursor.getString(5));
                pontoBean.setHora04(cursor.getString(6));
                pontoBean.setHora05(cursor.getString(7));
                pontoBean.setHora06(cursor.getString(8));
                pontoBean.setHora07(cursor.getString(9));
                pontoBean.setHora08(cursor.getString(10));
                pontoBean.setHora09(cursor.getString(11));
                pontoBean.setHora10(cursor.getString(12));
            }while (cursor.moveToNext());
        }else{
            throw new ErrorException("Nenhum um registro de ponto foi encontrado.");
        }

        return pontoBean;
    }

    public List<PontoBean> listarTodos() throws ErrorException, ParseException {
        List<PontoBean> pontoBeans = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABELA,null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                PontoBean pontoBean = new PontoBean();
                pontoBean.setId(cursor.getInt(0));
                pontoBean.setPessoaId(cursor.getInt(1));
                pontoBean.setData(DateUtils.parse(cursor.getString(2)));
                pontoBean.setHora01(cursor.getString(3));
                pontoBean.setHora02(cursor.getString(4));
                pontoBean.setHora03(cursor.getString(5));
                pontoBean.setHora04(cursor.getString(6));
                pontoBean.setHora05(cursor.getString(7));
                pontoBean.setHora06(cursor.getString(8));
                pontoBean.setHora07(cursor.getString(9));
                pontoBean.setHora08(cursor.getString(10));
                pontoBean.setHora09(cursor.getString(11));
                pontoBean.setHora10(cursor.getString(12));
                pontoBeans.add(pontoBean);
            }while (cursor.moveToNext());

        }else {
            throw new ErrorException("Registros de pontos não encontrado.");
        }

        return pontoBeans;
    }

    public void deletar(Integer id){
        db.delete(TABELA," _id = ?",new String[] {String.valueOf(id)});
    }

    //criar uma metódo para listarConlunas();


}
