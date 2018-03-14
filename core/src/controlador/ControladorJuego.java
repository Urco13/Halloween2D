package controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.my02fgc.game.game.Audio;
import com.my02fgc.game.modelo.Calabazin;
import com.my02fgc.game.modelo.Ghost;
import com.my02fgc.game.modelo.Mundo;
import com.my02fgc.game.modelo.Nave;
import com.my02fgc.game.modelo.Zombies;

import java.util.HashMap;


/**
 * Created by urco1 on 16/01/2018.
 */

public class ControladorJuego {
    private Mundo miMundo;
    private Calabazin calabazin;
    private Ghost ghost;
    private Nave nave;

    private Preferences prefAudio;
    private Boolean audio;
    private Zombies zombies1;
    private boolean finJuego=false;

    public ControladorJuego(Mundo mundo) {
        this.miMundo = mundo;
        this.calabazin = miMundo.getCalabazin();
        this.zombies1 = miMundo.getZombies1();
        this.ghost = miMundo.getGhost();


        //sonido//
        prefAudio = Gdx.app.getPreferences("prefSound");
        audio = prefAudio.getBoolean("soundOn");


    }//fin constructor


    ///controlar enemigos///
    private void controlarZombi1(float delta){
        zombies1.update(delta);
           zombies1.setVelocidadeX(100);
           if (Intersector.overlaps(calabazin.getRectangulo(),zombies1.getRectangulo())){
               int vida = miMundo.getCalabazin().getVida();
               vida -=200;
               miMundo.getCalabazin().setVida(vida);
               muertePesonaje();
           }
    }


    /////////////Creamos el control de la teclas////////////////////////
    public enum Keyboard {
        ESQUERDA, DEREITA, ARRIBA, ABAIXO
    }

    HashMap<Keyboard, Boolean> keys = new HashMap<Keyboard, Boolean>();

    {
        keys.put(Keyboard.ESQUERDA, false);
        keys.put(Keyboard.DEREITA, false);
        keys.put(Keyboard.ARRIBA, false);
        keys.put(Keyboard.ABAIXO, false);
    }

    ;

    /**
     * Modifica o estado do mapa de teclas e pon a true
     *
     * @param keyboard: keyboard pulsada
     */
    public void pulsarTecla(Keyboard keyboard) {
        keys.put(keyboard, true);
    }

    /**
     * Modifica o estado do mapa de teclas e pon a false
     *
     * @param keyboard: keyboard liberada
     */
    public void liberarTecla(Keyboard keyboard) {
        keys.put(keyboard, false);
    }

    public HashMap<Keyboard, Boolean> getKeys() {
        return keys;
    }

    /////////////////controlar calabazin//////////////////////////////
    private void controlarMovimientoCalabazin() {

        if (!(keys.get(Keyboard.ESQUERDA)) && (!(keys.get(Keyboard.DEREITA)))){
            calabazin.setVelocidadeX(0);
        }

        if (!(keys.get(Keyboard.ARRIBA)) && (!(keys.get(Keyboard.ABAIXO)))) {
            calabazin.setVelocidadeY(0);
        }
        if (keys.get(Keyboard.DEREITA)){
            calabazin.setVelocidadeX(calabazin.velocidadMaxima);

        }
        //this.sonidoMovimiento();
        if (keys.get(Keyboard.ESQUERDA)){
            calabazin.setVelocidadeX(-calabazin.velocidadMaxima);
        }
        //this.sonidoMovimiento();

        if (keys.get(Keyboard.ARRIBA)) {
            calabazin.setVelocidadeY(calabazin.velocidadMaxima);
//        this.sonidoMovimiento();
        }
        if (keys.get(Keyboard.ABAIXO)) {
            calabazin.setVelocidadeY(-calabazin.velocidadMaxima);
        }


    }//fin controlarMovimientoCalabazin


