package com.my02fgc.game.modelo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dam207 on 16/01/2018.
 */

public abstract class Personaje {

    //Velocidade que toma cando se move.
    public float velocidadMaxima;

    //Velocidade actual
    protected float velocidad = 0;

    //Posición
    protected Vector2 posicion;

    //Tamaño
    protected Vector2 tamano;

    private Rectangle rectangulo;

     //Constructor por defecto
    public Personaje(){

    }
    /**
     * Instancia unha personaxe
     *
     * @param posicion
     * @param tamano
     * @param velocidadMaxima
     */
    public Personaje(Vector2 posicion, Vector2 tamano, float velocidadMaxima) {
        this.posicion = posicion;
        this.tamano = tamano;
        this.velocidadMaxima = velocidadMaxima;

        rectangulo = new Rectangle(posicion.x,posicion.y,tamano.x,tamano.y);
    }


    public void setTamanoRectangulo(float width,float height){
        rectangulo.setWidth(width);
        rectangulo.setHeight(height);

    }

    /**
     * Actualiza a posición do rectángulo asociado á forma do gráfico
     *
     */
    public void actualizarRectangulo(){
        rectangulo.x=posicion.x;
        rectangulo.y=posicion.y;

        //getRectangulo().x = getPosicion().x+getTamano().x/4;
        //getRectangulo().y = getPosicion().y+getTamano().y/4;
    }

    /**
     * Devolve o rectángulo asociado
     * @return rectangulo
     */

    public Rectangle getRectangulo(){
        return rectangulo;
    }

    /**
     * Devolve a posicion
     * @return posicion
     */
    public Vector2 getPosicion() {
        return posicion;
    }

    /**
     * Modifica a posición
     * @param posicion: a nova posicion
     */
    public void setPosicion(Vector2 posicion) {
        this.posicion = posicion;
        actualizarRectangulo();
    }

    /**
     * Modifica a posición
     *
     * @param x: nova posición x
     * @param y: nova posición y
     */
    public void setPosicion(float x, float y) {
        posicion.x = x;
        posicion.y = y;
        actualizarRectangulo();
    }


    /**
     * Modifica a velocidad
     * @param velocidad: a nova velocidad
     */
    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * Devolve a velocidad
     * @return velocidad
     */
    public float getVelocidad() {
        return velocidad;
    }

    /**
     * Devolve o tamaño
     * @return tamano
     */
    public Vector2 getTamano() {
        return tamano;
    }


    /**
     * Asina un novo tamano
     * @param tamano: o novo tamano.
     */
    public void setTamano(Vector2 tamano) {
        this.tamano=tamano;
        setTamanoRectangulo(tamano.x,tamano.y);
    }

    /**
     * Modifica o tamano
     *
     * @param width: novo tamano de ancho
     * @param height: novo tamano de alto
     */
    public void setTamano(float width, float height) {
        this.tamano.set(width,height);
        setTamanoRectangulo(width, height);
    }

    public void setVelocidadMaxima(float velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    /**
     * Actualiza a posición en función da velocidad
     * @param delta: tempo entre unha chamada e a seguinte
     */
    public abstract void update(float delta);
}
