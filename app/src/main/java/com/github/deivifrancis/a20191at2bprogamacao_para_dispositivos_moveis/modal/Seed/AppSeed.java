package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed;

import android.content.Context;
import android.util.Log;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;

import java.util.ArrayList;
import java.util.List;

public class AppSeed {

    private List<AbstractSeed> listaSeed = new ArrayList<>();

    public AppSeed(Context context) {
        listaSeed.add(new PapelSeed(context));
        listaSeed.add(new PessoaSeed(context));
    }

    public void cadastrarPorPadrao() throws ErrorException {
        for(AbstractSeed dd: listaSeed){
            dd.executarDAO();
            Log.d("cadpadrao",dd.getClass().getSimpleName() + " Executado.");
        }
    }
}
