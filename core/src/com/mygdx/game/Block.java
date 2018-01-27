package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Block {
	private int x,y;
	private Body body;
	private float width, height;
	
	Block(int x, int y, World world, float width, float height) {
		this.width = width/2;
		this.height = height/2;
		
		//Creating body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(((float)x)/96, ((float)y)/96);
		this.body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		
		
		shape.setAsBox(this.width, this.height);
		this.body.createFixture(shape, 0.f);
		shape.dispose(); //Remove shape
	}
	
	public float getX() {
		return body.getPosition().x;
	}
	
	public float getY() {
		return body.getPosition().y;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
}
