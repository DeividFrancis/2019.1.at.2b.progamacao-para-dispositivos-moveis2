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
                String email = edtEmail.getText().toString();
                String msg = "Sua senha foi enviada para o email " + email + "/r";
                msg += "DEBUG: sua senha é 1234";

                //pessoa controller resetarSenha(email)
                //vai retornar uma nova senha.
                //se não existir email, email nao enctrado.
                //senha padrao 1234
                //deixar de modo configurado
                //nao posso deixar mais de uma linha.

                Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
