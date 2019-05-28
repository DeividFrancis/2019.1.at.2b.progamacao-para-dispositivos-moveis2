package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

public class ConnectionSP {


    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public  ConnectionSP(Context context){
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }



    public void inserir(List<Object> list){
        editor = sp.edit();

        for(Object obj: list){
            if(obj.getClass().equals(Integer.class)){
            }
        }
    }
}
