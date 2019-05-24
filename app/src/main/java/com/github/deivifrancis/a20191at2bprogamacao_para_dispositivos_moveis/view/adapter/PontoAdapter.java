package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PapelController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PessoaPapelController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PontoController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.Seed.PapelSeed;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PontoBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.OsUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
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

        if (pontoList != null && pontoList.size() > 0) {

            PontoBean pontoBean = pontoList.get(i);

            SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM");
            String diaStr = sdf.format(pontoBean.getData());
            String hora01 = (pontoBean.getHora01() == null) ? DateUtils.FORMAT_HOUR_NULL : pontoBean.getHora01();
            String hora02 = (pontoBean.getHora02() == null) ? DateUtils.FORMAT_HOUR_NULL : pontoBean.getHora02();
            String hora03 = (pontoBean.getHora03() == null) ? DateUtils.FORMAT_HOUR_NULL : pontoBean.getHora03();
            String hora04 = (pontoBean.getHora04() == null) ? DateUtils.FORMAT_HOUR_NULL : pontoBean.getHora04();


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

        private static final String HORA01 = "hora01";
        private static final String HORA02 = "hora02";
        private static final String HORA03 = "hora03";
        private static final String HORA04 = "hora04";

        public TextView txtDia;
        public TextView txtHora01;
        public TextView txtHora02;
        public TextView txtHora03;
        public TextView txtHora04;

        public PontoViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtDia = itemView.findViewById(R.id.txtDia);
            txtHora01 = itemView.findViewById(R.id.txtHora01);
            txtHora02 = itemView.findViewById(R.id.txtHora02);
            txtHora03 = itemView.findViewById(R.id.txtHora03);
            txtHora04 = itemView.findViewById(R.id.txtHora04);

//            try {
//                PontoBean pontoBean = pontoList.get(getLayoutPosition());
//                Integer pessoaId = pontoBean.getPessoaBean().getId();
//                PapelController papelController = new PapelController(itemView.getContext());
//                PapelBean papelBean = papelController.getDadosPapelPessoa(pessoaId);
//                if (papelBean.getId().equals(PapelSeed.ADMIN) == true) {

                    preparaTimePicker(R.id.contentHora01, txtHora01, HORA01);
                    preparaTimePicker(R.id.contentHora02, txtHora02, HORA02);
                    preparaTimePicker(R.id.contentHora03, txtHora03, HORA03);
                    preparaTimePicker(R.id.contentHora04, txtHora04, HORA04);

//                }
//            } catch (ErrorException e) {
//                e.printStackTrace();
//            }
        }

        private void preparaTimePicker(int id, final TextView txtView, final String column) {
            LinearLayout contetHora01 = itemView.findViewById(id);
            contetHora01.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    OsUtils.vibrar(v.getContext());
                    timePickerSimple(itemView.getContext(), txtView, column);
                    return false;
                }

            });


        }

        public void timePickerSimple(final Context context, final TextView txtView, final String column) {

            final PontoBean pontoBean = pontoList.get(getLayoutPosition());

            String hora = txtView.getText().toString();
            Date data = null;
            try {
                data = DateUtils.parse(hora, DateUtils.FORMAT_HOUR);
            } catch (ErrorException e) {
                e.printStackTrace();
            }

            DateUtils.timePickerSiple(itemView.getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    String selectedHourStr = "" + selectedHour;
                    String selectedMinuteStr = "" + selectedMinute;
                    if (selectedHour < 10) {
                        selectedHourStr = "0" + selectedHourStr;
                    }
                    if (selectedMinute < 10) {
                        selectedMinuteStr = "0" + selectedMinuteStr;
                    }
                    String hora = selectedHourStr + ":" + selectedMinuteStr;

                    PontoController pontoController = new PontoController(itemView.getContext());

                    chamaMetodoCertoDaHora(column, pontoBean, hora);

                    try {
                        pontoController.atualizaPonto(pontoBean);
                    } catch (ErrorException e) {
                        Toast.makeText(itemView.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                    txtView.setText(hora);
                    Toast.makeText(itemView.getContext(), "Hora atualizada!", Toast.LENGTH_LONG).show();
                }
            }, data);
        }

        private PontoBean chamaMetodoCertoDaHora(String column, PontoBean pontoBean, String hora) {

            switch (column) {
                case HORA01:
                    pontoBean.setHora01(hora);
                    break;
                case HORA02:
                    pontoBean.setHora02(hora);
                    break;
                case HORA03:
                    pontoBean.setHora03(hora);
                    break;
                case HORA04:
                    pontoBean.setHora04(hora);
                    break;
                default:
                    Log.e("ERRO", "Deu pau " + hora);
            }

            return pontoBean;
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
