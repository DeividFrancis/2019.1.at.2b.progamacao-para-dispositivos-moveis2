package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean;

import java.util.Date;

public class PontoBean {

    Integer id, pessoaId;
    Date data;
    String hora01,hora02,hora03,hora04,hora05,hora06,hora07,hora08,hora09,hora10;

    public PontoBean(){
        this.id = getId();
        this.data = getData();
        this.pessoaId = getPessoaId();
        this.hora01 = getHora01();
        this.hora02 = getHora02();
        this.hora03 = getHora03();
        this.hora04 = getHora04();
        this.hora05 = getHora05();
        this.hora06 = getHora06();
        this.hora07 = getHora07();
        this.hora08 = getHora08();
        this.hora09 = getHora09();
        this.hora10 = getHora10();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora01() {
        return hora01;
    }

    public void setHora01(String hora01) {
        this.hora01 = hora01;
    }

    public String getHora02() {
        return hora02;
    }

    public void setHora02(String hora02) {
        this.hora02 = hora02;
    }

    public String getHora03() {
        return hora03;
    }

    public void setHora03(String hora03) {
        this.hora03 = hora03;
    }

    public String getHora04() {
        return hora04;
    }

    public void setHora04(String hora04) {
        this.hora04 = hora04;
    }

    public String getHora05() {
        return hora05;
    }

    public void setHora05(String hora05) {
        this.hora05 = hora05;
    }

    public String getHora06() {
        return hora06;
    }

    public void setHora06(String hora06) {
        this.hora06 = hora06;
    }

    public String getHora07() {
        return hora07;
    }

    public void setHora07(String hora07) {
        this.hora07 = hora07;
    }

    public String getHora08() {
        return hora08;
    }

    public void setHora08(String hora08) {
        this.hora08 = hora08;
    }

    public String getHora09() {
        return hora09;
    }

    public void setHora09(String hora09) {
        this.hora09 = hora09;
    }

    public String getHora10() {
        return hora10;
    }

    public void setHora10(String hora10) {
        this.hora10 = hora10;
    }

    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }
}



