package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.CondicaoEnum;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.ConnectionDB;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.Filtro;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO extends ConnectionDB {

    SQLiteDatabase db;
    ContentValues dados;

    private static final String TABELA = "PESSOA";

    public PessoaDAO(Context context){
        super(context);
        db = getWritableDatabase();
    }

    public PessoaBean inserir (PessoaBean pessoaBean) throws ErrorException {
        Long pessoaId = null;

        dados = new ContentValues();
        dados.put("nome",pessoaBean.getNome());
        dados.put("cpf",pessoaBean.getCpf());
        dados.put("aniversario", DateUtils.format(pessoaBean.getAniversario()));
        dados.put("logradouro",pessoaBean.getLogradouro());
        dados.put("telefone",pessoaBean.getTelefone());
        dados.put("email",pessoaBean.getEmail());
        dados.put("senha",pessoaBean.getSenha());
        dados.put("numero",pessoaBean.getNumero());
        dados.put("cidade",pessoaBean.getCidade());
        dados.put("estado",pessoaBean.getEstado());
        try {
            pessoaId = db.insertOrThrow(TABELA,null,dados);
        }catch (SQLException e){
            throw new ErrorException("Erro ao cadastrar uma pessoa",e);
        }
        pessoaBean.setId(pessoaId.intValue());

        return pessoaBean;
    }

    public PessoaBean atualizar (PessoaBean pessoaBean) throws ErrorException {
        dados = new ContentValues();
        dados.put("nome",pessoaBean.getNome());
        dados.put("cpf",pessoaBean.getCpf());
        dados.put("aniversario",DateUtils.format(pessoaBean.getAniversario()));
        dados.put("logradouro",pessoaBean.getLogradouro());
        dados.put("telefone",pessoaBean.getTelefone());
        dados.put("email",pessoaBean.getEmail());
        dados.put("senha",pessoaBean.getSenha());
        dados.put("numero",pessoaBean.getNumero());
        dados.put("cidade",pessoaBean.getCidade());
        dados.put("estado",pessoaBean.getEstado());
        try {
            db.update(TABELA,dados,"_id "+CondicaoEnum.EQUALS.get()+" ?", new String[] {String.valueOf(pessoaBean.getId())});
        }catch (SQLException e){
            throw new ErrorException("Erro ao atualizar Pessoa.",e);
        }
        return pessoaBean;
    }

    public PessoaBean buscarId(Integer id) throws ErrorException{
        PessoaBean pessoaBean = null;
        Filtro filtro = new Filtro();
        filtro.adicionar("_id", CondicaoEnum.EQUALS,id);
        List<PessoaBean> lista = buscar(filtro);
        pessoaBean = lista.get(0);
        return pessoaBean;
    }

    public List<PessoaBean> buscar(Filtro filtro) throws ErrorException{
        List<PessoaBean> pessoaBeans = new ArrayList<PessoaBean>();

        String condicao = "";
        String[] parametros = null;

        if(filtro != null){
            condicao = filtro.criarCondicao();
            parametros = filtro.criarParametros();
        }

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABELA + " WHERE 1 "+CondicaoEnum.EQUALS.get()+" 1 " + condicao, parametros);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                PessoaBean pessoaBean = new PessoaBean();
                pessoaBean.setId(cursor.getInt(0));
                pessoaBean.setNome(cursor.getString(1));
                pessoaBean.setCpf(cursor.getString(2));
                pessoaBean.setAniversario(DateUtils.parse(cursor.getString(3)));
                pessoaBean.setLogradouro(cursor.getString(4));
                pessoaBean.setTelefone(cursor.getString(5));
                pessoaBean.setEmail(cursor.getString(6));
                pessoaBean.setSenha(cursor.getString(7));
                pessoaBean.setNumero(cursor.getString(8));
                pessoaBean.setCidade(cursor.getString(9));
                pessoaBean.setEstado(cursor.getString(10));
                pessoaBeans.add(pessoaBean);
            }while (cursor.moveToNext());
        }else{
            throw new ErrorException("Pessoas não encontradas.");
        }

        return pessoaBeans;
    }

    public void deletar(Integer id){
        db.delete(TABELA,"_id "+ CondicaoEnum.EQUALS +" ?", new String[] {String.valueOf(id)});
    }

    public void fundir(PessoaBean pessoaBean) throws ErrorException {
        try {
            buscarId(pessoaBean.getId());
            atualizar(pessoaBean);
        }catch (ErrorException e){
            inserir(pessoaBean);
        }
    }
}
