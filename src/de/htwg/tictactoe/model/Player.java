package de.htwg.tictactoe.model;

import de.htwg.tictactoe.model.Grid;

public class Player {
	private String name;
	private String symbol;
	
	private Grid[] grids;
	GameState gameState;
	
	
	/**
	 * Constructor
	 * @param name
	 * @param symbol
	 * @param grids
	 */
	public Player(String name, String symbol,Grid[] grids) {
		this.name = name;
		this.symbol = symbol;
		this.grids = grids;
		gameState = new GameState();
	}
	/**
	 * sets a move and returns if a player won
	 * @param row
	 * @param column
	 * @param grid
	 * @return
	 */
	public boolean move(int row, int column, int grid){
		if(grids[grid].setCell(row, column, symbol)){
			return playerWon(row, column, grid);
		}
		return false;
	}
	
	
	/**
	 * reset the gameState
	 */
	public void resetPlayer(){
		gameState = new GameState();
	}
	/**
	 * checks if a player won 
	 * @param row
	 * @param column
	 * @param grid
	 * @return
	 */
	private boolean playerWon(int row, int column, int grid){
		return gameState.checkForWin(row, column, grid);
	}
	
	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}	

}
