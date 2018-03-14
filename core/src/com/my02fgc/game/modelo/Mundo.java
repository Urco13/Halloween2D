package com.my02fgc.game.modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by dam207 on 11/01/2018.
 */

public class Mundo {

    public static final int TAMANO_MUNDO_ANCHO = 1280;
    public static final int TAMANO_MUNDO_ALTO = 768;
    public static final float PROPORCION_REAL_MUNDO_ANCHO = ((float) Gdx.graphics.getWidth() / Mundo.TAMANO_MUNDO_ANCHO);
    public static final float PROPORCION_REAL_MUNDO_ALTO = ((float) Gdx.graphics.getHeight() / Mundo.TAMANO_MUNDO_ALTO);

    private final static int TEMPO_INICIAL = 0;
    private float cronometro;

    //personaje//
    private Calabazin calabazin;

    //enemigos//
    private Zombies zombies1;
    private Ghost ghost;

    //tilemap//
    private TiledMap map;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    //llaves//
    private boolean llave1=false;
    private boolean llave2=false;
    private boolean llave3=false;
    private boolean llaveSalida=false;

    public Mundo() {

        //crear personaje//
        calabazin = new Calabazin(new Vector2(600, 100), new Vector2(32, 32), 100);

        //crear enemigos//
        zombies1 = new Zombies(new Vector2(300,100),new Vector2(32,32),100);

        ghost = new Ghost(new Vector2(150,400),new Vector2(30,25),20);

        //crear cronometro//
        cronometro = TEMPO_INICIAL;

        //crear tiled map//
        map = new TmxMapLoader().load("graficos/controles/escenario/mapajuego/mapajuego.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

    }//fin constructor



    public TiledMap getMap() {
        return map;
    }

    public OrthogonalTiledMapRenderer getTiledMapRenderer() {
        return tiledMapRenderer;
    }

    public Calabazin getCalabazin() {
        return calabazin;
    }

    public int getCronometro() {
        return (int) cronometro;
    }

    public void setCronometro(float cronometro) {
        this.cronometro = cronometro;
    }

    public void updateCronometro(float delta) {
        cronometro += delta;
    }

    public Zombies getZombies1() {
        return zombies1;
    }
   public Ghost getGhost() {
        return ghost;
    }

    public void setLlave1(boolean llave1) {
        this.llave1 = llave1;
    }

    public void setLlave2(boolean llave2) {
        this.llave2 = llave2;
    }

    public void setLlave3(boolean llave3) {
        this.llave3 = llave3;
    }

    public void setLlaveSalida(boolean llaveSalida) {
        this.llaveSalida = llaveSalida;
    }

    public static int getTamanoMundoAncho() {
        return TAMANO_MUNDO_ANCHO;
    }

    public boolean isLlave1() {
        return llave1;
    }

    public boolean isLlave2() {
        return llave2;
    }

    public boolean isLlave3() {
        return llave3;
    }

    public boolean isLlaveSalida() {
        return llaveSalida;
    }


}
