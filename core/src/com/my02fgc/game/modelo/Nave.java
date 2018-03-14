package com.my02fgc.game.modelo;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by urco1 on 16/01/2018.
 */

public class Nave extends Personaje{
    private float tiempo;
    private boolean parado;
    private final int TIEMPO_MOVIENDOSE = 3;

    public Nave(Vector2 posicion, Vector2 tamano, float velocidade_max, boolean parado, float tiempo) {
        super(posicion, tamano, velocidade_max);
        this.parado=false;
        this.tiempo=0;
        setVelocidad(velocidade_max);
    }

    @Override
    public void update(float delta) {
        //setPosicion(getPosicion().x+delta*velocidad, getPosicion().y);

        // Se chega ó final do recorrido cambiamos de dirección
        /*if (posicion.x >=Mundo.TAMANO_MUNDO_ANCHO-getTamano().x){
            setPosicion(Mundo.TAMANO_MUNDO_ANCHO-getTamano().x,getPosicion().y);
            velocidad=-1*velocidad;
        } else if (posicion.x<=0) {
            setPosicion(0,getPosicion().y);
            velocidad=-1*velocidad;
        }*/
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    public boolean isParado() {
        return parado;
    }

    public void setParado(boolean parado) {
        this.parado = parado;
    }

    public int getTIEMPO_MOVIENDOSE() {
        return TIEMPO_MOVIENDOSE;
    }


}
