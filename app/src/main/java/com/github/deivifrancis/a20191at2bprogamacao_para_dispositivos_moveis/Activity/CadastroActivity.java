package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try{
            PessoaHelper pessoa = new PessoaHelper(this);
            Toast.makeText(this, "Conectado com Sucesso!", Toast.LENGTH_LONG).show();
        }catch (SQLiteException e){
            Toast.makeText(this, "Conexão com erro", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
