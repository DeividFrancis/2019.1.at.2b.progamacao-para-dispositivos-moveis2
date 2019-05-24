package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db;

public enum CondicaoEnum {
    EQUALS("="),
    GREAT(">"),
    LESS("<"),
    LIKE("LIKE"),
    GREATOREQUALS(">="),
    LESSOREQUALS("<=");

    String condicao;
    CondicaoEnum(String condicao){
        this.condicao = condicao;
    }

    public String get(){
        return this.condicao;
    }
}