    ///////////////controlar colisiones calabazin/////////////////////////////
    private void controlarColisionesCalabazin(float delta) {
        // Actualiza Calabazin
        calabazin.update(delta);

        //Evitar que salga de la pantalla
        int ancho = (Integer) miMundo.getMap().getProperties().get("width");
        // Impide que se mova fora dos límites da pantalla
        if (calabazin.getPosicion().x <= 0) {
            calabazin.setPosicion(0, calabazin.getPosicion().y);
        } else {
            if (calabazin.getPosicion().x >= (ancho*64) - calabazin.getTamano().x) {
                calabazin.setPosicion((ancho*64) - calabazin.getTamano().x, calabazin.getPosicion().y);
            }

        }



        //controlar muros///
        MapObjects muros = miMundo.getMap().getLayers().get("CapaObjetosMuros").getObjects();
        for (MapObject muro : muros) {
            Rectangle rectanguloMuro = ((RectangleMapObject) muro).getRectangle();
            if (Intersector.overlaps(rectanguloMuro, calabazin.getRectangulo())) {    // Pulsar Juego nuevo

                if (rectanguloMuro.getY()>=calabazin.getPosicion().y){
                        calabazin.setPosicion(calabazin.getPosicion().x,calabazin.getPosicion().y-5);

                    }
                if (rectanguloMuro.getY()<=calabazin.getPosicion().y){
                    calabazin.setPosicion(calabazin.getPosicion().x,calabazin.getPosicion().y+5);

                }

                if (rectanguloMuro.getX()>=calabazin.getPosicion().x){
                    calabazin.setPosicion(calabazin.getPosicion().x-5,calabazin.getPosicion().y);

                }
                if (rectanguloMuro.getX()<=calabazin.getPosicion().x){
                    calabazin.setPosicion(calabazin.getPosicion().x+5,calabazin.getPosicion().y);

                }

            }
        }//fin muros

        //controlar puertas//
        MapObject puerta1 = miMundo.getMap().getLayers().get("CapaObjetosPuertas").getObjects().get("puerta1");
        Rectangle rectanguloPuerta1 = ((RectangleMapObject)puerta1).getRectangle();
            if (Intersector.overlaps(rectanguloPuerta1,calabazin.getRectangulo())){
                    if (!miMundo.isLlave1()){
                        puertas();
                    }
            }

        MapObject puerta2 = miMundo.getMap().getLayers().get("CapaObjetosPuertas").getObjects().get("puerta2");
        Rectangle rectanguloPuerta2 = ((RectangleMapObject)puerta2).getRectangle();
        if (Intersector.overlaps(rectanguloPuerta2,calabazin.getRectangulo())){
            if (!miMundo.isLlave2()){
                puertas();
            }
        }

        MapObject puerta3 = miMundo.getMap().getLayers().get("CapaObjetosPuertas").getObjects().get("puerta3");
        Rectangle rectanguloPuerta3 = ((RectangleMapObject)puerta3).getRectangle();
        if (Intersector.overlaps(rectanguloPuerta3,calabazin.getRectangulo())){
            if (!miMundo.isLlave3()){
                puertas();
            }
        }

        MapObject puertaSalida = miMundo.getMap().getLayers().get("CapaObjetosPuertas").getObjects().get("puertaSalida");
        Rectangle rectanguloPuertaSalida = ((RectangleMapObject)puertaSalida).getRectangle();
        if (Intersector.overlaps(rectanguloPuertaSalida,calabazin.getRectangulo())){
            if (!miMundo.isLlaveSalida()){
                puertas();
            }
        }

        //fin juego//
        MapObject finjuego = miMundo.getMap().getLayers().get("finjuego").getObjects().get("finjuego");
        Rectangle rectanguloFinJuego = ((RectangleMapObject)finjuego).getRectangle();
        if (Intersector.overlaps(rectanguloFinJuego,calabazin.getRectangulo())){

            finJuego = true;

        }

        //controlar cofres//
        MapObject cofre1 = miMundo.getMap().getLayers().get("CapaObjetosItems").getObjects().get("cofreLlave1");
        Rectangle rectanguloCofre1 = ((RectangleMapObject)cofre1).getRectangle();
        if (Intersector.overlaps(rectanguloCofre1,calabazin.getRectangulo())){
            miMundo.setLlave1(true);
        }

        MapObject cofre2 = miMundo.getMap().getLayers().get("CapaObjetosItems").getObjects().get("cofreLlave2");
        Rectangle rectanguloCofre2 = ((RectangleMapObject)cofre2).getRectangle();
        if (Intersector.overlaps(rectanguloCofre2,calabazin.getRectangulo())){
            miMundo.setLlave2(true);
        }

        MapObject cofre3 = miMundo.getMap().getLayers().get("CapaObjetosItems").getObjects().get("cofreLlave3");
        Rectangle rectanguloCofre3 = ((RectangleMapObject)cofre3).getRectangle();
        if (Intersector.overlaps(rectanguloCofre3,calabazin.getRectangulo())){
            miMundo.setLlave3(true);
        }

        MapObject cofreLlaveSalida = miMundo.getMap().getLayers().get("CapaObjetosItems").getObjects().get("cofreLlaveSalida");
        Rectangle rectanguloCofreLlaveSalida = ((RectangleMapObject)cofreLlaveSalida).getRectangle();
        if (Intersector.overlaps(rectanguloCofreLlaveSalida,calabazin.getRectangulo())){
            miMundo.setLlaveSalida(true);
        }

        MapObject pocionVida = miMundo.getMap().getLayers().get("CapaObjetosItems").getObjects().get("pocionVida");
        Rectangle rectanguloPocionVida = ((RectangleMapObject)pocionVida).getRectangle();
        if (Intersector.overlaps(rectanguloPocionVida,calabazin.getRectangulo())){
            calabazin.setVida(200);

        }

        //controlar VelocidadNormal///
        MapObjects velocidadNormal = miMundo.getMap().getLayers().get("CapaVelocidadNormal").getObjects();
        for (MapObject veloc : velocidadNormal) {
            Rectangle rectanguloVeloc = ((RectangleMapObject) veloc).getRectangle();
            if (Intersector.overlaps(rectanguloVeloc, calabazin.getRectangulo())) {    // Pulsar Juego nuevo
                calabazin.setVelocidadMaxima(100);
            }
        }

        //controlar trampaArena///
        MapObjects arenasLento = miMundo.getMap().getLayers().get("CapaObjetosArena").getObjects();
        for (MapObject arena : arenasLento) {
            Rectangle rectanguloArena = ((RectangleMapObject) arena).getRectangle();
            if (Intersector.overlaps(rectanguloArena, calabazin.getRectangulo())) {    // Pulsar Juego nuevo
                calabazin.setVelocidadMaxima(20);
            }
        }


        //controlar pinchos///
        MapObjects pinchos = miMundo.getMap().getLayers().get("trampaPinchos").getObjects();
        for (MapObject pincho : pinchos) {

            Rectangle rectanguloPincho = ((RectangleMapObject) pincho).getRectangle();
            if (Intersector.overlaps(rectanguloPincho, calabazin.getRectangulo())) {    // Pulsar Juego nuevo
                int vida = miMundo.getCalabazin().getVida();
                vida -=1;
                miMundo.getCalabazin().setVida(vida);
                muertePesonaje();
            }
        }//fin pinchos


        //controlar trampaVelocidad///
        MapObjects velocidad = miMundo.getMap().getLayers().get("trampaVelocidad").getObjects();
        for (MapObject veloc : velocidad) {
            Rectangle rectanguloVeloc = ((RectangleMapObject) veloc).getRectangle();
            if (Intersector.overlaps(rectanguloVeloc, calabazin.getRectangulo())) {    // Pulsar Juego nuevo
                    calabazin.setVelocidadMaxima(250);
            }
        }

        //controlar cuando te toca el ghost
      if (Intersector.overlaps(ghost.getRectangulo(), calabazin.getRectangulo())){

          int vida = miMundo.getCalabazin().getVida();
          vida -=2;
          miMundo.getCalabazin().setVida(vida);
          muertePesonaje();
      }



    }//fin controlarColisionesCalabazin

