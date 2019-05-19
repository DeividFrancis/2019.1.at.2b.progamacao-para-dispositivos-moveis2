package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.ConfiguracaoGeralBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;

public class ConfiguracaoGeralDAO {


    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public ConfiguracaoGeralDAO(Context context){
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sp.edit();
    }

    public void inserir(ConfiguracaoGeralBean configuracaoGeralBean){

        editor.putString(ConfiguracaoGeralBean.USUARIO, configuracaoGeralBean.getUsuario());
        editor.putString(ConfiguracaoGeralBean.SENHA, configuracaoGeralBean.getSenha());
        editor.putBoolean(ConfiguracaoGeralBean.IS_SENHA_SALVA, configuracaoGeralBean.getSalvaSenha());
        editor.putString(ConfiguracaoGeralBean.ULTIMO_LOGIN, DateUtils.format(configuracaoGeralBean.getUltimoLogin()));
        editor.putInt(ConfiguracaoGeralBean.USUARIO_LOGADO, configuracaoGeralBean.getUsuarioLogadoId());
        editor.apply();
    }

    public ConfiguracaoGeralBean buscar() throws ErrorException {

        ConfiguracaoGeralBean configuracaoGeralBean = new ConfiguracaoGeralBean();
        configuracaoGeralBean.setUsuarioLogadoId(sp.getInt(ConfiguracaoGeralBean.USUARIO_LOGADO, 0));
        configuracaoGeralBean.setSalvaSenha(sp.getBoolean(ConfiguracaoGeralBean.IS_SENHA_SALVA, false));
        configuracaoGeralBean.setUltimoLogin(DateUtils.parse(sp.getString(ConfiguracaoGeralBean.ULTIMO_LOGIN, "")));
        configuracaoGeralBean.setUsuario(sp.getString(ConfiguracaoGeralBean.USUARIO, ""));
        configuracaoGeralBean.setSenha(sp.getString(ConfiguracaoGeralBean.SENHA, ""));

        return configuracaoGeralBean;
    }
}
