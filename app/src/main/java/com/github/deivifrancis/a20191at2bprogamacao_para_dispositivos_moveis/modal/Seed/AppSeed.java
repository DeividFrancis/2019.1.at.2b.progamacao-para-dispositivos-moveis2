package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed;

import android.content.Context;
import android.util.Log;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;

import java.util.ArrayList;
import java.util.List;

public class AppSeed {

    private List<AbstractSeed> listaDD = new ArrayList<AbstractSeed>();

    public AppSeed(Context context) {
        listaDD.add(new PapelSeed(context));
        listaDD.add(new PessoaSeed(context));
        listaDD.add(new PessoaPapelSeed(context));
    }

    public void cadastrarPorPadrao() throws ErrorException {
        for(AbstractSeed dd: listaDD){
            dd.executarDAO();
            Log.d("cadpadrao",dd.getClass().getSimpleName() + " Executado.");
        }
    }
}
