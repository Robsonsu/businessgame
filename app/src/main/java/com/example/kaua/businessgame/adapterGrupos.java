package com.example.kaua.businessgame;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kaua.businessgame.Model.GrupoPergunta;

import java.util.List;

/**
 * Created by Kauã on 01/10/2017.
 */

public class adapterGrupos extends RecyclerView.Adapter<adapterGrupos.ViewHolder>  {

    private List<GrupoPergunta> grupos;
    private Context context;
    private OnDataSelected  onDataSelected;

    public adapterGrupos(Context context, List<GrupoPergunta> grupos) {
        this.context = context;
        this.grupos = grupos;
    }

    @Override
    public adapterGrupos.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grupos, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GrupoPergunta grupo = grupos.get(position);
     //   holder.tvGrupo.setText(context.getString(R.string.numeroLider, grupo.getIdGrupo()));

      //  ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, grupos.); //selected item will look like a spinner set from XML
       // spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // holder.spnLideres.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public int getItemCount() {
        return grupos.size();
    }

    public static interface OnDataSelected {

        public void onDataSelected(View view, int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvGrupo;
        public Spinner spnLideres;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    treatOnDataSelectedIfNecessary(v,getAdapterPosition());
                }
            });

            tvGrupo = (TextView) view.findViewById(R.id.tvGruposItem);
            spnLideres = (Spinner) view.findViewById(R.id.spnGrupoItem);

        }
    }

    private void treatOnDataSelectedIfNecessary(View view, int position) {
        if(onDataSelected != null) {
            onDataSelected.onDataSelected(view, position);
        }
    }
}
