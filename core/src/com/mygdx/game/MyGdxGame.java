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
    private Character loli2;
    
    
    //put objects here
    //------------------
    
    
    //------------------
    
    
    //Add update functions in here
    private void update() {
    	loli.moveX((int)(Math.random()*4)-2);
    	loli.moveY((int)(Math.random()*4)-2);
    	
    	loli2.moveX((int)(Math.random()*4)-1);
    	loli2.moveY((int)(Math.random()*4)-1);
    	

    	
    	if(loli.getX() > 1280) {
    		loli.setX(0);
    	}
    	else if(loli.getX() < 0 ) {
    		loli.setX(1280);    		
    	}

    	if(loli.getY() > 800) {
    		loli.setY(0);
    	}
    	else if(loli.getY() < 0 ) {
    		loli.setY(800);
    	}
    	
    	if(loli2.getX() > 1280) {
    		loli2.setX(0);
    	}
    	else if(loli2.getX() < 0 ) {
    		loli2.setX(1280);    		
    	}

    	if(loli2.getY() > 800) {
    		loli2.setY(0);
    	}
    	else if(loli2.getY() < 0 ) {
    		loli2.setY(800);
    	}
    	
    }
    
    @Override
    public void create() {        
        batch = new SpriteBatch();    
        font = new BitmapFont();
        loli = new Character(0,0);
        loli2 = new Character(600,600);
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
        
        int muu = 0;
        
        double x = Math.pow((loli.getX() - loli2.getX()),2);
        double y = Math.pow((loli.getY() - loli2.getY()),2);
    	
        double distance = Math.sqrt(x+y);
        if(distance < 10) {
        	System.out.println("OMAEWA MO SHINDEIRU!");
        	System.exit(0);
        }
        	
        
    	batch.begin();
    	
    	//left
    	if(loli.goingRight())
    		font.draw(batch, "(>owo)>", loli.getX(), loli.getY());
    	else
    		font.draw(batch, "<(owo<)", loli.getX(), loli.getY());
        //right
    	if(loli2.goingRight())
    		font.draw(batch, "xD", loli2.getX(), loli2.getY());
    	else
    		font.draw(batch, "Cx", loli2.getX(), loli2.getY());
        
        muu++;
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
