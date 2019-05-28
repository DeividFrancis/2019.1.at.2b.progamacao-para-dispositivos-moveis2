package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.core;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.ConfiguracaoGeralController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PapelController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed.PapelSeed;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.ConfiguracaoGeralBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.Dashboard2Activity;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.LoginActivity;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.ScreenSplash;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.UsuarioListActivity;

public class AppCompatActivityDefault extends AppCompatActivity {



    public PessoaBean pessoaLogada;
    public PapelBean papelBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            carregaConfiguracaoGeral();
        } catch (ErrorException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        boolean isMainActivity = false;

        if (this.getClass().equals(Dashboard2Activity.class) == false) {
            isMainActivity = true;
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(isMainActivity); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(isMainActivity);
        getMenuInflater().inflate(R.menu.menu_main, menu);


        if((papelBean.getId().equals(PapelSeed.ADMIN)) == false){
            MenuItem itemUsuarios = menu.findItem(R.id.itemUsuarios);
            itemUsuarios.setVisible(false);
        }

//        SearchView itemSearch = (SearchView) menu.findItem(R.id.itemSearch).getActionView();

        // Setar na tela
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String text = null;
        switch (item.getItemId()) {
            case R.id.itemUsuarios:
                if(getClass().equals(UsuarioListActivity.class)) break;
                startActivity(new Intent(this, UsuarioListActivity.class));

            case R.id.itemConfig:

                break;
            case R.id.itemSair:
                alertDialogExit();
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                Toast.makeText(this, "Opção não mapeada", Toast.LENGTH_LONG).show();
                break;
        }
        if (text != null)
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    protected void alertDialogExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja realmente sair do app?");
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ConfiguracaoGeralController configuracaoGeralController = new ConfiguracaoGeralController(getApplicationContext());
                try {
                    ConfiguracaoGeralBean configuracaoGeralBean = configuracaoGeralController.busca();
                    configuracaoGeralBean.setSalvaSenha(false);
                    configuracaoGeralController.inserir(configuracaoGeralBean);
                } catch (ErrorException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                finishAffinity();
                startActivity(new Intent(getApplicationContext(), ScreenSplash.class));
            }
        });
        final Context obj = this;
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(obj, "Uffa!", Toast.LENGTH_LONG).show();
            }
        });

        builder.show();
    }


    private void carregaConfiguracaoGeral() throws ErrorException {

        ConfiguracaoGeralController configuracaoGeralController = new ConfiguracaoGeralController(this);
        ConfiguracaoGeralBean configuracaoGeralBean = configuracaoGeralController.busca();

        PessoaController pessoaController = new PessoaController(this);
        pessoaLogada = pessoaController.getDadosPessoaLogada(configuracaoGeralBean.getUsuarioLogadoId());

        PapelController papelController = new PapelController(this);
        papelBean = papelController.getDadosPapelPessoa(configuracaoGeralBean.getUsuarioLogadoId());

    }
}

