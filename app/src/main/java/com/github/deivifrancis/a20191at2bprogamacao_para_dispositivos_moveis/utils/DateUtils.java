package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.controller.PontoController;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view.CadastroActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static final String FORMAT_DATE = "dd/MM/yyyy";
    public static final String FORMAT_HOUR = "HH:mm";
    public static final String FORMAT_HOUR_NULL = "--:--";

    public static String format(Date data) {
        return format(data, FORMAT_DATE);
    }

    public static String format(Date data, String format) {
        if (data == null) return null;
        String dataStr = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        dataStr = sdf.format(data);

        return dataStr;
    }

    public static Date parse(String dataStr) throws ErrorException {
        return parse(dataStr, FORMAT_DATE);
    }

    public static Date parse(String dataStr, String format) throws ErrorException {
        if (StringUtils.naoTemValor(dataStr)) return null;
        Date dataObj = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try {
            dataObj = sdf.parse(dataStr);
        } catch (ParseException e) {
            throw new ErrorException("Erro ao converter data", e);
        }
        return dataObj;
    }

    public static void datePickerSimple(final Context context, final TextView edtDate) {
        edtDate.setShowSoftInputOnFocus(false);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                edtDate.setText(DateUtils.format(myCalendar.getTime()));
            }

        };
//        edtDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new DatePickerDialog(context, date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
        edtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) return;
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    public static void timePickerSiple(Context context, TimePickerDialog.OnTimeSetListener timePickerDialogOnTimeSetListener, Date date) {
        Calendar mcurrentTime = Calendar.getInstance();
        if(date != null) mcurrentTime.setTime(date);
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, timePickerDialogOnTimeSetListener, hour, minute, true);//Yes 24 hour time
//        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }
}

