package com.mygdx.game;

import java.io.BufferedReader;
import java.io.FileReader;

import com.badlogic.gdx.physics.box2d.World;

public class Map {
	private int[][] matrix;
	private Block[][] blocks;
	private int spawnX, spawnY;
	private int number_of_rows;
	private int number_of_columns;
	
	Map(String fileName, World world, float RENDER_TO_WORLD) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String[] firstLineArray = in.readLine().split(" ");
			number_of_rows = Integer.parseInt(firstLineArray[0]);
			number_of_columns = Integer.parseInt(firstLineArray[1]);
			
			matrix = new int[number_of_rows][number_of_columns];
			
			for(int i = 0; i < number_of_rows; i++) {
				String line = in.readLine();
				String[] rowArray = line.split(" ");
				
				for(int x = 0; x < rowArray.length; x++) {
					matrix[i][x] = Integer.parseInt(rowArray[x]);
					//System.out.print(matrix[i][x]);
				}
			}
			
			in.close();
			
		}catch(Exception fnfe) {
			fnfe.printStackTrace();
		}
		
		for(int y = 0; y < this.getMapHeight(); y++) {
			for(int x = 0; x < this.getMapWidth(); x++) {
				System.out.print(matrix[y][x] + " ");	
			}
			System.out.println();
		}
		
		blocks = new Block[number_of_rows][number_of_columns];
		
		for(int y = this.getMapHeight() - 1; y >= 0; y--) {
			for(int x = 0; x < this.getMapWidth(); x++) {
		    	if(this.getValue(y, x) != 0) {
		    		blocks[y][x] = new Block(x*64, (1280-64)-y*64, world, 64*RENDER_TO_WORLD, 64*RENDER_TO_WORLD);
		    	}	
			}
		}
		
		
	}
	
	public int getSpawnX() {
		return spawnX;
	}
	
	public int getSpawnY() {
		return spawnY;
	}
	
	public int getMapWidth() {
		return number_of_columns ;
	}
	
	public int getMapHeight() {
		return number_of_rows;
	}
	
	public int getValue(int y, int x) {
		return matrix[y][x];
	}
	public void render() {
		
	}
	
}
