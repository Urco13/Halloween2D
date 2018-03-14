package com.my02fgc.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.my02fgc.game.game.Audio;
import com.my02fgc.game.game.HighScores;
import com.my02fgc.game.game.Juego;
import com.my02fgc.game.game.Utiles;
import com.my02fgc.game.modelo.Controles;
import com.my02fgc.game.modelo.Mundo;
import com.my02fgc.game.renderer.RenderJuego;

import controlador.ControladorJuego;

/**
 * Created by urco1 on 09/01/2018.
 */

public class PantallaJuego implements Screen, InputProcessor {

    private boolean pause;
    private boolean finJuego;
    private boolean salir;
    private Juego miJuego;
    private Mundo mundo;
    private RenderJuego renderJuego;
    private ControladorJuego controladorJuego;
    private Preferences prefAudio;
    private Boolean audio;
    private int puntuacion=0;


    public PantallaJuego(Juego miJuego) {
        this.mundo = new Mundo();
        this.miJuego = miJuego;
        this.renderJuego = new RenderJuego(mundo);
        this.controladorJuego = new ControladorJuego(mundo);

        Audio.inicializarAudio();
        this.prefAudio = Gdx.app.getPreferences("prefSound");
        this.audio = prefAudio.getBoolean("soundOn");
        System.out.println(audio);


    }//fin constructor


    @Override
    public void show() {
        Utiles.imprimirLog("PantallaJuego", "show", "Show02FGC");
        Gdx.input.setInputProcessor(this);

        pause = false;
        System.out.println("audio show "+audio);

        Audio.musicJuego.play();
        System.out.println(audio);
        if (audio) {
            Audio.controlarAudio(false);
        }



    }//fin show

    @Override
    public void render(float delta) {
        controladorJuego.update(delta);
        renderJuego.render(delta);


        if (mundo.getCalabazin().getNumVidas().size >= 4 || controladorJuego.isFinJuego()) {
            HighScores.anadirPuntuacion(mundo.getCronometro());
            finJuego = true;
        }

        if (finJuego) {
            miJuego.setScreen(new PantallaMarcadores(miJuego));
//            Audio.controlarAudio(!audio);
            return;
        }

        if (salir) {
            salir = false;

        }

        if (pause) {
            System.out.println("audio pause" + audio);
            if (Audio.musicJuego.isPlaying()) {
                Audio.musicJuego.pause();
            }

            miJuego.setScreen(new PantallaPause(miJuego, this));
            return;
        }

    }//fin render

    @Override
    public void resize(int width, int height) {
        Utiles.imprimirLog("PantallaJuego", "resize", "Resize02FGC");
        renderJuego.resize(width, height);
    }

    @Override
    public void pause() {
        Utiles.imprimirLog("PantallaJuego", "pause", "Pause02FGC");
        Gdx.input.setInputProcessor(null);
        if (!finJuego) {
            pause = true;
        }
    }

    @Override
    public void resume() {
        Utiles.imprimirLog("PantallaJuego", "resume", "Resume02FGC");
        Gdx.input.setInputProcessor(this);
        pause = false;
    }

    @Override
    public void hide() {
        Utiles.imprimirLog("PantallaJuego", "hide", "Hide02FGC");
        Gdx.input.setInputProcessor(null);

        if ((finJuego) || (salir)){
            dispose();
        }
    }

    @Override
    public void dispose() {
        Utiles.imprimirLog("PantallaJuego", "dipose", "Dispose02FGC");
        Gdx.input.setInputProcessor(null);
        renderJuego.dispose();
        Audio.musicJuego.dispose();

    }

    //////////////////////////Metodos InputProcesor/////////////////////////////////////
    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        // Liberamos as teclas para que se arrastramos o dedo a outro control sen soltar o anterior non xunte o efecto
        controladorJuego.liberarTecla(ControladorJuego.Keyboard.ABAIXO);
        controladorJuego.liberarTecla(ControladorJuego.Keyboard.ARRIBA);
        controladorJuego.liberarTecla(ControladorJuego.Keyboard.ESQUERDA);
        controladorJuego.liberarTecla(ControladorJuego.Keyboard.DEREITA);

