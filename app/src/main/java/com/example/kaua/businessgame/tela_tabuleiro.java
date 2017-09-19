package com.example.kaua.businessgame;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class tela_tabuleiro extends AppCompatActivity {

    Drawning v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = new Drawning(tela_tabuleiro.this);
        setContentView(v);


    }
}
