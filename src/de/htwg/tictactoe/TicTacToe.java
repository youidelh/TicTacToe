package de.htwg.tictactoe;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import de.htwg.tictactoe.controller.Controller;
import de.htwg.tictactoe.model.Game;
import de.htwg.tictactoe.view.*;
import de.htwg.tictactoe.view.gui.TicTacToeGUI;

public class TicTacToe { 
	static Scanner scanner;
	String line = ""; 
	public static void main(String[] args) {
		
		// Set up logging through log4j
        PropertyConfigurator.configure("log4j.properties");
		
		Game game = new Game();
		Controller controller = new Controller(game);
		TextUI tui = new TextUI(controller);
		new TicTacToeGUI(controller);
		tui.printTUI(); 	
		 //continue until the user decides to quit
		boolean continu = true;
		scanner = new Scanner(System.in);
		while (continu) {
		    continu = tui.processInputLine(scanner.next());		
		}
	}
}
