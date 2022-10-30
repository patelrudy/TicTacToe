import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int playerNum = TicTacToe.getNumberPlayers(input);
        char player1 = 'X';
        char player2 = 'O';
        if (playerNum == 1) {

            //player vs computer
            onePlayerSimulator(input);
        }

        if (playerNum == 2) {
            //player vs player
            twoPlayerSimulator(input);
        }

    }

    public static void onePlayerSimulator(Scanner input) {

        Board ticTackToeBoard = new Board();
        Random rand = new Random();
        int x = 0;
        int y = 0;
        Location loc = new Location(0, 0);
        Location loc3 = new Location(0, 0);

        System.out.println(ticTackToeBoard.toString());


        while (ticTackToeBoard.getGameState() == GameState.ONGOING) {


            do {

                loc = TicTacToe.getInput("1", input);

            } while (!ticTackToeBoard.validLocation(loc));

            ticTackToeBoard.placingChar(loc, 'X');

            System.out.println(ticTackToeBoard.toString());


            if (ticTackToeBoard.getGameState() == GameState.PLAYER1_WIN) {
                break;
            }

            if (ticTackToeBoard.getGameState() == GameState.TIE) {
                break;
            }


            System.out.println("Computer is making a move: ");
            do {
                x = rand.nextInt(3);
                y = rand.nextInt(3);

                loc3 = new Location(x, y);

            } while (!ticTackToeBoard.validLocation(loc3));

            ticTackToeBoard.placingChar(loc3, 'O');

            System.out.println(ticTackToeBoard.toString());


            if (ticTackToeBoard.getGameState() == GameState.TIE) {
                break;
            }

        }

        if (ticTackToeBoard.getGameState() == GameState.PLAYER1_WIN) {
            System.out.println("Player 1 wins!");
        }

        if (ticTackToeBoard.getGameState() == GameState.PLAYER2_WIN) {
            System.out.println("Computer wins!");
        }

        if (ticTackToeBoard.getGameState() == GameState.TIE) {
            System.out.println("It's a Tie!");
        }

    }


    public static void twoPlayerSimulator(Scanner input) {


        Board ticTackToeBoard = new Board();

        Location loc = new Location(0, 0);
        Location loc2 = new Location(0, 0);


        System.out.println(ticTackToeBoard.toString());

        while (ticTackToeBoard.getGameState() == GameState.ONGOING) {


            do {

                loc = TicTacToe.getInput("1", input);

            } while (!ticTackToeBoard.validLocation(loc));

            ticTackToeBoard.placingChar(loc, 'X');

            System.out.println(ticTackToeBoard.toString());

            if (ticTackToeBoard.getGameState() == GameState.PLAYER1_WIN) {
                break;
            }

            if (ticTackToeBoard.getGameState() == GameState.TIE) {
                break;
            }

            do {

                loc2 = TicTacToe.getInput("2", input);

            } while (!ticTackToeBoard.validLocation(loc2));


            ticTackToeBoard.placingChar(loc2, 'O');

            System.out.println(ticTackToeBoard.toString());


            if (ticTackToeBoard.getGameState() == GameState.TIE) {
                break;
            }

        }

        if (ticTackToeBoard.getGameState() == GameState.PLAYER1_WIN) {
            System.out.println("Player 1 wins!");
        }

        if (ticTackToeBoard.getGameState() == GameState.PLAYER2_WIN) {
            System.out.println("Player 2 wins!");
        }

        if (ticTackToeBoard.getGameState() == GameState.TIE) {
            System.out.println("It's a Tie!");
        }

    }

    /**
     * Gets number of players from command line
     * @param sc        for reading from command-line
     * @return number of players (always 1 or 2)
     */
    private static int getNumberPlayers(Scanner sc) {
        boolean repeatPrompt = true;
        int numPlayers = 0;
        while (repeatPrompt) {
            System.out.print("How many players (1 or 2)? ");
            String input = sc.next();
            try {
                numPlayers = Integer.parseInt(input);
                if (numPlayers == 1 || numPlayers == 2) {
                    repeatPrompt = false;
                } else {
                    System.out.println("Enter 1 or 2 players.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please only enter a number.");
            }
        }
        return numPlayers;
    }

    /**
     * Handles scanner calls and user input
     * @param player    whose turn it is
     * @param sc        for reading from command-line
     * @return int[] holding row, column in that order
     */
    private static Location getInput(String player, Scanner sc) {
        boolean repeatPrompt = true;
        int row = -1;
        int col = -1;
        while (repeatPrompt) {
            System.out.print("Enter desired square for " + player + ": ");
            String input = sc.next();
            input = input.trim();
            String[] splitInput = input.split(",");
            try {
                row = Integer.parseInt(splitInput[0]);
                col = Integer.parseInt(splitInput[1]);
                repeatPrompt = false;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Please follow the format 'row,col'; for ex '1,2'");
            }
        }
        Location loc = new Location(row, col);
        return loc;
    }

}


