package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Game extends AppCompatActivity {

    players currentTurn;

    enum players {
        WHITE,
        BLACK
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ImageView infoBox = findViewById(R.id.infoBox);
        choseWhoPlays(infoBox);

        ImageView[] panels = {
                findViewById(R.id.topLeft),
                findViewById(R.id.topCenter),
                findViewById(R.id.topRight) ,
                findViewById(R.id.middleLeft) ,
                findViewById(R.id.middleCenter) ,
                findViewById(R.id.middleRight) ,
                findViewById(R.id.bottomLeft) ,
                findViewById(R.id.bottomCenter) ,
                findViewById(R.id.bottomRight)
        };

        Button playAgainBtn = findViewById(R.id.playAgainBtn);
        playAgainBtn.setVisibility(View.GONE);
        playAgainBtn.setOnClickListener(view -> {
            finish();
        });

        for (final ImageView panel : panels) {
            panel.setOnClickListener(view -> {
                playTurn(panel, infoBox, panels, playAgainBtn);
            });
        }


    }

    void playTurn(ImageView panel, ImageView infoBox, ImageView[] panels, Button playAgainBtn) {
        if (panel.getTag() == null) {
            if (currentTurn == players.WHITE) {
                panel.setImageResource(R.drawable.o);
                panel.setTag("o");
                currentTurn = players.BLACK;
                infoBox.setImageResource(R.drawable.xplay);
            } else {
                panel.setImageResource(R.drawable.x);
                panel.setTag("x");
                currentTurn = players.WHITE;
                infoBox.setImageResource(R.drawable.oplay);
            }
            checkIfWon(panels, infoBox, playAgainBtn);
        }
    }

    void choseWhoPlays(ImageView infoBox) {
        int random = new Random().nextInt(2);
        if (random == 0) {
            currentTurn = players.WHITE;
            infoBox.setImageResource(R.drawable.oplay);
        } else {
            currentTurn = players.BLACK;
            infoBox.setImageResource(R.drawable.xplay);
        }
    }

    void checkIfWon(ImageView[] panels, ImageView infoBox, Button playAgainBtn) {
        int[][] possiableWins = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                                 {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                {0, 4, 8}, {2, 4, 6}};
        boolean didWin = false;
        for (int i=0; i < possiableWins.length; i ++) {
            final int[] currentWins = possiableWins[i];
            if (panels[currentWins[0]].getTag() != null && panels[currentWins[1]].getTag() != null & panels[currentWins[2]].getTag() != null && panels[currentWins[0]].getTag().equals(panels[currentWins[1]].getTag()) && panels[currentWins[1]].getTag().equals(panels[currentWins[2]].getTag())) {
                if (panels[currentWins[0]].getTag().equals("x")) {
                    infoBox.setImageResource(R.drawable.xwin);
                } else {
                    infoBox.setImageResource(R.drawable.owin);
                }
                didWin = true;
                playAgainBtn.setVisibility(View.VISIBLE);
                ImageView markImg = findViewById(R.id.markImg);
                if (i == 0)
                    markImg.setImageResource(R.drawable.mark6);
                else if (i == 1)
                    markImg.setImageResource(R.drawable.mark7);
                else if (i == 2)
                    markImg.setImageResource(R.drawable.mark8);
                else if (i == 3)
                    markImg.setImageResource(R.drawable.mark3);
                else if (i == 4)
                    markImg.setImageResource(R.drawable.mark4);
                else if (i == 5)
                    markImg.setImageResource(R.drawable.mark5);
                else if (i == 6)
                    markImg.setImageResource(R.drawable.mark1);
                else
                    markImg.setImageResource(R.drawable.mark2);
            }
        }
        if (!didWin) {
            boolean isFull = true;
            for (final ImageView panel : panels) {
                if (panel.getTag() == null) {
                    isFull = false;
                    break;
                }
            }
            if (isFull) {
                infoBox.setImageResource(R.drawable.nowin);
                playAgainBtn.setVisibility(View.VISIBLE);
            }

        }

    }
}