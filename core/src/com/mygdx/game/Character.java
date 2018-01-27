package com.mygdx.game;

public class Character {
	private int x;
	private int y;
	
	private boolean right;
	private boolean down;
	
	Character(int sx, int sy) {
		x = sx;
		y = sy;
		
	}
	
	public void moveX(int mx){
		x += mx;
		if(mx > 0)
			right = true;
		else
			right = false;
	}
	
	public void moveY(int my) {
		y += my;
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
		
		
}
