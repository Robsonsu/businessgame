package com.example.kaua.businessgame;

import android.content.Context;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class tela_tabuleiro extends AppCompatActivity {

    final static int maxN = 10;
    private Context context;
    private ImageView[][] ivCell = new ImageView[maxN][maxN];

    private Drawable[] drawCell = new Drawable[4];//0 is empty, 1 is player, 2 is bot, 3 is background

    private Button btnPlay;
    private TextView tvTurn;
    private int[][] valueCell = new int[maxN][maxN];///0 is empty,1 is player,2 is bot
    private boolean firstMove;
    private int xMove, yMove;//x and y axis of cell => define position of cell
    private int turnPlay;// whose turn?

    public tela_tabuleiro(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_tabuleiro);
        context = this;
        loadResources();
        designBoardGame();

    }

    private void loadResources() {
        drawCell[3] = context.getResources().getDrawable(R.drawable.cell_bg);//background
        //copy 2 image for 2 drawable player and bot
        //edit it :D
        drawCell[0] = null;//empty cell

    }

    @SuppressLint("NewApi")
    private void designBoardGame() {
        //create layoutparams to optimize size of cell
        // we create a horizotal linearlayout for a row
        // which contains maxN imageView in
        //need to find out size of cell first

        int sizeofCell = Math.round(ScreenWidth() / maxN);
        LinearLayout.LayoutParams lpRow = new LinearLayout.LayoutParams(sizeofCell * maxN, sizeofCell);
        LinearLayout.LayoutParams lpCell = new LinearLayout.LayoutParams(sizeofCell, sizeofCell);

        LinearLayout linBoardGame = (LinearLayout) findViewById(R.id.linBoardGame);

        //create cells
        for (int i = 0; i < maxN; i++) {
            LinearLayout linRow = new LinearLayout(context);
            //make a row

            for (int j = 0; j < maxN; j++) {
                    ivCell[i][j] = new ImageView(context);
                    //make a cell
                    //need to set background default for cell
                    //cell has 3 status, empty(defautl),player,bot
                    ivCell[i][j].setBackground(drawCell[3]);
                    linRow.addView(ivCell[i][j], lpCell);

            }
            linBoardGame.addView(linRow, lpRow);

        }
    }

    private float ScreenWidth() {
        Resources resources = context.getResources();//ok
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

}
