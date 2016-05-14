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
	public void testCheckDiagOfAllGrids(){
		assertFalse(state.checkDiagOfAllGrids(1, 0, 1));
		assertFalse(state.checkDiagOfAllGrids(1, 1, 1));
		assertTrue(state.checkDiagOfAllGrids(0, 1, 1));
		assertFalse(state.checkDiagOfAllGrids(0, 0, 1));
		 
		assertFalse(state.checkDiagOfAllGrids(1, 0, 3));

		assertFalse(state.checkDiagOfAllGrids(1, 2, 0));
		assertTrue(state.checkDiagOfAllGrids(1, 2, 2));
	}
	@Test
	public void testCheckDiagOfAllGrids2(){
		assertFalse(state.checkDiagOfAllGrids(1, 1, 1));
		assertFalse(state.checkDiagOfAllGrids(2, 1, 0));
		assertTrue(state.checkDiagOfAllGrids(0, 1, 2));
		
	} 
	@Test
	public void testIncrementCell(){ 
		assertFalse(state.checkDiagOfAllGrids(2, 1, 2));
		assertFalse(state.checkDiagOfAllGrids(0, 1, 0));
		assertTrue(state.checkDiagOfAllGrids(1, 1, 1));
	}
	@Test
	public void testDiagStartingFromMiddle(){
		assertFalse(state.diagStartingFromMiddle(0, 2));
		assertFalse(state.diagStartingFromMiddle(1, 0));
		assertFalse(state.diagStartingFromMiddle(0, 1));
		assertTrue(state.diagStartingFromMiddle(0, 1));
		assertFalse(state.diagStartingFromMiddle(1, 1));
		assertFalse(state.diagStartingFromMiddle(3, -2));
	}
	@Test
	public void testDiagStartingFromMiddle2(){
		assertFalse(state.diagStartingFromMiddle(2, 1));
		assertFalse(state.diagStartingFromMiddle(1, 2));
		assertFalse(state.diagStartingFromMiddle(5, -2));
		assertTrue(state.diagStartingFromMiddle(2, 1));
	} 
	@Test 
	public void testDiagStartingFromMiddle3(){
		assertFalse(state.diagStartingFromMiddle(2, 1));
		assertFalse(state.checkDiagOfAllGrids(1, 0, 1));
		assertTrue(state.diagStartingFromMiddle(2, 1));
	}
 
	@Test
	public void testDiagStartingFromMiddle4(){ 
	assertFalse(state.checkDiagOfAllGrids(2, 1, 1));
	assertFalse(state.checkDiagOfAllGrids(2, 2, 0));
	assertTrue(state.diagStartingFromMiddle(1, 0));
	}
	@Test 
	public void testHelpForDiagOfSpecifiedRows(){
		assertFalse(state.diagOfSpecifiedRows(0, 0, 2));
		assertFalse(state.diagOfSpecifiedRows(3, 0, 1));

	}
	@Test 
	public void testDiagOfSpecifiedRows(){
		assertFalse(state.helpForDiagOfSpecifiedRows(1, 1, 1, 1));
		assertFalse(state.helpForDiagOfSpecifiedRows(1, 1, 1, 1));
		assertTrue(state.helpForDiagOfSpecifiedRows(1, 1, 1, 1));
		assertFalse(state.helpForDiagOfSpecifiedRows(1, 1, 3, 1));
		assertFalse(state.helpForDiagOfSpecifiedRows(1, 4, 1, 1));
		assertTrue(state.helpForDiagOfSpecifiedRows(1, 0, 1, 2));
	}
	@Test 
	public void testDiagOfSpecifiedRows2(){	

		assertFalse(state.checkDiagOfAllGrids(1, 0, 0));
		assertFalse(state.checkDiagOfAllGrids(1, 1, 1));
		assertTrue(state.helpForDiagOfSpecifiedRows(1, 1, 0, 2));
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
	@Test
	public void testCheckGivenGrid(){
		assertEquals(0, state.checkGivenGrid(1));
	}
}
