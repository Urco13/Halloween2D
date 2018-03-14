package com.my02fgc.game.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.my02fgc.game.game.AssetsJuego;
import com.my02fgc.game.modelo.Calabazin;
import com.my02fgc.game.modelo.Controles;
import com.my02fgc.game.modelo.Ghost;
import com.my02fgc.game.modelo.Mundo;
import com.my02fgc.game.modelo.Zombies;

import controlador.ControladorJuego;

public class RenderJuego implements InputProcessor {

    private final BitmapFont bitMapFont;
    private final StringBuilder sbuffer;
    private SpriteBatch batch;
    private OrthographicCamera camara2d;
    private Vector3 temporal;
    private ShapeRenderer shaperender;
    private Mundo miMundo;
    private float crono;
    private float crono2;
    private float crono3;
    private float crono4;
    private boolean debugger=true;

    private ControladorJuego controladorJuego;
    private TiledMap map;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public RenderJuego(Mundo mundo) {
        this.miMundo=mundo;
        this.crono=0;
        this.crono2=0;
        this.crono3=0;
        this.crono4=0;
        this.camara2d = new OrthographicCamera();
        this.batch = new SpriteBatch();
        this.shaperender = new ShapeRenderer();
        this.temporal = new Vector3();

        Gdx.input.setInputProcessor(this);


        //Libgdx by default, creates a BitmapFont using the default 15pt Arial font included in the libgdx JAR file.
        //Using FreeTypeFont, it is possible so create fonts with a desired size on the fly.
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/fuenteterror.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)(60 * Mundo.PROPORCION_REAL_MUNDO_ANCHO);
        this.bitMapFont = generator.generateFont(parameter); // font size in pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        sbuffer = new StringBuilder();
        this.bitMapFont.setColor(Color.WHITE);


