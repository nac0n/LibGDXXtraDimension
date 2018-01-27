package com.mygdx.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Map {
	private int[][] matrix;
	private int spawnX, spawnY;
	
	Map(String fileName) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String[] firstLineArray = in.readLine().split(" ");
			int number_of_rows = Integer.parseInt(firstLineArray[0]);
			int number_of_columns = Integer.parseInt(firstLineArray[1]);
			
			matrix = new int[number_of_rows][number_of_columns];
			
			for(int i = 0; i < number_of_rows; i++) {
				String line = in.readLine();
				String[] rowArray = line.split(" ");
				
				for(int x = 0; x < rowArray.length; x++) {
					matrix[i][x] = Integer.parseInt(rowArray[x]);
					//System.out.print(matrix[i][x]);
				}
			}
			
		}catch(Exception fnfe) {
			fnfe.printStackTrace();
		}
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
