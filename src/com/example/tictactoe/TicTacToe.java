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

/**
 * TicTacToe.java
 * <P> Tic Tac Toe Game, User Vs. Computer </p>
 * Date: 2019-08-19
 * Time: 12:45 AM
 * Package: PACKAGE_NAME
 *
 * @author Mohamad Jayyusi - https://github.com/jayyusi
 * @version 2.0
 */

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
    private boolean userPlayed = false;
    private boolean gameOver = false;
    private int countMoves = 0;

    public TicTacToe() {
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

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void addActionListener() {
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                grid.getButton()[i][j].addActionListener(this);
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        // synchronized block ensures only one thread
        // running at a time.
        synchronized (this) {
            Object whichButton = ae.getSource();
            for (int i = 0; i < grid.getWidth(); i++) {
                for (int j = 0; j < grid.getHeight(); j++) {
                    if (whichButton == grid.getButton()[i][j]) {
                        if ((grid.getButton()[i][j].getText()).equals("X") ||
                                (grid.getButton()[i][j].getText()).equals("O")) {
                            output.setText("Illegal Move");
                        } else {
                            grid.getButton()[i][j].setText("O");
                            userPlayed = true;
                            if (++countMoves == 9 && isWinner() == -1) {
                                output.setText("Game Over - No Win");
                                disableButtons();
                            }

                            if (isWinner() == 1) {
                                output.setText("User Won");
                                disableButtons();
                            }//else output.setText("...");


                            //System.out.println("Waiting for return key.");
                            System.out.println("inside actionperformed - Player clicked panel");

                            // notifies the produce thread that it
                            // can wake up.
                            notify();

                            // Sleep
                            //Thread.sleep(2000);
                        }
                    }
                }
            }
        }
    }

    public void play() throws InterruptedException {

        synchronized (this) {
            while (true) {
                computerPlay();
                if (isWinner() == 0) {
                    output.setText("Computer Won");
                    disableButtons();
                    break;
                }

                //debugging line
                //System.out.println("inside play method ( Computer Played )");

                // releases the lock on shared resource
                wait();

                //debugging line
                // and waits till some other method invokes notify().
                System.out.println("User clicked the panel, resumed play method ( Computer Turn )");
            }
        }
    }

    public void computerPlay() throws InterruptedException {
        int rand1, rand2;
        while (true) {
            rand1 = (int) (grid.getWidth() * Math.random());
            rand2 = (int) (grid.getHeight() * Math.random());
            if (grid.getButton()[rand1][rand2].getText().equals("") && !gameOver) {
                TimeUnit.SECONDS.sleep(2);//2 seconds wait, to give user time to see what happened on the board.
                grid.getButton()[rand1][rand2].setText("X");
                if (++countMoves == 9 && isWinner() == -1) {
                    output.setText("Game Over - No Win");
                    disableButtons();
                }
                break;
            }
        }
    }

    public void disableButtons() {
        middlePanel.getComponent(0).setEnabled(false);
        middlePanel.getComponent(1).setEnabled(false);
        middlePanel.getComponent(2).setEnabled(false);
        middlePanel.getComponent(3).setEnabled(false);
        middlePanel.getComponent(4).setEnabled(false);
        middlePanel.getComponent(5).setEnabled(false);
        middlePanel.getComponent(6).setEnabled(false);
        middlePanel.getComponent(7).setEnabled(false);
        middlePanel.getComponent(8).setEnabled(false);
        gameOver = true;
    }

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

    //Main method, creates the frame and runs the play method.
    public static void main(String[] args) throws InterruptedException {
        TicTacToe tictactoe = new TicTacToe();
        tictactoe.setSize(tictactoe.getFrameWidth(), tictactoe.getFrameHeight());
        tictactoe.setResizable(false);
        tictactoe.setVisible(true);
        tictactoe.setTitle("Tic Tac Toe Game");
        ImageIcon img = new ImageIcon("tictactoe_logo.gif");
        tictactoe.setIconImage(img.getImage());
        //Get the screen size
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //Set the frame to be centred on screen
        tictactoe.setLocation(dim.width / 2 - tictactoe.getSize().width / 2,
                dim.height / 2 - tictactoe.getSize().height / 2);

        //play the game
        tictactoe.play();
        //destroy frame upon window close
        tictactoe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
