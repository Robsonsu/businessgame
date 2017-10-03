package com.example.kaua.businessgame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TelaConfiguracoes extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView rvListaGrupos;
    private EditText edtQtdGrupos;
    private LinearLayout llBtn;
    private Context context;
    private ArrayList<Grupo> grupos = new ArrayList<Grupo>();
    private ArrayList<String> lideres = new ArrayList<>();

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
        edtQtdGrupos = (EditText) v.findViewById(R.id.edtQtdGrupo);
        llBtn = (LinearLayout) v.findViewById(R.id.llBtnConfig);
    }

    public void acoesViews(){
        edtQtdGrupos.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            int qtdGrupos = Integer.parseInt(edtQtdGrupos.getText().toString());
                            if (qtdGrupos > 10){
                                Toast.makeText(context, R.string.erroQtdGrupos, Toast.LENGTH_LONG).show();
                                return false;
                            }

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
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
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
