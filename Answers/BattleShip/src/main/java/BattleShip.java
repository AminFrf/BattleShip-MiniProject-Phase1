import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 The BattleShip class manages the gameplay of the Battleship game between two players.
 It includes methods to manage grids, turns, and check the game status.
 */
public class Main {

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


        printGrid(player1Grid);
        System.out.println();
        printGrid(player2Grid);



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
        System.out.println();
        System.out.println("⠀⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀⠀⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠈⢻⣿⣿⡄⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⣸⣿⡏⠀⠀⠀⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣷⡄⠀ \n" +
                "⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⠀ \n" +
                "⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿⠀ \n" +
                "⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀ \n" +
                "⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇⠀⠀ \n" +
                "⠀⣿⣿⠁⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⢸⣿⣧⠀⠀ \n" +
                "⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⢸⣿⣿⠀⠀ \n" +
                "⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⢸⣿⣿⠀⠀ \n" +
                "⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⢸⣿⡇⠀⠀ \n" +
                "⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⣿⣿⠃⠀⠀ \n" +
                "⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⢠⣿⣿⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀ ⢸⣿⡇⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀ ⣸⣿⠇⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏⠀⠀⠀⠀ \n" +
                "⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
    }//end of main

    /**
     * Initializes the grid by filling it with water ('~').
     *
     * @param grid The grid to initialize.
     */
    static void initializeGrid(char[][] grid) {
        //int numrow = 0 ;
        for (int i = 0; i <= 9; i++) {
            //  System.out.println(numrow);
            for (int j = 0; j <= 9; j++) {
                grid[i][j] = '~';
            }//end of nested for
            //   numrow++;
        }//end of for
    }//initializeGrid

    /**
     * Places ships randomly on the given grid.
     * This method is called for both players to place their ships on their respective grids.
     *
     * @param grid The grid where ships need to be placed.
     */
    static void placeShips(char[][] grid) {
        Random rand = new Random();
        int row = 0 ;
        int col = 0 ;

        int random  ;
        for (int i = 2; i <= 5; i++) {
            random = ThreadLocalRandom.current().nextInt(2);
            boolean horizontal = true ;
            if (random == 0) {
                //ofoghi
                horizontal = true;
                while(!canPlaceShip(grid , row , col , i , horizontal)){
                    row = ThreadLocalRandom.current().nextInt(10);
                    col = ThreadLocalRandom.current().nextInt(10) ;
                    while (col > (9 - i) + 1) {
                        col = ThreadLocalRandom.current().nextInt(10);
                    }//end of while

                }
                for (int j = 0; j < i ; j++) {
                    grid[row][col] = '#' ;
                    col++;
                }//end of for*
            }//end of if*

            else {
                //amoodi
                horizontal = false;
                while(!canPlaceShip(grid , row , col , i , horizontal)){
                    row = ThreadLocalRandom.current().nextInt(10);
                    col = ThreadLocalRandom.current().nextInt(10) ;
                    while (row > (9 - i) + 1) {
                        row = ThreadLocalRandom.current().nextInt(10);
                    }//end of while loop
                }//end of while
                //if(canPlaceShip(char[][] grid, row , col1 , i , true)==true){
                for (int j = 0; j < i ; j++) {
                    grid[row][col] = '#' ;
                    row++;
                }//end of for*
            }//end of if*

        }//end of if
    }//end of placeShips


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
    static boolean canPlaceShip(char [][] grid, int row, int col, int size, boolean horizontal) {
        if(horizontal){
            if(col + size > 10){
                return false ;
            }//end of if
        }//end of if
        else{
            if(row + size > 9){
                return false ;
            }//end of if
        }//end of else
        for (int i = 0; i < size ; i++) {
            if (grid[row][col] != '~'){
                return false ;
            }//end of if
            /*
            if(grid[row][col+1]=='#' || grid[row+1][col]=='#' || grid[row-1][col] == '#' || grid[row+1][col+1]=='#' || grid[row-1][col-1]=='#'){
                return false;
            }//end of if
            */
            if(((row+1)<9 && grid[row+1][col] == '#' )|| ((row-1)>=0 &&  grid[row-1][col] == '#')){
                return false ;
            }
            if(((col+1)<9 && grid[row][col+1] == '#' )|| ((col-1)>=0 &&  grid[row][col-1] == '#')){
                return false ;
            }

            if(horizontal){
                col++;
            }//end of if
            else{
                row++;
            }//end of else

        }//end of for
        return true ;
       /*
        boolean empty = true;
        int num = 0 ;
        if (horizontal==true) {
            while(num<size) {
                if (grid[row][col] == '~') {
                    empty = true ;
                    col++;
                    num++;
                }//end pf if
                else {
                    return false ;
                }//end of else

            }//end of while
            return true;
        }//end of if

        if (horizontal==false) {
            while(num<size)  {
                if (grid[row][col] == '~') {
                    empty = true ;
                    row++;
                    num++;
                }//end pf if
                else {
                    return false ;
                }//end of else

            }//end of while
            return true;
        }//end of if*/

    }//end of canPlaceShip

    /**
     * Manages a player's turn, allowing them to attack the opponent's grid
     * and updates their tracking grid with hits or misses.
     *
     * @param opponentGrid The opponent's grid to attack.
     * @param trackingGrid The player's tracking grid to update.
     */
    static void playerTurn(char[][] opponentGrid, char[][] trackingGrid) {
        String coor = "" ;

        System.out.print("please enter your target (for example A5) : ");
        coor = scanner.next();
        if(!isValidInput(coor)) {
            System.out.println("invalid input!!!");
        }//end of if
        else{
            char [] ch = coor.toCharArray();
            int col = (int) ch[0];
            col = col - 65 ;
            int row = (int) ch[1];
            row = row - 48 ;
            int [] tar = { 0 , 0 };
            tar [0] = col ;
            tar [1] = row ;
            if(trackingGrid[tar[1]][tar[0]]== '*' || trackingGrid[tar[1]][tar[0]]== 'O'  ){
                System.out.println("Duplicate coordinates !!!");
            }//end of else
            else {
                if(opponentGrid[tar[1]][tar[0]] == '#'){
                    opponentGrid[tar[1]][tar[0]]='*' ;
                    trackingGrid[tar[1]][tar[0]]='*' ;
                    System.out.println("HIT!");
                }//end of else
                if(opponentGrid[tar[1]][tar[0]]== '~'){
                    trackingGrid[tar[1]][tar[0]]='O' ;
                    System.out.println("MISS!");
                }//end of if
            }//end of
        }//end of else


    }//end of playerturn

    /**
     * Checks if the game is over by verifying if all ships are sunk.
     *
     * @return true if the game is over (all ships are sunk), false otherwise.
     */
    static boolean isGameOver() {
        if(allShipsSunk(player1Grid) || allShipsSunk(player2Grid)) {

            return true;
        }
        else{
            return false;
        }
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
        int code = (int)chars[0];
        if (code > 74 || code < 65) {
            return false;
        }//end of else if
        else if(chars[1] > '9' || chars[1]<'0'){
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
        int rownum = 0 ;
        char colnum  ;
        System.out.print("  ");
        for (int i = 65 ; i <= 74; i++) {
            colnum = (char)i;
            System.out.print(colnum + " ");
        }
        System.out.println();

        for (int i = 0; i < grid.length; i++) {
            System.out.print(rownum + " ");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }//end of nested for
            rownum++;
            System.out.println();
        }//end of for
    }//end of printgrid
}//end of battle ship
