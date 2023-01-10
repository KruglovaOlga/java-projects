package gr.aueb.cf.projects;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  Tic-Tac-Toe Game
 *
 * The game loop is infinite, and the only way to end the game is
 * for one of the players to win or for the board to be full.
 * There is no way for a player to quit the game or for the game to end in a tie.
 *
 */

public class TicTacToeGameApp {

        public static void main(String[] args) {
            // Create a 2D array to represent the game board
            char[][] board = new char[3][3];
            // Initialize all elements of the board to a space character
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = ' ';
                }
            }

            // Create a scanner to read input from the command line
            Scanner scanner = new Scanner(System.in);
            // Initialize the current player to 'X'
            char currentPlayer = 'X';

            // Game loop
            while (true) {
                // Print the current state of the board
                printBoard(board);

                // Prompt the current player to enter their move
                System.out.print("Player " + currentPlayer + ", enter your move (row column): ");
                int row = 0;
                int column = 0;
                try {
                    row = scanner.nextInt();
                    column = scanner.nextInt();
                } catch (InputMismatchException e) {
                    // If the user enters a non-integer value, print an error message and continue the game loop
                    System.out.println("Invalid input. Please enter two integers.");
                    scanner.nextLine(); // consume the invalid input
                    continue;
                }

                // Check if the row or column is out of bounds
                if (row < 0 || row > 2 || column < 0 || column > 2) {
                    // If the row or column is out of bounds, print an error message and continue the game loop
                    System.out.println("Invalid input. Please enter a row and column between 0 and 2.");
                    continue;
                }

                // Check if the space is already occupied
                if (isSpaceOccupied(board, row, column)) {
                    // If the space is already occupied, print an error message and continue the game loop
                    System.out.println("That space is already occupied. Please try again.");
                    continue;
                }

                // Place the player's symbol on the board
                board[row][column] = currentPlayer;

                // Check if the current player has won the game
                if (hasWon(board, currentPlayer)) {
                    // If the current player has won, print a victory message and end the game
                    System.out.println("Player " + currentPlayer + " has won!");
                    break;
                }

                // Check if the board is full (i.e. a draw)
                if (isFull(board)) {
                    // If the board is full, print a draw message and end the game
                    System.out.println("The game is a draw.");
                    break;
                }

                // Switch to the other player
                if (currentPlayer == 'X') {
                    currentPlayer = 'O';
                } else {
                    currentPlayer = 'X';
                }
            }
        }

        // Prints the current state of the board to the console
        public static void printBoard(char[][] board) {
            for (int i = 0; i < 3; i++) {
                System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
                if (i != 2) {
                    System.out.println("-----------");
                }
            }
            System.out.println();
        }

    public static boolean isSpaceOccupied(char[][] board, int row, int column) {
        return board[row][column] != ' ';
    }

    // Returns true if the given player has won the game, false otherwise
    public static boolean hasWon(char[][] board, char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    // Returns true if the board is full, false otherwise
    public static boolean isFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

