package com.my02fgc.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by urco1 on 11/01/2018.
 */

public class AssetsJuego {

    public static Texture textureCorazon;

    public static Texture texturePuntoNegro;

    public static Texture textureControles;
    public static Texture texturePausa;
    public static Texture textureSair;
    public static Texture textureLifebar_back;
    public static Texture textureLifebar_front;
    public static Texture textureSonidoOn;
    public static Texture textureSonidoOff;

    ///calabazin//
    public static Animation animacionCalabazinCorriendoDerecha;
    public static Animation animacionCalabazinCorriendoIzquierda;
    public static Animation animacionCalabazinParado;

    public static TextureAtlas textureCalabazinCorriendoDerecha;
    public static TextureAtlas textureCalabazinCorriendoIzquierda;
    public static TextureAtlas textureCalabazinParado;

    //enemigos//

    public static Animation animacionZombieCorriendoDerecha;
    public static TextureAtlas textureAtlasZombieCorriendoDerecha;

    public static Animation animacionGhostAnimacion;
    public static TextureAtlas textureAtlasGhost;

    ////textura mundo
    public static TextureAtlas textureArrowSign;
    public static TextureAtlas textureMarcadores;

    //llaves//
    public static Texture textureLlave1;
    public static Texture textureLlave2;
    public static Texture textureLlave3;
    public static Texture textureLlaveSalida;


    /**
     * Metodo encargado de cargar todas las texturas
     */
    public static void cargarTexturas(){
        FileHandle corazonFileHandle = Gdx.files.internal("graficos/controles/heart.png");
        FileHandle puntoNegroFileHandle = Gdx.files.internal("graficos/libgdx_puntonegro.jpg");
        FileHandle controlesFileHandle = Gdx.files.internal("graficos/coontroles.png");
        FileHandle pausaFileHandle = Gdx.files.internal("graficos/pausejuego.png");
        FileHandle sairFileHandle = Gdx.files.internal("graficos/stop.png");
        FileHandle lifebar_backHandle = Gdx.files.internal("graficos/controles/bar.png");
        FileHandle lifebar_frontHandle =  Gdx.files.internal("graficos/controles/relleno.png");
        FileHandle llaveFileHandle = Gdx.files.internal("graficos/controles/skeletonkey.png");
        FileHandle sonidoOnFileHandle = Gdx.files.internal("graficos/controles/sound-on.png");
        FileHandle sonidoOffFileHandle = Gdx.files.internal("graficos/controles/sound-off.png");

        textureLlave1 = new Texture(llaveFileHandle);
        textureLlave2 = new Texture(llaveFileHandle);
        textureLlave3 = new Texture(llaveFileHandle);
        textureLlaveSalida = new Texture(llaveFileHandle);

        textureLifebar_back = new Texture(lifebar_backHandle);
        textureLifebar_front = new Texture(lifebar_frontHandle);

        textureArrowSign = new TextureAtlas("graficos/controles/escenario/escenario.txt");
        textureMarcadores = new TextureAtlas("graficos/controles/escenario/escenario.txt");

        textureCalabazinCorriendoDerecha = new TextureAtlas("graficos/controles/calabazin/calabazin_corriendo_derecha.txt");
        animacionCalabazinCorriendoDerecha = new Animation(0.09f, textureCalabazinCorriendoDerecha.getRegions());

        textureCalabazinCorriendoIzquierda = new TextureAtlas("graficos/controles/calabazin/calabazin_corriendo_izquierda.txt");
        animacionCalabazinCorriendoIzquierda = new Animation(0.09f, textureCalabazinCorriendoIzquierda.getRegions());

        textureCalabazinParado= new TextureAtlas("graficos/controles/calabazin/calabazin_parado.txt");
        animacionCalabazinParado = new Animation(0.09f, textureCalabazinParado.getRegions());

        ////enemigos////

        textureAtlasZombieCorriendoDerecha = new TextureAtlas("graficos/controles/Enemigos/zombie_hombre.txt");
        animacionZombieCorriendoDerecha = new Animation(0.09f,textureAtlasZombieCorriendoDerecha.getRegions());

        textureAtlasGhost = new TextureAtlas("graficos/controles/Enemigos/ghost.txt");
        animacionGhostAnimacion = new Animation(0.09f,textureAtlasGhost.getRegions());



        textureCorazon = new Texture(corazonFileHandle);
        texturePuntoNegro = new Texture(puntoNegroFileHandle);
        textureControles = new Texture(controlesFileHandle);
        texturePausa = new Texture(pausaFileHandle);
        textureSair = new Texture(sairFileHandle);
        textureSonidoOn = new Texture(sonidoOnFileHandle);
        textureSonidoOff = new Texture(sonidoOffFileHandle);


    }//fin cargarTexturas


    /**
     * Metodo encargado de liberar todas las texturas
     */
    public static void liberarTexturas(){
//        textureCalabazin.dispose();
        textureCorazon.dispose();

        //liberar calabazin///
        textureCalabazinCorriendoDerecha.dispose();
        textureCalabazinCorriendoIzquierda.dispose();
        textureCalabazinParado.dispose();

        //liberar enemigos///
        textureAtlasZombieCorriendoDerecha.dispose();
        textureAtlasGhost.dispose();

        //liberar controloes//
        textureLifebar_back.dispose();
        textureLifebar_front.dispose();
        texturePuntoNegro.dispose();
        textureControles.dispose();
        textureCorazon.dispose();
        textureLlave1.dispose();
        textureLlave2.dispose();
        textureLlave3.dispose();
        textureLlaveSalida.dispose();
        textureSonidoOff.dispose();
        textureSonidoOn.dispose();
    }
}
