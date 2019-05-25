package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils;

public class NumberUtils {

    public static Integer getAleatorio(int primeiro, int ultimo) {

        return (int) Math.ceil(Math.random() * (ultimo  - primeiro + 1)) - 1 + primeiro;
    }
}
