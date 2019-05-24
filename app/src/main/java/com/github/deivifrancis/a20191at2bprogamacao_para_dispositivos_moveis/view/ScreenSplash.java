package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.R;

public class ScreenSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_splash);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarLogin();
            }
        }, 2000);

    }

    private void mostrarLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
