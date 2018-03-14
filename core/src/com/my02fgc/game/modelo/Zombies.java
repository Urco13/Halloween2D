package com.my02fgc.game.modelo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by urco1 on 25/02/2018.
 */

public class Zombies extends Personaje {


    private Vector2 velocidad;
    private Array<Zombies.TIPOS_VIDA> numVidas;
    public static enum TIPOS_VIDA{INICIAL,MUERTO};

    public Zombies(Vector2 posicion, Vector2 tamano, float velocidadMaxima) {
        super(posicion, tamano, velocidadMaxima);
        velocidad = new Vector2(0,0);
        numVidas = new Array<Zombies.TIPOS_VIDA>();

    }


    public Array<Zombies.TIPOS_VIDA> getNumVidas() {
        return numVidas;
    }

    public void setNumVidas(Zombies.TIPOS_VIDA vida) {
        numVidas.add(vida);
    }



    public void inicializarZombie(){
        setPosicion(0, 64);
        setVelocidadeX(0);
        setVelocidadeY(0);
        setTamano(96,96);

    }

    public float getVelocidadeX(){
        return velocidad.x;
    }
    public float getVelocidadeY(){
        return velocidad.y;
    }

    public void setVelocidadeX(float x){
        velocidad.x = x;

    }
    public void setVelocidadeY(float y){
        velocidad.y = y;
    }


    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        //setPosicion(getPosicion().x+velocidad.x*delta, getPosicion().y+velocidad.y*delta);

        setPosicion(getPosicion().x+(velocidad.x)*delta,
                getPosicion().y+ velocidad.y);
    }//fin update


}
