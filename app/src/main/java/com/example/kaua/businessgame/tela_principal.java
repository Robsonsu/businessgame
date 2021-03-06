package com.example.kaua.businessgame;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class tela_principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        TelaToken.OnFragmentInteractionListener,
        TelaConfiguracoes.OnFragmentInteractionListener,
        TelaCadastro.OnFragmentInteractionListener,
        TelaNovaPartida.OnFragmentInteractionListener,
        TelaPergunta.OnFragmentInteractionListener,
        TelaTabuleiro.OnFragmentInteractionListener{

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        setView();
        setAcoesView();
        carregarTela(getIntent().getExtras());
    }

    public void setView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //       setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setAcoesView(){}

    public void carregarTela(Bundle bundle){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        String sFrag = bundle.getString("fragment");

        if (sFrag != null){
            switch (sFrag){
                case "TelaCadastro":
                    toolbar.setVisibility(View.GONE);
                    transaction.replace(R.id.fl_principal, new TelaCadastro()); // newInstance() is a static factory method.
                    break;
                case "TelaToken":
                    toolbar.setVisibility(View.VISIBLE);
                    transaction.replace(R.id.fl_principal, new TelaToken()); // newInstance() is a static factory method.
                    break;
                case "TelaNovaPartida":
                    toolbar.setVisibility(View.GONE);
                    transaction.replace(R.id.fl_principal, new TelaNovaPartida()); // newInstance() is a static factory method.
                    break;
                case "TelaConfiguracoes":
                    toolbar.setVisibility(View.VISIBLE);
                    transaction.replace(R.id.fl_principal, new TelaConfiguracoes());
                    break;
                case "TelaPergunta":
                    toolbar.setVisibility(View.VISIBLE);
                    transaction.replace(R.id.fl_principal, new TelaPergunta());
                    break;
                case "TelaTabuleiro":
                    toolbar.setVisibility(View.VISIBLE);
                    transaction.replace(R.id.fl_principal, new TelaTabuleiro());
                    break;
            }

            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (id){
            case R.id.nav_novapartda:
                transaction.replace(R.id.fl_principal, TelaNovaPartida.newInstance());
                transaction.commit();
                break;
            case R.id.nav_tabuleiro:
//                startActivity(new Intent(tela_principal.this, tela_tabuleiro.class));
                transaction.replace(R.id.fl_principal, new TelaTabuleiro());
                transaction.commit();
                break;
            case R.id.nav_perguntas:
                transaction.replace(R.id.fl_principal, new TelaPergunta());
                transaction.commit();
                break;
//            case R.id.nav_equipes:
//                break;
//            case R.id.nav_configuracoes:
//                transaction.replace(R.id.fl_principal, TelaConfiguracoes.newInstance());
//                transaction.commit();
//                break;
//            case R.id.nav_encerrar:
//                finish();
//                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
