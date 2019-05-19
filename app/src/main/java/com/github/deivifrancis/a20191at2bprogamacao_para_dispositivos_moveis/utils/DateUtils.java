package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static final String FORMAT_DATE = "dd/MM/yyyy";
	public static final String FORMAT_HOUR = "HH:mm";
	public static final String FORMAT_HOUR_NULL = "--:--";

	public static String format(Date data) {
		return format(data, FORMAT_DATE);
	}
	
	public static String format(Date data, String format) {
		if(data == null) return null;
		String dataStr = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		dataStr = sdf.format(data);
		
		return dataStr;
	}
	
	public static Date parse(String dataStr) throws ErrorException{
		return parse(dataStr, FORMAT_DATE);
	}
	
	public static Date parse(String dataStr, String format) throws ErrorException {
		if(StringUtils.naoTemValor(dataStr)) return null;
		Date dataObj = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		try {
			dataObj = sdf.parse(dataStr);
		} catch (ParseException e) {
			throw new ErrorException("Erro ao converter data", e);
		}
		return dataObj;
	}
}
