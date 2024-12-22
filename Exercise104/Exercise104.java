package gr.aueb.cf.ch10.Exercises.Exercise104;

import java.util.Scanner;

/**
 * Project 04
 * Αναπτύξτε ένα παιχνίδι Τρίλιζα, όπου δύο παίκτες παίζουν Χ και Ο (ή 1 και 2 αν θέλετε να υλοποιήσετε με πίνακα ακεραίων και όχι με πίνακα char) και κερδίζει ο παίκτης που έχει συμπληρώσει τρία ίδια σύμβολα ή αριθμούς σε οποιαδήποτε διάσταση του πίνακα, οριζόντια, κάθετα ή διαγώνια. Η main() μπορεί να ελέγχει τη ροή του παιχνιδιού, όπως ποιος παίκτης παίζει κάθε φορά (εναλλαγή μεταξύ των δύο παικτών), να διαβάζει από το stdin το σύμβολο που δίνει ο κάθε παίκτης και να εμφανίζει με γραφικό τρόπο την τρίλιζα μετά από κάθε κίνηση κάθε παίκτη. Ενώ, μπορείτε να δημιουργήσετε και μία μέθοδο που να ελέγχει (μετά από κάθε κίνηση) αν ο παίκτης που έκανε την κίνηση έκανε τρίλιζα. Το πρόγραμμα θα πρέπει να λαμβάνει υπόψη την περίπτωση ισοπαλίας όπως και να μην επιτρέπει ένας παίκτης να παίξει σε θέση που είναι ήδη κατειλημμένη.
 */
public class Exercise104 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean replay = true;

        while (replay) {
            try {
                System.out.println("starting Tic-Tac-Toe...");
                String emptyBoard[][] = gridCreator();
                gameplay(emptyBoard, in);

                System.out.print("Do you want to play again? (yes/no): ");
                in.nextLine();
                String response = in.nextLine().trim().toLowerCase();

//                replay = response.equals("yes");
                // Check if the response is "yes" or "no"

                if (response.equals("yes")) {
                    replay = true;
                } else if (response.equals("no")) {
                    replay = false;
                } else {
                    System.out.println("Invalid input. Please type 'yes' or 'no'. Exiting... GG bb");
                    replay = false;
                }

            } catch (Exception e) {
                System.err.println("ERROR");
                e.printStackTrace();
            }
        }
    }

    public static String[][] gridCreator() {
        String[][] board = new String[3][3];
//        for (String[] row: board) {
//            for (String el: row) {
//                el = " ";
//            }
//        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";
            }
        }
    return board;
    }

    public static void gameplay(String[][] board, Scanner in) {

        boolean isXplaying = true;
        boolean isGameOver = false;

        while (!isGameOver) {
            if (isXplaying) {
                xRound(board, in);
                isXplaying = false;
            } else {
                yRound(board, in);
                isXplaying = true;
            }

            int gameResult = isGameOver(board);
            if (gameResult != 0) {
                switch (gameResult) {
                    case 1 -> System.out.println("It's a draw!");
                    case 2 -> System.out.println("X wins!");
                    case 3 -> System.out.println("Y wins!");
                }
                return;
            }
        }
    }

    public static String[][] xRound(String[][] board, Scanner in) {
        //TODO screen must be cleaned here and i dont know how to do it
        clearScreen();

        System.out.println("it's X round. X-Player please choose an option");
        showGrid(board);

        int[] move = new int[2];
        while (true) {
            System.out.print("Enter column (0, 1, or 2): ");

            //problem
            if (!in.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 0 and 2.");
                in.next();
                continue;
            }

            move[0] = in.nextInt();
            System.out.print("Enter row (0, 1, or 2): ");

            //problem
            if (!in.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 0 and 2.");
                in.next();
                continue;
            }

            move[1] = in.nextInt();

            if (moveChecker(board,move)) {
                board[move[1]][move[0]] = "X";
                break;
            }
        }
        return board;
    }

    public static String[][] yRound(String[][] board, Scanner in) {
        clearScreen();

        System.out.println("it's Y round. Y-Player please choose an option");
        showGrid(board);

        int[] move = new int[2];
        while (true) {
            System.out.print("Enter column (0, 1, or 2): ");
            //problem
            if (!in.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 0 and 2.");
                in.next();
                continue;
            }
            move[0] = in.nextInt();
            System.out.print("Enter row (0, 1, or 2): ");
            //problem
            if (!in.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 0 and 2.");
                in.next();
                continue;
            }
            move[1] = in.nextInt();

            if (moveChecker(board,move)) {
                board[move[1]][move[0]] = "Y";
                break;
            }
        }
        return board;
    }

    public static void showGrid(String[][] board){
        int biggerSize = 0;
        if (board[0].length >= board[1].length && board[0].length >= board[2].length){
            biggerSize = board[0].length;
        } else if (board[1].length >= board[0].length && board[1].length >= board[2].length) {
            biggerSize = board[1].length;
        } else {
            biggerSize = board[0].length;
        }

        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        for (int i = 0; i < biggerSize; i++) {
            System.out.print("----");
        }
        System.out.println();
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);        for (int i = 0; i < biggerSize; i++) {
            System.out.print("----");
        }
        System.out.println();
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }

    public static boolean moveChecker(String[][] board, int[] move){

        try {
            if (
                    move[0] < 0 ||
                            move[0] >= board[0].length ||
                            move[1] < 0 ||
                            move[1] >= board.length
            ) {
                System.out.println("Invalid move. Please enter a row and column between 0 and 2.");
                return false;
            }
            if (board[move[1]][move[0]].equals(" ")) {
                return true;
            } else {
                System.out.println("That spot is already taken. Please try again.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter numbers between 0 and 2.");
            return false;
        }
    }

    public static void clearScreen() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
        // Print 50 newlines to simulate clearing the screen -> gpt solution
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static int isGameOver (String[][] board) {
        boolean draw = drawChecker(board);
        boolean xWins = xWinChecker(board);
        boolean yWins = yWinChecker(board);

        if (draw) {
            return 1;
        } else if (xWins) {
            return 2;
        } else if (yWins) {
            return 3;
        } else {
            return 0;
        }
    }

    public static boolean drawChecker(String[][] board) {
        for (String[] row: board) {
            for (String el: row) {
                if (el.equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean xWinChecker (String[][] board) {
        return winChecker("X", board);
    }

    public static boolean yWinChecker (String[][] board) {
        return winChecker("Y", board);
    }

    public static boolean winChecker(String c, String[][] board){
        int length = board[0].length;


        for (int i = 0; i < length; i++) {
            // check row
            if (
                board[0][i].equals(c) &&
                board[1][i].equals(c) &&
                board[2][i].equals(c)
            ) {
                return true;
            }

            // check columns
            if (
                board[i][0].equals(c) &&
                board[i][1].equals(c) &&
                board[i][2].equals(c)
            ){
                return true;
            }
        }
        //check diagonals
        if (
            board[0][0].equals(c) &&
            board[1][1].equals(c) &&
            board[2][2].equals(c)
        ) {
            return true;
        }

        if (
            board[0][2].equals(c) &&
            board[1][1].equals(c) &&
            board[2][0].equals(c)
        ) {
            return true;
        }
        return false;
    }
}
