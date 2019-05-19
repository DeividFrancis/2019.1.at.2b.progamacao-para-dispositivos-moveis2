package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed;

import android.content.Context;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSeed {

    protected Context context;
    protected List<Object> listaBean = new ArrayList<Object>();

    public AbstractSeed(Context context){
        this.context = context;
        preparar();
    }

    public abstract void preparar();

    public abstract void executarDAO() throws ErrorException;

    public void adicionar(Object object){
        listaBean.add(object);
    }

}
