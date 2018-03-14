package com.my02fgc.game.game;

import com.badlogic.gdx.Game;
import com.my02fgc.game.pantallas.PantallaInicio;

/**
 * Created by urco1 on 09/01/2018.
 */

public class Juego extends Game{


    @Override
    public void create() {

        AssetsJuego.cargarTexturas();
        Audio.inicializarAudio();
        setScreen(new PantallaInicio(this));


    }

    @Override
    public void dispose() {
        super.dispose();
        AssetsJuego.liberarTexturas();
        Audio.liberarAudio();
    }

    @Override
    public void render() {
        super.render();


    }
}
