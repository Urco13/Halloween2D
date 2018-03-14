package com.my02fgc.game.modelo;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by urco1 on 16/01/2018.
 */

public class ElementoMobil extends Personaje {
    public static enum TIPOS_ELEMENTOS {COCHE, AUTOBUS};
    private TIPOS_ELEMENTOS tipo;

    public ElementoMobil(Vector2 posicion, Vector2 tamano, float velocidade_max, TIPOS_ELEMENTOS tipo) {
        super(posicion, tamano, velocidade_max);
        setVelocidad(velocidade_max);
        this.tipo=tipo;
    }

    public TIPOS_ELEMENTOS getTipo() {
        return tipo;
    }

    @Override
    public void update(float delta) {
//        posicion.add((velocidad*delta),0);
//        setPosicion(posicion.add(velocidad*delta,0));
        setPosicion((getPosicion().x+ velocidad *delta),getPosicion().y);

    }
}
