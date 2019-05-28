package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PessoaPapelDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.CondicaoEnum;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.Filtro;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.NumberUtils;

import java.util.List;

public class PessoaSeed extends AbstractSeed {

    public static final int ADMIN = 1;
    public static final String RESETA_SENHA_PADRAO = "1234";
    public static final String IRONMAN_NOME = "Tony Stark";

    public PessoaSeed(Context context) throws ErrorException {
        super(context);
    }

    @Override
    public void preparar() throws ErrorException {
        adicionar(new PessoaBean(ADMIN,"Nick Fury","123.123.123-12","Hell's Kitchen","69999265689","fury@shield.com","123","140","Nova York","NY",DateUtils.parse("01/05/1963")));
        adicionar(new PessoaBean(3,IRONMAN_NOME,"286.001.768-24","Long Island","(69)99834-0473","homemdeferro@shield.com","123","980","Nova York","NY", DateUtils.parse("25/11/1984")));
        adicionar(new PessoaBean(4,"Steve Rogers","808.708.138-20","Brooklyn","(41)99424-4825","capitaoamerica@shield.com","123","344","Nova York","NY", DateUtils.parse("04/07/1920")));
        adicionar(new PessoaBean(5,"Thor","776.935.463-32","Asgard","(69)98654-2266","thor@shield.com","123","756","Asgard","AG", DateUtils.parse("01/01/500")));
        adicionar(new PessoaBean(6,"Bruce Banner","671.002.050-93","Dayton","(62)98629-6413","hulk@shield.com","123","426","Ohio","OH", DateUtils.parse("19/02/1943")));
        adicionar(new PessoaBean(7,"Natasha Romanoff","992.147.432-40","Volgogrado","(63)99686-6070","viuvanegra@shield.com","123","205","Volgogrado","VG", DateUtils.parse("07/12/1967")));
        adicionar(new PessoaBean(8,"Clint Barton","494.169.905-07","Waverly","(48)99492-3766","gaviaoarqueiro@shield.com","123","616","Iowa","IA", DateUtils.parse("12/09/1963")));
        adicionar(new PessoaBean(9,"Wanda Maximoff","468.130.550-20","Transia","(69)99936-3923","feiticeiraescarlate@shield.com","123","1940","Transia","TR", DateUtils.parse("06/09/1970")));
        adicionar(new PessoaBean(10,"Visão","977.892.930-01","Brooklyn","(69)99916-4784","visao@shield.com","123","140","Nova York","NY", DateUtils.parse("11/12/2000")));
        adicionar(new PessoaBean(11,"Scott Lang","977.892.930-01","Coral Gables","(69)99952-9200","homemformiga@shield.com","123","200","Flórida","FL", DateUtils.parse("24/12/1980")));
        adicionar(new PessoaBean(12,"Stephen Strange","977.892.930-01","Filadélfia","(69)99952-9200","drestranho@shield.com","123","200","Pennsylvania","PA", DateUtils.parse("24/12/1980")));
        adicionar(new PessoaBean(13,"Peter Parker","977.892.930-01","Queens","(69)99952-9200","homemaranha@shield.com","123","200","Nova York","NY", DateUtils.parse("24/12/1980")));
        adicionar(new PessoaBean(14,"Peter Quill","977.892.930-01","Montanhas Rochosas","(69)99952-9200","senhordasestrelas@shield.com","123","200","Colorado","CO", DateUtils.parse("24/12/1980")));
        adicionar(new PessoaBean(15,"T’Challa","977.892.930-01","Wakanda","(69)99952-9200","panteranegra@shield.com","123","200","África","AF", DateUtils.parse("24/12/1980")));
        adicionar(new PessoaBean(16,"Carol Danvers","977.892.930-01","Boston","(69)99952-9200","capitamarvel@shield.com","123","200","Massachussets","MA", DateUtils.parse("24/12/1980")));
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
