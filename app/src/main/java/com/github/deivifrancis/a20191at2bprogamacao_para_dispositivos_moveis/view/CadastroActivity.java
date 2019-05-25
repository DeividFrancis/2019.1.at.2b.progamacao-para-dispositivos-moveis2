package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view;

import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PapelController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaPapelController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PontoController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.MascaraUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CadastroActivity extends AppCompatActivity {


    EditText edtNome, edtEmail, edtAniversario, edtCpf, edtTelefone, edtEndereco, edtCidade, edtEstado, edtSenha, edtConfirmarSenha;
    Bundle bundle;
    Spinner spiPapel;

    PessoaBean pessoaBean;


    private List<Integer> papelIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setTitle("Cadastro");

        try {
            loadSpinner();

            edtNome = findViewById(R.id.edtNome);
            edtEmail = findViewById(R.id.edtEmail);

            edtAniversario = findViewById(R.id.edtAniversario);
            DateUtils.datePickerSimple(CadastroActivity.this, edtAniversario);

            edtCpf = findViewById(R.id.edtCpf);
            edtCpf.addTextChangedListener(MascaraUtils.mask(edtCpf, MascaraUtils.FORMAT_CPF));

            edtTelefone = findViewById(R.id.edtTelefone);
            edtTelefone.addTextChangedListener(MascaraUtils.mask(edtTelefone, MascaraUtils.FORMAT_FONE));

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
        papelIdList = new ArrayList<>();
        papelIdList.add(0);
        labelList.add("Papel");
        for (PapelBean papelBean : papelList) {
            papelIdList.add(papelBean.getId());
            labelList.add(papelBean.getDescricao());
        }

        spiPapel = findViewById(R.id.spiPapel);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, labelList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiPapel.setAdapter(dataAdapter);
    }

    private void carregaCamposAlterar() throws ErrorException {
        if (pessoaBean == null || pessoaBean.getId() == null) return;
        edtNome.setText(pessoaBean.getNome());
        edtEmail.setText(pessoaBean.getEmail());
        edtAniversario.setText(DateUtils.format(pessoaBean.getAniversario()));
        edtCidade.setText(pessoaBean.getCidade());
        edtCpf.setText(pessoaBean.getCpf());
        edtTelefone.setText(pessoaBean.getTelefone());
        edtEstado.setText(pessoaBean.getEstado());
        edtEndereco.setText(pessoaBean.getLogradouro());
        edtSenha.setText(pessoaBean.getSenha());
        edtConfirmarSenha.setText(pessoaBean.getSenha());

        PessoaPapelController pessoaPapelController = new PessoaPapelController(this);
        PessoaPapelBean pessoaPapelBean = pessoaPapelController.buscaPorPessoaId(pessoaBean.getId());

        int position = 0;
        for (int i = 0; i < papelIdList.size(); i++) {
            if (papelIdList.get(i).equals(pessoaPapelBean.getPapelBean().getId())) {
                position = i;
            }
        }

        spiPapel.setSelection(position);
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

            switch (item.getItemId()) {
                case R.id.itemOK:
                    cadastraOuAteraPessoa();
                    break;
                case R.id.itemDelete:
                    deletarPessoa();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
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
            int position = spiPapel.getSelectedItemPosition();
            Integer papelId = papelIdList.get(position);

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