        switch (keycode) {
            case Input.Keys.UP:
                controladorJuego.pulsarTecla(ControladorJuego.Keyboard.ARRIBA);
                break;
            case Input.Keys.DOWN:
                controladorJuego.pulsarTecla(ControladorJuego.Keyboard.ABAIXO);
                break;
            case Input.Keys.LEFT:
                controladorJuego.pulsarTecla(ControladorJuego.Keyboard.ESQUERDA);
                break;
            case Input.Keys.RIGHT:
                controladorJuego.pulsarTecla(ControladorJuego.Keyboard.DEREITA);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        switch (keycode) {
            case Input.Keys.UP:
                controladorJuego.liberarTecla(ControladorJuego.Keyboard.ARRIBA);
                break;
            case Input.Keys.DOWN:
                controladorJuego.liberarTecla(ControladorJuego.Keyboard.ABAIXO);
                break;
            case Input.Keys.LEFT:
                controladorJuego.liberarTecla(ControladorJuego.Keyboard.ESQUERDA);
                break;
            case Input.Keys.RIGHT:
                controladorJuego.liberarTecla(ControladorJuego.Keyboard.DEREITA);
                break;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 temporal = new Vector3(screenX, screenY, 0);
        this.renderJuego.getCamara2d().unproject(temporal);

        Circle dedo = new Circle(temporal.x, temporal.y, 2);

        Rectangle recTemporal = new Rectangle();

        if (Intersector.overlaps(new Circle(temporal.x, temporal.y, 2), Controles.FLECHA_IZQUIERDA)) {
            controladorJuego.pulsarTecla(ControladorJuego.Keyboard.ESQUERDA);
        } else if (Intersector.overlaps(new Circle(temporal.x, temporal.y, 2), Controles.FLECHA_DERECHA)) {
            controladorJuego.pulsarTecla(ControladorJuego.Keyboard.DEREITA);

        } else if (Intersector.overlaps(new Circle(temporal.x, temporal.y, 2), Controles.FLECHA_ARRIBA)) {
            controladorJuego.pulsarTecla(ControladorJuego.Keyboard.ARRIBA);
        } else if (Intersector.overlaps(new Circle(temporal.x, temporal.y, 2), Controles.FLECHA_ABAJO)) {
            controladorJuego.pulsarTecla(ControladorJuego.Keyboard.ABAIXO);

        }


        recTemporal.set(Controles.CONTROL_PAUSE.x, Controles.CONTROL_PAUSE.y, Controles.CONTROL_PAUSE.width, Controles.CONTROL_PAUSE.height);
        if (Intersector.overlaps(dedo, recTemporal)) {
            pause = true;
        }

        recTemporal.set(Controles.CONTROL_SAIR.x, Controles.CONTROL_SAIR.y, Controles.CONTROL_SAIR.width, Controles.CONTROL_SAIR.height);

        if (Intersector.overlaps(dedo, recTemporal)) {
            dispose();
            if (audio){
                prefAudio.putBoolean("soundOn", false);
                audio = prefAudio.getBoolean("soundOn");
                prefAudio.flush();
                System.out.println("audio pantalla juego terminar "+audio);
            }
            System.out.println(audio);
            miJuego.setScreen(new PantallaInicio(miJuego));

        }
        return false;


    }//fin touchDown

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        controladorJuego.liberarTecla(ControladorJuego.Keyboard.ESQUERDA);
        controladorJuego.liberarTecla(ControladorJuego.Keyboard.DEREITA);
        controladorJuego.liberarTecla(ControladorJuego.Keyboard.ARRIBA);
        controladorJuego.liberarTecla(ControladorJuego.Keyboard.ABAIXO);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}