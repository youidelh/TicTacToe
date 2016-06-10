package de.htwg.tictactoe.model;


/**
 * Patterns: Abstract Factory
 * This is the abstract factory for the strategy to create grids
 */
public abstract class AbstractWinStateStrategyFactory {
	
	public abstract WinStateStrategyTemplate getInstance();
	
	
}