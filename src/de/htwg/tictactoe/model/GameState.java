package de.htwg.tictactoe.model;

import java.util.ArrayList;
import java.util.List;


public class GameState{
	

	private static final int NUMBER_TO_WIN = 3;
	private List<int[]> rowGridScore;
	private List<int[]> colsGridScore;
	private List<int[]> diagGridScore;
	private List<int[][]> diagOfAllGrids;
	private int[][] colsAllGrid;
	 
	/**
	 * constructor
	 */
	public GameState() {
		initRowsArrays(); 
		initDiagArrays();
		initAllDiagArrays();
		initColumnsArrays();
	}
	private void initAllDiagArrays() {
		diagOfAllGrids = new ArrayList<int[][]>(3);
		//3 * 3 matrix for the three Grid
		for (int i = 0; i < 3; i++) {
			diagOfAllGrids.add(new int[3][3]);
		}	
	}
	private void initColumnsArrays() {
		colsGridScore = new ArrayList<int[]>(3);
		// adding 3 intArrays for the 3 grids
		for (int i = 0; i < 3; i++) {
			colsGridScore.add(new int[3]);
		}
		// vertical column of all grids
		colsAllGrid = new int[3][3];
		
	}
	private void initDiagArrays() {
		diagGridScore = new ArrayList<int[]>(3);
		// adding 3 intArrays for the 3 grids
		for (int i = 0; i < 3; i++) {
			diagGridScore.add(new int[2]);
		}		
	}
	private void initRowsArrays() {
		rowGridScore = new ArrayList<int[]>(3);
		// adding 3 intArrays for the 3 grids
		for (int i = 0; i < 3; i++) {
			rowGridScore.add(new int[3]);
		}
		
	}
	/**
	 * increments and checks a row of one grid
	 * @param row
	 * @param column 
	 * @param grid placement of the grid 
	 * @return
	 */
	public boolean checkRow(int row, int grid){
		if(++rowGridScore.get(grid)[row] == NUMBER_TO_WIN){
			return true;
		} 
		return false;
	}
	/**
	 * increments and checks a column of one grid and the vertical column in all grids
	 * @param row
	 * @param column 
	 * @param grid placement of the grid
	 * @return
	 */
	public boolean checkColumn(int row, int column, int grid){
		if(++colsAllGrid[row][column] == NUMBER_TO_WIN){
			return true;
		}
		if(++colsGridScore.get(grid)[column] == NUMBER_TO_WIN){
			return true;
		}
		return false;
	}
	/**
	 * increments and checks diagonal of one grid
	 * @param row
	 * @param column 
	 * @param grid placement of the grid
	 * @return 
	 */
	public boolean checkDiagonal(int row, int column, int grid){
		if(row == column && ++diagGridScore.get(grid)[0] == NUMBER_TO_WIN){
			return true;
		}
		return checkCellInOtherSideOfDiagOneGrid(row, column, grid);
	}

	/**
	 * check cell of the other side in the diagonal in one grid
	 */
	public boolean checkCellInOtherSideOfDiagOneGrid(int row, int column, int grid){
		for (int i = 0; i < NUMBER_TO_WIN; i++) {
			if(row == i && column == (NUMBER_TO_WIN - 1 - i) && ++diagGridScore.get(grid)[1] == NUMBER_TO_WIN){
				return true;
			}
		}
		return false;
	} 
	
	/**
	 * checks a row of all grids
	 */
	public boolean checkRowOfAllGridsTogether(int row){
		int rowLength = 0;
		int reverseRowLength = 0;
		for (int i = 0; i < diagOfAllGrids.size(); i++) {
			rowLength += diagOfAllGrids.get(i)[row][i];
			reverseRowLength+= diagOfAllGrids.get(i)[row][2 - i];
		}
		return rowLength == NUMBER_TO_WIN || reverseRowLength == NUMBER_TO_WIN;
	}
	

	/**
	 * checks a column of all grids
	 */
	public boolean checkColumnOfAllGridsTogether(int column){
		int columnLength = 0;
		int reverseColumnLength = 0;
		for (int i = 0; i < diagOfAllGrids.size(); i++) {
			columnLength += diagOfAllGrids.get(i)[i][column];
			reverseColumnLength+= diagOfAllGrids.get(i)[2 - i][column];
		}
		return columnLength == NUMBER_TO_WIN || reverseColumnLength == NUMBER_TO_WIN;
	}
	

	/**
	 * checks a diagonal of all grids
	 */
	public boolean checkDiagOfAllGridsTogether(int row, int column){
		if(row == column){
			return checkDiagOfAllGridsTogetherColumnEqualRow();
		}else if(row + column == 2){
			return checkDiagOfAllGridsTogetherInOtherSideOfDiagOneGrid();
		}
		return false;
	}
	
	/**
	 * checks if diagonal of 00 11 22 of all grids
	 * @return
	 */ 
	private boolean checkDiagOfAllGridsTogetherColumnEqualRow(){
		int diagLength = 0;
		int reverseDiagLength = 0;
		for (int i = 0; i < diagOfAllGrids.size(); i++) {
			diagLength += diagOfAllGrids.get(i)[i][i];
			reverseDiagLength+= diagOfAllGrids.get(i)[2 - i][2 - i];
		}
		return diagLength == NUMBER_TO_WIN || reverseDiagLength == NUMBER_TO_WIN;
	}
	
	/**
	 * checks diagonal of 20 11 02 of all grids
	 * @return
	 */
	private boolean checkDiagOfAllGridsTogetherInOtherSideOfDiagOneGrid(){
		int diagLength = 0;
		int reverseDiagLength = 0;
		for (int i = 0; i < diagOfAllGrids.size(); i++) {
			diagLength += diagOfAllGrids.get(i)[2 - i][i];
			reverseDiagLength+= diagOfAllGrids.get(i)[i][2 - i];
			
		}
		return diagLength == NUMBER_TO_WIN || reverseDiagLength == NUMBER_TO_WIN;
	} 
	/**
	 * increments and checks diagonal of 3D grid
	 * @param row
	 * @param column
	 * @param grid
	 * @return
	 */
	public boolean checkDiagOfAllGrids(int row, int column, int grid) {
		++diagOfAllGrids.get(grid)[row][column];
		if(checkRowOfAllGridsTogether(row)){
			return true;
		}else if(checkColumnOfAllGridsTogether(column)){
			return true;
		}else if(checkDiagOfAllGridsTogether(row, column)){
			return true;
		}
		return false;
	}
	
	/**
	 * checks for win
	 */
	public boolean checkForWin(int row, int column, int grid){
		if(checkRow(row, grid) 
				|| checkColumn(row, column, grid) 
				|| checkDiagonal(row, column, grid)
				|| checkDiagOfAllGrids(row, column, grid)){
			return true;
		}
		return false; 
	}

}
