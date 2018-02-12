package com.mygdx.game;

import java.io.Console;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
    private BitmapFont font;
    private Character loli;
    private Texture backImage;
    private Map map;
    
    private boolean showDebug = false;
    
    //Constants used to go between coordinate systems
    //example: renderX = body.getPosition().x * WORLD_TO_RENDER;
    private final float WORLD_TO_RENDER = 96f;
    private final float RENDER_TO_WORLD = 1/96f;
    
    //put objects here
    //------------------
    private World world;
    //private Texture charTex;

    private ContactListener cl;
    private Contact contact;
    
    private RayHandler rayhandler;
    private PointLight pl;
    
    private Texture[] blockTex;
    
    private Matrix4 cameraBox2D;
    private Box2DDebugRenderer debugRender;
    public OrthographicCamera worldCamera, lightCamera;
    private Camera cam;
    
    
    private Sound testSound;
    private Music testMusic;
    //------------------
    
    private void input() {
    	if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
    		loli.moveX(-2);
    	} 
    	else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
    		loli.moveX(2);
    	}
    	else {
    		loli.moveX(0);
    	}
    
    	if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
    		loli.moveY(7f);
    	}
    	
    	if(Gdx.input.isKeyJustPressed(Input.Keys.D))
    		showDebug = !showDebug;
    	
    	if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
    		testSound.play();
    	}
    	
    }
    
    //Add update functions in here
    private void update() {
    	
    	//Input update
    	input();
    	
    	//Do a step in the physics
    	world.step(1/60f, 3, 3);
    	
    	//Render camera update after stepping is done in physics
    	
    	//Rendering stuff updates according to camera.... l doesn't do that though...
    	//worldCamera.position.set(loli.getBoxX()*WORLD_TO_RENDER, loli.getBoxY()*WORLD_TO_RENDER, 0);
    	worldCamera.position.set((loli.getBoxX()*WORLD_TO_RENDER)+(loli.getWidth()/2), (loli.getBoxY()*WORLD_TO_RENDER)+(loli.getHeight()/2), 0);
    	
    	worldCamera.update();
    	
    	//Change light position to follow player to give sight
    	pl.setPosition((loli.getBoxX()+loli.getWidth()),(loli.getBoxY()+loli.getHeight()*1.5f));
    	//pl.setPosition((loli.getBoxX()*WORLD_TO_RENDER)+(loli.getWidth()/2), (loli.getBoxY()*WORLD_TO_RENDER) +(loli.getHeight()/2));
    	//pl.setPosition(loli.getBoxX()*WORLD_TO_RENDER, loli.getBoxY()*WORLD_TO_RENDER);
    	pl.update();
    	
    	//System.out.println(loli.getBoxX()*WORLD_TO_RENDER);
    	//lightCamera.position.set((loli.getBoxX()*WORLD_TO_RENDER)+(loli.getWidth()/2), (loli.getBoxY()*WORLD_TO_RENDER)+(loli.getHeight()/2), 0);
    	lightCamera.position.set(loli.getBoxX(), loli.getBoxY(), 0);
    	//lightCamera.position.set(loli.getBoxX()*WORLD_TO_RENDER, loli.getBoxY()*WORLD_TO_RENDER, 0);
    	lightCamera.update();
    	loli.update();
    	cam.update(loli.getBoxX(),loli.getBoxY(),loli.getWidth(),loli.getHeight());
    	
    }
    
    @Override
    public void create() {   
    	//Init for Box2D world
    	Box2D.init();
    	world = new World(new Vector2(0,-10f),true);

    	rayhandler = new RayHandler(world);
    	rayhandler.setShadows(true);
    	rayhandler.setAmbientLight(0, 0, 0, 0.0f); 
    	rayhandler.setBlurNum(2);
    	
    	map = new Map("../core/assets/matrix.txt", world, RENDER_TO_WORLD);
    	
    	float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();      
        
        cam = new Camera(w,h);
        
        
        batch = new SpriteBatch();
        font = new BitmapFont();
        loli = new Character(200,400, world, 123*RENDER_TO_WORLD, 192*RENDER_TO_WORLD);
        
        worldCamera = new OrthographicCamera(w, h);
        
//        worldCamera.position.set(loli.getBoxX()*WORLD_TO_RENDER, loli.getBoxY()*WORLD_TO_RENDER, 0);
        //worldCamera.position.set((loli.getBoxX())*WORLD_TO_RENDER, loli.getBoxY()*WORLD_TO_RENDER, 0);
        ///worldCamera.position.set(worldCamera.viewportWidth / 2f, worldCamera.viewportHeight / 2f, 0);
        //System.out.println(loli.getWidth() +" " + loli.getHeight());
        worldCamera.update();
    	
    	lightCamera = new OrthographicCamera(w*RENDER_TO_WORLD, h*RENDER_TO_WORLD);
    	//lightCamera.position.set(loli.getBoxX()*WORLD_TO_RENDER, loli.getBoxY()*WORLD_TO_RENDER, 0);
    	lightCamera.update();
    	
        pl = new PointLight(rayhandler, 256, new Color(1,1,1,0.8f), 600*RENDER_TO_WORLD, 0, 0);
        pl.setSoft(true);
        pl.setStaticLight(true);
        
        backImage = new Texture(Gdx.files.internal("../core/assets/generalconcept.png"));
        //Block textures create:
        blockTex = new Texture[4];
        
        blockTex[0] =  new Texture(Gdx.files.internal("../core/assets/block1.png"));
        blockTex[1] =  new Texture(Gdx.files.internal("../core/assets/block2.png"));
        blockTex[2] =  new Texture(Gdx.files.internal("../core/assets/block3.png"));
        blockTex[3] =  new Texture(Gdx.files.internal("../core/assets/block4.png"));
        
        //charTex = new Texture(Gdx.files.internal("../core/assets/protag.png")); 
        
        
        font.setColor(Color.RED);	
        
        debugRender = new Box2DDebugRenderer();
        
        //audio
        testSound = Gdx.audio.newSound(Gdx.files.internal("../core/assets/heartbeat.mp3"));
        testMusic = Gdx.audio.newMusic(Gdx.files.internal("../core/assets/indoor_ambient.mp3"));
        testMusic.setLooping(true);
        //testMusic.play();
    }

    
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        world.dispose();
        rayhandler.dispose();
    }

    @Override
    public void render() {        
    	Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        update();
        //cameraBox2D = worldCamera.combined.cpy();
        //cameraBox2D.scl(WORLD_TO_RENDER);
        
        //batch.setProjectionMatrix(worldCamera.combined);
        batch.setProjectionMatrix(cam.getWorld().combined);
        
    	batch.begin();
    	batch.draw(backImage,0,0);
    	
    	//left
    	//batch.draw(charTex, loli.getBoxX()*WORLD_TO_RENDER, loli.getBoxY()*WORLD_TO_RENDER);
    	loli.draw(batch, WORLD_TO_RENDER);
    	
    	//Render the map from map object
    	for(int y = map.getMapHeight() - 1; y >= 0 ; y--) {
			for(int x = 0; x < map.getMapWidth(); x++) {
				if(map.getValue(y, x) != 0) {
		    		batch.draw(blockTex[map.getValue(y, x) - 1], x*64, (1280-64)-y*64);
		    	}	
			}
		}
    	
    	
        batch.end();
        
        //rayhandler.setCombinedMatrix(lightCamera);
        rayhandler.setCombinedMatrix(cam.getLight());
        rayhandler.updateAndRender();
        
        if(showDebug) {
        	//debugRender.render(world, cameraBox2D);
        	debugRender.render(world, cam.getDebug());
        }
        	
    }
    
    @Override
    public void resize(int width, int height) {
    	
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
    
    
}
