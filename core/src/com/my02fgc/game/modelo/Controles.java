package com.my02fgc.game.modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by urco1 on 18/01/2018.
 */


public class Controles {


//    public final static Rectangle FONDO_NEGRO = new Rectangle(0,0,Mundo.TAMANO_MUNDO_ANCHO,12);

    public static Rectangle control = new Rectangle(0,0, 100,100);

    public static Rectangle FLECHA_IZQUIERDA = new Rectangle(Controles.control.x,Controles.control.y+Controles.control.height/3,Controles.control.width/2,Controles.control.height/3);
    public static Rectangle FLECHA_DERECHA = new Rectangle(Controles.control.x+Controles.control.width/2,Controles.control.y+Controles.control.height/3,Controles.control.width/2,Controles.control.height/3);
    public static Rectangle FLECHA_ARRIBA = new Rectangle(Controles.control.x,Controles.control.y+Controles.control.height*2/3,Controles.control.width,Controles.control.height/3);
    public static Rectangle FLECHA_ABAJO = new Rectangle(Controles.control.x,Controles.control.y,Controles.control.width,Controles.control.height/3);



    public final static int POSVIDAS1 = 500;
    public final static int POSVIDAS2 = 560;
    public final static int POSVIDAS3 = 620;

    public final static Rectangle CONTROL_PAUSE = new Rectangle(Gdx.graphics.getWidth()-100,0,50,50);
    public final static Rectangle CONTROL_SAIR = new Rectangle(Gdx.graphics.getWidth(),0,50,50);


    public static Rectangle getControl() {
        return control;
    }

    public void actualizarRectangulo(){
        /*control.x=posicion.x;
        control.y=posicion.y;*/

        //getRectangulo().x = getPosicion().x+getTamano().x/4;
        //getRectangulo().y = getPosicion().y+getTamano().y/4;
    }
    public static void setControl(Rectangle control) {
        Controles.control = control;
    }

}
