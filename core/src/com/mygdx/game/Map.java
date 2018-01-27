package com.mygdx.game;

import java.io.BufferedReader;
import java.io.FileReader;

public class Map {
	private int[][] matrix;
	private int spawnX, spawnY;
	private BufferedReader in = new BufferedReader(new FileReader(""));
	
	Map(String fileName) {
		//Open file with fileName and add into the matrix
		//Read in x,y size
		//matrix = new int[x]
		//read in all elements from the file
		//matrix[x][y] = something
	}
	
	public int getSpawnX() {
		return spawnX;
	}
	
	public int getSpawnY() {
		return spawnY;
	}
	
	public void render() {
		//Render stuff here??
	}
	
}
