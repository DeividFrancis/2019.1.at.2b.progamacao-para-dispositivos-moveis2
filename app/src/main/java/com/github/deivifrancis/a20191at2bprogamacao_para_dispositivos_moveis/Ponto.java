package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis;

import java.util.Date;

public class Ponto {

    Integer id, pes_id;
    Date data;
    String hora01,hora02,hora03,hora04,hora05,hora06,hora07,hora08;

    public Ponto(){
        this.id = getId();
        this.data = getData();
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
}

