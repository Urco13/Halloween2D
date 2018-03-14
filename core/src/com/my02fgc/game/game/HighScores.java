package com.my02fgc.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by urco1 on 31/01/2018.
 */



public class HighScores {

    public static String[] highscores = { "0", "0", "0" };
    public static String archivoHighscores = "highscores.dat";

    public static void load() {
        FileHandle arquivo = Gdx.files.external(HighScores.archivoHighscores);
        if (!arquivo.exists()){
            HighScores.save();
        }else{
            String copia = arquivo.readString();
            highscores = copia.split(",");
        }

       //codigo
    }

    public static void anadirPuntuacion(int puntuacion) {
        boolean encontrado = false;
//        int i = 0;
        for (int i = 0; i < HighScores.highscores.length; i++) {
            if (puntuacion > Integer.parseInt(HighScores.highscores[i])) {
                for (int j = HighScores.highscores.length-1; j>i ; j--) {
                    HighScores.highscores[j] = HighScores.highscores[j - 1];
                }
                HighScores.highscores[i] = puntuacion + "";
                encontrado=true;
                break;
            }
        }

        if (encontrado){
            HighScores.save();
            //codigo salvar juego
        }

    }

    private static void save() {
        FileHandle arquivo = Gdx.files.external(HighScores.archivoHighscores);
        arquivo.writeString(HighScores.highscores[0] + ",", false);
        arquivo.writeString(HighScores.highscores[1] + ",", true);
        arquivo.writeString(HighScores.highscores[2] , true);

    }
}