    private void controlarGhost(float delta){

        ghost.puntoDestino.set(new Vector2(new Vector2(calabazin.getPosicion().x, calabazin.getPosicion().y)));
        Vector2 direccion = ghost.puntoDestino.cpy().sub(ghost.posicion);
        ghost.direccion.set(direccion.nor());
        ghost.update(delta);
    }//fin controlarGhost

    /**
     * Vai chamar a todos os métodos para mover e controlar os personaxes
     * Tamén xestionará os eventos producidos polo usuario e que veñen dende a clase PantallaXogo
     *
     * @param delta
     */
    public void update(float delta) {
        controlarColisionesCalabazin(delta);
        controlarMovimientoCalabazin();
        controlarZombi1(delta);
        miMundo.updateCronometro(delta);
        controlarGhost(delta);

    }//fin update

    private void sonidoMovimiento() {
        /*Es necesario controlar velocidad de x e y ya que en el método procesar entradas
        * de ControladorJuego se ejecutan siempre ambos métodos. Por ejemplo, cuando se
        * pulsa a la derecha se ejecuara setVelocidadX, pero además, al valer ARRIBA y ABAJO false
        * se ejecutará también setVelocidadY*/
     /*   if ((calabazin.getVelocidadeX() == 0) && (calabazin.getVelocidadeY() == 0)) {
           *//* if (Audio.musicMovimiento.isPlaying())
                Audio.musicMovimiento.stop();*//*
        } else {*/
           /* if (!Audio.musicMovimiento.isPlaying())

                Audio.musicMovimiento.play();
//            Audio.musicMovimiento.setVolume(0.5f);
            Audio.bajarMusica(audio);*/
//        }
    }//fin sonidoMovimiento


   public void muertePesonaje(){
       if (miMundo.getCalabazin().getVida()<=0){
           calabazin.inicializarCalabazin();
           calabazin.setNumVidas(Calabazin.TIPOS_VIDA.MUERTO);
           ghost.setPosicion(300,400);
           zombies1.setPosicion(300,100);
           Audio.musicMuerte.play();


       }
   }

   public void puertas(){
       MapObjects puertas = miMundo.getMap().getLayers().get("CapaObjetosPuertas").getObjects();
       for (MapObject muro : puertas) {
           Rectangle rectanguloPuertas = ((RectangleMapObject) muro).getRectangle();
           if (Intersector.overlaps(rectanguloPuertas, calabazin.getRectangulo())) {    // Pulsar Juego nuevo

               if (rectanguloPuertas.getY()>=calabazin.getPosicion().y){
                   calabazin.setPosicion(calabazin.getPosicion().x,calabazin.getPosicion().y-5);

               }
               if (rectanguloPuertas.getY()<=calabazin.getPosicion().y){
                   calabazin.setPosicion(calabazin.getPosicion().x,calabazin.getPosicion().y+5);

               }

               if (rectanguloPuertas.getX()>=calabazin.getPosicion().x){
                   calabazin.setPosicion(calabazin.getPosicion().x-5,calabazin.getPosicion().y);

               }
               if (rectanguloPuertas.getX()<=calabazin.getPosicion().x){
                   calabazin.setPosicion(calabazin.getPosicion().x+5,calabazin.getPosicion().y);

               }

           }
       }//fin muros
   }

    public boolean isFinJuego() {
        return finJuego;
    }
}//fin class
