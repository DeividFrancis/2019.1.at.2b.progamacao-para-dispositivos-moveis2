package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PapelController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PontoController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {


    EditText edtNome, edtEmail, edtAniversario, edtCpf, edtTelefone, edtEndereco, edtCidade, edtEstado, edtSenha, edtConfirmarSenha;
    Bundle bundle;
    Spinner spiPapel;

    PessoaBean pessoaBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        try {
            loadSpinner();


            edtNome = findViewById(R.id.edtNome);
            edtEmail = findViewById(R.id.edtEmail);
            edtAniversario = findViewById(R.id.edtAniversario);
            edtCpf = findViewById(R.id.edtCpf);
            edtTelefone = findViewById(R.id.edtTelefone);
            edtEndereco = findViewById(R.id.edtEndereco);
            edtCidade = findViewById(R.id.edtCidade);
            edtEstado = findViewById(R.id.edtEstado);
            edtSenha = findViewById(R.id.edtSenha);
            edtConfirmarSenha = findViewById(R.id.edtConfirmarSenha);
            spiPapel = findViewById(R.id.spiPapel);

            carregabundle();
            carregaCamposAlterar();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    private void loadSpinner() throws ErrorException {

        PapelController papelController = new PapelController(this);
        List<PapelBean> papelList = papelController.listarTodos();

        List<String> labelList = new ArrayList<>();
        for (PapelBean papelBean : papelList) {
            labelList.add(papelBean.getDescricao());
        }

        spiPapel = findViewById(R.id.spiPapel);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, labelList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiPapel.setAdapter(dataAdapter);

    }

    // TODO terminar o resto dos campos
    private void carregaCamposAlterar() {
        if (pessoaBean != null) ;
        edtNome.setText(pessoaBean.getNome());
    }

    private void carregabundle() throws ErrorException {
        bundle = getIntent().getExtras();
        if (bundle == null) bundle = new Bundle();

        Integer pessoaId = bundle.getInt("pessoa_id", 0);

        pessoaBean = new PessoaBean();
        if (pessoaId != 0) {
            PessoaController pessoaController = new PessoaController(this);
            pessoaBean = pessoaController.buscaId(pessoaId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_cadastro, menu);

        if (pessoaBean == null || pessoaBean.getId() == null) {
            MenuItem itemDeletar = menu.findItem(R.id.itemDelete);
            itemDeletar.setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            carregabundle();
        } catch (ErrorException e) {
            e.printStackTrace();
        }
        switch (item.getItemId()) {
            case R.id.itemOK:
                cadastraOuAteraPessoa();
                break;
            case R.id.itemDelete:
                deletarPessoa();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deletarPessoa() {


        final Context context = this;

        AlertDialog builder = new AlertDialog.Builder(CadastroActivity.this).create();
        builder.setTitle("Deletar");
        builder.setMessage("Deseja realmente deletar " + pessoaBean.getNome() + "?");
        builder.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PessoaController pessoaController = new PessoaController(context);
                String msg = null;
                try {
                    msg = pessoaController.deletar(pessoaBean.getId());
                } catch (ErrorException e) {
                    msg = e.getMessage();
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                finish();
            }
        });

        builder.show();
    }

    private void cadastraOuAteraPessoa() {
        String retorno = null;
        try {
            pessoaBean.setNome(edtNome.getText().toString());
            pessoaBean.setEmail(edtEmail.getText().toString());
            pessoaBean.setAniversario(DateUtils.parse(edtAniversario.getText().toString()));
            pessoaBean.setCpf(edtCpf.getText().toString());
            pessoaBean.setTelefone(edtTelefone.getText().toString());
            pessoaBean.setLogradouro(edtEndereco.getText().toString());
            pessoaBean.setCidade(edtCidade.getText().toString());
            pessoaBean.setEstado(edtEstado.getText().toString());
            pessoaBean.setSenha(edtSenha.getText().toString());
//
            Integer papelId = 0;

            String confirmarSenha = edtConfirmarSenha.getText().toString();
            PessoaController pessoaController = new PessoaController(this);


            if (pessoaBean.getId() != null) {
                retorno = pessoaController.atualizar(pessoaBean);
            } else {
                retorno = pessoaController.cadastrarPessoaPadrao(pessoaBean, confirmarSenha, papelId);
            }


            Toast.makeText(this, retorno, Toast.LENGTH_LONG).show();
            finish();

        } catch (ErrorException e) {
            retorno = e.getMessage();
            Toast.makeText(this, retorno, Toast.LENGTH_LONG).show();
            Log.e("cadastrar", e.getMessage());
            e.printStackTrace();
        }
    }
}
