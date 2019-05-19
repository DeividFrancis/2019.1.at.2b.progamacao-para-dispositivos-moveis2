package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;

public class CadastroActivity extends AppCompatActivity {


    EditText edtNome, edtEmail, edtAniversario, edtCpf, edtTelefone, edtEndereco, edtCidade, edtEstado, edtSenha, edtConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtAniversario = findViewById(R.id.edtAniversario);
        edtCpf = findViewById(R.id.edtCpf);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtCidade = findViewById(R.id.edtCidade);
        edtEstado = findViewById(R.id.edtEstado);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfirmarSenha = findViewById(R.id.edtConfirmarSenha);

    }

    public void onClick(View view) throws ErrorException {
        if(view.getId() == R.id.btnCadastrar){

            PessoaBean pessoaBean = new PessoaBean();
            pessoaBean.setNome(edtNome.getText().toString());
            pessoaBean.setEmail(edtEmail.getText().toString());
            pessoaBean.setAniversario(DateUtils.parse(edtAniversario.getText().toString()));
            pessoaBean.setCpf(edtCpf.getText().toString());
            pessoaBean.setTelefone(edtTelefone.getText().toString());
            pessoaBean.setLogradouro(edtEndereco.getText().toString());
            pessoaBean.setCidade(edtCidade.getText().toString());
            pessoaBean.setEstado(edtEstado.getText().toString());
            pessoaBean.setSenha(edtSenha.getText().toString());
            String confirmarSenha = edtConfirmarSenha.getText().toString();

            String retorno = null;
            try {
                PessoaController pessoaController = new PessoaController(this);
                retorno = pessoaController.cadastrarPessoaPadrao(pessoaBean, confirmarSenha);
                Toast.makeText(this,retorno,Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
                finish();

            }catch (ErrorException e){
                retorno = e.getMessage();
                Toast.makeText(this,retorno,Toast.LENGTH_LONG).show();
                Log.e("cadastrar",e.getMessage());
                e.printStackTrace();
            }

        }
    }
}
