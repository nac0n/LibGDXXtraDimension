package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
    private BitmapFont font;
    private Character loli;
    private Texture backImage;
    
    //put objects here
    //------------------
    
    
    //------------------
    
    
    //Add update functions in here
    private void update() {
    	System.out.println(loli.getX());
    	System.out.println(loli.getY());
    	loli.moveX(1);
    	loli.moveY(1);
    	System.out.println(loli.getX());
    	System.out.println(loli.getY());
    	
    	if(loli.getX() > 1280) {
    		loli.setX(0);
    	}
    	if(loli.getY() > 800) {
    		loli.setY(0);
    	}
    }
    
    @Override
    public void create() {        
        batch = new SpriteBatch();    
        font = new BitmapFont();
        loli = new Character(0,0);
        backImage = new Texture(Gdx.files.internal("../core/assets/generalconcept.png"));
        font.setColor(Color.RED);
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
    	font.draw(batch, "LOLIS!!", loli.getX(), loli.getY());
        
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
