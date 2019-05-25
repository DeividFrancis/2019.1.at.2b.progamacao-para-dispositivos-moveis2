package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.PapelDAO;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.NumberUtils;

public class PapelSeed extends AbstractSeed {

    public static final int ADMIN = 1;

    public PapelSeed(Context context) {
        super(context);
    }

    @Override
    public void preparar() {
        adicionar(new PapelBean(ADMIN,"Gerente"));
        adicionar(new PapelBean(2,"Secretário(a)"));
        adicionar(new PapelBean(3,"Designer"));
        adicionar(new PapelBean(4,"T.I"));
        adicionar(new PapelBean(5,"Contador"));
        adicionar(new PapelBean(6,"Vendedor"));
    }

    @Override
    public void executarDAO() throws ErrorException {
        for(Object object : listaBean){
            PapelDAO papelDAO = new PapelDAO(context);
            papelDAO.fundir((PapelBean) object);
        }
    }

    public static Integer getIdAleatorio(){
        return NumberUtils.getAleatorio(2,6);
    }
}
