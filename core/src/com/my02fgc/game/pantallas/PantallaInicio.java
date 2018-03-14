package com.my02fgc.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.my02fgc.game.game.AssetsJuego;
import com.my02fgc.game.game.Audio;
import com.my02fgc.game.game.Juego;
import com.my02fgc.game.modelo.Calabazin;
import com.my02fgc.game.modelo.Mundo;
import com.my02fgc.game.renderer.RenderJuego;

/**
 * Created by urco1 on 09/01/2018.
 */

public class PantallaInicio implements Screen, InputProcessor {

    private Juego miJuego;
    private OrthographicCamera camara2d;
    private SpriteBatch spritebatch;
    private RenderJuego renderJuego;
    private TiledMap map;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
//    private Rectangle botones[] = {new Rectangle(10, 10, 86, 87), new Rectangle(100, 200, 98, 32)};
    private Calabazin calabazininicio;
    private float crono;

    //musica//
    private Rectangle controlMusica = new Rectangle(10, 10, 50, 50);
    private Preferences prefAudio;
    private Boolean audio;
    private static Texture controlMusicaOn;
    private static Texture controlMusicaOff;


    private final BitmapFont bitMapFont;
    private final StringBuilder sbuffer;

    public PantallaInicio(Juego miJuego) {
        this.miJuego = miJuego;
        this.camara2d = new OrthographicCamera();
        this.spritebatch = new SpriteBatch();
        this.crono=0;
        this.calabazininicio = new Calabazin(new Vector2(350, 20), new Vector2(200, 200), 0);


        //Libgdx by default, creates a BitmapFont using the default 15pt Arial font included in the libgdx JAR file.
        //Using FreeTypeFont, it is possible so create fonts with a desired size on the fly.
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/fuenteterror.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (64 * Mundo.PROPORCION_REAL_MUNDO_ANCHO);
        this.bitMapFont = generator.generateFont(parameter); // font size in pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        //bitMapFont = new BitmapFont();
        sbuffer = new StringBuilder();

        this.controlMusicaOn = AssetsJuego.textureSonidoOn;
        this.controlMusicaOff = AssetsJuego.textureSonidoOff;

        this.prefAudio = Gdx.app.getPreferences("prefSound");
        this.audio = prefAudio.getBoolean("soundOn", true);
        this.prefAudio.flush();


    }//fin constructor



    @Override
    public void show() {
        //Utiles.imprimirLog("PantallaInicio", "show", "Show02FGC");
        Gdx.input.setInputProcessor(this);

        map = new TmxMapLoader().load("graficos/controles/escenario/mapaini/mapainicio4.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        audio = prefAudio.getBoolean("soundOn");

            Audio.musicPresentacion.play();
            Audio.musicPresentacion.setLooping(true);


        System.out.println("show pantalla inicio "+audio);


    }

    @Override
    public void render(float delta) {
        crono += Gdx.graphics.getDeltaTime();
//        camara2d.update();

        tiledMapRenderer.setView(camara2d);
        tiledMapRenderer.render();

        spritebatch.begin();
        bitMapFont.setColor(Color.RED);
        bitMapFont.draw(spritebatch, sbuffer, 30 * Mundo.PROPORCION_REAL_MUNDO_ANCHO, 450 * Mundo.PROPORCION_REAL_MUNDO_ALTO);

        bitMapFont.draw(spritebatch, "Terror en Halloween ",200, 650);

        spritebatch.draw((TextureRegion) AssetsJuego.animacionCalabazinParado.getKeyFrame(crono,true), calabazininicio.getPosicion().x, calabazininicio.getPosicion().y, calabazininicio.getTamano().x, calabazininicio.getTamano().y);
        spritebatch.enableBlending();

        System.out.println("audio render inicio "+audio);

          if (!audio) {
            spritebatch.draw(controlMusicaOn, 10, 10, 50, 50);
        } else {
            spritebatch.draw(controlMusicaOff, 10, 10, 50, 50);

        }
        spritebatch.end();



       /* this.spritebatch.draw(fondo,0,0);
        this.start.draw(spritebatch);
        this.marcadores.draw(spritebatch);*/
    }

    @Override
    public void resize(int width, int height) {
        //Utiles.imprimirLog("PantallaInicio", "resize", "Resize02FGC");
        camara2d.setToOrtho(false, 1280, 704);
        camara2d.update();

        spritebatch.setProjectionMatrix(camara2d.combined);
        spritebatch.disableBlending();

    }

    @Override
    public void pause() {
        // Utiles.imprimirLog("PantallaInicio", "pause", "Pause02FGC");
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resume() {
        //Utiles.imprimirLog("PantallaInicio", "resume", "Resume02FGC");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide() {
        //Utiles.imprimirLog("PantallaInicio", "hide", "Hide02FGC");
        dispose();

    }

    @Override
    public void dispose() {
        //Utiles.imprimirLog("PantallaInicio", "dipose", "Dispose02FGC");
        Gdx.input.setInputProcessor(null);

//        spritebatch.dispose();
        map.dispose();

        Audio.musicPresentacion.dispose();
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
        Vector3 temp = new Vector3(screenX, screenY, 0);
        camara2d.unproject(temp);
        Circle dedo = new Circle(temp.x, temp.y, 2);

        MapObject start = map.getLayers().get("CapaObjetosInicio").getObjects().get("Start");
        Rectangle rectanguloStart = ((RectangleMapObject)start).getRectangle();

        MapObject scores = map.getLayers().get("CapaObjetosInicio").getObjects().get("Scores");
        Rectangle rectanguloScores = ((RectangleMapObject)scores).getRectangle();

        if (Intersector.overlaps(dedo, rectanguloStart)) {
//            Audio.musicPresentacion.stop();
            dispose();
            miJuego.setScreen(new PantallaJuego(miJuego));

        } else if (Intersector.overlaps(dedo, rectanguloScores)) {

            dispose();
            miJuego.setScreen(new PantallaMarcadores(miJuego));
        }

        if (Intersector.overlaps(dedo, controlMusica)) {

            if (audio) {
                Audio.controlarAudio(audio);
                prefAudio.putBoolean("soundOn", false);
                audio = prefAudio.getBoolean("soundOn");
                prefAudio.flush();
                System.out.println(audio);
            } else {
                Audio.controlarAudio(audio);
                prefAudio.putBoolean("soundOn", true);
                audio = prefAudio.getBoolean("soundOn");
                prefAudio.flush();
                System.out.println(audio);
            }
        }

        return false;
    }//fin touchDown

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
