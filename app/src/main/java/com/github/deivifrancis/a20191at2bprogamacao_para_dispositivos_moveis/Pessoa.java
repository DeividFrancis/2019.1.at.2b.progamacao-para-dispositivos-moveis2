package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis;

public class Pessoa {

    Integer id;
    String nome, cpf, idade, logradouro, telefone, email, senha, numero, cidade, estado;

    public Pessoa(){
        this.id = getId();
        this.nome = getNome();
        this.email = getEmail();
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
