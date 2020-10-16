package com.company;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static char[][] output = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    static int totalMoves = 0;
    static int totalMovesPlaced = 0;
    static char inputXO = 'X';
    static boolean winner = false;

    public static void main(String[] args) {
        // print the game board
        printField();
        do {
            userInput();
            printField();
            if (totalMovesPlaced > 4){
                checkWinner();
                if (winner){
                    System.exit(0);
                }
            }
            checkWinner();
            if (totalMovesPlaced == 9 && !winner)  {
                System.out.println("Draw!");
                System.exit(0);
            }
        } while(totalMovesPlaced < 10);
    }

    // check if a valid move
    public static void userInput(){
        System.out.println("Enter the coordinates: ");
        if (!sc.hasNextInt()) {
            System.out.println("You should enter numbers!");
            sc.next(); // Need this to get rid of the 2nd entry
            userInput();
        } else {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (x > 3 || x < 1 || y > 3 || y < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                userInput();
            } // Check if space is free
            else if (output[3 - y][x - 1] != ' '){
                System.out.println("This cell is occupied! Choose another one!");
                userInput();
            } else {
                inputXO = totalMovesPlaced % 2 == 0 ? 'X' : 'O';
                output[3 - y][x - 1] = inputXO;
                totalMovesPlaced++;
            }
        }
        totalMoves++;
    }

    public static void printField(){
        // print the game board
        System.out.println("---------");
        for (char[] cell : output) {
            System.out.print("| ");
            for (char value : cell) {
                System.out.print(value + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    public static void checkWinner() {
        // go through the first row
        for (int i  = 0; i < 3; i++) {
            if (winner) {
                break;
            }
            for (int j = 0; j < 3; j++) {
                // Check each row
                if (output[i][0] == output[i][1] && output[i][0] == output[i][2]) {
                    if (output[i][0] != ' ') {
                        System.out.println(output[i][0] + " wins!");
                        System.exit(0);
                        winner = true;
                    }
                    break;
                }
                // Check each column
                else if (output[0][j] == output[1][j] && output[0][j] == output[2][j]) {
                    if (output[0][j] != ' ') {
                        System.out.println(output[0][j] + " wins!");
                        System.exit(0);
                        winner = true;
                    }
                    break;
                }
            }
        }
        // Check Diagonals
        if (output[0][0] == output[1][1] && output[0][0] == output[2][2]) {
            if (output[0][0] != ' ') {
                System.out.println(output[0][0] + " wins!");
                System.exit(0);
                winner = true;
            }

        } else if (output[2][0] == output[1][1] && output[2][0] == output[0][2]) {
            if (output[2][0] != ' ') {
                System.out.println(output[2][0] + " wins!");
                System.exit(0);
                winner = true;
            }

        } else if (winner = false) {
            System.out.println("Draw");
            System.exit(0);
        }
    }
}
