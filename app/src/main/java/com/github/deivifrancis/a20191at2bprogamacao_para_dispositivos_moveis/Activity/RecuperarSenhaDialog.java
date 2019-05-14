package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.Activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;

public class RecuperarSenhaDialog extends Dialog {

    EditText edtEmail;
    public RecuperarSenhaDialog(Context context) {
        super(context);
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_recuperarsenha);

        edtEmail = findViewById(R.id.edtEmail);
    }

    public void onRecuperaSenha(View view){
        String email = edtEmail.getText().toString();
        String msg = "Sua senha foi enviada para o email " + email + ".";
        msg += "DEBUG: sua senha é 1234";


        if(view.getId() == R.id.btnEnviar){
            Toast.makeText(null, msg, Toast.LENGTH_LONG).show();
        }
    }
}
