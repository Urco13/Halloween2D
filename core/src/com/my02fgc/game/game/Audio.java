package com.my02fgc.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by urco1 on 26/01/2018.
 */

public class Audio {

    public static Music musicPresentacion;
    public static Music musicInicio;
    public static Music musicJuego;
    public static Music musicMuerte;
//    public static Music musicMovimiento;
//    private static boolean juegoPausada;




    public static void inicializarAudio(){

        musicPresentacion = Gdx.audio.newMusic(Gdx.files.internal("sonidos/music/screamofsouls.ogg"));
        musicInicio = Gdx.audio.newMusic(Gdx.files.internal("sonidos/music/theloomingbattle.ogg"));
        musicJuego = Gdx.audio.newMusic(Gdx.files.internal("sonidos/music/theloomingbattle.ogg"));
        musicMuerte = Gdx.audio.newMusic(Gdx.files.internal("sonidos/music/dead.mp3"));
//        musicFin = Gdx.audio.newMusic(Gdx.files.internal("sonidos/music/fin.mp3"));
//        musicMovimiento = Gdx.audio.newMusic(Gdx.files.internal("sonidos/music/movimiento.ogg"));

    }//fin inicializarAudio

  public static void liberarAudio(){

        musicPresentacion.dispose();
        musicInicio.dispose();
        musicJuego.dispose();
        musicMuerte.dispose();
/*        musicFin.dispose();
        musicMovimiento.dispose();
        soundOvniContacto.dispose();
*/

    }//fin liberarAudio

    public static void controlarAudio(boolean audio){
        if (!audio) {
            musicPresentacion.setVolume(0);
            musicJuego.setVolume(0);
            musicMuerte.setVolume(0);
        }else{
            musicPresentacion.setVolume(1);
            musicJuego.setVolume(1);
            musicMuerte.setVolume(1);
        }
//        musicMovimiento.stop();
    }

    public static void pararAudio(){
        musicInicio.stop();
        musicJuego.stop();
        musicMuerte.stop();
    }
 /*   public static void iniciarAudio(){
        musicPresentacion.play();
        musicJuego.play();

//        musicMovimiento.stop();
    }*/



}//fin class
