package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean;

import java.util.Date;

public class PessoaBean {

    private Integer id;
    private String nome;
    private String cpf;
    private String logradouro;
    private String telefone;
    private String email;
    private String senha;
    private String numero;
    private String cidade;
    private String estado;
    private Date aniversario;

    public PessoaBean(){

    }

    public PessoaBean(Integer id, String nome, String cpf, String logradouro, String telefone, String email, String senha, String numero, String cidade, String estado, Date aniversario) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.logradouro = logradouro;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.aniversario = aniversario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getAniversario() {
        return aniversario;
    }

    public void setAniversario(Date aniversario) {
        this.aniversario = aniversario;
    }
}
