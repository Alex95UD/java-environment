package game;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Entry point and complete console implementation of a Tic Tac Toe game.
 *
 * <p>This class contains the full game flow, board state management, input validation,
 * winner detection, draw detection, score tracking, and restart logic for a two-player
 * console-based Tic Tac Toe game.</p>
 *
 * <p>Even though the entire solution is contained in a single class as requested,
 * the code is organized into small, focused methods to improve readability,
 * maintainability, and testability.</p>
 *
 * <p>Game rules:</p>
 * <ul>
 *   <li>The board is 3x3.</li>
 *   <li>Two players participate: Player X and Player O.</li>
 *   <li>Players alternate turns.</li>
 *   <li>A move is valid only if the selected cell is empty.</li>
 *   <li>A player wins by placing three marks in a row, column, or diagonal.</li>
 *   <li>If the board is full and nobody wins, the game ends in a draw.</li>
 * </ul>
 *
 * <p>Console messages are displayed in Spanish, while source code and Javadoc
 * are written in English as requested.</p>
 *
 * @author OpenAI
 * @version 1.0
 * @since 17
 */
public class Main {

    /**
     * Board size for the Tic Tac Toe game.
     */
    private static final int BOARD_SIZE = 3;

    /**
     * Marker used for empty cells.
     */
    private static final char EMPTY = ' ';

    /**
     * Scanner used to read user input from console.
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Internal game board.
     */
    private static final char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    /**
     * Current player marker.
     */
    private static char currentPlayer = 'X';

    /**
     * Score counter for player X.
     */
    private static int scoreX = 0;

    /**
     * Score counter for player O.
     */
    private static int scoreO = 0;

    /**
     * Draw counter.
     */
    private static int draws = 0;

    /**
     * Application entry point.
     *
     * @param args command line arguments, not used
     */
    public static void main(String[] args) {
        printWelcomeMessage();
        boolean keepPlaying = true;

        while (keepPlaying) {
            startNewGame();
            playSingleMatch();
            printScoreboard();
            keepPlaying = askIfUserWantsToPlayAgain();
        }

        printFarewellMessage();
        SCANNER.close();
    }

    /**
     * Prints the initial game welcome message.
     */
    private static void printWelcomeMessage() {
        System.out.println("==========================================");
        System.out.println("      BIENVENIDO AL JUEGO TIC TAC TOE     ");
        System.out.println("==========================================");
        System.out.println("Reglas básicas:");
        System.out.println("- Juegan 2 personas: X y O.");
        System.out.println("- Gana quien logre 3 símbolos en línea.");
        System.out.println("- Puede ser fila, columna o diagonal.");
        System.out.println("- Si se llena el tablero y nadie gana, es empate.");
        System.out.println();
    }

    /**
     * Initializes a new game by clearing the board and setting the initial player.
     */
    private static void startNewGame() {
        initializeBoard();
        currentPlayer = 'X';
        System.out.println("Se inicia una nueva partida.");
        System.out.println("El jugador X comienza.");
        System.out.println();
    }

    /**
     * Runs the main loop for a single match until a win or draw occurs.
     */
    private static void playSingleMatch() {
        boolean gameFinished = false;

        while (!gameFinished) {
            printBoard();
            System.out.println("Turno del jugador " + currentPlayer + ".");
            int[] move = readValidMove();
            placeMove(move[0], move[1], currentPlayer);

            if (hasPlayerWon(currentPlayer)) {
                printBoard();
                System.out.println("¡El jugador " + currentPlayer + " ha ganado!");
                updateWinnerScore(currentPlayer);
                gameFinished = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("La partida terminó en empate.");
                draws++;
                gameFinished = true;
            } else {
                switchPlayer();
            }
        }
    }

