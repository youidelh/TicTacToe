package de.htwg.tictactoe.model;

public class ThreeDimensionGridsStateStrategyFactory extends AbstractWinStateStrategyFactory{

	@Override
	public WinStateStrategyTemplate getInstance() {
		return new ThreeDimensionGridsStateStrategy();
	}

}
