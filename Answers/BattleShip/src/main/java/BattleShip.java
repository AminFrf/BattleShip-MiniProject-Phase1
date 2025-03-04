import java.util.Scanner;
import java.util.Random;

/**
  The BattleShip class manages the gameplay of the Battleship game between two players.
  It includes methods to manage grids, turns, and check the game status.
 */
public class BattleShip {

    // Grid size for the game
    static final int GRID_SIZE = 10;

    // Player 1's main grid containing their ships
    static char[][] player1Grid = new char[GRID_SIZE][GRID_SIZE];

    // Player 2's main grid containing their ships
    static char[][] player2Grid = new char[GRID_SIZE][GRID_SIZE];

    // Player 1's tracking grid to show their hits and misses
    static char[][] player1TrackingGrid = new char[GRID_SIZE][GRID_SIZE];

    // Player 2's tracking grid to show their hits and misses
    static char[][] player2TrackingGrid = new char[GRID_SIZE][GRID_SIZE];

    // Scanner object for user input
    static Scanner scanner = new Scanner(System.in);
    Random rand = new Random();
    int ranNum = 0;
    char ranChar = ' ';

    /**
     * The main method that runs the game loop.
     * It initializes the grids for both players, places ships randomly, and manages turns.
     * The game continues until one player's ships are completely sunk.
     */
    public static void main(String[] args) {
        // Initialize grids for both players
        initializeGrid(player1Grid);
        initializeGrid(player2Grid);
        initializeGrid(player1TrackingGrid);
        initializeGrid(player2TrackingGrid);

        // Place ships randomly on each player's grid
        placeShips(player1Grid);
        placeShips(player2Grid);

        // Variable to track whose turn it is
        boolean player1Turn = true;

        // Main game loop, runs until one player's ships are all sunk
        while (!isGameOver()) {
            if (player1Turn) {
                System.out.println("Player 1's turn:");
                printGrid(player1TrackingGrid);
                playerTurn(player2Grid, player1TrackingGrid);
            } else {
                System.out.println("Player 2's turn:");
                printGrid(player2TrackingGrid);
                playerTurn(player1Grid, player2TrackingGrid);
            }
            player1Turn = !player1Turn;
        }

        System.out.println("Game Over!");
    }

    /**
     * Initializes the grid by filling it with water ('~').
     *
     * @param grid The grid to initialize.
     */
    static void initializeGrid(char[][] grid) {

        for (int i = 0; i <= J; i++) {
            for (int j = 0; j <= 9; j++) {
                grid[i][j] = "~";
            }//end of nested for
        }//end of for
        //todo
    }//initializeGrid

    /**
     * Places ships randomly on the given grid.
     * This method is called for both players to place their ships on their respective grids.
     *
     * @param grid The grid where ships need to be placed.
     */
    static void placeShips(char[][] grid) {
        int min = 48;
        int max = 49;
        int random = rand.nextInt(max - min + 1) + min;
        for (int i = 2; i <= 5; i++) {

            if (random == 0) {
            //ofoghi
                do {
                    boolean horizontal = true;
                    min = 48;
                    max = 57;
                    int row = rand.nextInt(max - min + 1) + min;
                    min = 65;
                    max = 74;
                    while (col1 <= (74 - i) + 1) {
                        int col1 = rand.nextInt(max - min + 1) + min;
                    }//end of while
                    char col = (char) row;
                }while(canPlaceShip(char[][] grid, row , col1 , i , true))
                    for (int j = 0; j < i ; j++) {
                        grid[row][col] = '#' ;
                    }//end of for
                }//end of if
        }//end of if
        if (random == 1) {
            //amoodi
            do{
            boolean horizontal = false;
            min = 48;
            max = 57;
            while (row <= ((10-i)-1) {
                int row = rand.nextInt(max - min + 1) + min;
            }//end of while loop
            min = 65;
            max = 74;
                int col1 = rand.nextInt(max - min + 1) + min;
            char col = (char) col1;
            }while(canPlaceShip(char[][] grid, row , col1 , i , true))
            //if(canPlaceShip(char[][] grid, row , col1 , i , true)==true){
                for (int j = 0; j < i ; j++) {
                    grid[row][col] = '#' ;
                }//end of for
            }//end of if

        }//end of if
    }//end of for...
    }

    /**
     * Checks if a ship can be placed at the specified location on the grid.
     * This includes checking the size of the ship, its direction (horizontal or vertical),
     * and if there's enough space to place it.
     *
     * @param grid       The grid where the ship is to be placed.
     * @param row        The starting row for the ship.
     * @param col        The starting column for the ship.
     * @param size       The size of the ship.
     * @param horizontal The direction of the ship (horizontal or vertical).
     * @return true if the ship can be placed at the specified location, false otherwise.
     */
    static boolean canPlaceShip(char[][] grid, int row, int col, int size, boolean horizontal) {
        boolean empty = false;
        if (horizontal==true) {
            for (int i = 0; i < size; i++) {
                if (grid[row][col] == '~') {
                    boolean empty = true ;
                }//end pf if
                else {
                    boolean empty = false ;
                    break ;
                }//end of else
                col++;
            }//end of for
            return empty;
        }//end of if

        if (horizontal==false) {
            for (int i = 0; i < size; i++) {
                if (grid[row][col] == '~') {
                    boolean empty = true ;
                }//end pf if
                else {
                    boolean empty = false ;
                    break ;
                }//end of else
                row++;
            }//end of for
            return empty;
        }//end of if
    }
    e
    /**
     * Manages a player's turn, allowing them to attack the opponent's grid
     * and updates their tracking grid with hits or misses.
     *
     * @param opponentGrid The opponent's grid to attack.
     * @param trackingGrid The player's tracking grid to update.
     */
    static void playerTurn(char[][] opponentGrid, char[][] trackingGrid) {
        //todo
    }

    /**
     * Checks if the game is over by verifying if all ships are sunk.
     *
     * @return true if the game is over (all ships are sunk), false otherwise.
     */
    static boolean isGameOver() {
        if(allShipsSunk(player1Grid) || allShipsSunk(player2Grid)){
            return true;
        }//end of if
        return false;
    }//end of isGameOver

    /**
     * Checks if all ships have been destroyed on a given grid.
     *
     * @param grid The grid to check for destroyed ships.
     * @return true if all ships are sunk, false otherwise.
     */
    static boolean allShipsSunk(char[][] grid) {
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == '#') {
                    return false;
                }//end of if
            }//end of nested for
        }//end of for
        return true;
    }//end of allShipsSunk

    /**
     * Validates if the user input is in the correct format (e.g., A5).
     *
     * @param input The input string to validate.
     * @return true if the input is in the correct format, false otherwise.
     */
    static boolean isValidInput(String input) {
        char [] chars = input.toCharArray();
        if(chars.length != 2){
            return false;
        }//end of if
        int code = (int)chars[0]
        else if (code > 74 || code < 65) {
            return false;
        }//end of else if
        else if(chars[1] > 9 || chars[1]< 0){
            return false;
        }//end of else if
        else {
            return true;
        }//end of else
    }//end of isValidInput

    /**
     * Prints the current state of the player's tracking grid.
     * This method displays the grid, showing hits, misses, and untried locations.
     *
     * @param grid The tracking grid to print.
     */
    static void printGrid(char[][] grid) {


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j <= grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }//end of nested for
            System.out.println();
        }//end of for
    }//end of printgrid
