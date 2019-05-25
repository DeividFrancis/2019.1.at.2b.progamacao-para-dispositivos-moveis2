package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PapelDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaPapelDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.CondicaoEnum;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.Filtro;

import java.util.List;

public class PapelController {

    Context context;
    PapelDAO papelDAO;

    public PapelController(Context context){
        this.context = context;
        papelDAO = new PapelDAO(context);
    }

    public PapelBean getDadosPapelPessoa(Integer pessoaId) throws ErrorException {

        Filtro filtro = new Filtro();
        filtro.adicionar("pessoa_id", CondicaoEnum.EQUALS ,pessoaId);

        PessoaPapelDAO pessoaPapelDAO = new PessoaPapelDAO(context);
        PessoaPapelBean pessoaPapelBean = pessoaPapelDAO.buscar(filtro).get(0);

        return pessoaPapelBean.getPapelBean();
    }

    public List<PapelBean> listarTodos() throws ErrorException {
        return papelDAO.buscar(null);
    }
}
