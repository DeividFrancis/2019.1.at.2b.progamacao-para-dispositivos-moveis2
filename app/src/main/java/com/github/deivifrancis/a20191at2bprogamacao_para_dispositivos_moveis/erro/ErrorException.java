package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro;

public class ErrorException extends Exception {

    public ErrorException(String msg,Throwable err){
        super(msg, err);
    }

    public ErrorException(String msg){
        super(msg);
    }
}