       /* map = new TmxMapLoader().load("graficos/controles/escenario/mapajuego/mapajuego.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);*/
    }//fin renderJuego



    private void dibujarCalabazin(){
        Calabazin calabazin = miMundo.getCalabazin();
        crono2 += Gdx.graphics.getDeltaTime();

        if(calabazin.getVelocidadeX()==0){
            batch.draw((TextureRegion) AssetsJuego.animacionCalabazinParado.getKeyFrame(crono2,true), calabazin.getPosicion().x, calabazin.getPosicion().y, calabazin.getTamano().x, calabazin.getTamano().y);
        }else if (calabazin.getVelocidadeX()<0){
            batch.draw((TextureRegion) AssetsJuego.animacionCalabazinCorriendoIzquierda.getKeyFrame(crono2,true), calabazin.getPosicion().x, calabazin.getPosicion().y, calabazin.getTamano().x, calabazin.getTamano().y);
        }else if (calabazin.getVelocidadeX()>0){
            batch.draw((TextureRegion) AssetsJuego.animacionCalabazinCorriendoDerecha.getKeyFrame(crono2,true), calabazin.getPosicion().x, calabazin.getPosicion().y, calabazin.getTamano().x, calabazin.getTamano().y);
        }


        // calabazin.setPosicion(calabazin.getPosicion().x, calabazin.getPosicion().y);
       /*Sprite personaje = AssetsJuego.textureCalabazinCorriendoDerecha.createSprite("Run (1)");
       personaje.draw(batch);*/

    }//fin dibujar calabazin

    private void dibujarZombie1(){
        Zombies zombies1 = miMundo.getZombies1();
        crono3 += Gdx.graphics.getDeltaTime();
//        System.out.println(zombies1.getVelocidadeX());
            zombies1.setVelocidadeX(120);
        if(zombies1.getVelocidadeX()>0){
            batch.draw((TextureRegion) AssetsJuego.animacionZombieCorriendoDerecha.getKeyFrame(crono2,true), zombies1.getPosicion().x, zombies1.getPosicion().y, zombies1.getTamano().x, zombies1.getTamano().y);

        }


        // calabazin.setPosicion(calabazin.getPosicion().x, calabazin.getPosicion().y);
       /*Sprite personaje = AssetsJuego.textureCalabazinCorriendoDerecha.createSprite("Run (1)");
       personaje.draw(batch);*/

    }//fin dibujar calabazin


    public void dibujarGhost(){
        Ghost ghost = miMundo.getGhost();
        crono4+=Gdx.graphics.getDeltaTime();
        batch.draw((TextureRegion) AssetsJuego.animacionGhostAnimacion.getKeyFrame(crono4,true), ghost.getPosicion().x, ghost.getPosicion().y, ghost.getTamano().x, ghost.getTamano().y);

    }//fin dibujarGhost


    private void dibujarControles(){

        batch.draw(AssetsJuego.textureControles, Controles.control.x, Controles.control.y, Controles.control.width, Controles.control.height);

        batch.draw(AssetsJuego.texturePausa, Controles.CONTROL_PAUSE.x,Controles.CONTROL_PAUSE.y,Controles.CONTROL_PAUSE.width,Controles.CONTROL_PAUSE.height);

        batch.draw(AssetsJuego.textureSair, Controles.CONTROL_SAIR.x,Controles.CONTROL_SAIR.y,Controles.CONTROL_SAIR.width,Controles.CONTROL_SAIR.height);

        batch.draw(AssetsJuego.textureLifebar_back,690,10,220,41);
        batch.draw(AssetsJuego.textureLifebar_front,700,10, miMundo.getCalabazin().getVida(),41);

        batch.setProjectionMatrix(camara2d.combined);
        shaperender.setProjectionMatrix(camara2d.combined);
        camara2d.update();

    }//fin dibujarControles

    private void dibujarVidas(){
        Texture textura1;
        Texture textura2;
        Texture textura3;


            if(miMundo.getCalabazin().getNumMuertes()==0){
                textura1 = AssetsJuego.textureCorazon;
                textura2 = AssetsJuego.textureCorazon;
                textura3 = AssetsJuego.textureCorazon;
                batch.draw(textura1,Controles.POSVIDAS1,0,60,60);
                batch.draw(textura2,Controles.POSVIDAS2,0,60,60);
                batch.draw(textura3,Controles.POSVIDAS3,0,60,60);
            }else if(miMundo.getCalabazin().getNumMuertes()==1){
                textura2 = AssetsJuego.textureCorazon;
                textura3 = AssetsJuego.textureCorazon;
                batch.draw(textura2,Controles.POSVIDAS2,0,60,60);
                batch.draw(textura3,Controles.POSVIDAS3,0,60,60);
            }else if(miMundo.getCalabazin().getNumMuertes()==2){
                textura3 = AssetsJuego.textureCorazon;
                batch.draw(textura3,Controles.POSVIDAS3,0,60,60);
            }

        }

        private void dibujarLlaves(){
            if (miMundo.isLlave1()){
                batch.draw(AssetsJuego.textureLlave1,440,0,60,60);
            }
            if (miMundo.isLlave2()){
                batch.draw(AssetsJuego.textureLlave2,380,0,60,60);
            }
            if (miMundo.isLlave3()){
                batch.draw(AssetsJuego.textureLlave3,320,0,60,60);
            }
            if (miMundo.isLlaveSalida()){
                batch.draw(AssetsJuego.textureLlaveSalida,240,0,60,60);
            }
        }

    public OrthographicCamera getCamara2d(){
        return this.camara2d;
    }


    private void dibujarTiempo(){
        // Borrar texto
        sbuffer.setLength(0);
        sbuffer.append(miMundo.getCronometro());//cronometro
        // cpy() needed to properly set afterwards because calling set() seems to modify kept matrix, not replaces it
        Matrix4 originalMatrix = batch.getProjectionMatrix().cpy();
        batch.setProjectionMatrix(originalMatrix.cpy().scale(1/Mundo.PROPORCION_REAL_MUNDO_ANCHO, 1/Mundo.PROPORCION_REAL_MUNDO_ALTO,1));
        this.bitMapFont.draw(batch, sbuffer, 200, 70);
        this.bitMapFont.setColor(Color.RED);
        batch.setProjectionMatrix(originalMatrix); //revert projection
    }

    /**
     * Debuxa todos os elementos graficos da pantalla
     *
     * @param delta
     *            : tempo que pasa entre un frame e o seguinte.
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        miMundo.getTiledMapRenderer().setView(camara2d);
        miMundo.getTiledMapRenderer().render();

        batch.begin();
        dibujarCalabazin();
        dibujarZombie1();
        dibujarControles();
        dibujarVidas();
        dibujarTiempo();
        dibujarGhost();
        dibujarLlaves();
        batch.end();

        batch.setProjectionMatrix(camara2d.combined);

      /*  if (debugger){
            debugger(delta);
        }*/

    }

    public void resize(int width, int height) {

        camara2d.setToOrtho(false, Mundo.TAMANO_MUNDO_ANCHO, Mundo.TAMANO_MUNDO_ALTO);
        camara2d.update();

        batch.setProjectionMatrix(camara2d.combined);
        shaperender.setProjectionMatrix(camara2d.combined);
    }//fin resize

    public void dispose() {

        Gdx.input.setInputProcessor(null);
        batch.dispose();
    }


/*    private void debugger(float delta){
        Calabazin calabazin = miMundo.getCalabazin();
        Zombies zombies1 = miMundo.getZombies1();
        shaperender.begin(ShapeRenderer.ShapeType.Line);
        shaperender.setColor(Color.RED);

        shaperender.rect(Controles.control.x,Controles.getControl().y,Controles.getControl().getWidth(),Controles.getControl().getHeight());

        shaperender.rect(calabazin.getPosicion().x, calabazin.getPosicion().y, calabazin.getRectangulo().width, calabazin.getRectangulo().height);

        shaperender.rect(zombies1.getPosicion().x, zombies1.getPosicion().y, zombies1.getRectangulo().width, zombies1.getRectangulo().height);

        shaperender.end();

    }//fin debugger*/


    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub

        temporal.set(screenX,screenY,0);
        camara2d.unproject(temporal);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }


}