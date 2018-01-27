package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
    private BitmapFont font;
    private Character loli;
    private Character loli2;
    private Controller controller;
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
    //------------------
    
    
    //Add update functions in here
    private void update() {
    	
    	world.step(1/60f, 6, 2);

    	//System.out.println("Box X: " + loli.getBoxX());
    	//System.out.println("Box Y: " + loli.getBoxY());
    	
    	if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
    		loli.moveX(-1);
    	}
    	if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
    		loli.moveX(1);
    	}
    	if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
    	}
    	
//    	loli.moveX((int)(Math.random()*4)-2);
//    	loli.moveY((int)(Math.random()*4)-2);
//    	
//    	loli2.moveX((int)(Math.random()*4)-1);
//    	loli2.moveY((int)(Math.random()*4)-1);
//    	
//
//    	
//    	if(loli.getX() > 1280) {
//    		loli.setX(0);
//    	}
//    	else if(loli.getX() < 0 ) {
//    		loli.setX(1280);    		
//    	}
//
//    	if(loli.getY() > 800) {
//    		loli.setY(0);
//    	}
//    	else if(loli.getY() < 0 ) {
//    		loli.setY(800);
//    	}
//    	
//    	if(loli2.getX() > 1280) {
//    		loli2.setX(0);
//    	}
//    	else if(loli2.getX() < 0 ) {
//    		loli2.setX(1280);    		
//    	}
//
//    	if(loli2.getY() > 800) {
//    		loli2.setY(0);
//    	}
//    	else if(loli2.getY() < 0 ) {
//    		loli2.setY(800);
//    	}
//    	
    }
    
    @Override
    public void create() {   
    	//Init for Box2D world
    	Box2D.init();
    	world = new World(new Vector2(0,-10f),true);
    	
    	floor = new Block(0,48, world, 1280*RENDER_TO_WORLD, 96*RENDER_TO_WORLD);
    	
    	world = new World(new Vector2(0,-0.1f),true);
    	map = new Map("../core/assets/matrix.txt");
        batch = new SpriteBatch();    
        font = new BitmapFont();
        loli = new Character(0,750, world);
        loli2 = new Character(600,600, world);

        backImage = new Texture(Gdx.files.internal("../core/assets/generalconcept.png"));
        floorTex = new Texture(Gdx.files.internal("../core/assets/placeFloor.png"));
        charTex = new Texture(Gdx.files.internal("../core/assets/placeChar.png")); 
        font.setColor(Color.RED);
        
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
        
        
        
    	batch.begin();
    	batch.draw(backImage,0,0);
    	
    	
    	//left
    	batch.draw(charTex, loli.getBoxX()*WORLD_TO_RENDER, loli.getBoxY()*WORLD_TO_RENDER);
        
    	

    	batch.draw(floorTex, (floor.getX()-floor.getWidth())*WORLD_TO_RENDER,
    				(floor.getY()+floor.getHeight())*WORLD_TO_RENDER);
        
        
        batch.end();
    
        
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
