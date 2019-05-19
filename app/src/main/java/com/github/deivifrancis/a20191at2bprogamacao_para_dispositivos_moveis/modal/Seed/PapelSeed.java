package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PapelDAO;

public class PapelSeed extends AbstractSeed {

    public static final int ADMIN = 1;
    public static final int FUNCIONARIO = 2;

    public PapelSeed(Context context) {
        super(context);
    }

    @Override
    public void preparar() {
        adicionar(new PapelBean(ADMIN,"ADMIN"));
        adicionar(new PapelBean(FUNCIONARIO,"FUNCIONARIO"));
    }

    @Override
    public void executarDAO() throws ErrorException {
        for(Object object : listaBean){
            PapelDAO papelDAO = new PapelDAO(context);
            papelDAO.fundir((PapelBean) object);
        }
    }
}
