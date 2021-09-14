/**
 * Board class creates the board which the Vacuum object will clean. Board can be any size and 
 * has the method populate() to choose randomly which indices will be dirty or clean. There are also some
 * methods to make getting information from the board easier in other classes.
 */

import java.util.Random;

public class Board {

	Random r;
	int x;
	int y;
	boolean [][] grid;
	
	public Board() {
		System.out.println("default constructor");
		
	}
	
	/*
	 * takes in an x and y value and creates a 2D array for a board
	 */
	public Board(int x, int y) {
		r = new Random();
		this.x = x;
		this.y = y;
		grid = new boolean[x][y];

	}
	
	/*
	 * fills the board randomly with true or false values
	 */
	public void populate() {
		for(int i = 0; i<x; i++) {
			for(int j = 0; j<y; j++) {
				if(r.nextInt(2) == 1) {
					grid[i][j] = true;
				}
				else {
					grid[i][j] = false;
				}
			}
		}
		/*for(int i = 0; i<x; i++) {
			for(int j = 0; j<y; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println("");
		}*/
		
		
	}
	
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getTileStatus(int x, int y) {
		return grid[x][y];
	}
	
	public void setTileStatus(int x, int y, boolean b) {
		grid[x][y] = b;
	}
}
