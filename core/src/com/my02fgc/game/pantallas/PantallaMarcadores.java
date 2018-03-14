package com.my02fgc.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.my02fgc.game.game.HighScores;
import com.my02fgc.game.game.Juego;
import com.my02fgc.game.game.Utiles;
import com.my02fgc.game.modelo.Mundo;


/**
 * Created by urco1 on 09/01/2018.
 */

public class PantallaMarcadores implements Screen, InputProcessor {

    private final BitmapFont bitMapFont;
    private final StringBuilder sbuffer;
    private boolean pause;
    private boolean finxogo;
    private boolean sair;

    private Juego miJuego;
    //private OrthographicCamera camara2d;
    private SpriteBatch spritebatch;
    private static Texture fondo;
    private OrthographicCamera camara2d;
    private Boolean audio;
    private Preferences prefAudio;
    private TiledMap map;
    private OrthogonalTiledMapRenderer tiledMapRenderer;


    public PantallaMarcadores(Juego miJuego) {
        this.miJuego = miJuego;
        camara2d = new OrthographicCamera();
        spritebatch = new SpriteBatch();


        //Libgdx by default, creates a BitmapFont using the default 15pt Arial font included in the libgdx JAR file.
        //Using FreeTypeFont, it is possible so create fonts with a desired size on the fly.
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/fuenteterror.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (64 * Mundo.PROPORCION_REAL_MUNDO_ANCHO);
        this.bitMapFont = generator.generateFont(parameter); // font size in pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        //bitMapFont = new BitmapFont();
        sbuffer = new StringBuilder();

        prefAudio = Gdx.app.getPreferences("prefSound");
        audio = prefAudio.getBoolean("soundOn");
    }

    @Override
    public void show() {
        Utiles.imprimirLog("PantallaMarcadores", "show", "Show02FGC");
        Gdx.input.setInputProcessor(this);

        map = new TmxMapLoader().load("graficos/controles/escenario/mapmarcadores/marcadores.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tiledMapRenderer.setView(camara2d);
        tiledMapRenderer.render();

        spritebatch.begin();


        bitMapFont.setColor(Color.RED);
        bitMapFont.draw(spritebatch, sbuffer, 30 * Mundo.PROPORCION_REAL_MUNDO_ANCHO, 450 * Mundo.PROPORCION_REAL_MUNDO_ALTO);

        //Código que muestra las puntuaciones

        bitMapFont.draw(spritebatch, "Terror en Halloween ",200, 650);

        bitMapFont.draw(spritebatch, "PRIMERA POSICION: "+HighScores.highscores[0],200, 550);
        bitMapFont.draw(spritebatch, "SEGUNDA POSICION: "+HighScores.highscores[1],200, 450);
        bitMapFont.draw(spritebatch, "TERCERA POSICION: "+HighScores.highscores[2],200, 350);


        spritebatch.end();


        HighScores.load();
     /*   if (!Audio.musicFin.isPlaying()){
            Audio.musicPresentacion.play();
            Audio.bajarMusica(audio);
            Audio.musicPresentacion.setLooping(true);
        }*/



    }

    @Override
    public void resize(int width, int height) {
        //Utiles.imprimirLog("PantallaMarcadores", "resize", "Resize02FGC");

        //camara2d.setToOrtho(false, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO);
        //camara2d.update();

        //spritebatch.setProjectionMatrix(camara2d.combined);
        //spritebatch.disableBlending();
        camara2d.setToOrtho(false, 1280, 704);
        camara2d.update();

    }

    @Override
    public void pause() {
        Utiles.imprimirLog("PantallaMarcadores", "pause", "Pause02FGC");
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resume() {
        Utiles.imprimirLog("PantallaMarcadores", "resume", "Resume02FGC");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide() {
        Utiles.imprimirLog("PantallaMarcadores", "hide", "Hide02FGC");

    }

    @Override
    public void dispose() {
        Utiles.imprimirLog("PantallaMarcadores", "dipose", "Dispose02FGC");
        Gdx.input.setInputProcessor(null);
        spritebatch.dispose();
        bitMapFont.dispose();


    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 temp = new Vector3(screenX,screenY,0);
        camara2d.unproject(temp);
//        Audio.musicPresentacion.stop();
        miJuego.setScreen(new PantallaInicio(miJuego));

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
