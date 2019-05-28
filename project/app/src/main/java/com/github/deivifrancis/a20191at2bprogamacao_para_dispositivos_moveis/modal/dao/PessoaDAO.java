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

        if (id == null){
            throw new ErrorException("Id está vazio.");
        }

        PessoaBean pessoaBean = null;
        Filtro filtro = new Filtro();
        filtro.adicionar("pes._id", CondicaoEnum.EQUALS,id);
        List<PessoaBean> lista = buscar(filtro);
        pessoaBean = lista.get(0);
        return pessoaBean;
    }

    public List<PessoaBean> buscar(Filtro filtro) throws ErrorException{
        List<PessoaBean> pessoaBeans = new ArrayList<PessoaBean>();

        String condicoes = "";
        String[] parametros = null;

        if(filtro != null){
            condicoes = filtro.criarCondicao();
            parametros = filtro.criarParametros();
        }

        StringBuilder pessoaQuery = new StringBuilder();
        pessoaQuery.append("    select                                                             ");
        pessoaQuery.append("  		  pes._id,                                                     ");
        pessoaQuery.append("  		  pes.nome,                                                    ");
        pessoaQuery.append("  		  pes.cpf,                                                     ");
        pessoaQuery.append("  		  pes.aniversario,                                             ");
        pessoaQuery.append("  		  pes.logradouro,                                              ");
        pessoaQuery.append("  		  pes.telefone,                                                ");
        pessoaQuery.append("  		  pes.email,                                                   ");
        pessoaQuery.append("  		  pes.senha,                                                   ");
        pessoaQuery.append("  		  pes.numero,                                                  ");
        pessoaQuery.append("  		  pes.cidade,                                                  ");
        pessoaQuery.append("  		  pes.estado                                                   ");
        pessoaQuery.append("      from "+TABELA+" pes                                              ");
        pessoaQuery.append("  	where 1 = 1 "+condicoes                                             );

        Cursor cursor = db.rawQuery(pessoaQuery.toString(), parametros);

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

    public void deletar(Integer id) throws ErrorException {
        try {
            db.delete(TABELA,"_id = ?", new String[] {String.valueOf(id)});
        }catch (Exception e){
            throw new ErrorException("Erro ao deletar a linha " + id  + " " + e.getMessage(), e );
        }
    }

    public PessoaBean fundir(PessoaBean pessoaBean) throws ErrorException {
        try {
            buscarId(pessoaBean.getId());
            return atualizar(pessoaBean);
        }catch (ErrorException e){
            return inserir(pessoaBean);
        }
    }
}
