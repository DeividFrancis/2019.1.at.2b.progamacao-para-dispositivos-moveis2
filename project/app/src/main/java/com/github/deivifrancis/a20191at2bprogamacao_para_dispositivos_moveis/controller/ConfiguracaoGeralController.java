package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.ConfiguracaoGeralBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.ConfiguracaoGeralDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;

public class ConfiguracaoGeralController {


    ConfiguracaoGeralDAO configuracaoGeralDAO;
    public ConfiguracaoGeralController(Context context){
        configuracaoGeralDAO = new ConfiguracaoGeralDAO(context);
    }

    public void inserir(ConfiguracaoGeralBean configuracaoGeralBean){
        configuracaoGeralDAO.inserir(configuracaoGeralBean);
    }

    public ConfiguracaoGeralBean busca() throws ErrorException {
        return configuracaoGeralDAO.buscar();
    }
}
