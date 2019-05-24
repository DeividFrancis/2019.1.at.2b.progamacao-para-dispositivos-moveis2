package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaPapelController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PontoController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.ConfiguracaoGeralBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PontoBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.StringUtils;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.adapter.PontoAdapter;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.core.AppCompatActivityDefault;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Dashboard2Activity extends AppCompatActivityDefault {

    TextView txtNome;
    TextView txtPapel;
    SwipeRefreshLayout swipeRefresh;
    RecyclerView rcPonto;
    FloatingActionButton fab;

    PessoaBean pessoaBean;
    PessoaPapelBean pessoaPapelBean;

    List<PontoBean> pontoList;
    PontoController pontoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dashboard2);
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle("");
            setSupportActionBar(toolbar);

            txtNome = findViewById(R.id.txtNomeUsuario);
            txtPapel = findViewById(R.id.txtPapel);
            rcPonto = findViewById(R.id.rcPonto);
            fab = findViewById(R.id.fab);
            swipeRefresh = findViewById(R.id.swiperefresh);
            prepararSwipeRefresh();

            configTelaPelaPessoa();
            mostrarBundle();

            txtNome.setText(pessoaBean.getNome());
            txtPapel.setText(pessoaPapelBean.getPapelBean().getDescricao());

            prepararOnClickFab();

            rcPonto.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rcPonto.setLayoutManager(linearLayoutManager);

            carregaPontoLista();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void prepararOnClickFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog builder = new AlertDialog.Builder(Dashboard2Activity.this).create();
                builder.setTitle("Registrar ponto");
                builder.setMessage("Deseja realmente registrar seu ponto agora?");
                builder.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pontoController = new PontoController(getApplicationContext());
                        String msg = pontoController.registrarPonto(pessoaBean);
                        carregaPontoLista();
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    }
                });

                builder.show();
            }
        });
    }

    private void prepararSwipeRefresh() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        carregaPontoLista();
                        swipeRefresh.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    private void configTelaPelaPessoa() throws ErrorException {
        Bundle bundle = getIntent().getExtras();
        PessoaPapelController pessoaPapelController = new PessoaPapelController(this);
        if (bundle.containsKey("pessoa_id") == true) {
            Integer pessoaId = bundle.getInt("pessoa_id");

            pessoaPapelBean = pessoaPapelController.buscaPorPessoaId(pessoaId);
            pessoaBean = pessoaPapelBean.getPessoaBean();

            if (pessoaLogada.getId().equals(pessoaId)) {
                fab.show();
            } else {
                fab.hide();
            }
        } else {
            pessoaPapelBean = pessoaPapelController.buscaPorPessoaId(pessoaLogada.getId());
            pessoaBean = pessoaPapelBean.getPessoaBean();
            fab.show();
        }

    }

    private void carregaPontoLista() {

        try {
            pontoController = new PontoController(this);
            pontoList = pontoController.listaTodosPontosPessoa(pessoaBean);
            rcPonto.setAdapter(new PontoAdapter(pontoList));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


    private void mostrarBundle() {
        Bundle bundle = getIntent().getExtras();

        String usuario = bundle.getString(ConfiguracaoGeralBean.USUARIO);
        String ultimoLogin = bundle.getString(ConfiguracaoGeralBean.ULTIMO_LOGIN);

        if ((StringUtils.naoTemValor(usuario) == false) || (StringUtils.naoTemValor(ultimoLogin) == false)) {
            String msg = "Último login: " + ultimoLogin + ", usuário: " + usuario;
            Snackbar.make(fab, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }
}
