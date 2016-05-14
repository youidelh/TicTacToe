package de.htwg.tictactoe.controller;

import de.htwg.tictactoe.model.Game;
import de.htwg.tictactoe.model.Messages;
import de.htwg.tictactoe.model.Player;
import de.htwg.util.observer.Observable;

public class Controller extends Observable {

	private String statusMessage = Messages.WELCOME_MESSAGE;
	private Game game;
	private Player[] players = new Player[2];
	private boolean myTurn = true;
	private boolean[] won = new boolean[2];

	/**
	 * Constructor
	 * @param game 
	 */
	public Controller(Game game) {
		this.game = game;
		winInit();
	}
	public boolean getTurn(){
		return myTurn;
	}
	
	public boolean getWin(int i){
		if(i == 1 || i == 0){
			return won[i];
		}
		return false;
	}
	public Player getPlayer(int player){
		if(player < 2){
			return players[player];
		}else{
			return null;
		}
	}
	
	public Game getGame(){
		return game;
	}
	
	/**
	 * boolean for the two player states
	 */
	public void winInit(){ 
		for (int i = 0; i < won.length; i++) {
			won[i] = false;
		}
	}
	/**
	 * reset the game and notify all observers
	 */
	public void reset() {
		if (players[0] != null && players[1] != null) {
			game.resetGame();
			myTurn = true;
			winInit();
			setPlayers(players[0].getName(), players[1].getName());
			setStatusMessage(Messages.GAME_RESET_MESSAGE + players[0].getName() + Messages.INFO_ABOUT_THE_GAME);
			notifyObservers();
		} else {
			setStatusMessage(Messages.ERROR_GIVE_PLAYERS_RESET);
			notifyObservers();
		}

	}
	/**
	 * exit
	 * @return 
	 */
	public void exit() {
		System.exit(0);
	}
	
	/**
	 * checks if cell is set and notifies all observers
	 * @param playerIndex
	 * @param row
	 * @param column
	 * @param grid
	 */
	public void tryToMove(int playerIndex, int row, int column, int grid){
		if(game.cellIsSet(row, column, grid)){
				myTurn = !myTurn;
				setStatusMessage(Messages.CELL_IS_SET);
				notifyObservers();
		}else{
			won[playerIndex] = players[playerIndex].move(row, column, grid);
			int nextIndex = playerIndex == 0 ? 1 : 0;
			setStatusMessage(Messages.playerMoveToString(players[playerIndex].getName(), row, column, grid) +
					players[nextIndex].getName() + Messages.NEXT);
			notifyObservers();
		}
		
	}
	/**
	 * check if move correct
	 */
	public boolean checkData(int row, int column, int grid){
		if(row > 2 || column > 2 || grid > 2){
			setStatusMessage(Messages.ERROR_MOVE);
			notifyObservers();
			return false;
		}
		return true;
	}
	
	/**
	 * check for win
	 */
	public void checkForWin(){
		if(won[0]){
			setStatusMessage(players[0].getName() + Messages.WIN_MESSAGE);
			notifyObservers();
		}else if(won[1]){
			setStatusMessage(players[1].getName() +Messages.WIN_MESSAGE);
			notifyObservers();
		}
	}
	
	/**
	 * main method, sets the value and checks if a player won
	 * @param row
	 * @param column
	 * @param grid
	 */
	public void setValue(int row, int column, int grid){
		if (players[0] == null || players[1] == null) {
			setStatusMessage(Messages.ERROR_GIVE_PLAYERS_START);
			notifyObservers();
			return;
		}
		if (checkData(row, column, grid)) {
			if(myTurn){
				tryToMove(0, row, column, grid);
			}else{
				tryToMove(1, row, column, grid);
			}
			myTurn = !myTurn;
			checkForWin();
		}
		
	}

	/**
	 * sets the players
	 * @param player1
	 * @param player2
	 */
	public void setPlayers(String player1, String player2) {
		checkBeforeSetPlayer(player1, 0, "X");
		checkBeforeSetPlayer(player2, 1, "O");
		setStatusMessage(Messages.PLAYER_DEFINED_MESSAGE + player1 + Messages.INFO_ABOUT_THE_GAME);
		notifyObservers(); 
	}
	
	public void checkBeforeSetPlayer(String player, int i, String symbol){
		if("".equals(player)){
			setStatusMessage(Messages.PLAYER_NAME); 
			notifyObservers();
		}else{
			this.players[i] = new Player(player, symbol, game.getGrids());
		}
	}

	/**
	 * to string of Game
	 * @return
	 */
	public String getGameString() {
		return game.toString();
	}

	public String getStatus() {
		return statusMessage;
	}

	private void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}
