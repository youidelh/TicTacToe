package de.htwg.tictactoe.model;

/**
 * 
 * @author Youssef Idelhoussain
 *
 */
public class ThreeDimensionGridsStateStrategyFactory extends AbstractWinStateStrategyFactory{

	@Override
	public WinStateStrategyTemplate getInstance() {
		return new ThreeDimensionGridsStateStrategy();
	}

}
