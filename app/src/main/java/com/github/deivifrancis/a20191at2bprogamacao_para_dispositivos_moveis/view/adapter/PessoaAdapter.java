package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaBean;

import java.util.List;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder> {

    List<PessoaBean> pessoaList;

    public PessoaAdapter(List<PessoaBean> pessoaList){
        this.pessoaList = pessoaList;
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

            pessoaViewHolder.getTxtNome().setText(pessoaBean.getNome());
            pessoaViewHolder.getTxtPapel().setText("Admin");

        }
    }

    @Override
    public int getItemCount() {
        return pessoaList.size();
    }

    protected class PessoaViewHolder extends  RecyclerView.ViewHolder{

        private ImageView imgFotoPerfil;
        private TextView txtNome;
        private TextView txtPapel;

        public PessoaViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);

            imgFotoPerfil = itemView.findViewById(R.id.imgFotoPerfil);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtPapel = itemView.findViewById(R.id.txtPapel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Voce clicou", Toast.LENGTH_LONG).show();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
        }

        public ImageView getImgFotoPerfil() {
            return imgFotoPerfil;
        }

        public void setImgFotoPerfil(ImageView imgFotoPerfil) {
            this.imgFotoPerfil = imgFotoPerfil;
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
