package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaPapelDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.NumberUtils;

public class PessoaSeed extends AbstractSeed {

    public static final int ADMIN = 1;
    public static final String RESETA_SENHA_PADRAO = "1234";

    public PessoaSeed(Context context){
        super(context);
    }

    @Override
    public void preparar() {
        // TODO: CADASTRAR VÁRIAS PESSOAS COM DADOS REAIS. (10)
        adicionar(new PessoaBean(ADMIN,"Direto ao Ponto","12312312312",null,null,"ponto@gmail.com","123",null,null,null,null));
    }

    @Override
    public void executarDAO() throws ErrorException {
        PessoaDAO pessoaDAO = new PessoaDAO(context);
        PessoaPapelDAO pessoaPapelDAO = new PessoaPapelDAO(context);
        for(Object bean : listaBean){
            PessoaBean pessoaBean = pessoaDAO.fundir((PessoaBean) bean);

            Integer papelId = (pessoaBean.getId().equals(PessoaSeed.ADMIN)) ? PapelSeed.ADMIN : PapelSeed.getIdAleatorio() ;
            PessoaPapelBean pessoaPapelBean = new PessoaPapelBean();
            pessoaPapelBean.setPessoaBean(pessoaBean);
            pessoaPapelBean.getPapelBean().setId(papelId);
            pessoaPapelDAO.fundir(pessoaPapelBean);
        }
    }
}
