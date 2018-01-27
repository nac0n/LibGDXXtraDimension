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
    
    
    //put objects here
    //------------------
    
    
    //------------------
    
    
    //Add update functions in here
    private void update() {
    	
    }
    
    @Override
    public void create() {        
        batch = new SpriteBatch();    
        font = new BitmapFont();
        loli = new Character(0,0);
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
        
    
    	
    	batch.begin();
        font.draw(batch, "LOLIS!!", 300, 800);
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
