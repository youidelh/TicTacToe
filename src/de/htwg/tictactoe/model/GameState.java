package de.htwg.tictactoe.model;

import java.util.ArrayList;

public class GameState {
	

	private final int NUMBER_TO_WIN = 3;
	private ArrayList<int[]> rowGridScore;
	private ArrayList<int[]> colsGridScore;
	private ArrayList<int[]> diagGridScore;
	public ArrayList<int[][]> diagOfAllGrids;
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
		rowGridScore = new ArrayList<>(3);
		colsGridScore = new ArrayList<>(3);
		diagGridScore = new ArrayList<>(3);
		diagOfAllGrids = new ArrayList<>(2);
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
	public boolean checkRow(int row, int column, int grid){
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
		if(row == column){
			if(++diagGridScore.get(grid)[0] == NUMBER_TO_WIN){
				return true;
			}
		}
		for (int i = 0; i < NUMBER_TO_WIN; i++) {
			if(row == i && column == (NUMBER_TO_WIN - 1 - i)){
				if(++diagGridScore.get(grid)[1] == NUMBER_TO_WIN){
					return true;
				}
			}
		}
		return false;
	}
	
	/***
	 * help method to increment the score counter in a specified cell
	 * @param cell
	 * @return
	 */
	private boolean incrementAllCells(int[][] cell){
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
	 * increments and checks diagonal of 3D grid
	 * @param row
	 * @param column
	 * @param grid
	 * @return
	 */
	public boolean checkDiagOfAllGrids(int row, int column, int grid) {
		if(grid == 1){
			if(row == 1 && column == 1){
				for (int k = 0; k < diagOfAllGrids.size(); k++) {
					boolean value = incrementAllCells(diagOfAllGrids.get(k));
					if(value){
						return true;
					}
				}
			}else {
				return diagStartingFromMiddle(row, column);
			}
		}else if(grid == 2 || grid == 0){
			if(grid == 2){
				grid = 1;
			}
			if(++diagOfAllGrids.get(grid)[row][column] == NUMBER_TO_WIN){
				return true;
			}
			if(grid == 0){
				return helpForDiagOfSpecifiedRows(grid + 1, row, column);
			}else if(grid == 1){
				return helpForDiagOfSpecifiedRows(grid - 1, row, column);
			}
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
	public boolean helpForDiagOfSpecifiedRows(int grid, int row, int column){
		if(row == 2 || column == 2){
			return diagOfSpecifiedRows(grid, row, column, -2);
		}else if(row == 0 || column == 0){
			return diagOfSpecifiedRows(grid, row, column, 2);
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
			int row1 = row == 0 ? 0 : 2;
			int column1 = column == 0 ? 0 : 2;
			for (int i = 0; i < diagOfAllGrids.size(); i++) {
				if(++diagOfAllGrids.get(i)[0][0] == NUMBER_TO_WIN 
						|| ++diagOfAllGrids.get(i)[row1][column1] == NUMBER_TO_WIN)
					return true;
			}
		}else if (column + row == NUMBER_TO_WIN && (column == 2 || row == 2)){
			int row1 = row == 1 ? 0 : 2;
			int column1 = column == 1 ? 0 : 2;
			for (int i = 0; i < diagOfAllGrids.size(); i++) {
				if(++diagOfAllGrids.get(i)[2][2] == NUMBER_TO_WIN 
						|| ++diagOfAllGrids.get(i)[row1][column1] == NUMBER_TO_WIN)
					return true;
			}
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
	public boolean diagOfSpecifiedRows(int grid, int row, int column, int token){
		int mainRow = Math.abs(row + token);
		int mainColumn = Math.abs(column + token);
		if( (mainRow < NUMBER_TO_WIN && mainColumn < NUMBER_TO_WIN) 
			&& ++diagOfAllGrids.get(grid)[mainRow][mainColumn] == NUMBER_TO_WIN){
			return true;
		}
		if( ( mainColumn < NUMBER_TO_WIN && row < NUMBER_TO_WIN 
				&& row != mainRow) 
				&& ++diagOfAllGrids.get(grid)[row][mainColumn] == NUMBER_TO_WIN){
			return true;
		}
		if( (mainRow < NUMBER_TO_WIN && column < NUMBER_TO_WIN 
				&& column != mainColumn) 
				&& ++diagOfAllGrids.get(grid)[mainRow][column] == NUMBER_TO_WIN){
			return true;
		}
		return false;
	}
	/**
	 * main function for check score
	 * @param row
	 * @param column
	 * @param grid 
	 * @return  
	 */
	public boolean checkForWin(int row, int column, int grid){
		if(checkRow(row, column, grid) 
				|| checkColumn(row, column, grid) 
				|| checkDiagonal(row, column, grid)
				|| checkDiagOfAllGrids(row, column, grid)){
			return true;
		}
		return false; 
	}
	/*public static void main(String[] args) {
		GameState state = new GameState(); 

		state.checkDiagOfAllGrids(1, 0, 0);
		state.checkDiagOfAllGrids(1, 1, 1);
		state.diagOfSpecifiedRows(1, 1, 0, 2);  
		//state.diagOfSpecifiedRows(2, 1, 0, 2); 
		//state.diagOfSpecifiedRows(2, 1, 0, 2); 
		//state.diagStartingFromMiddle(1, 1); 
		//state.diagStartingFromMiddle(1, 0); 
		/*
		state.checkDiagOfAllGrids(1, 1, 1);
		state.checkDiagOfAllGrids(0, 1, 1);
		state.checkDiagOfAllGrids(0, 0, 1);  
		state.checkDiagOfAllGrids(1, 2, 0); 
		state.checkDiagOfAllGrids(1, 2, 2);*/
		/*for (int k = 0; k < 2; k++) {
			for (int i = 0; i < state.diagOfAllGrids.get(k).length; i++) {
				for (int j = 0; j < state.diagOfAllGrids.get(k)[i].length; j++) {
					System.out.print(state.diagOfAllGrids.get(k)[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}*/
}
