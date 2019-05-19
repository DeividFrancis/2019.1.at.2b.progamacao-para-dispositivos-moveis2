package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils;

public class StringUtils {


    public static boolean naoTemValor(String valor){
        boolean res = false;

        if(valor == null) res = true;

        else if(valor.equals("") == true) res = true;

        return res;
    }
}
