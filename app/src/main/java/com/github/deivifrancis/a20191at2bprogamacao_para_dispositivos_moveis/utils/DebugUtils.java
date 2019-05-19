package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils;

import android.util.Log;

public class DebugUtils {
    private static final boolean DEBUG = true;


    public static void log(String msg){
        log("MEULOG", msg);
    }
    public static void log(String tag, String msg){
        if(DEBUG == true){
            Log.e(tag, msg);
        }
    }
}
