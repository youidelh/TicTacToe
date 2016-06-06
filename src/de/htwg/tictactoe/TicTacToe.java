package de.htwg.tictactoe;

import java.util.Scanner;

import de.htwg.tictactoe.controller.Controller;
import de.htwg.tictactoe.model.Game;
import de.htwg.tictactoe.view.*;

public class TicTacToe {
	static Scanner scanner;
	String line = ""; 
	public static void main(String[] args) {
		Game game = new Game();
		Controller controller = new Controller(game);
		TextUI tui = new TextUI(controller);
		tui.printTUI();
		 //continue until the user decides to quit
		boolean continu = true;
		scanner = new Scanner(System.in);
		while (continu) {
		    continu = tui.processInputLine(scanner.next());		
		}
	}
}
