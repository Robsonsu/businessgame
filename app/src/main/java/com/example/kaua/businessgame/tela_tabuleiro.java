package com.example.kaua.businessgame;

import android.content.Context;


import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class tela_tabuleiro extends AppCompatActivity {

    public tela_tabuleiro(){

    }

    ImageView iv_dice1, iv_dice2;
    TextView tv_dice1, tv_dice2;

    Random r;

    int dice1Point = 0, dice2Point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_tabuleiro);

        iv_dice1 = (ImageView) findViewById(R.id.dice1);
        iv_dice2 = (ImageView) findViewById(R.id.dice2);

        tv_dice1 = (TextView) findViewById(R.id.tv_dice1);
        tv_dice2 = (TextView) findViewById(R.id.tv_dice2);

        r = new Random();

        iv_dice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                int dice1Throw = r.nextInt(6) + 1;
                int dice2Throw = r.nextInt(6) + 1;

                setImagedice1(dice1Throw);
                setImagedice2(dice2Throw);

                if(dice1Throw > dice2Throw){
                    dice1Point++;
                }else{
                    dice2Point++;
                }

                tv_dice1.setText("DICE1: " + dice1Point);
                tv_dice2.setText("DICE2: " + dice2Point);

                Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
                iv_dice1.startAnimation(rotate);
                iv_dice2.startAnimation(rotate);
            }
        });
    }

    public void setImagedice1(int num){
        switch (num){
            case 1:
                iv_dice1.setImageResource(R.drawable.dice1);
                break;
            case 2:
                iv_dice1.setImageResource(R.drawable.dice2);
                break;
            case 3:
                iv_dice1.setImageResource(R.drawable.dice3);
                break;
            case 4:
                iv_dice1.setImageResource(R.drawable.dice4);
                break;
            case 5:
                iv_dice1.setImageResource(R.drawable.dice5);
                break;
            case 6:
                iv_dice1.setImageResource(R.drawable.dice6);
                break;
        }
    }
    public void setImagedice2(int num){
        switch (num){
            case 1:
                iv_dice2.setImageResource(R.drawable.dice1);
                break;
            case 2:
                iv_dice2.setImageResource(R.drawable.dice2);
                break;
            case 3:
                iv_dice2.setImageResource(R.drawable.dice3);
                break;
            case 4:
                iv_dice2.setImageResource(R.drawable.dice4);
                break;
            case 5:
                iv_dice2.setImageResource(R.drawable.dice5);
                break;
            case 6:
                iv_dice2.setImageResource(R.drawable.dice6);
                break;
        }
    }


}
