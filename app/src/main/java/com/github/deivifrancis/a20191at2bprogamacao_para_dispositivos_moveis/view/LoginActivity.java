package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.ConfiguracaoGeralController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.ConfiguracaoGeralBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed.AppSeed;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.StringUtils;

import java.text.ParseException;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    EditText edtCpf, edtSenha;
    Button btnEntrar, btnRecuperarSenha, btnCadastrar;
    Switch swtLembrarSenha;

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            iniciarDD();

            edtCpf = findViewById(R.id.edtCpf);
            edtSenha = findViewById(R.id.edtSenha);

            btnEntrar = findViewById(R.id.btnEntrar);
            btnCadastrar = findViewById(R.id.btnCadastre);
            btnRecuperarSenha = findViewById(R.id.btnRecuperarSenha);

            swtLembrarSenha = findViewById(R.id.swtLembrarSenha);

            validarLembrarSenhaECarregarBundle();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("Login", e.getMessage());
            e.printStackTrace();
        }

    }

    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.btnEntrar:
                    String usuario = edtCpf.getText().toString();
                    String senhaMD5 = edtSenha.getText().toString();

                    fazerLogin(usuario, senhaMD5);
                    break;
                case R.id.btnRecuperarSenha:
                    new RecuperarSenhaDialog(this).show();
                    break;
                case R.id.btnCadastre:
                    startActivity(new Intent(this, CadastroActivity.class));
                    break;
            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void fazerLogin(String usuario, String senhaMd5) throws ErrorException {
        // Faz comunicação com banco de dados e valida;

        if (StringUtils.naoTemValor(usuario) || StringUtils.naoTemValor(senhaMd5)) {
            throw new ErrorException("Insira os dados faltando");
        }

        PessoaController pessoaController = new PessoaController(this);
        PessoaBean pessoaBean  = pessoaController.fazerLogin(usuario, senhaMd5);

        ConfiguracaoGeralBean configuracaoGeralBean = new ConfiguracaoGeralBean();
        configuracaoGeralBean.setUsuarioLogadoId(pessoaBean.getId());
        configuracaoGeralBean.setUsuario(usuario);
        configuracaoGeralBean.setUltimoLogin(new Date());

        boolean isChecked = swtLembrarSenha.isChecked();
        if (isChecked == true) {
            configuracaoGeralBean.setSalvaSenha(isChecked);
            configuracaoGeralBean.setSenha(senhaMd5);
        }

        ConfiguracaoGeralController configuracaoGeralController = new ConfiguracaoGeralController(this);
        configuracaoGeralController.inserir(configuracaoGeralBean);

        Intent intent = new Intent(this, DashobardActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void iniciarDD() throws ErrorException {
       AppSeed appSeed = new AppSeed(this);
       appSeed.cadastrarPorPadrao();
    }

    public void validarLembrarSenhaECarregarBundle() throws ErrorException, ParseException {

        ConfiguracaoGeralController configuracaoGeralController = new ConfiguracaoGeralController(this);
        ConfiguracaoGeralBean configuracaoGeralBean = configuracaoGeralController.busca();
        boolean isChecked = configuracaoGeralBean.getSalvaSenha();


        String usuarioAnterior = configuracaoGeralBean.getUsuario();
        String ultimoLoginAnterior = DateUtils.format(configuracaoGeralBean.getUltimoLogin());

        if ((StringUtils.naoTemValor(usuarioAnterior) == false) || (StringUtils.naoTemValor(ultimoLoginAnterior) == false)) {
            bundle.putString(ConfiguracaoGeralBean.USUARIO, usuarioAnterior);
            bundle.putString(ConfiguracaoGeralBean.ULTIMO_LOGIN, ultimoLoginAnterior);
        }

        if (isChecked == true) {
            // pegar usuario e senha do shered preferences e fazer o login;
            String usuario = configuracaoGeralBean.getUsuario();
            String senha = configuracaoGeralBean.getSenha();
            fazerLogin(usuario, senha);
        }
    }
}
