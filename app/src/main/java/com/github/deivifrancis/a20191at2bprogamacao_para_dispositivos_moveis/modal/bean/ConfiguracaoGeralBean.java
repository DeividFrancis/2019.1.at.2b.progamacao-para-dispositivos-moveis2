package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean;

import java.util.Date;

public class ConfiguracaoGeralBean {

    public static final String IS_SENHA_SALVA = "isSalvaSenha";
    public static final String ULTIMO_LOGIN = "ultimoLogin";
    public static final String USUARIO = "usuario";
    public static final String SENHA = "senha";
    public static final String USUARIO_LOGADO = "usuarioLogadoId";

    private  Integer usuarioLogadoId;
    private Date ultimoLogin;
    private  Boolean isSalvaSenha;
    private  String usuario;
    private  String senha;

    public Integer getUsuarioLogadoId() {
        return usuarioLogadoId;
    }

    public void setUsuarioLogadoId(Integer usuarioLogadoId) {
        this.usuarioLogadoId = usuarioLogadoId;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public Boolean getSalvaSenha() {
        if(isSalvaSenha == null) isSalvaSenha = false;
        return isSalvaSenha;
    }

    public void setSalvaSenha(Boolean salvaSenha) {
        isSalvaSenha = salvaSenha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
