package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
    private BitmapFont font;
    private Character loli;
    private Texture backImage;
    private Map map;
    
    //Constants used to go between coordinate systems
    //example: renderX = body.getPosition().x * WORLD_TO_RENDER;
    private final float WORLD_TO_RENDER = 96f;
    private final float RENDER_TO_WORLD = 1/96f;
    
    //put objects here
    //------------------
    private World world;
    private Block floor;
    private Texture floorTex;
    private Texture charTex;
    private ContactListener cl;
    private Contact contact;
    
    private Texture[] blockTex;
    
    private Matrix4 cameraBox2D;
    private Box2DDebugRenderer debugRender;
    public OrthographicCamera camera;
    //------------------
    
    
    //Add update functions in here
    private void update() {
    	
    	//System.out.println("Box X: " + loli.getBoxX());
    	//System.out.println("Box Y: " + loli.getBoxY());
    	
    	if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
    		loli.moveX(-1);
    	} 
    	else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
    		loli.moveX(1);
    	}
    	else {
    		loli.moveX(0);
    	}
    	
    	world.step(1/60f, 10, 5);
    	camera.update();
    	
    	if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
    		loli.moveY(7f);
    	}
    
    }
    
    @Override
    public void create() {   
    	//Init for Box2D world
    	Box2D.init();
    	world = new World(new Vector2(0,-10f),true);
    	//floor = new Block(0,48, world, 1280*RENDER_TO_WORLD, 96*RENDER_TO_WORLD);
    	
    	map = new Map("../core/assets/matrix.txt", world, RENDER_TO_WORLD);
    	
    	float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();      
        
        camera = new OrthographicCamera(w, h);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    	camera.update();
    	
        batch = new SpriteBatch();    
        font = new BitmapFont();
        loli = new Character(0,750, world, 123*RENDER_TO_WORLD, 192*RENDER_TO_WORLD);
        //loli2 = new Character(600,600, world);
        
        backImage = new Texture(Gdx.files.internal("../core/assets/generalconcept.png"));
        floorTex = new Texture(Gdx.files.internal("../core/assets/placeFloor.png"));
        
        //Block textures create:
        blockTex = new Texture[4];
        //block1 = new Texture(Gdx.files.internal("../core/assets/block1"));
        
        blockTex[0] =  new Texture(Gdx.files.internal("../core/assets/block1.png"));
        blockTex[1] =  new Texture(Gdx.files.internal("../core/assets/block2.png"));
        blockTex[2] =  new Texture(Gdx.files.internal("../core/assets/block3.png"));
        blockTex[3] =  new Texture(Gdx.files.internal("../core/assets/block4.png"));
        
        charTex = new Texture(Gdx.files.internal("../core/assets/protag.png")); 
        font.setColor(Color.RED);
        
        debugRender = new Box2DDebugRenderer();
        
        fillWorld();
        
    }

    private void fillWorld() {
    	
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {        
    	Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        update();
        cameraBox2D = camera.combined.cpy();
        cameraBox2D.scl(WORLD_TO_RENDER);
        
        batch.setProjectionMatrix(camera.combined);
        
    	batch.begin();
    	batch.draw(backImage,0,0);
    	
    	//left
    	batch.draw(charTex, loli.getBoxX()*WORLD_TO_RENDER, loli.getBoxY()*WORLD_TO_RENDER);
        
    	//batch.draw(floorTex, floor.getX()*WORLD_TO_RENDER, floor.getY()*WORLD_TO_RENDER);
    	
    	//Render floor from map class 
    	for(int y = map.getMapHeight() - 1; y >= 0 ; y--) {
			for(int x = 0; x < map.getMapWidth(); x++) {
				if(map.getValue(y, x) != 0) {
		    		batch.draw(blockTex[map.getValue(y, x) - 1], x*64, (1280-64)-y*64);
		    	}	
			}
		}
    	
    	
        batch.end();
        debugRender.render(world, cameraBox2D);
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
