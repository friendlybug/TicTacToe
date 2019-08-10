/*
  TicTacToe.java
 Application for a Tic Tac Toe Game.
*/

package com.example.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {

    //Declare Local Variables
    private int frameWidth = 300;
    private int frameHeight = 300;

    private String welcomeMessage = "Welcome to the Tic Tac Toe Game";
    private String outputMessage = "Output Message";

    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    private JLabel instructions = new JLabel(welcomeMessage);
    private JTextArea output = new JTextArea(outputMessage);

    private Grid grid = new Grid();

    public TicTacToe(){
        //Define layout for frame
        getContentPane().setLayout(new BorderLayout());

        // Set Names for Panels.
        topPanel.setBorder(BorderFactory.createTitledBorder("Top Panel"));
        middlePanel.setBorder(BorderFactory.createTitledBorder("Middle Panel"));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Bottom Panel"));

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

    public void actionPerformed(ActionEvent ae){
        Object whichButton = ae.getSource();
        for(int i = 0; i<grid.getWidth();i++){
            for(int j=0; j<grid.getHeight();j++){
                if(whichButton == grid.getButton()[i][j]){
                    output.setText("You Pressed " + grid.getButton()[i][j].getText());
                }
            }
        }
    }
    //Main method, creates the frame and closes it
    public static void main(String[] args) {
        TicTacToe tictactoe = new TicTacToe();
        tictactoe.setSize(tictactoe.getFrameWidth(),tictactoe.getFrameHeight());
        tictactoe.setResizable(false);
        tictactoe.setVisible(true);
        tictactoe.setTitle("Tic Tac Toe Game");
        tictactoe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
