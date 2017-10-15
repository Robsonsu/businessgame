package com.example.kaua.businessgame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class TelaConfiguracoes extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView rvListaGrupos;
    private Spinner spnQtdGrupos;
    private LinearLayout llBtn;
    private Context context;
    private ArrayList<Grupo> grupos = new ArrayList<Grupo>();
    private ArrayList<String> lideres = new ArrayList<>();
    private Button btnOkGrupos;

    public TelaConfiguracoes() {
        // Required empty public constructor
    }

    public static TelaConfiguracoes newInstance() {
        return new TelaConfiguracoes();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tela_configuracoes, container, false);

        setView(view);
        acoesViews();
        lideres.add("Kauã Estriga");
        lideres.add("Robson Su");
        lideres.add("Gabriel França");
        lideres.add("Felipe Santos");

        // Inflate the layout for this fragment
        return view;
    }

    public void setView(View v){
        rvListaGrupos = (RecyclerView) v.findViewById(R.id.rvQtdGrupos);
        spnQtdGrupos = (Spinner) v.findViewById(R.id.spnQtdGrupo);
        llBtn = (LinearLayout) v.findViewById(R.id.llBtnConfig);
        btnOkGrupos = (Button) v.findViewById(R.id.btnOkGrupos);

        String[] grupos = new String[]{"1 Grupo", "2 Grupos", "3 Grupos", "4 Grupos", "5 Grupos"};

        ArrayAdapter<String> arrayGrupos = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, grupos);
        arrayGrupos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnQtdGrupos.setAdapter(arrayGrupos);
    }

    public void acoesViews(){
        btnOkGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setarLideres(spnQtdGrupos.getSelectedItemPosition() + 1);
            }
        });
//        spnQtdGrupos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (spnQtdGrupos.getSelectedItemPosition()){
//                    case 1:
//                        setarLideres(1);
//                        break;
//                    case 2:
//                        setarLideres(2);
//                        break;
//                    case 3:
//                        setarLideres(3);
//                        break;
//                    case 4:
//                        setarLideres(4);
//                        break;
//                    case 5:
//                        setarLideres(5);
//                        break;
//                    default:
//                        llBtn.setVisibility(View.GONE);
//                        break;
//                }
//            }
//        });
    }

    public void setarLideres(int qtdGrupos){
        grupos.clear();
        Grupo grupo;

        for (int i = 0; i < qtdGrupos; i++){
            grupo = new Grupo();
            grupo.setIdGrupo(String.valueOf(i+1));
            grupo.setLideres(lideres);
            grupos.add(grupo);
        }

        // Create adapter passing in the sample user data
        adapterGrupos adapter = new adapterGrupos(context, grupos);
        // Attach the adapter to the recyclerview to populate items
        rvListaGrupos.setAdapter(adapter);
        // Set layout manager to position the items
        rvListaGrupos.setLayoutManager(new LinearLayoutManager(context));

        llBtn.setVisibility(View.VISIBLE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
