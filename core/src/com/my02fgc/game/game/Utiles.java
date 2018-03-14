package com.my02fgc.game.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by dam207 on 09/01/2018.
 */

public class Utiles {
    private static final String LOG = "XOGO2D";

    /**
     * Método para imprimir mensaxes de log no Eclipse.
     * Usado para depuración (debugger)
     * @param clase: nome da clase de onde se chama
     * @param metodo: nome do método de onde se chama
     * @param mensaxe: mensaxe a imprimir
     */
    public static void imprimirLog(String clase, String metodo, String mensaxe){
        Gdx.app.log(LOG, clase + ":"+metodo+":"+mensaxe);
    }
}