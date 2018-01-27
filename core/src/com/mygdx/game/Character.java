package com.mygdx.game;

public class Character {
	private int x;
	private int y;
	
	Character(int sx, int sy) {
		x = sx;
		y = sy;
		
	}
	
	public void moveX(int mx){
		x += mx;
	}
	
	public void moveY(int my) {
		y += my;
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
		
		
}
