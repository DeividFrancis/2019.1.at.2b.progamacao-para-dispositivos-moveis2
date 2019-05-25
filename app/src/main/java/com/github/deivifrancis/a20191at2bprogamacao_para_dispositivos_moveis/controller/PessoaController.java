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
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.NumberUtils;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.StringUtils;

import java.util.List;

public class PessoaController {

    Context context;
    PessoaDAO pessoaDAO;

    public PessoaController(Context context) {
        this.context = context;
        pessoaDAO = new PessoaDAO(context);
    }

    public PessoaBean fazerLogin(String usuario, String senhaMd5) throws ErrorException {

        try {
            Filtro filtro = new Filtro();
            filtro.adicionar("cpf", CondicaoEnum.EQUALS, usuario);
            filtro.adicionar("senha", CondicaoEnum.EQUALS, senhaMd5);

            List<PessoaBean> pessoaLIsta = pessoaDAO.buscar(filtro);
            return pessoaLIsta.get(0);

        } catch (ErrorException e) {
            throw new ErrorException("Usuário ou senha inválidos!", e);
        }

    }

    public String atualizar(PessoaBean pessoaBean) throws ErrorException {
        pessoaDAO.atualizar(pessoaBean);
        return "Pessoa atulizada com sucesso!";
    }

    public String cadastrarPessoaPadrao(PessoaBean pessoaBean, String confirmarSenha, Integer papelId) throws ErrorException {

        if (StringUtils.naoTemValor(confirmarSenha) || StringUtils.naoTemValor(pessoaBean.getSenha()))
            throw new ErrorException("Senha é um campo obrigatório");

        if (StringUtils.naoTemValor(pessoaBean.getCpf())) {
            throw new ErrorException("Cpf é um campo obrigatório.");
        }

        if (existeDB("pes.cpf", pessoaBean.getCpf())) {
            throw new ErrorException("Cpf ja cadastrado");
        }

        if (existeDB("pes.email", pessoaBean.getEmail())) {
            throw new ErrorException("Email ja cadastrado");
        }

        if(papelId == null){
            papelId = PapelSeed.getIdAleatorio();
        }

        if ((pessoaBean.getSenha().equals(confirmarSenha)) == false) {
            throw new ErrorException("As senhas não conferem.");
        } else {

            //cadastro da pessoa.
            pessoaBean = pessoaDAO.inserir(pessoaBean);

            //definindo o papel para a pessoa cadastrando anteriormente.
            PessoaPapelBean pessoaPapelBean = new PessoaPapelBean();
            pessoaPapelBean.setPessoaBean(pessoaBean);

            pessoaPapelBean.getPapelBean().setId(papelId);

            PessoaPapelDAO pessoaPapelDAO = new PessoaPapelDAO(context);
            pessoaPapelBean = pessoaPapelDAO.inserir(pessoaPapelBean);

            return "Cadastrado com Sucesso!";
        }
    }

    private boolean existeDB(String coluna, String valor) {
        boolean ret = true;

        Filtro filtro = new Filtro();
        filtro.adicionar(coluna, CondicaoEnum.EQUALS, valor);

        List<PessoaBean> pessoaList = null;
        try {
            pessoaList = pessoaDAO.buscar(filtro);
        } catch (ErrorException e) {
            ret = false;
        }

        if (pessoaList != null && pessoaList.size() > 0) ret = true;
        else ret = false;

        return ret;
    }

    public PessoaBean getDadosPessoaLogada(Integer usuarioLogadoId) throws ErrorException {
        return pessoaDAO.buscarId(usuarioLogadoId);
    }

    public PessoaBean resetarSenha(String email) throws ErrorException {
        try {
            Filtro filtro = new Filtro();
            filtro.adicionar("pes.email", CondicaoEnum.EQUALS, email);
            List<PessoaBean> pessoaList = pessoaDAO.buscar(filtro);

            PessoaBean pessoaBean = pessoaList.get(0);
            pessoaBean.setSenha(PessoaSeed.RESETA_SENHA_PADRAO);
            pessoaBean = pessoaDAO.atualizar(pessoaBean);

            return pessoaBean;
        } catch (ErrorException e) {
            throw new ErrorException("Email não encontrado.", e);
        }
    }

    public List<PessoaBean> getTodos() throws ErrorException {
        return pessoaDAO.buscar(null);
    }

    public PessoaBean buscaId(Integer pessoaId) throws ErrorException {
        return pessoaDAO.buscarId(pessoaId);
    }

    public String deletar(Integer id) throws ErrorException {
        pessoaDAO.deletar(id);

        return "Deletado com sucesso!";
    }
}