    /**
     * Fills the entire board with empty markers.
     */
    private static void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                board[row][column] = EMPTY;
            }
        }
    }

    /**
     * Prints the board to the console in a readable format.
     *
     * <p>Empty cells are displayed using numbers from 1 to 9 to guide
     * the user when selecting a move.</p>
     */
    private static void printBoard() {
        System.out.println();
        System.out.println("Tablero actual:");
        System.out.println();

        int cellNumber = 1;

        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print(" ");

            for (int column = 0; column < BOARD_SIZE; column++) {
                char cellValue = board[row][column];
                char displayValue = (cellValue == EMPTY) ? Character.forDigit(cellNumber, 10) : cellValue;

                System.out.print(displayValue);

                if (column < BOARD_SIZE - 1) {
                    System.out.print(" | ");
                }

                cellNumber++;
            }

            System.out.println();

            if (row < BOARD_SIZE - 1) {
                System.out.println("---+---+---");
            }
        }

        System.out.println();
    }

    /**
     * Reads and returns a valid move selected by the current player.
     *
     * <p>The user must enter a number between 1 and 9. The method validates
     * that the input is numeric, within range, and references an available cell.</p>
     *
     * @return an array containing row and column positions in that order
     */
    private static int[] readValidMove() {
        while (true) {
            int selectedPosition = readPositionFromUser();

            if (!isPositionInRange(selectedPosition)) {
                System.out.println("Entrada inválida. Debe ingresar un número entre 1 y 9.");
                continue;
            }

            int[] coordinates = convertPositionToCoordinates(selectedPosition);
            int row = coordinates[0];
            int column = coordinates[1];

            if (!isCellAvailable(row, column)) {
                System.out.println("La casilla seleccionada ya está ocupada. Intente nuevamente.");
                continue;
            }

            return coordinates;
        }
    }

    /**
     * Reads a board position from the user.
     *
     * @return the numeric board position entered by the user
     */
    private static int readPositionFromUser() {
        while (true) {
            try {
                System.out.print("Jugador " + currentPlayer + ", ingrese una posición (1-9): ");
                int position = SCANNER.nextInt();
                SCANNER.nextLine();
                return position;
            } catch (InputMismatchException exception) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
                SCANNER.nextLine();
            }
        }
    }

    /**
     * Verifies whether the entered board position is within the valid range.
     *
     * @param position user-selected position
     * @return {@code true} if the position is between 1 and 9; otherwise {@code false}
     */
    private static boolean isPositionInRange(int position) {
        return position >= 1 && position <= 9;
    }

    /**
     * Converts a board position from 1-9 into row and column coordinates.
     *
     * <p>The mapping is performed from left to right and top to bottom:</p>
     * <pre>
     * 1 | 2 | 3
     * 4 | 5 | 6
     * 7 | 8 | 9
     * </pre>
     *
     * @param position board position from 1 to 9
     * @return an array containing row and column
     */
    private static int[] convertPositionToCoordinates(int position) {
        int zeroBasedPosition = position - 1;
        int row = zeroBasedPosition / BOARD_SIZE;
        int column = zeroBasedPosition % BOARD_SIZE;
        return new int[]{row, column};
    }

    /**
     * Checks whether a specific board cell is available.
     *
     * @param row target row
     * @param column target column
     * @return {@code true} if the cell is empty; otherwise {@code false}
     */
    private static boolean isCellAvailable(int row, int column) {
        return board[row][column] == EMPTY;
    }

    /**
     * Places a player's mark on the specified board position.
     *
     * @param row target row
     * @param column target column
     * @param playerMark mark to place
     */
    private static void placeMove(int row, int column, char playerMark) {
        board[row][column] = playerMark;
    }

    /**
     * Determines whether the specified player has won the game.
     *
     * @param playerMark mark to evaluate
     * @return {@code true} if the player has a complete row, column, or diagonal;
     *         otherwise {@code false}
     */
    private static boolean hasPlayerWon(char playerMark) {
        return hasCompletedAnyRow(playerMark)
                || hasCompletedAnyColumn(playerMark)
                || hasCompletedMainDiagonal(playerMark)
                || hasCompletedSecondaryDiagonal(playerMark);
    }

    /**
     * Checks whether the player has completed any row.
     *
     * @param playerMark mark to evaluate
     * @return {@code true} if any row is fully occupied by the same mark
     */
    private static boolean hasCompletedAnyRow(char playerMark) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            boolean fullRow = true;

            for (int column = 0; column < BOARD_SIZE; column++) {
                if (board[row][column] != playerMark) {
                    fullRow = false;
                    break;
                }
            }

            if (fullRow) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether the player has completed any column.
     *
     * @param playerMark mark to evaluate
     * @return {@code true} if any column is fully occupied by the same mark
     */
    private static boolean hasCompletedAnyColumn(char playerMark) {
        for (int column = 0; column < BOARD_SIZE; column++) {
            boolean fullColumn = true;

            for (int row = 0; row < BOARD_SIZE; row++) {
                if (board[row][column] != playerMark) {
                    fullColumn = false;
                    break;
                }
            }

            if (fullColumn) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether the player has completed the main diagonal.
     *
     * @param playerMark mark to evaluate
     * @return {@code true} if the main diagonal is fully occupied by the same mark
     */
    private static boolean hasCompletedMainDiagonal(char playerMark) {
        for (int index = 0; index < BOARD_SIZE; index++) {
            if (board[index][index] != playerMark) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether the player has completed the secondary diagonal.
     *
     * @param playerMark mark to evaluate
     * @return {@code true} if the secondary diagonal is fully occupied by the same mark
     */
    private static boolean hasCompletedSecondaryDiagonal(char playerMark) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            int column = BOARD_SIZE - 1 - row;
            if (board[row][column] != playerMark) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether the board is completely full.
     *
     * @return {@code true} if there are no empty cells left; otherwise {@code false}
     */
    private static boolean isBoardFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (board[row][column] == EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Switches the current active player.
     */
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    /**
     * Updates the score of the winning player.
     *
     * @param playerMark winning player mark
     */
    private static void updateWinnerScore(char playerMark) {
        if (playerMark == 'X') {
            scoreX++;
        } else {
            scoreO++;
        }
    }

    /**
     * Prints the accumulated scoreboard to the console.
     */
    private static void printScoreboard() {
        System.out.println();
        System.out.println("Marcador acumulado:");
        System.out.println("Jugador X: " + scoreX);
        System.out.println("Jugador O: " + scoreO);
        System.out.println("Empates  : " + draws);
        System.out.println();
    }

    /**
     * Asks the user if a new match should be started.
     *
     * @return {@code true} if the user wants to continue playing; otherwise {@code false}
     */
    private static boolean askIfUserWantsToPlayAgain() {
        while (true) {
            System.out.print("¿Desean jugar otra partida? (S/N): ");
            String answer = SCANNER.nextLine().trim().toUpperCase();

            if ("S".equals(answer)) {
                System.out.println();
                return true;
            }

            if ("N".equals(answer)) {
                return false;
            }

            System.out.println("Respuesta inválida. Ingrese S para sí o N para no.");
        }
    }

    /**
     * Prints the farewell message before closing the application.
     */
    private static void printFarewellMessage() {
        System.out.println();
        System.out.println("Gracias por jugar Tic Tac Toe.");
        System.out.println("Programa finalizado.");
    }
}