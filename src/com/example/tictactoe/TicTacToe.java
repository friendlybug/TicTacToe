/*
  TicTacToe.java
 Application for a Tic Tac Toe Game.
*/

package com.example.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class TicTacToe extends JFrame implements ActionListener {

    //Declare Local Variables
    private int frameWidth = 300;
    private int frameHeight = 300;

    private String welcomeMessage = "Tic Tac Toe Game";
    private String outputMessage = "Output Message";

    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    private JLabel instructions = new JLabel(welcomeMessage);
    private JTextArea output = new JTextArea(outputMessage);

    private Grid grid = new Grid();

    private boolean gameOver = false;
//    private int countMoves = 0;
//    Object whichButton;

    public TicTacToe(){
        //Define layout for frame
        getContentPane().setLayout(new BorderLayout());

        // Set Names for Panels.
        topPanel.setBorder(BorderFactory.createTitledBorder("Welcome"));
        middlePanel.setBorder(BorderFactory.createTitledBorder("Middle Panel"));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Output & Instructions"));

        //Fill Panels with content
        topPanel.add(instructions);
        middlePanel = grid.createGrid();
        bottomPanel.add(output);

        //Add Panels to the main App Frame
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(middlePanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        //Call the action listener method
        addActionListener();
    }

    public int getFrameWidth(){
        return frameWidth;
    }

    public int getFrameHeight(){
        return frameHeight;
    }

    public void addActionListener(){
        for(int i = 0; i<grid.getWidth();i++){
            for(int j=0; j<grid.getHeight();j++){
                grid.getButton()[i][j].addActionListener(this);
            }
        }
    }
//
//    public boolean gameOver(){
//        if(countMoves == 9){
//            return true;
//        }else return false;
//    }

    public void play() throws InterruptedException {
//        while(!gameOver() && !gameOver ) {
//            output.setText("Computer's Turn");
//            computerMove();
//            //check if game is over
//            if (isWinner() == 0) {
//                output.setText("Computer Won");
//                gameOver = true;
//            } else if (isWinner() == 1) {
//                output.setText("User Won");
//                gameOver = true;
//            } else {
//                playerMove();
//            }
//            output.setText(Integer.toString(countMoves));
//        }
//    }

        int rand1, rand2;
        int countMoves = 0;
        while (!gameOver) {
            //computer turn
            output.setText("Computer's Turn");
            boolean computerTurn = true;
            while(computerTurn) {
                rand1 = (int) (grid.getWidth() * Math.random());
                rand2 = (int) (grid.getHeight() * Math.random());
                if ( (grid.getButton()[rand1][rand2].getText()).equals("")){
                    grid.getButton()[rand1][rand2].setText("X");
                    if (countMoves == 9){
                        output.setText("No one won");
                        break;
                    }else countMoves++;
                    computerTurn = false;
                }
            }
            //check if game is over
            if (isWinner() == 0) {
                output.setText("Computer Won");
                gameOver = true;
                break;
            }
            else if (isWinner() == 1) {
                output.setText("User Won");
                gameOver = true;
                break;
            }

            //player turn
            output.setText("Player's Turn");
            TimeUnit.SECONDS.sleep(6);
            if (countMoves == 9){
                output.setText("No one won");
                break;
            }else countMoves++;
            //check if game is over
            if (isWinner() == 0) {
                output.setText("Computer Won");
                gameOver = true;
                break;
            }
            else if (isWinner() == 1) {
                output.setText("User Won");
                gameOver = true;
                break;
            }

        }
    }

//    public void computerMove() {
//        int rand1, rand2;
//        //computer turn
//        output.setText("Computer's Turn");
//        boolean computerTurn = true;
//        while (computerTurn) {
//            rand1 = (int) (grid.getWidth() * Math.random());
//            rand2 = (int) (grid.getHeight() * Math.random());
//            if ((grid.getButton()[rand1][rand2].getText()).equals("")) {
//                grid.getButton()[rand1][rand2].setText("X");
//                countMoves++;
//                computerTurn = false;
//            }
//        }
//    }

//    public void playerMove(){
//        output.setText("Player's Turn");
//        JButton button = (JButton) whichButton;
//        if (button.getText().equals("O")){
//            countMoves++;
//        }
//    }

    //0 is computer, 1 is user
    public int isWinner() {
        int result = -1;

        //check row 1
        if (grid.getButton()[0][0].getText().equals(grid.getButton()[0][1].getText()) &&
                grid.getButton()[0][1].getText().equals(grid.getButton()[0][2].getText())) {
            if (grid.getButton()[0][0].getText().equals("X")) {
                result = 0;
            } else if (grid.getButton()[0][0].getText().equals("O")) {
                result = 1;
            }
        }
        //check row 2
        if (grid.getButton()[1][0].getText().equals(grid.getButton()[1][1].getText()) &&
                grid.getButton()[1][1].getText().equals(grid.getButton()[1][2].getText())) {
            if (grid.getButton()[1][0].getText().equals("X")) {
                result = 0;
            } else if (grid.getButton()[1][0].getText().equals("O")) {
                result = 1;
            }
        }
        //check row 3
        if (grid.getButton()[2][0].getText().equals(grid.getButton()[2][1].getText()) &&
                grid.getButton()[2][1].getText().equals(grid.getButton()[2][2].getText())) {
            if (grid.getButton()[2][0].getText().equals("X")) {
                result = 0;
            } else if (grid.getButton()[2][0].getText().equals("O")) {
                result = 1;
            }
        }
        //check col 1
        if (grid.getButton()[0][0].getText().equals(grid.getButton()[1][0].getText()) &&
                grid.getButton()[1][0].getText().equals(grid.getButton()[2][0].getText())) {
            if (grid.getButton()[0][0].getText().equals("X")) {
                result = 0;
            } else if (grid.getButton()[0][0].getText().equals("O")) {
                result = 1;
            }
        }
        //check col 2
        if (grid.getButton()[0][1].getText().equals(grid.getButton()[1][1].getText()) &&
                grid.getButton()[1][1].getText().equals(grid.getButton()[2][1].getText())) {
            if (grid.getButton()[0][1].getText().equals("X")) {
                result = 0;
            } else if (grid.getButton()[0][1].getText().equals("O")) {
                result = 1;
            }
        }
        //check col 3
        if (grid.getButton()[0][2].getText().equals(grid.getButton()[1][2].getText()) &&
                grid.getButton()[1][2].getText().equals(grid.getButton()[2][2].getText())) {
            if (grid.getButton()[0][2].getText().equals("X")) {
                result = 0;
            } else if (grid.getButton()[0][2].getText().equals("O")) {
                result = 1;
            }
        }
        //check diagonal \
        if (grid.getButton()[0][0].getText().equals(grid.getButton()[1][1].getText()) &&
                grid.getButton()[1][1].getText().equals(grid.getButton()[2][2].getText())) {
            if (grid.getButton()[0][0].getText().equals("X")) {
                result = 0;
            } else if (grid.getButton()[0][0].getText().equals("O")) {
                result = 1;
            }
        }
        //check diagonal /
        if (grid.getButton()[0][2].getText().equals(grid.getButton()[1][1].getText()) &&
                grid.getButton()[1][1].getText().equals(grid.getButton()[2][0].getText())) {
            if (grid.getButton()[0][2].getText().equals("X")) {
                result = 0;
            } else if (grid.getButton()[0][2].getText().equals("O")) {
                result = 1;
            }
        }
        return result;
    }
    public void actionPerformed(ActionEvent ae){
        Object whichButton = ae.getSource();
        for(int i = 0; i<grid.getWidth();i++){
            for(int j=0; j<grid.getHeight();j++){
                if(whichButton == grid.getButton()[i][j]){
                    if((grid.getButton()[i][j].getText()).equals("X") ||
                            (grid.getButton()[i][j].getText()).equals("O")){
                        output.setText("Illegal Move");
                    }else {
                        grid.getButton()[i][j].setText("O");
                        output.setText("");
                    }
                }
            }
        }
    }


    //Main method, creates the frame and runs the play method.
    public static void main(String[] args) throws InterruptedException {
        TicTacToe tictactoe = new TicTacToe();
        tictactoe.setSize(tictactoe.getFrameWidth(),tictactoe.getFrameHeight());
        tictactoe.setResizable(false);
        tictactoe.setVisible(true);
        tictactoe.setTitle("Tic Tac Toe Game");
        //Get the screen size
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //Set the frame to be centred on screen
        tictactoe.setLocation(dim.width/2-tictactoe.getSize().width/2,
                dim.height/2-tictactoe.getSize().height/2);

        //play the game
        tictactoe.play();
        //destroy frame upon window close
        tictactoe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
