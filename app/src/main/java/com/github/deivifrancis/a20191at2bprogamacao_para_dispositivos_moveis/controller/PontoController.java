package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PontoBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PontoDAO;

public class PontoController {

    Context context;

    public PontoController(Context context){
        this.context = context;
    }

    public void registrarPonto(PontoBean pontoBean) throws ErrorException {
        PontoDAO pontoDAO = new PontoDAO(context);
        pontoDAO.inserir(pontoBean);
    }
}
