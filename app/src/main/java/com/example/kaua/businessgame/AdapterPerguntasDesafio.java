package com.example.kaua.businessgame;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kaua.businessgame.Model.GrupoPergunta;
import com.example.kaua.businessgame.Model.PerguntasDesafio;

import java.util.ArrayList;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;

/**
 * Created by Robson on 23/10/2017.
 */

public class AdapterPerguntasDesafio extends RecyclerView.Adapter<AdapterPerguntasDesafio.ViewHolder> {

    private Context context;
    private ArrayList<PerguntasDesafio> mList;

    // data is passed into the constructor
    AdapterPerguntasDesafio(Context context, ArrayList<PerguntasDesafio> mList) {
        this.context = context;
        this.mList = mList;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pergunta_desafio, parent, false);

        return new ViewHolder(itemView);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PerguntasDesafio obj = mList.get(position);

        holder.btnPergunta.setText(obj.getCdPergunta());

        holder.btnPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPerguntas cdd=new dialogPerguntas(context);
                cdd.show();
            }
        });
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button btnPergunta;

        ViewHolder(View itemView) {
            super(itemView);

            btnPergunta = (Button) itemView.findViewById(R.id.btnPerguntaItem);
        }

        @Override
        public void onClick(View view) {

        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
