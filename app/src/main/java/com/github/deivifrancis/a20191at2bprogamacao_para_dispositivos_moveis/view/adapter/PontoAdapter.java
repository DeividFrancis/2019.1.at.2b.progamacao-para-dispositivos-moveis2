package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PontoBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.List;

public class PontoAdapter extends RecyclerView.Adapter<PontoAdapter.PontoViewHolder> {

    private List<PontoBean> pontoList;

    public PontoAdapter(List<PontoBean> pontoList) {
        this.pontoList = pontoList;
    }

    @NonNull
    @Override
    public PontoAdapter.PontoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.row_ponto, viewGroup, false);

        PontoViewHolder pontoViewHolder = new PontoViewHolder(view);

        return pontoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PontoAdapter.PontoViewHolder viewHolder, int i) {

        if(pontoList != null && pontoList.size() > 0 ){

            PontoBean pontoBean = pontoList.get(i);

            SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM");
            String diaStr = sdf.format(pontoBean.getData());
            String hora01 = (pontoBean.getHora01() == null)? DateUtils.FORMAT_HOUR_NULL : pontoBean.getHora01();
            String hora02 = (pontoBean.getHora02() == null)? DateUtils.FORMAT_HOUR_NULL : pontoBean.getHora02();
            String hora03 = (pontoBean.getHora03() == null)? DateUtils.FORMAT_HOUR_NULL : pontoBean.getHora03();
            String hora04 = (pontoBean.getHora04() == null)? DateUtils.FORMAT_HOUR_NULL : pontoBean.getHora04();


            viewHolder.getTxtDia().setText(diaStr);
            viewHolder.getTxtHora01().setText(hora01);
            viewHolder.getTxtHora02().setText(hora02);
            viewHolder.getTxtHora03().setText(hora03);
            viewHolder.getTxtHora04().setText(hora04);
        }

    }

    @Override
    public int getItemCount() {
        return pontoList.size();
    }

    // Responsavel por comunicar com a interface
    protected class PontoViewHolder extends RecyclerView.ViewHolder {

        public TextView txtDia;
        public TextView txtHora01;
        public TextView txtHora02;
        public TextView txtHora03;
        public TextView txtHora04;

        public PontoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDia = itemView.findViewById(R.id.txtDia);
            txtHora01 = itemView.findViewById(R.id.txtHora01);
            txtHora02 = itemView.findViewById(R.id.txtHora02);
            txtHora03 = itemView.findViewById(R.id.txtHora03);
            txtHora04 = itemView.findViewById(R.id.txtHora04);
        }

        public TextView getTxtDia() {
            return txtDia;
        }

        public void setTxtDia(TextView txtDia) {
            this.txtDia = txtDia;
        }

        public TextView getTxtHora01() {
            return txtHora01;
        }

        public void setTxtHora01(TextView txtHora01) {
            this.txtHora01 = txtHora01;
        }

        public TextView getTxtHora02() {
            return txtHora02;
        }

        public void setTxtHora02(TextView txtHora02) {
            this.txtHora02 = txtHora02;
        }

        public TextView getTxtHora03() {
            return txtHora03;
        }

        public void setTxtHora03(TextView txtHora03) {
            this.txtHora03 = txtHora03;
        }

        public TextView getTxtHora04() {
            return txtHora04;
        }

        public void setTxtHora04(TextView txtHora04) {
            this.txtHora04 = txtHora04;
        }
    }
}
