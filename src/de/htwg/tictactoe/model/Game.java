package de.htwg.tictactoe.model;

public class Game {
	
	Grid[] grids;
	private int SIZE = 3;
	
	/**
	 * constructor
	 */
	public Game() {
		grids = new Grid[SIZE];
		for (int i = 0; i < grids.length; i++) {
			grids[i] = new Grid();
		}
	}
	/**
	 * get all grids
	 * @return
	 */
	public Grid[] getGrids(){
		return grids;
	}
	
	 /**
	  * checks if cell is set
	  * @param row
	  * @param column
	  * @param grid
	  * @return
	  */
	public boolean CellIsSet(int row, int column, int grid){
		return grids[grid].cellIsSet(row, column);
	}
	
	/**
	 * resets all Grids
	 */
	public void resetGame(){
		for (int i = 0; i < grids.length; i++) {
			grids[i].resetGrid();
		}
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < grids.length; i++) {
			sb.append(grids[i].toString(i));
		}
		return sb.toString();
	}
}
