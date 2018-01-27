package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.*;

public class Character {
	private int x;
	private int y;
	private Body body;
	
	private boolean right;
	private boolean down;
	
	private float vY;
	Character(int sx, int sy, World world) {
		x = sx;
		y = sy;
		
		//Creating body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(((float)x)/96, ((float)y)/96);
		this.body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(0.5f, 1f);
		this.body.createFixture(shape, 1.f);
		shape.dispose(); //Remove shape
		body.setLinearVelocity(0.1f, 0.0f);
	}
	
	public void moveX(float mx){
		body.setLinearVelocity(mx, body.getLinearVelocity().y);
		if(mx > 0)
			right = true;
		else
			right = false;
	}
	
	public void moveY(float my) {
		body.setLinearVelocity(body.getLinearVelocity().x, my);
		if(my < 0)
			down = true;
		else
			down = false;
	}
		
	public int getX() {
		return x;		
	}
		
	public int getY() {
		return y;
	}
		
	public void setX(int sx) {
		x = sx;
	
	}
	
	public void setY(int sy) {
		y = sy;
	
	}
	
	public boolean goingRight() {
		return right;
	}
	
	public boolean goingDown() {
		return down;
	}
	
	public float getBoxX() {
		return body.getPosition().x;
	}
	
	public float getBoxY() {
		return body.getPosition().y;
	}
		
}
