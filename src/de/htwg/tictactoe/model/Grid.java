package de.htwg.tictactoe.model;

public class Grid {

	private int GRIDE_SIZE = 3;
	
	// 2D Cell field
	private Cell[][] cell;
	
	/**
	 * Constructor 
	 */
	public Grid() {
		cell = new Cell[GRIDE_SIZE][GRIDE_SIZE];
		for (int row = 0; row < cell.length; row++) {
			for (int column = 0; column < cell[row].length; column++) {
				cell[row][column] = new Cell(row, column);
			}
		}
	}
	/**
	 * get the cell in a position in Grid 
	 * @param row
	 * @param column
	 * @return a Cell object or null
	 */
	public Cell getCell(int row, int column){
		if(row < cell.length && column < cell[row].length){
			return cell[row][column];
		}else {
			return null;
		}
	}
	
	/**
	 * set the Cell with the Value given
	 * @param row number
	 * @param column number
	 * @param value
	 * @return true if is Set or false if it isn't
	 */
	public boolean setCell(int row, int column, String value){
		if(row < cell.length && column < cell[row].length && 
				cell[row][column].getValue() == ""){
			cell[row][column].setValue(value);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Checks if the cell is Set 
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean cellIsSet(int row, int column){
		return cell[row][column].isSet();
	}
	
	/**
	 * Get Grid size
	 * @return Grid size
	 */
	public int getGridSize(){
		return GRIDE_SIZE;
	}
	
	public String newLine(int row){
		String newLine = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		sb.append(newLine);
		String space = "";
		for (int i = 0; i < row; i++) {
			space+= "  ";
		}
		sb.append(space+"\\-----\\-----\\-----\\");
		sb.append(" \\-------\\-------\\-------\\");
		sb.append(newLine);
		return sb.toString();
	}
	public void resetGrid(){
		cell = new Cell[GRIDE_SIZE][GRIDE_SIZE];
		for (int row = 0; row < cell.length; row++) {
			for (int column = 0; column < cell[row].length; column++) {
				cell[row][column] = new Cell(row, column);
			}
		}
	}


	public String toString(int grid){
		StringBuilder sb = new StringBuilder();
		sb.append(newLine(0));
		for (int row = 0; row < cell.length; row++) {
			String space = " ";
			for (int i = 0; i < row; i++) {
				space+= "  ";
			}
			sb.append(space+"\\");
			for (int column = 0; column < cell[row].length; column++) {
				String value = cell[row][column].getValue() == "" ?
						" " : cell[row][column].getValue();
				sb.append("  "+value+"  \\");
			}
			sb.append(" \\");
			for (int column = 0; column < cell[row].length; column++) {
				sb.append("  "+row+column+grid+"  \\");
			}
			sb.append(newLine(row+1));
		}
		return sb.toString();
	}
	
}
