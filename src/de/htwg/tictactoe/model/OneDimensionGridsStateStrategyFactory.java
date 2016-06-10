package de.htwg.tictactoe.model;

public class OneDimensionGridsStateStrategyFactory extends AbstractWinStateStrategyFactory{
	
	@Override
	public WinStateStrategyTemplate getInstance() {
		return new OneDimensionGridsStateStrategy();
	}


}
