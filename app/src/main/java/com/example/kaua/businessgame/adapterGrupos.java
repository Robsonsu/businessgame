package com.example.kaua.businessgame;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Kauã on 01/10/2017.
 */

public class adapterGrupos extends RecyclerView.Adapter<adapterGrupos.ViewHolder>  {

    private TextView tvGrupos;
    private Spinner spnLideres;

    @Override
    public adapterGrupos.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(adapterGrupos.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }
}
