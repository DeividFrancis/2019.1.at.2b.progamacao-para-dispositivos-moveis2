package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.OsUtils;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.CadastroActivity;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.Dashboard2Activity;

import java.util.ArrayList;
import java.util.List;

import static com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R.id.txtFirstLetter;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder> {

    List<PessoaBean> pessoaList;
    List<PapelBean> papelList;

    public PessoaAdapter(List<PessoaPapelBean> pessoaPapelList){
        this.pessoaList = new ArrayList<>();
        this.papelList = new ArrayList<>();

        for(PessoaPapelBean pessoaPapelBean: pessoaPapelList){
            pessoaList.add(pessoaPapelBean.getPessoaBean());
            papelList.add(pessoaPapelBean.getPapelBean());
        }
    }


    @NonNull
    @Override
    public PessoaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view =  layoutInflater.inflate(R.layout.row_pessoa, viewGroup, false);

        PessoaViewHolder pessoaViewHolder = new PessoaViewHolder(view, viewGroup.getContext());

        return pessoaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaViewHolder pessoaViewHolder, int i) {
        if(pessoaList != null && pessoaList.size() > 0){
            PessoaBean pessoaBean = pessoaList.get(i);
            PapelBean papelBean = papelList.get(i);

            String nome = pessoaBean.getNome();
            String firstLetter = nome.substring(0,1).toUpperCase();

            pessoaViewHolder.getTxtNome().setText(nome);
            pessoaViewHolder.getTxtPapel().setText(papelBean.getDescricao());
            pessoaViewHolder.getTxtFirstLetter().setText(firstLetter);

        }
    }

    @Override
    public int getItemCount() {
        return pessoaList.size();
    }

    protected class PessoaViewHolder extends  RecyclerView.ViewHolder{

        private TextView txtFirstLetter;
        private TextView txtNome;
        private TextView txtPapel;

        public PessoaViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);

//            imgFotoPerfil = itemView.findViewById(R.id.imgFotoPerfil);
            txtFirstLetter = itemView.findViewById(R.id.txtFirstLetter);
            txtNome = itemView.findViewById(R.id.txtNomeUsuario);
            txtPapel = itemView.findViewById(R.id.txtPapel);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PessoaBean pessoaBean = pessoaList.get(getLayoutPosition());

                    Intent i = new Intent(context, Dashboard2Activity.class);
                    i.putExtra("pessoa_id", pessoaBean.getId());
                    ((AppCompatActivity) context).startActivityForResult(i, 0);
                    ((AppCompatActivity) context).finish();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    OsUtils.vibrar(context);

                    PessoaBean pessoaBean = pessoaList.get(getLayoutPosition());

                    Intent i = new Intent(context, CadastroActivity.class);
                    i.putExtra("pessoa_id", pessoaBean.getId());
                    ((AppCompatActivity) context).startActivityForResult(i, 0);
                    return false;
                }
            });
        }

        public TextView getTxtFirstLetter() {
            return txtFirstLetter;
        }

        public void setTxtFirstLetter(TextView txtFirstLetter) {
            this.txtFirstLetter = txtFirstLetter;
        }

        public TextView getTxtNome() {
            return txtNome;
        }

        public void setTxtNome(TextView txtNome) {
            this.txtNome = txtNome;
        }

        public TextView getTxtPapel() {
            return txtPapel;
        }

        public void setTxtPapel(TextView txtPapel) {
            this.txtPapel = txtPapel;
        }
    }
}
