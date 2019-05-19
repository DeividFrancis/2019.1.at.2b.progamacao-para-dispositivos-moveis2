package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaDAO;

public class PessoaSeed extends AbstractSeed {

    public static final int ADMIN = 1;
    public static final String RESETA_SENHA_PADRAO = "1234";

    public PessoaSeed(Context context){
        super(context);
    }

    @Override
    public void preparar() {
        adicionar(new PessoaBean(ADMIN,"Direto ao Ponto","12312312312",null,null,"ponto@gmail.com","123",null,null,null,null));
    }

    @Override
    public void executarDAO() throws ErrorException {
        for(Object bean : listaBean){
            PessoaDAO pessoaDAO = new PessoaDAO(context);
            pessoaDAO.fundir((PessoaBean) bean);
        }
    }
}
