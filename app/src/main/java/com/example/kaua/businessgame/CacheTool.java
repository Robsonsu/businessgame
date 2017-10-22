package com.example.kaua.businessgame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Robson on 21/10/2017.
 */

public class CacheTool extends AppCompatActivity {

    private String pergunta;

//    private SharedPreferences.OnSharedPreferenceChangeListener callback = new SharedPreferences.OnSharedPreferenceChangeListener() {
//        @Override
//        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
//            Log.i("Script",s + " updated");
//        }
//    };
//

    public void setCache(SharedPreferences prShared, String CacheName, String Value){

        SharedPreferences.Editor editor = prShared.edit();
        editor.putString(CacheName, Value);
        editor.commit();
    }

    public String getCache(SharedPreferences prShared, String CacheName){

        pergunta =  prShared.getString(CacheName, "");
        return pergunta;
    }

    public void cleanCache(SharedPreferences prShared,String CacheName){

        SharedPreferences.Editor editor = prShared.edit();
        editor.remove(CacheName);
        editor.commit();
    }

    public void updateCache(SharedPreferences prShared, String CacheName, String Value){

        pergunta =  prShared.getString(CacheName, "");
        SharedPreferences.Editor editor = prShared.edit();
        editor.putString( pergunta + CacheName, Value);
        editor.commit();
    }
}
