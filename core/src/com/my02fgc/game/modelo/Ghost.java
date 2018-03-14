package com.my02fgc.game.modelo;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by urco1 on 19/01/2018.
 */

public class Ghost extends Personaje {

    public Vector2 direccion,temporal;
    public Vector2 tamano,posicion;
    public float velocidade,velocidade_max;
    public Vector2 puntoDestino;

    public Ghost(Vector2 posicion, Vector2 tamano, float velocidade_max) {
        super(posicion, tamano, velocidade_max);

        this.tamano = tamano;
        this.posicion = posicion;
        this.velocidade_max = velocidade_max;

        temporal = new Vector2();
        direccion = new Vector2(0,0);
        puntoDestino = new Vector2();
    }

    public void update(float delta){

        temporal.set(direccion);
        posicion.add(temporal.scl(velocidade_max*delta));
        actualizarRectangulo();

    }
}
