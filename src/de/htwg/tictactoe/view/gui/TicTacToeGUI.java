package de.htwg.tictactoe.view.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import de.htwg.tictactoe.controller.Controller;
import de.htwg.tictactoe.model.Messages;
import de.htwg.util.observer.IObserver;

public class TicTacToeGUI extends JFrame implements IObserver{

	
	private Container pane;
    private static final int DEFAULT_Y = 300;
    private static final int DEFAULT_X = 300;
    private static final long serialVersionUID = 1L;
    private LoginPanel loginPanel;
	
    public TicTacToeGUI(Controller controller) {
		pane = getContentPane();
		controller.addObserver(this); 
        setTitle(Messages.WELCOME_MESSAGE); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(DEFAULT_X, DEFAULT_Y)); 
        constructTicTacToePane(controller);
	} 
	private void constructTicTacToePane(Controller controller2) {
		loginPanel = new LoginPanel(controller2); 
		pane.add(loginPanel, BorderLayout.CENTER);
		setVisible(true); 
	}
	
	@Override
	public void update() {
		dispose();
	}


}
