package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.adapter.PessoaAdapter;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.core.AppCompatActivityDefault;

import java.util.List;

public class UsuarioListActivity extends AppCompatActivityDefault {

    RecyclerView rcPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rcPessoa = findViewById(R.id.rcPessoa);
        rcPessoa.setHasFixedSize(true);
        rcPessoa.setLayoutManager(new LinearLayoutManager(this));
        try {
            carregaPessoaList();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent i = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivityForResult(i, 0);
            }

        });
    }

    private void carregaPessoaList() throws ErrorException {
        PessoaController pessoaController = new PessoaController(this);
        List<PessoaBean> pessoaList = pessoaController.getTodos();

        rcPessoa.setAdapter(new PessoaAdapter(pessoaList));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            carregaPessoaList();
        } catch (ErrorException e) {
            e.printStackTrace();
        }
    }
}
