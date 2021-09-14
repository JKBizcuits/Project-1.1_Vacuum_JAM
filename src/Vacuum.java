/**
 * Vacuum object holds most of the logic for the application. It takes in a board and does a count of how
 * many spaces are on the board. Then it tries to check every square at random, looking to see if a tile
 * is dirty or not. After visiting every square it knows the board is clean and ends the application.
 */

import java.util.Random;

public class Vacuum {

	int x;
	int y;
	Board board;
	Random r;
	Boolean [][] cleanTiles;
	//ArrayList<Coordinate> cleanTiles;
	int boardSize;
	
	public Vacuum() {
		System.out.println("This is the default vacuum");
	}
	
	/*
	 * Constructor that takes in a board, populates it. Creates a map of unvisited tiles. Then chooses a
	 * random location to start. Until every tile is visited, it will keep moving around the board and checking
	 * every tile.
	 */
	public Vacuum(Board board) {
		r = new Random();
		this.board = board;
		this.board.populate();
		cleanTiles = new Boolean[board.getX()][board.getY()];
		boardSize = (board.getX() * board.getY());
		for (int i = 0; i<board.getX(); i++) {
			for(int j = 0; j<board.getY(); j++) {
				cleanTiles[i][j] = false;
			}
		}
		//cleanTiles = new ArrayList<Coordinate>();
		randomStart();
		while(boardSize>0) {
			move();
			checkTile();
		}
		System.out.println("All clean!");
		
		
	}
	
	
	/**
	 * checks the tile the vacuum is standing on and if it's dirty, it calls the clean method. Also checks
	 * to see if the tile has been visited before
	 */
	public void checkTile() {
		//System.out.println("Checking: " + y + x);
		if(board.getTileStatus(x, y) == true) {
			
			//System.out.println("CLeaning: "+y+x +board.grid[this.x][this.y] );
			clean();
		}
		//coord = new Coordinate(y,x);
		if(cleanTiles[x][y] == false) {
			cleanTiles[x][y] = true;
			boardSize-=1;
			System.out.println(boardSize + " more spaces left to check");
		}

	}
	
	/**
	 * randomly picks a number representing a direction. Checks to see if the move is legal and if not,
	 * it calls itself again until a legal random direction is decided
	 */
	public void move() {
		switch(r.nextInt(4) + 1) {
		case 1: 
			if((this.y-1)<0) {
			move();
			}
			else {
				this.y-=1;
				System.out.println("Vacuum moved left 1 space to [" + this.y + ", " + this.x + "]");
				getBoard();
				break;
			}
		case 2: 
			if((this.y+1)>board.getX()-1) {
			move();
			}
			else {
				this.y+=1;
				System.out.println("Vacuum moved right 1 space to [" + this.y + ", " + this.x + "]");
				getBoard();
				break;
			}
		case 3: 
			if((this.x-1)<0) {
			move();
			}
			else {
				this.x-=1;
				System.out.println("Vacuum moved up 1 space to [" + this.y + ", " + this.x + "]");
				getBoard();
				break;
			}
		case 4: 
			if((this.x+1)>board.getY()-1) {
			move();
			}
			else {
				this.x+=1;
				System.out.println("Vacuum moved down 1 space to [" + this.y + ", " + this.x + "]");
				getBoard();
				break;
			}
		}
		
	}
	
	/**
	 * Make dirty tiles clean (already confirmed in checkTile() if it's dirty or not)
	 */
	public void clean() {
		//System.out.println("coordinate"+ board.grid[this.x][this.y] );
		board.setTileStatus(x, y, false);
		//System.out.println("CLeaned: "+y+x +board.grid[this.x][this.y] );
		System.out.println("Vacuum cleaned [" + this.y + ", " + this.x + "]");
		getBoard();
	}
	
	/**
	 * chooses random location within board to start from and checks to see if the starting position is 
	 * dirty or not
	 */
	public void randomStart() {
		this.x = r.nextInt(board.getX());
		this.y = r.nextInt(board.getY());
		System.out.println("Vacuum starting at [" + this.y + ", " + this.x + "]");
		getBoard();
		checkTile();
	}
	
	/**
	 * displays the board with markers showing whether or not the tiles are dirty and if the vacuum is present
	 */
	public void getBoard() {
		for(int i = 0; i<board.getX(); i++) {
			for(int j = 0; j<board.getY(); j++) {
				if(board.grid[i][j] == true ) {
					
					System.out.print("[D");
					if(i == this.x && j == this.y) {
						System.out.print("O] "); //+ y+x + j+i + board.grid[i][j]);
					}
					else {
						System.out.print("U] "); // + y+x + j+i + board.grid[i][j]+board.getTileStatus(x, y));
					}
				}
				else if(board.grid[i][j] == false) {
					System.out.print("[C");
					if(i == this.x && j == this.y) {
						System.out.print("O] "); // + y+x + j+i + board.grid[i][j]);
					}
					else {
						System.out.print("U] "); // + y+x + j+i + board.grid[i][j]);
					}
				}
			}
			System.out.println("");
		}
	}
	
}
