package de.htwg.tictactoe.model;


import org.junit.Test;
import static org.junit.Assert.*;
public class MessagesTest {
	
	@Test
	public void testMessages(){
		Messages msgs = new Messages();
		assertEquals("Game was reseted!!!! \n", Messages.GAME_RESET_MESSAGE);
	}

}
