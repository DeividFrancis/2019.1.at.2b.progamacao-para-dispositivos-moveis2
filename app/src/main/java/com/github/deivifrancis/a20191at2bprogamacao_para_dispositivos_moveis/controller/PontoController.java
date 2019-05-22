package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PontoBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PontoDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.CondicaoEnum;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.Filtro;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;

import java.util.Date;
import java.util.List;

//TODO deixar todos os controller com os DAOS como globais pra a classe

public class PontoController {

    Context context;

    public PontoController(Context context) {
        this.context = context;
    }

    public void registrarPonto(PontoBean pontoBean) throws ErrorException {
        PontoDAO pontoDAO = new PontoDAO(context);
        pontoDAO.inserir(pontoBean);
    }

    public List<PontoBean> listaTodosPontosPessoa(PessoaBean pessoaBean) throws ErrorException {
        PontoDAO pontoDAO = new PontoDAO(context);
        Filtro filtro = new Filtro();
        filtro.adicionar("pessoa_id", CondicaoEnum.EQUALS, pessoaBean.getId());
        return pontoDAO.buscar(filtro);

    }

    public String registrarPonto(PessoaBean pessoaLogada) {

        PontoDAO pontoDAO = new PontoDAO(context);
        PontoBean pontoBean = null;
        Date data = new Date();

        // Busca algum ponto ja feito Hoje
        try {
            Filtro filtro = new Filtro();
            filtro.adicionar("pessoa_id", CondicaoEnum.EQUALS, pessoaLogada.getId());
            filtro.adicionar("data", CondicaoEnum.EQUALS, data);

            List<PontoBean> pontoList = pontoDAO.buscar(filtro);
            pontoBean = pontoList.get(0);
        } catch (ErrorException e) {
            pontoBean = new PontoBean();
            pontoBean.setPessoaBean(pessoaLogada);
            pontoBean.setData(data);
        }

        try {
            pontoBean = registraHoraNaColunaCerta(pontoBean, data);
            pontoDAO.fundir(pontoBean);
            return "Ponto registrado!";
        } catch (ErrorException e) {
            return e.getMessage();
        }

    }

    private PontoBean registraHoraNaColunaCerta(PontoBean pontoBean, Date data) throws ErrorException {
        String hora = DateUtils.format(data, DateUtils.FORMAT_HOUR);

        Date dataAtual = DateUtils.parse(DateUtils.format(data, DateUtils.FORMAT_HOUR), DateUtils.FORMAT_HOUR);
        Date dataAlmoco = DateUtils.parse("12:00", DateUtils.FORMAT_HOUR);

        if(dataAtual.compareTo(dataAlmoco) < 0){ // Se data Atual for antes de a data do almoço
            if(pontoBean.getHora01() ==  null) pontoBean.setHora01(hora);
            else pontoBean.setHora02(hora);

        }else if(dataAtual.compareTo(dataAlmoco) > 0){ // Se a data atual for depois do almoço
            if(pontoBean.getHora03() == null) pontoBean.setHora03(hora);
            else pontoBean.setHora04(hora);
        }else{
            throw  new ErrorException("Erro ao comparar as datas, entre em contato com o suporte");
        }

        return pontoBean;
    }
}
