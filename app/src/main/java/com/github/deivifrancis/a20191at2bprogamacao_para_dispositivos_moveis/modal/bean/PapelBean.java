package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean;

public class PapelBean {

    Integer id;
    String descricao;

    public PapelBean(){
        this.id = getId();
        this.descricao = getDescricao();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
