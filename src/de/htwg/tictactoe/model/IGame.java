package de.htwg.tictactoe.model;

/**
 * Created by Daniel on 17.06.2016.
 */
public interface IGame {

    /**
     * sets all values ​​of the current game state to the initial state
     */
    void resetGame();

    /**
     * checks the value in one cell of tree possible cells, whether the value is set
     *
     * @param row the row of the current move
     * @param column the column of the current move
     * @param grid the grid with the row and the column of the current move
     *
     * @return if the cell is set, the method will return true, otherwise false.
     */
    boolean cellIsSet(int row, int column, int grid);

    /**
     * gives the grids of the current game state with all current values of the grids
     *
     * @return a array with all grids of the current game
     */
    Grid[] getGrids();

}
