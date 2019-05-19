package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.ConfiguracaoGeralController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PapelController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PontoController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.ConfiguracaoGeralBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PontoBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.StringUtils;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.adapter.PontoAdapter;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.core.AppCompatActivityDefault;

import java.util.List;

public class Dashboard2Activity extends AppCompatActivityDefault {

    TextView txtNome;
    TextView txtPapel;
    RecyclerView rcPonto;

    List<PontoBean> pontoList;
    PontoController pontoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dashboard2);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            txtNome = findViewById(R.id.txtNomeUsuario);
            txtPapel = findViewById(R.id.txtPapel);
            rcPonto = findViewById(R.id.rcPonto);
            FloatingActionButton fab = findViewById(R.id.fab);

            mostrarBundle();

            txtNome.setText(pessoaLogada.getNome());
            txtPapel.setText(papelBean.getDescricao());

            fab.setOnClickListener(registrarPontoView());

            rcPonto.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rcPonto.setLayoutManager(linearLayoutManager);

            carregaPontoLista();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private View.OnClickListener registrarPontoView() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                AlertDialog builder = new AlertDialog.Builder(Dashboard2Activity.this).create();
                builder.setTitle("Registrar ponto");
                builder.setMessage("Deseja realmente registrar seu ponto agora?");
                builder.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pontoController = new PontoController(getApplicationContext());
                        String msg = pontoController.registrarPonto(pessoaLogada);
                        try {
                            carregaPontoLista();
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        } catch (ErrorException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

                builder.show();
            }
        };
    }

    private void carregaPontoLista() throws ErrorException {

        // Aqui chama o Controller com a lista do DB
        pontoController = new PontoController(this);
        this.pontoList = pontoController.listaTodosPontosPessoa(pessoaLogada);

        rcPonto.setAdapter(new PontoAdapter(pontoList));
    }


    private void mostrarBundle() {
        Bundle bundle = getIntent().getExtras();

        String usuario = bundle.getString(ConfiguracaoGeralBean.USUARIO);
        String ultimoLogin = bundle.getString(ConfiguracaoGeralBean.ULTIMO_LOGIN);

        if ((StringUtils.naoTemValor(usuario) == false) || (StringUtils.naoTemValor(ultimoLogin) == false)) {
            String msg = "Último login: " + ultimoLogin + ", usuário: " + usuario;
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }
}
