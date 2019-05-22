package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PapelDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaPapelDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.CondicaoEnum;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.Filtro;

public class PessoaPapelController {

    private Context context;

    public PessoaPapelController(Context context){
        this.context = context;
    }

    public void atribuirPapelParaPessoa(Integer pessoaId, Integer papelId) throws ErrorException {
        PessoaPapelDAO pessoaPapelDAO = new PessoaPapelDAO(context);
        PessoaDAO pessoaDAO = new PessoaDAO(context);
        PapelDAO papelDAO = new PapelDAO(context);
        PessoaPapelBean pessoaPapelBean = new PessoaPapelBean();

        try {
        Filtro filtroPessoa = new Filtro();
        filtroPessoa.adicionar("_id", CondicaoEnum.EQUALS,pessoaId);
        Filtro filtroPapel = new Filtro();
        filtroPapel.adicionar("_id",CondicaoEnum.EQUALS,papelId);
        pessoaDAO.buscar(filtroPessoa);
        papelDAO.buscar(filtroPapel);

        pessoaPapelBean.getPessoaBean().setId(pessoaId);
        pessoaPapelBean.getPapelBean().setId(papelId);
        pessoaPapelDAO.inserir(pessoaPapelBean);

        }catch (ErrorException e){
            throw new ErrorException("Erro ao cadastrar papel para uma pessoa 2");
        }
    }
}
