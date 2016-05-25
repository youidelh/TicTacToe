package de.htwg.tictactoe.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import de.htwg.tictactoe.controller.Controller;
import de.htwg.util.observer.IObserver;


public class GraphicalUI extends JPanel implements ActionListener, IObserver {
    public static final int PANELS = 3;
    private Controller controller;
    private JTextArea textField;
    private SingleBoard [] boards = new SingleBoard[PANELS];
    private char currentPlayer = 'X';


    public GraphicalUI(Controller controller) {
        this.controller = controller;
        controller.addObserver(this);
        controller.setPlayers("Youssef", "Daniel");
        textField = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(textField);
        textField.setEditable(false);
    }

    @Override
    public void update() {
        System.out.println("Updated!!!!!!!!!");
    }

    class BoardButton extends JButton {
        public int row, col, plane;
        public BoardButton(int row, int col, int plane) {
            this.row = row;
            this.col = col;
            this.plane = plane;
            this.setText("   ");
        }

        public String toString() {
            return "(" + row + "," + col + "," + plane + ") = " + this.getText();
        }

    }

    class SingleBoard extends JPanel {
        public static final int ROWS = 3;
        public static final int COLS = 3;

        private BoardButton [] items = new BoardButton[ROWS*COLS];

        public SingleBoard(int plane, ActionListener listener) {
            setLayout( new GridLayout( ROWS, COLS ) );
            for (int row=0; row<ROWS; row++) {
                for (int col=0; col<COLS; col++) {
                    BoardButton b = new BoardButton(row, col, plane);
                    b.addActionListener(listener);
                    add(b );
                    items[row*COLS+col] = b;
                }
            }
        }

        public char getValue(int row, int col) {
            String s = items[row*COLS+col].getText();
            return (s==null || s.length()==0) ? ' ' : s.charAt(0);
        }

        public void setValue(int row, int col, char val) {
            items[row*COLS+col].setText(String.valueOf(val));
        }

    }

    public GraphicalUI() {
        setLayout( new GridLayout( 3, 0, 10, 10 ) );
        for(int panel=0; panel<PANELS; panel++) {
            SingleBoard sb = new SingleBoard(panel, this);
            boards[panel] = sb;
            add(sb);
        }
    }

    public char getValue(int row, int col, int plane) {
        return boards[plane].getValue(row, col);
    }

    public void setValue(int row, int col, int plane, char val) {
        boards[plane].setValue(row, col, val);
    }

    public void actionPerformed(ActionEvent evt) {
        BoardButton button = (BoardButton)evt.getSource();
        setValue(button.row, button.col, button.plane, currentPlayer);
        //System.out.println((int)b.row +" " + (int)b.col + " " +(int)b.plane);

        button.setEnabled(false);
        currentPlayer = (currentPlayer=='X') ? 'O' : 'X';
        System.out.println(button );

        this.controller.setValue(button.row, button.col, button.plane);
    }

    public void test() {
        JFrame frm = new JFrame("Layout Test");
        TextField text1 = new TextField();

        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.getContentPane().setLayout(new BorderLayout());
        frm.getContentPane().add(textField, BorderLayout.BEFORE_FIRST_LINE);
        textField.setText("current player: X");
        frm.getContentPane().add(new GraphicalUI(), BorderLayout.CENTER);
        frm.pack();
        frm.setVisible(true);
        frm.setSize(300, 900);
        frm.setLocationRelativeTo(null);
        //textField.setText("Player: B");

    }

}
