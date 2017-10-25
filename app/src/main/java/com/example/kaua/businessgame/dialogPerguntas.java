package com.example.kaua.businessgame;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by Kauã on 25/10/2017.
 */

public class dialogPerguntas extends Dialog implements
        android.view.View.OnClickListener{

    private Context context;
    private RadioButton rbA, rbB, rbC, rbD;
    private Button btnSalvar;

    public dialogPerguntas(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        rbA = (RadioButton) findViewById(R.id.rbADesafio);
        rbB = (RadioButton) findViewById(R.id.rbBDesafio);
        rbC = (RadioButton) findViewById(R.id.rbCDesafio);
        rbD = (RadioButton) findViewById(R.id.rbDDesafio);
        btnSalvar = (Button) findViewById(R.id.btnSalvarDesafio);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbADesafio:
                break;
            case R.id.rbBDesafio:
                break;
            case R.id.rbCDesafio:
                break;
            case R.id.rbDDesafio:
                break;
            case R.id.btnSalvarDesafio:
                Toast.makeText(context, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        dismiss();
    }
}
