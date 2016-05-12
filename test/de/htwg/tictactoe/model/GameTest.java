package de.htwg.tictactoe.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
	
	Game game;
	
	/**
	 * set up the Game 
	 * @throws Exception
	 */
	@Before
    public void setUp() throws Exception {
        game = new Game();
    }
	
	@Test
	public void testGetGrid(){
		Grid[] grids = game.getGrids();
		assertEquals(3, grids.length);
		 assertTrue(grids[0] instanceof Grid);
	}
	
	@Test
	public void testResetGame(){
		game.resetGame();
		String value = game.getGrids()[0].getCell(0, 0).getValue();
		assertEquals("", value);
	}
	@Test
	public void testCellIsSet(){
		game.getGrids()[0].setCell(0, 0, "X");
		assertTrue(game.CellIsSet(0, 0, 0));
	}
}
