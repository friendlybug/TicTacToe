/* Grid class is a class that creates the buttons for the tic tac toe game.
    This class is made to make things easier by excluding the buttons from the main app, and keeping code neat.
    its sole purpose is to create a grid and return its' reference upon the main app's request.
 */
package com.example.tictactoe;

import javax.swing.*;
import java.awt.*;

public class Grid{

    //Declare local variables
    private int width = 3;
    private int height = 3;

    private JPanel panel = new JPanel();
//    private String[][] str = {
//            {"1", "2", "3" },
//            {"4","5","6"},
//            {"7", "8", "9"}
//    };

    private String[][] str = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""}
    };
    private JButton[][] button = new JButton[width][height];

    public JButton[][] getButton() {
        return button;
    }

    public void setButton(JButton[][] button) {
        this.button = button;
    }

    //Constructor
    public JPanel createGrid(){
        panel.setLayout(new GridLayout(width,height));

        for (int i =0; i<width; i++){
            for(int j=0; j<height; j++){
                button[i][j] = new JButton(str[i][j]);
            }
        }
        for (int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                panel.add(button[i][j]);
            }
        }

        return panel;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
