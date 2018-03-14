package com.my02fgc.game.modelo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by dam207 on 16/01/2018.
 */

public class Calabazin extends Personaje {

    private Vector2 velocidad;
    private Array<TIPOS_VIDA> numVidas;
    public static enum TIPOS_VIDA{SALVADO,MUERTO};
    private int vida = 200;

    public Calabazin(Vector2 posicion, Vector2 tamano, float velocidadMaxima) {
        super(posicion, tamano, velocidadMaxima);
        this.velocidad = new Vector2(0,0);
        this.numVidas = new Array<Calabazin.TIPOS_VIDA>();

    }


    public Array<TIPOS_VIDA> getNumVidas() {
        return numVidas;
    }

    public void setNumVidas(TIPOS_VIDA vida) {
        numVidas.add(vida);
    }

    public int getNumVidasSalvadas(){
        int num=0;
        for (TIPOS_VIDA vida : numVidas){
            if (vida == TIPOS_VIDA.SALVADO){
                num++;
            }
        }

        return num;
    }

    public int getNumMuertes(){
        int num=0;
        for (TIPOS_VIDA vida : numVidas){
            if (vida == TIPOS_VIDA.MUERTO){
                num++;
            }
        }

        return num;
    }
    public void inicializarCalabazin(){
        setPosicion(600, 100);
        setVelocidadMaxima(100);
        setTamano(32,32);
        setVida(200);

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
                getPosicion().y+ velocidad.y*delta);
    }//fin update

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }


}//fin class
