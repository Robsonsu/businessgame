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
import android.widget.Toast;

import com.example.kaua.businessgame.Response.RespostaServidor;
import com.example.kaua.businessgame.Response.responseEfetuarLogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaConfiguracoes extends Fragment {

    private OnFragmentInteractionListener mListener;
//    private RecyclerView rvListaGrupos;
    private Spinner spnQtdGrupos;
    private LinearLayout llBtn;
    private Context context;
   // private ArrayList<Grupo> grupos = new ArrayList<Grupo>();
    private ArrayList<String> lideres = new ArrayList<>();
//    private Button btnOkGrupos;

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
//        rvListaGrupos = (RecyclerView) v.findViewById(R.id.rvQtdGrupos);
        spnQtdGrupos = (Spinner) v.findViewById(R.id.spnQtdEquipes);
        llBtn = (LinearLayout) v.findViewById(R.id.llBtnConfig);
//        btnOkGrupos = (Button) v.findViewById(R.id.btnOkGrupos);

        String[] qt_equipes = new String[]{"1 ", "2 ", "3 ", "4 ", "5"};
        try {
          CarregaGruposPerguntas();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> arrayGrupos = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, qt_equipes);
        arrayGrupos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnQtdGrupos.setAdapter(arrayGrupos);
    }

    public void acoesViews(){
//        btnOkGrupos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setarLideres(spnQtdGrupos.getSelectedItemPosition() + 1);
//            }
//        });
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

    public void CarregaGruposPerguntas() throws IOException {

        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<RespostaServidor> call = service.getByUserGrupo(cacheAplicativo.getResponseEfetuarLogin().getCd_usuario());


        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<RespostaServidor> call, Throwable t) {
            }
        });

    }


           // llBtn.setVisibility(View.VISIBLE);


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
