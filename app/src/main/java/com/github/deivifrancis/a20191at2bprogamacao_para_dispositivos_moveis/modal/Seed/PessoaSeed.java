package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaPapelDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.CondicaoEnum;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.Filtro;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.NumberUtils;

import java.util.List;

public class PessoaSeed extends AbstractSeed {

    public static final int ADMIN = 1;
    public static final String RESETA_SENHA_PADRAO = "1234";

    public PessoaSeed(Context context){
        super(context);
    }

    @Override
    public void preparar() {
        // TODO: CADASTRAR VÁRIAS PESSOAS COM DADOS REAIS. (10)
        adicionar(new PessoaBean(ADMIN,"Direto ao Ponto","123.123.123-12",null,null,"ponto@gmail.com","123",null,null,null,null));
    }


    // TODO: AQUI ESTAVA COM UM BUG PQ SEMPRE QUE IA PASSAR POR AQUI ELE ADICIONADA UMA NOVA TAREFA PARA A MESMA PESSOA AI TODA VEZ FAZIA ISSO RESULTADO: FICAVA UMA LISTA ENORME NA TELA DE USUARIOS

    @Override
    public void executarDAO() throws ErrorException {
        PessoaDAO pessoaDAO = new PessoaDAO(context);
        PessoaPapelDAO pessoaPapelDAO = new PessoaPapelDAO(context);
        for(Object bean : listaBean){
            PessoaBean pessoaBean = pessoaDAO.fundir((PessoaBean) bean);

            PessoaPapelBean pessoaPapelBean = null;
            try {
                Filtro filtro = new Filtro();
                filtro.adicionar("pepa.pessoa_id", CondicaoEnum.EQUALS, pessoaBean.getId());
                List<PessoaPapelBean> pessoaPapelList = pessoaPapelDAO.buscar(filtro);
                pessoaPapelBean = pessoaPapelList.get(0);

            }catch (ErrorException e){
                pessoaPapelBean = new PessoaPapelBean();
                Integer papelId = (pessoaBean.getId().equals(PessoaSeed.ADMIN)) ? PapelSeed.ADMIN : PapelSeed.getIdAleatorio() ;
                pessoaPapelBean.setPessoaBean(pessoaBean);
                pessoaPapelBean.getPapelBean().setId(papelId);
                pessoaPapelDAO.fundir(pessoaPapelBean);
            }

        }
    }
}
