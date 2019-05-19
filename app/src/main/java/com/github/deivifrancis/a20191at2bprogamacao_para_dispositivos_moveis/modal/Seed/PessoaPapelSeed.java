package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaPapelDAO;

public class PessoaPapelSeed extends AbstractSeed {

    public PessoaPapelSeed(Context context) {
        super(context);
    }

    @Override
    public void preparar() {
        adicionar(new PessoaPapelBean(1, PessoaSeed.ADMIN, PapelSeed.ADMIN));
    }

    @Override
    public void executarDAO() throws ErrorException {
        for(Object bean : listaBean){
            PessoaPapelDAO pessoaPapelDAO = new PessoaPapelDAO(context);
            pessoaPapelDAO.fundir((PessoaPapelBean) bean);
        }
    }
}
