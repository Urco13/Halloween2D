package com.my02fgc.game.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.my02fgc.game.game.Audio;
import com.my02fgc.game.game.Juego;
import com.my02fgc.game.game.Utiles;
import com.my02fgc.game.modelo.Mundo;

/**
 * Created by urco1 on 09/01/2018.
 */

public class PantallaPause implements Screen, InputProcessor {

    private boolean pause;
    private boolean finxogo;
    private boolean sair;

    private Juego miJuego;
    private PantallaJuego pantallaJuego;

    private OrthographicCamera camara2d;
    private SpriteBatch spritebatch;
    private static Texture fondo;
    private Game game;

    private TiledMap map;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private final BitmapFont bitMapFont;
    private final StringBuilder sbuffer;

    public PantallaPause(Juego miJuego, PantallaJuego pantallaJuego) {
        this.miJuego = miJuego;
        this.pantallaJuego = pantallaJuego;
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


        fondo = new Texture(Gdx.files.internal("graficos/libgdx_itin1_pantallapause.jpg"));

    }

    @Override
    public void show() {
        Utiles.imprimirLog("PantallaPause", "show", "Show02FGC");
        Gdx.input.setInputProcessor(this);

        map = new TmxMapLoader().load("graficos/controles/escenario/mapmarcadores/marcadores.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void render(float delta) {

        tiledMapRenderer.setView(camara2d);
        tiledMapRenderer.render();
        spritebatch.begin();

        bitMapFont.setColor(Color.RED);
        bitMapFont.draw(spritebatch, sbuffer, 30 * Mundo.PROPORCION_REAL_MUNDO_ANCHO, 450 * Mundo.PROPORCION_REAL_MUNDO_ALTO);
        bitMapFont.draw(spritebatch, "PANTALLA EN PAUSA ",200, 650);
        bitMapFont.draw(spritebatch, "¿TIENES MIEDO"+"\n"+"DE SEGUIR JUGANDO?",200, 500);
        bitMapFont.draw(spritebatch, "NO SEAS COBARDE",200, 400);

//        spritebatch.draw(fondo,0,0, Mundo.TAMANO_MUNDO_ANCHO,Mundo.TAMANO_MUNDO_ALTO);

        spritebatch.end();
    }

    @Override
    public void resize(int width, int height) {
        Utiles.imprimirLog("PantallaPause", "resize", "Resize02FGC");

        camara2d.setToOrtho(false, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO);
        camara2d.update();

        /*spritebatch.setProjectionMatrix(camara2d.combined);
        spritebatch.disableBlending();*/
    }

    @Override
    public void pause() {
        Utiles.imprimirLog("PantallaPause", "pause", "Pause02FGC");
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resume() {
        Utiles.imprimirLog("PantallaPause", "resume", "Resume02FGC");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide() {
        Utiles.imprimirLog("PantallaPause", "hide", "Hide02FGC");
    }

    @Override
    public void dispose() {
        Utiles.imprimirLog("PantallaPause", "dipose", "Dispose02FGC");

        Gdx.input.setInputProcessor(null);

        spritebatch.dispose();
        fondo.dispose();
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
        dispose();
    /*    if (Audio.isInicioPausada()) {
            Audio.musicInicio.play();
            Audio.setInicioPausada(false);
        }
        if (Audio.isJuegoPausada()) {
            Audio.musicJuego.play();
            Audio.setJuegoPausada(false);
        }*/

        miJuego.setScreen(this.pantallaJuego);
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
