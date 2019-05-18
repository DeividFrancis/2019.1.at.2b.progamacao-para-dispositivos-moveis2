package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean;

public class PessoaPapelBean {

    private Integer id;
    private Integer pessoaId;
    private Integer papelId;

    public PessoaPapelBean(){

    }

    public PessoaPapelBean(Integer id, Integer pessoaId, Integer papelId) {
        this.id = id;
        this.pessoaId = pessoaId;
        this.papelId = papelId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Integer getPapelId() {
        return papelId;
    }

    public void setPapelId(Integer papelId) {
        this.papelId = papelId;
    }
}
