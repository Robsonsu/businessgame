package com.example.kaua.businessgame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class TelaNovaPartida extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Context context;
    private RadioGroup rgTpAcesso;
    private EditText edtToken;
    private Button btnConectar;

    public TelaNovaPartida() {
        // Required empty public constructor
    }

    public static TelaNovaPartida newInstance() {
        return new TelaNovaPartida();
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
        View view = inflater.inflate(R.layout.fragment_tela_nova_partida, container, false);

        setView(view);

        return view;
    }

    public void setView(View v){
        rgTpAcesso = (RadioGroup) v.findViewById(R.id.rgTpAcesso);
        edtToken = (EditText) v.findViewById(R.id.edtTokenPartida);
        btnConectar = (Button) v.findViewById(R.id.btnConectarPartida);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
