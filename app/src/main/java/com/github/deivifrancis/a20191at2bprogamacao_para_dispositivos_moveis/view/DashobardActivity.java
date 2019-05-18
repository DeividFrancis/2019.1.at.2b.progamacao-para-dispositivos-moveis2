package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.ConfiguracaoGeralController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PapelController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.ConfiguracaoGeralBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.StringUtils;

public class DashobardActivity extends AppCompatActivity {

    TextView txtNome, txtPapel;
    PessoaBean pessoaLogada;
    PapelBean papelBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashobard);

        try {
            carregaConfiguracaoGeral();

            txtNome = findViewById(R.id.txtNomeUsuario);
            txtPapel = findViewById(R.id.txtPapel);

            txtNome.setText(pessoaLogada.getNome());
            txtPapel.setText(papelBean.getDescricao());

            mostrarBundle();
        } catch (ErrorException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void carregaConfiguracaoGeral() throws ErrorException {

        ConfiguracaoGeralController configuracaoGeralController = new ConfiguracaoGeralController(this);
        ConfiguracaoGeralBean configuracaoGeralBean = configuracaoGeralController.busca();


        PessoaController pessoaController = new PessoaController(this);
        pessoaLogada = pessoaController.getDadosPessoaLogada(configuracaoGeralBean.getUsuarioLogadoId());

        PapelController papelController = new PapelController(this);
        papelBean = papelController.getDadosPapelPessoa(configuracaoGeralBean.getUsuarioLogadoId());

    }

    private void mostrarBundle() {
        Bundle bundle = getIntent().getExtras();

        String usuario = bundle.getString(ConfiguracaoGeralBean.USUARIO);
        String ultimoLogin = bundle.getString(ConfiguracaoGeralBean.ULTIMO_LOGIN);

        if ((StringUtils.naoTemValor(usuario) == false) || (StringUtils.naoTemValor(ultimoLogin) == false)) {
            String msg = "Último login: " + ultimoLogin + ", usuário: " + usuario;
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }
}
