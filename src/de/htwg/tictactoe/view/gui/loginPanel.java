package de.htwg.tictactoe.view.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.htwg.tictactoe.controller.Controller;
import javafx.application.Application;

public class loginPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton start;
	JTextField player1;
	JTextField player2;
	JLabel info;
	Controller controller ;
	JLabel user1;
	JLabel user2;
	public loginPanel(Controller controller) {
		start  = new JButton("start");
		player1 = new JTextField();
		user1 = new JLabel("First Player");
		user2 = new JLabel("Second Player");
		player2 = new JTextField();
		this.controller = controller;
		start.addActionListener(this);
		info = new JLabel("<html><div WIDTH=285>"+controller.getStatus()+"</div></html>");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		panel.add(user1);
		panel.add(player1); 
		panel.add(user2);
		panel.add(player2);
		panel.add(start);
		 
		setLayout(new GridLayout(2, 1));
		add(info);
		add(panel);
		
	}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); 
		if(source == start){
			if(!player1.getText().trim().equals("") && !player2.getText().trim().equals("")){
				controller.setPlayers(player1.getText(), player2.getText());
				controller.removeAllObservers();
				GameGui sw = new GameGui();
				sw.setController(controller);
				Application.launch(GameGui.class);
			}else{
				JOptionPane.showMessageDialog(null, "You did not give both users name try again");
			}
		}
		
	}

}
