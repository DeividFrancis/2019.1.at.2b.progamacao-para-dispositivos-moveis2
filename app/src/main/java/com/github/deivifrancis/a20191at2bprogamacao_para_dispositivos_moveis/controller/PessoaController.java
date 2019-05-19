package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed.PessoaSeed;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaPapelDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.CondicaoEnum;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.Filtro;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed.PapelSeed;

import java.util.List;

public class PessoaController {

    Context context;

    public PessoaController(Context context){
        this.context = context;
    }

    public PessoaBean fazerLogin(String usuario, String senhaMd5) throws ErrorException{

       try {
           PessoaDAO pessoaDAO = new PessoaDAO(context);
           Filtro filtro = new Filtro();
           filtro.adicionar("cpf", CondicaoEnum.EQUALS, usuario);
           filtro.adicionar("senha", CondicaoEnum.EQUALS, senhaMd5);

           List<PessoaBean> pessoaLIsta = pessoaDAO.buscar(filtro);
           return pessoaLIsta.get(0);

       }catch (ErrorException e){
           throw new ErrorException("Usuário ou senha inválidos!", e);
       }

    }

    public String cadastrarPessoaPadrao(PessoaBean pessoaBean, String confirmarSenha) throws ErrorException {

        if((pessoaBean.getSenha().equals(confirmarSenha)) == false){
            throw new ErrorException("As senhas não conferem.");
        }else{

            //cadastro da pessoa.
            PessoaDAO pessoaDAO = new PessoaDAO(context);
            pessoaBean = pessoaDAO.inserir(pessoaBean);

            //definindo o papel para a pessoa cadastrando anteriormente.
            PessoaPapelBean pessoaPapelBean = new PessoaPapelBean();
            pessoaPapelBean.setPessoaId(pessoaBean.getId());
            pessoaPapelBean.setPapelId(PapelSeed.ADMIN);

            PessoaPapelDAO pessoaPapelDAO = new PessoaPapelDAO(context);
            pessoaPapelBean = pessoaPapelDAO.inserir(pessoaPapelBean);

            return "Cadastrado com Sucesso!";
        }
    }

    public PessoaBean getDadosPessoaLogada(Integer usuarioLogadoId) throws ErrorException {
        PessoaDAO pessoaDAO = new PessoaDAO(context);
        return pessoaDAO.buscarId(usuarioLogadoId);
    }

    public PessoaBean resetarSenha(String email) throws ErrorException {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO(context);
            PessoaBean pessoaBean = new PessoaBean();
            Filtro filtro = new Filtro();
            filtro.adicionar("email",CondicaoEnum.EQUALS,email);
            List<PessoaBean> pessoaBeans = pessoaDAO.buscar(filtro);

            pessoaBean = pessoaBeans.get(0);
            pessoaBean.setSenha(PessoaSeed.RESETA_SENHA_PADRAO);
            pessoaBean =  pessoaDAO.atualizar(pessoaBean);

            return pessoaBean;
        }catch (ErrorException e){
            throw new ErrorException("Email não encontrado.", e);
        }
    }
}
