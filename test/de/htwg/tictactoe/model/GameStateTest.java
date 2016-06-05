package de.htwg.tictactoe.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameStateTest {
	
	GameState state;
	
	/**
	 * set up state 
	 * @throws Exception
	 */
	@Before
    public void setUp() throws Exception {
        state = new GameState();
    }
	
	@Test
	public void testCheckRow(){
		assertFalse(state.checkRow(0, 0));
		assertFalse(state.checkRow(0, 0));
		assertTrue(state.checkRow(0, 0));
		
	}
	
	@Test
	public void testCheckColumn(){
		// normal column
		assertFalse(state.checkColumn(0, 0, 0));
		assertFalse(state.checkColumn(1, 0, 0));
		assertTrue(state.checkColumn(2, 0, 0));
		
		// 3D column
		assertFalse(state.checkColumn(0, 0, 1));
		assertTrue(state.checkColumn(0, 0, 2));
	}
	
	@Test
	public void testCheckDiagonal(){
		// row == column
		assertFalse(state.checkDiagonal(0, 0, 0));
		assertFalse(state.checkDiagonal(1, 1, 0));
		assertTrue(state.checkDiagonal(2, 2, 0));	
		// row != column
		assertFalse(state.checkDiagonal(0, 2, 0));
		assertTrue(state.checkDiagonal(2, 0, 0));	
	}
	
	@Test
	public void testCheckRowOfAllGridsTogether(){
		assertFalse(state.checkDiagOfAllGrids(0, 0, 2));
		assertFalse(state.checkDiagOfAllGrids(0, 1, 1));
		assertTrue(state.checkDiagOfAllGrids(0, 2, 0));
	}
	@Test
	public void testCheckColumnOfAllGridsTogether(){
		assertFalse(state.checkDiagOfAllGrids(0, 0, 2));
		assertFalse(state.checkDiagOfAllGrids(1, 0, 1));
		assertTrue(state.checkDiagOfAllGrids(2, 0, 0));
	}
	
	@Test
	public void testCheckDiagOfAllGridsTogether(){
		assertFalse(state.checkDiagOfAllGrids(2, 0, 2));
		assertFalse(state.checkDiagOfAllGrids(1, 1, 1));
		assertTrue(state.checkDiagOfAllGrids(0, 2, 0));
	}
	@Test
	public void testCheckDiagOfAllGridsTogetherOtherSide(){
		assertFalse(state.checkDiagOfAllGrids(2, 0, 0));
		assertFalse(state.checkDiagOfAllGrids(1, 1, 1));
		assertTrue(state.checkDiagOfAllGrids(0, 2, 2));
	}
	@Test
	public void testCheckDiagOfAllGridsTogetherEqualRow(){
		assertFalse(state.checkDiagOfAllGrids(0, 0, 2));
		assertFalse(state.checkDiagOfAllGrids(1, 1, 1));
		assertTrue(state.checkDiagOfAllGrids(2, 2, 0));
	}
	
	@Test
	public void testCheckDiagOfAllGrids(){
		assertFalse(state.checkDiagOfAllGrids(0, 0, 0));
		assertFalse(state.checkDiagOfAllGrids(0, 1, 1));
		assertTrue(state.checkDiagOfAllGrids(0, 2, 2));
		
		assertFalse(state.checkDiagOfAllGrids(1, 0, 1));
		assertTrue(state.checkDiagOfAllGrids(2, 0, 2));
	}
	
	@Test 
	public void testCheckForWin(){
		// check Column
		assertFalse(state.checkForWin(1, 1, 1));
		assertFalse(state.checkForWin(1, 1, 0));
		assertTrue(state.checkForWin(1, 1, 2));
		// check row
		assertFalse(state.checkForWin(0, 0, 0));
		assertFalse(state.checkForWin(0, 1, 0));
		assertTrue(state.checkForWin(0, 2, 0));
		// check diagonal
		assertTrue(state.checkForWin(2, 2, 0));
		//check diagonal of all grids
		assertTrue(state.checkForWin(2, 2, 2));
	}
}
