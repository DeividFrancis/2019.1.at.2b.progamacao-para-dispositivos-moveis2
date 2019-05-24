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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaPapelController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.StringUtils;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.adapter.PessoaAdapter;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.core.AppCompatActivityDefault;

import java.util.List;

public class UsuarioListActivity extends AppCompatActivityDefault {

    RecyclerView rcPessoa;
    SearchView svPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Usuários");
        setSupportActionBar(toolbar);


        rcPessoa = findViewById(R.id.rcPessoa);
        rcPessoa.setHasFixedSize(true);
        rcPessoa.setLayoutManager(new LinearLayoutManager(this));
        try {
            carregaPessoaList(null);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem itemSearch = menu.findItem(R.id.itemSearch);
        itemSearch.setVisible(true);

        svPessoa = (SearchView) itemSearch.getActionView();
        svPessoa.setVisibility(View.VISIBLE);
        preparaSeachViewPessoa();

        return super.onCreateOptionsMenu(menu);
    }

    private void preparaSeachViewPessoa() {
        svPessoa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                try {
                    carregaPessoaList(newText);
                } catch (ErrorException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
    }

    private void carregaPessoaList(String nome) throws ErrorException {

        PessoaPapelController pessoaPapelController = new PessoaPapelController(this);
        List<PessoaPapelBean> list = pessoaPapelController.buscarNome(nome);

        rcPessoa.setAdapter(new PessoaAdapter(list));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            carregaPessoaList(null);
        } catch (ErrorException e) {
            e.printStackTrace();
        }
    }
}
