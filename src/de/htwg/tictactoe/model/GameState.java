package de.htwg.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class GameState {
	

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
		init(); 
	}
	/**
	 * init of all Arrays for the 3 grids
	 */
	private void init(){
		rowGridScore = new ArrayList<int[]>(3);
		colsGridScore = new ArrayList<int[]>(3);
		diagGridScore = new ArrayList<int[]>(3);
		diagOfAllGrids = new ArrayList<int[][]>(2);
		// adding 3 int-arrays for the 3 grids
		for (int i = 0; i < 3; i++) {
			rowGridScore.add(new int[3]);
			colsGridScore.add(new int[3]);
			diagGridScore.add(new int[2]);
		}
		//3 * 3 matrix for the first Grid
		diagOfAllGrids.add(new int[3][3]);
		//3 * 3 matrix for the last Grid
		diagOfAllGrids.add(new int[3][3]);
		// vertical column of all grids
		colsAllGrid = new int[3][3];
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
		for (int i = 0; i < NUMBER_TO_WIN; i++) {
			if(row == i && column == (NUMBER_TO_WIN - 1 - i) && ++diagGridScore.get(grid)[1] == NUMBER_TO_WIN){
				return true;
			}
		}
		return false;
	}
	
	/***
	 * help method to increment the score counter in a specified cell
	 * @param cell
	 * @return
	 */
	private boolean incrementCell(int[][] cell){
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell[i].length; j++) {
				if(++cell[i][j] == NUMBER_TO_WIN){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * refractor of checkDiagOfAllGrids method
	 */
	private boolean helpForCheckDiagOfAllGrids(){
		for (int k = 0; k < diagOfAllGrids.size(); k++) {
			if(incrementCell(diagOfAllGrids.get(k))){
				return true;
			}
		}
		return false;
	}
	/**
	 * increments and checks diagonal of 3D grid
	 * @param row
	 * @param column
	 * @param grid
	 * @return
	 */
	public boolean checkDiagOfAllGrids(int row, int column, int grid) {
		if(grid == 1){
			if(row == 1 && column == 1){
				return helpForCheckDiagOfAllGrids();
			}else {
				return diagStartingFromMiddle(row, column);
			}
		}else if(grid == 2 || grid == 0){
			int gridToIncrement = grid == 2 ? 1: 0;
			if(++diagOfAllGrids.get(gridToIncrement)[row][column] == NUMBER_TO_WIN){
				return true;
			}
			return diagOfSpecifiedRows(gridToIncrement, row, column);
		}
		return false;
	}
	
	
	/**
	 * help for diagStartingFromMiddle
	 */
	private boolean helpForDiagStartingFromMiddle(int row, int column, int token){
		int row1 = row == token ? 0 : 2;
		int column1 = column == token ? 0 : 2;
		int rowCol = token == 0 ? 0 : 2;
		for (int i = 0; i < diagOfAllGrids.size(); i++) {
			if(++diagOfAllGrids.get(i)[rowCol][rowCol] == NUMBER_TO_WIN 
					|| ++diagOfAllGrids.get(i)[row1][column1] == NUMBER_TO_WIN)
				return true;
		}
		return false;
	}
	/**
	 * increments and checks the 3d grid diagonal starting from the middle normal grid
	 * @param row
	 * @param column
	 * @return
	 */ 
	public boolean diagStartingFromMiddle(int row, int column){
		if (column + row == 1 && (column == 0 || row == 0)){
			return helpForDiagStartingFromMiddle(row, column, 0);
		}else if (column + row == NUMBER_TO_WIN && (column == 2 || row == 2)){
			return helpForDiagStartingFromMiddle(row, column, 1);
		}
		return false;
	}
	/**
	 * Help method 
	 * @param grid
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean diagOfSpecifiedRows(int grid, int row, int column){
		if(grid != 0 && grid != 1 ){
			return false;
		}
		int token = grid == 0 ? 1 : 0;
		if(row == 2 || column == 2){
			return helpForDiagOfSpecifiedRows(token, row, column, -2);
		}else if(row == 0 || column == 0){
			return helpForDiagOfSpecifiedRows(token, row, column, 2);
		}
		return false;
	}
	/**
	 * increments and checks the 3d grid diagonal 
	 * @param grid
	 * @param row
	 * @param column
	 * @param token
	 * @return
	 */
	public boolean helpForDiagOfSpecifiedRows(int grid, int row, int column, int token){
		int mainRow = Math.abs(row + token);
		int mainColumn = Math.abs(column + token);
		if((mainRow < NUMBER_TO_WIN && mainColumn < NUMBER_TO_WIN) 
				&& ++diagOfAllGrids.get(grid)[mainRow][mainColumn] == NUMBER_TO_WIN){
			return true;
		}
		if( row != mainRow && checkIfWinForSpecifiedRows(row, mainColumn, grid)){
			return true;
		}
		if(column != mainColumn && checkIfWinForSpecifiedRows(mainRow, column, grid)){
			return true;
		}
		return false;
	}
	/**
	 * checks if rows and column are < 3 and checks for win 
	 * @param row
	 * @param column
	 * @param grid
	 * @return
	 */
	private boolean checkIfWinForSpecifiedRows(int row, int column, int grid){
		return row < NUMBER_TO_WIN && column < NUMBER_TO_WIN 
				&& ++diagOfAllGrids.get(grid)[row][column] == NUMBER_TO_WIN;
	}
	/**
	 * main function for check score
	 * @param row
	 * @param column
	 * @param grid 
	 * @return  
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
