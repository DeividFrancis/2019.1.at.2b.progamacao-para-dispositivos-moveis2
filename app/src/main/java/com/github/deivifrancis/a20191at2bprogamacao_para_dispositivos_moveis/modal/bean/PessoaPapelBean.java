package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean;

public class PessoaPapelBean {

    private Integer id;
    private PessoaBean pessoaBean;
    private PapelBean papelBean;

    public PessoaPapelBean(){

    }

    public PessoaPapelBean(Integer id, PessoaBean pessoaBean, PapelBean papelBean) {
        this.id = id;
        this.pessoaBean = pessoaBean;
        this.papelBean = papelBean;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PessoaBean getPessoaBean() {
        if(pessoaBean == null)  pessoaBean = new PessoaBean();
        return pessoaBean;
    }

    public void setPessoaBean(PessoaBean pessoaBean) {
        this.pessoaBean = pessoaBean;
    }

    public PapelBean getPapelBean() {
        if (papelBean == null) papelBean = new PapelBean();
        return papelBean;
    }

    public void setPapelBean(PapelBean papelBean) {
        this.papelBean = papelBean;
    }
}
