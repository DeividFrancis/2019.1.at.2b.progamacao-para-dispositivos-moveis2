package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtCpf, edtSenha;
    Button btnEntrar, btnRecuperarSenha, btnCadastrar;
    Switch swtLembrarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtCpf = findViewById(R.id.edtCpf);
        edtSenha = findViewById(R.id.edtSenha);

        btnEntrar = findViewById(R.id.btnEntrar);
        btnCadastrar = findViewById(R.id.btnCadastre);
        btnRecuperarSenha = findViewById(R.id.btnRecuperarSenha);

        swtLembrarSenha = findViewById(R.id.swtLembrarSenha);

        validarLembrarSenha();

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnEntrar:
                validarLogin();
                break;
            case R.id.btnRecuperarSenha:
                new RecuperarSenhaDialog(this).show();
                break;
            case R.id.btnCadastre:
                startActivity(new Intent(this, CadastroActivity.class));
                break;
        }
    }

    public void validarLogin(){
        String usuario = edtCpf.getText().toString();
        String senha = edtSenha.getText().toString();
        boolean isChecked = swtLembrarSenha.isChecked();


        // Validar com os dados do banco de dados


        if(isChecked == true){
            // Salva
        }else{
            // Retira os que tiver salvo
        }

        fazerLogin(usuario, senha);

        Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_LONG).show();
    }

    public void fazerLogin(String usuario, String senhaMd5){
        // Faz comunicação com banco de dados e valida;

        startActivity(new Intent(this, DashobardActivity.class));
    }

    public void validarLembrarSenha(){
        boolean isChecked = swtLembrarSenha.isChecked();
        if(isChecked == true){
            // pegar usuario e senha do shered preferences e fazer o login;
            String usuario = null;
            String senha = null;
            fazerLogin(usuario, senha);
        }
    }
}
