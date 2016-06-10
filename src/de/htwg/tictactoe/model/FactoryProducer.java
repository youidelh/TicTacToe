package de.htwg.tictactoe.model;

public class FactoryProducer {
	
	private FactoryProducer() {
	} 
	
	public static AbstractWinStateStrategyFactory getFactory(String choice){
		if("oneD".equals(choice)){
			return new OneDimensionGridsStateStrategyFactory();
		}else if("threeD".equals(choice)){
			return new ThreeDimensionGridsStateStrategyFactory();
		}
		return null;
	}
}
