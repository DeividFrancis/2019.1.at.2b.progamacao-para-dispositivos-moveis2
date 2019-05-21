package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils;

import android.content.Context;
import android.os.Vibrator;

public class OsUtils {
    public static void vibrar(Context context) {
        Vibrator rr = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long milliseconds = 30;//'30' é o tempo em milissegundos, é basicamente o tempo de duração da vibração. portanto, quanto maior este numero, mais tempo de vibração você irá ter
        rr.vibrate(milliseconds);
    }
}
