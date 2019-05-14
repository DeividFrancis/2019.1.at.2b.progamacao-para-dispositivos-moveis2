package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean;

public class PessoaPapelBean {

    Integer id, pes_id;

    public PessoaPapelBean(){
        this.id = getId();
        this.pes_id = getPes_id();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getPes_id() {
        return pes_id;
    }

    public void setPes_id(Integer pes_id) {
        this.pes_id = pes_id;
    }
}
