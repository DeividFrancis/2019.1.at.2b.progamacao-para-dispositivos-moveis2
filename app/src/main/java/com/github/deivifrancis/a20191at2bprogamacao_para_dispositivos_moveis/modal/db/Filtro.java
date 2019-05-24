package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Filtro {
    List<String> colunaLista = new ArrayList<String>();
    List<CondicaoEnum> condicaoLista = new ArrayList<CondicaoEnum>();
    List<Object> valorLista = new ArrayList<Object>();

    public String criarCondicao(){
        StringBuilder sb = new StringBuilder();

        for (int i =0; i < colunaLista.size(); i++){
            String coluna = colunaLista.get(i);
            CondicaoEnum condicaoEnum = condicaoLista.get(i);
            sb.append(" AND " + coluna + " " + condicaoEnum.get() + " ?");
        }

        return sb.toString();
    }

    public String[] criarParametros(){
        String [] array = new String[valorLista.size()];

        for (int i = 0; i < valorLista.size(); i++){
            Object objeto = valorLista.get(i);
            if (objeto.getClass().equals(Date.class)){
                objeto = DateUtils.format((Date) objeto);
            }else{
                objeto = objeto.toString();
            }

            if(condicaoLista.get(i).equals(CondicaoEnum.LIKE)){
                objeto = "%" + objeto + "%";
            }
            array[i] = (String) objeto;
        }

        return array;
    }

    public void adicionar(String coluna, CondicaoEnum condicaoEnum, Object valor){
        colunaLista.add(coluna);
        condicaoLista.add(condicaoEnum);
        valorLista.add(valor);
    }

}
