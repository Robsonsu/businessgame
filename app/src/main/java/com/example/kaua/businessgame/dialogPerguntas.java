package com.example.kaua.businessgame;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaua.businessgame.Model.PerguntasDesafio;

import org.w3c.dom.Text;

/**
 * Created by Kauã on 25/10/2017.
 */

public class dialogPerguntas extends Dialog implements
        android.view.View.OnClickListener{

    private Context context;
    private TextView tvCdPergunta;
    private TextView tvDsPergunta;
    private RadioButton rbA, rbB, rbC, rbD;
    private Button btnSalvar;
    private PerguntasDesafio perguntasDesafio;

    public dialogPerguntas(Context context, PerguntasDesafio perguntasDesafio) {
        super(context);
        this.context = context;
        this.perguntasDesafio = perguntasDesafio;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        tvCdPergunta = (TextView) findViewById(R.id.tvCdPergunta);
        tvDsPergunta = (TextView) findViewById(R.id.tvDsPergunta);
        rbA = (RadioButton) findViewById(R.id.rbADesafio);
        rbB = (RadioButton) findViewById(R.id.rbBDesafio);
        rbC = (RadioButton) findViewById(R.id.rbCDesafio);
        rbD = (RadioButton) findViewById(R.id.rbDDesafio);
        btnSalvar = (Button) findViewById(R.id.btnSalvarDesafio);

        tvCdPergunta.setText("Pergunta " + perguntasDesafio.getCdPergunta()+1);
        tvDsPergunta.setText(perguntasDesafio.getDsPergunta());
        rbA.setText(perguntasDesafio.getDsResposta1());
        rbB.setText(perguntasDesafio.getDsResposta2());
        rbC.setText(perguntasDesafio.getDsResposta3());
        rbD.setText(perguntasDesafio.getDsResposta4());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbADesafio:
                perguntasDesafio.setRespostaParticipante(rbA.getText().toString());
                break;
            case R.id.rbBDesafio:
                perguntasDesafio.setRespostaParticipante(rbB.getText().toString());
                break;
            case R.id.rbCDesafio:
                perguntasDesafio.setRespostaParticipante(rbC.getText().toString());
                break;
            case R.id.rbDDesafio:
                perguntasDesafio.setRespostaParticipante(rbD.getText().toString());
                break;
            case R.id.btnSalvarDesafio:
                perguntasDesafio.setRespondida(true);
                Toast.makeText(context, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        dismiss();
    }
}
