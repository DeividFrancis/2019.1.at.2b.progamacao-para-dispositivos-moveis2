package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;

public class RecuperarSenhaDialog extends Dialog {

    EditText edtEmail;
    Button btnEnviar;

    public RecuperarSenhaDialog(Context context) {
        super(context);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_recuperarsenha);

        edtEmail = findViewById(R.id.edtEmail);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = (edtEmail.getText().toString());
                String msg = "Sua senha foi enviada para o email: " + email + "                                ";
                msg += "DEBUG: Sua nova senha é: ";

                try {
                    //pessoa controller resetarSenha(email)
                    PessoaController pessoaController = new PessoaController(getContext());
                    PessoaBean pessoaBean = pessoaController.resetarSenha(email);

                    Toast.makeText(getContext(), msg + pessoaBean.getSenha(), Toast.LENGTH_LONG).show();
                } catch (ErrorException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),"E-mail não encontrado, por favor verifique.",Toast.LENGTH_LONG).show();
                }

                //vai retornar uma nova senha - ok
                //se não existir email, email nao enctrado - ok
                //senha padrao 1234 - ok
                //deixar de modo configurado
            }
        });
    }
}
