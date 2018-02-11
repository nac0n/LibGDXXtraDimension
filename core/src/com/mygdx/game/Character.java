package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Character {
	private int x;
	private int y;
	private int prevX;
	private int prevY;
	
	private Body body;
	
	private TextureRegion[][] animations;
	private int frameTicks = 0;
    private final int aniSpeed = 6;
    private int current_frame = 0;
    
    private int ticks = 0;
    private boolean isFalling = false;
    private boolean isJumping = true;
    
	private float width = 0.5f;
	private float height = 1.0f;
	
	private boolean right;
	private boolean down;
	
	
	Character(int sx, int sy, World world, float width, float height) {
		x = sx;
		y = sy;
		
		this.width = width/2;
		this.height = height/2;
		
		//Creating body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(((float)x)/96 + this.width, ((float)y)/96 + this.height);
		this.body = world.createBody(bodyDef);
		body.setFixedRotation(true);
		
		PolygonShape shape = new PolygonShape();
		Vector2[] vertices = new Vector2[8];
		float heightSlope = 0.02f;
		vertices[0] = new Vector2(-this.width/2, (this.height-heightSlope));
		vertices[1] = new Vector2(-(this.width/2-0.05f), this.height);

		vertices[2] = new Vector2((this.width/2-0.05f), this.height);
		vertices[3] = new Vector2(this.width/2, (this.height-heightSlope));

		vertices[4] = new Vector2(this.width/2, -(this.height-heightSlope));
		vertices[5] = new Vector2((this.width/2-0.05f), -(this.height-heightSlope/2));

		vertices[6] = new Vector2(-(this.width/2-0.05f),-(this.height-heightSlope/2));
		vertices[7] = new Vector2(-this.width/2,-(this.height-heightSlope));
		shape.set(vertices);
		//shape.setAsBox(this.width/2, this.height);
		
		FixtureDef fixt = new FixtureDef();
		fixt.shape = shape;
		fixt.density = 1.f;
		
		this.body.createFixture(fixt);
		
		//this.body.createFixture(shape, 1.f);
		shape.dispose(); //Remove shape
		
		//body.setLinearVelocity(0.1f, 0.0f);
		
		animations = new TextureRegion[3][4];
		Texture idleTex = new Texture(Gdx.files.internal("../core/assets/protag.png")); 
		Texture walkTex = new Texture(Gdx.files.internal("../core/assets/spritesheet4frames.png"));
		Texture jumpTex = new Texture(Gdx.files.internal("../core/assets/jump/1.png"));
        
        for (int i = 0; i < 4; i++) {
        	animations[1][i] = new TextureRegion(walkTex,i*123,0,123,220);
        }
        for (int i = 0; i < 4; i++) {
        	animations[0][i] = new TextureRegion(idleTex,0,0,123,220);
        }
        for (int i = 0; i < 4; i++) {
        	animations[2][i] = new TextureRegion(jumpTex,0,0,123,220);
        }
	}
	
	public void moveX(float mx) {
		body.setLinearVelocity(mx, body.getLinearVelocity().y);
		
		if(mx > 0)
			right = true;
		else if( mx < 0 )
			right = false;
	}
	
	public void moveY(float my) {
		//body.setLinearVelocity(body.getLinearVelocity().x, my);
		if(!isJumping && !isFalling)
			body.applyLinearImpulse(0, my, body.getPosition().x, body.getPosition().y, true);
		if(my < 0)
			down = true;
		else
			down = false;
	}
	
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public Body getBody() {
		return body;
	}
	
	public int getX() {
		return x;		
	}
		
	public int getY() {
		return y;
	}
		
	public int getPrevX() {
		return prevX;		
	}
		
	public int getPrevY() {
		return prevY;
	}
	
	public void setX(int sx) {
		x = sx;
	
	}
	
	public void setY(int sy) {
		y = sy;
	
	}
	public void setPrevX(int spx) {
		prevX = spx;
	}
		
	public void setPrevY(int spy) {
		prevY = spy;
	}
	
	public boolean goingRight() {
		return right;
	}
	
	public boolean goingDown() {
		return down;
	}
	
	public float getBoxX() {
		return body.getPosition().x - this.width;
	}
	
	public float getBoxY() {
		return body.getPosition().y - this.height;
	}
	
	public TextureRegion getTex(int i) {
		return animations[i][current_frame];
	}
	
	public void update() {
		
		frameTicks++;
		
		if(frameTicks == aniSpeed) {
			frameTicks = 0;
			current_frame++;

            if(current_frame == 4 ) {
            	current_frame = 0;
            }
		}
		
		//ticks for falling/jumping
		if(body.getLinearVelocity().y != 0) {
			ticks++;
		}
		else
			ticks = 0;
		
		//When n ticks have been reached, go to jumping/falling state
		if(ticks > 5) {
			if(body.getLinearVelocity().y > 0) {
				isJumping = true;
				isFalling = false;
			}
			else {
				isJumping = false;
				isFalling = true;
			}
				
		}
		else {
			isJumping = false;
			isFalling = false;
		}
	}
	
	public void draw(SpriteBatch batch, float WORLD_TO_RENDER) {
		
		//idle
		////
		if( body.getLinearVelocity().x == 0 && !isJumping && !isFalling) {
			if(right) {
				batch.draw(getTex(0), getBoxX()*WORLD_TO_RENDER, getBoxY()*WORLD_TO_RENDER);
			}
			else
				batch.draw(getTex(0),(getBoxX()+width*2)*WORLD_TO_RENDER, getBoxY()*WORLD_TO_RENDER,
							-(getTex(0).getRegionWidth()),(getTex(0).getRegionHeight()));
		}
		///////////
		//Going right 
		else if(right == true && !isJumping && !isFalling ) {
			batch.draw(getTex(1),getBoxX()*WORLD_TO_RENDER,getBoxY()*WORLD_TO_RENDER);
		}
		//Going up and right
		else if( right == true && isJumping ) {
			batch.draw(getTex(2),getBoxX()*WORLD_TO_RENDER,getBoxY()*WORLD_TO_RENDER);
			
		}
		//Going down and right
		else if( right == true && isFalling ) {
			batch.draw(getTex(2),getBoxX()*WORLD_TO_RENDER,getBoxY()*WORLD_TO_RENDER);
		}
		////////////
		//Going left 
		else if(right != true && !isJumping && !isFalling ) {
			batch.draw(getTex(1),(getBoxX()+width*2)*WORLD_TO_RENDER, getBoxY()*WORLD_TO_RENDER,
					-(getTex(0).getRegionWidth()),(getTex(0).getRegionHeight()));
		}
		
		//Going up and left
		else if( right != true && isJumping ) {
			batch.draw(getTex(2),(getBoxX()+width*2)*WORLD_TO_RENDER, getBoxY()*WORLD_TO_RENDER,
					-(getTex(0).getRegionWidth()),(getTex(0).getRegionHeight()));
		}
		//Going down and left
		else if( right != true && isFalling ) {
			batch.draw(getTex(2),(getBoxX()+width*2)*WORLD_TO_RENDER, getBoxY()*WORLD_TO_RENDER,
					-(getTex(0).getRegionWidth()),(getTex(0).getRegionHeight()));
			
		}

		
	}
		
}
