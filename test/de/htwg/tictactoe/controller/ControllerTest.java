package de.htwg.tictactoe.controller;

import org.junit.Before;
import org.junit.Test;

import de.htwg.tictactoe.model.Game;

import static org.junit.Assert.*;

public class ControllerTest {

	Controller controller;
	
	/**
	 * set up a Cell 
	 * @throws Exception
	 */
	@Before
    public void setUp() throws Exception {
		controller = new Controller(new Game());
    }

	@Test
	public void testSetPlayers(){
		controller.setPlayers("player2", "player1");
		assertEquals("player2", controller.getPlayer(0).getName());
		assertEquals("player1", controller.getPlayer(1).getName());
	}
	
	@Test
	public void testgetPlayer(){
		controller.setPlayers("player1", "player2");
		assertEquals("player1", controller.getPlayer(0).getName());
		assertEquals("player2", controller.getPlayer(1).getName());
		assertNull(controller.getPlayer(3));
	}
	
	@Test
	public void testReset(){
		controller.reset();
		controller.setPlayers("player1", "");
		controller.reset();
		controller.setPlayers("player1", "player2");
		controller.reset();
		assertFalse(controller.won[0]);
		assertFalse(controller.won[1]);
	}
	
		
	@Test
	public void testTryToMove(){
		controller.setPlayers("player1", "player2");

		controller.tryToMove(0, 0, 0, 0);
		assertTrue(controller.myTurn);
		controller.tryToMove(0, 0, 0, 0);
		assertFalse(controller.myTurn);
		controller.tryToMove(0, 0, 0, 0);

		controller.tryToMove(0, 3, 0, 0);
		controller.tryToMove(0, 0, 3, 0);
		controller.tryToMove(0, 0, 0, 3);
		

		controller.tryToMove(1, 2, 0, 0);
	}
	
	@Test
	public void testSetValue(){
		controller.setValue(0, 0, 0);
		controller.setPlayers("player1", "");
		controller.setValue(0, 0, 0);
		controller.setPlayers("player1", "player2");
		controller.setValue(0, 0, 0);
		controller.setValue(1, 1, 0);
		controller.setValue(0, 0, 1);
		controller.setValue(1, 1, 1);
		controller.setValue(0, 0, 2);
		controller.reset();
		controller.setPlayers("", "player2");
		controller.setValue(0, 0, 0);
		controller.setPlayers("player1", "player2");
		controller.setValue(0, 0, 0);
		controller.setValue(1, 1, 0);
		controller.setValue(0, 1, 1);
		controller.setValue(1, 1, 1);
		controller.setValue(0, 0, 2);
		controller.setValue(1, 1, 2);
	}
	
	@Test 
	public void testGetGame(){
		assertTrue(controller.getGame() instanceof Game);
	}
	
	@Test
	public void testGetGameString(){
		assertNotNull(controller.getGameString());
	}
	
	@Test 
	public void testGetStatus(){
		assertNotNull(controller.getStatus());
	}
}
