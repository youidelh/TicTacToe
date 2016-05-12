package de.htwg.tictactoe.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.htwg.tictactoe.controller.Controller;
import de.htwg.util.observer.IObserver;

public class TextUI implements IObserver {

	private Controller controller;


	public TextUI(Controller controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	@Override
	public void update() {
		printTUI();
	}

	public boolean processInputLine(String line) {
		boolean continu = true;
		if (line.equalsIgnoreCase("q")) {
			continu = false;
		}
		if (line.matches("^[a-zA-Z0-9_-]*$")) {
			if(line.contains("-")){
				String[] players = line.split("-");
				if(players.length == 2 ){
					controller.setPlayers(players[0], players[1]);
				}
			}
		}
		if (line.equalsIgnoreCase("r")) {
			controller.reset();
		}
		
		// if the command line has the form 120, set the cell (1,2) in the grid 0
		if (line.matches("[0-9][0-9][0-9]")) {
			Pattern p = Pattern.compile("[0-9]");
			Matcher m = p.matcher(line);
			int[] arg = new int[3];
			for (int i = 0; i < arg.length; i++) {
				m.find();
				arg[i] = Integer.parseInt(m.group());
			}
			controller.setValue(arg[0], arg[1], arg[2]);
		}
		return continu;
	}

	public void printTUI() {
		System.out.println(controller.getGameString());
		System.out.println(controller.getStatus());
	}
}

