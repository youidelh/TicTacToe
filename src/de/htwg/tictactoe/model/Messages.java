package de.htwg.tictactoe.model;

public class Messages {
	
	public Messages() {}
	public final static String WELCOME_MESSAGE = "Welcome to HTWG TicTacToe 3D! \n"
			+ "please enter the two players name with - between then (don't forget no spacing)";

	public final static String ERROR_GIVE_PLAYERS = "you cant reset the Game without giving the name of the players\n"
			+ "please enter the two players name with - between then (don't forget no spacing)";
	
	public final static String WIN_MESSAGE = " you won !! congratulation \n "
			+ " if you want to start again press r + enter, if not press x + enter to exit";
	
	public final static String PLAYER_DEFINED_MESSAGE = "Players are defined!!!! \n";
	
	public final static String GAME_RESET_MESSAGE = "Game was reseted!!!! \n";
	
	public final static String INFO_ABOUT_THE_GAME = " you can start, the grids with the number in them are\n" +
			" an example of what you should give here if you want to player in one of the cells,\n" +
			" the 3 numbers are the row, column, grid. Now, MAKE YOU FIRST MOVE :) !!\n";
	
	public final static String CELL_IS_SET = "This Cell is Already been setted, please try another one \n";
	
	public final static String NEXT = " you are next!! \n";

	public static final String ERROR_MOVE = "you are out of the limit of the grid!! please retry with correct data";
	
	public static final String PLAYER_NAME = "please enter players name again";
	
	public static String playerMove_toString(String player, int row, int column, int grid) {
		return player + " played : (" + row + "," + column + ") in Grid " + grid + "\n";
	}
	

}
