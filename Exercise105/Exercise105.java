package gr.aueb.cf.ch10.Exercises.Exercise105;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Project 5
 * Έστω ένα θέατρο που έχει θέσεις όπου η κάθε θέση περιγράφεται με ένα χαρακτήρα που είναι η στήλη και ένα αριθμό που είναι η σειρά. Για παράδειγμα η θέση C2 βρίσκεται στην 2η σειρά και 3η στήλη. Αναπτύξτε ένα πρόγραμμα διαχείρισης θεάτρου με 30 σειρές και 12 στήλες. Πιο συγκεκριμένα γράψτε μία μέθοδο void book(char column, int row) που να κάνει book  μία θέση αν δεν είναι ήδη booked και μία μέθοδο void cancel(char column, int row) που να ακυρώνει την κράτηση μία θέσης αν είναι ήδη booked.
 * Hint. Υποθέστε ότι ο δυσδιάστατος πίνακας που απεικονίζει το θέατρο είναι ένα πίνακας από boolean, όπου το true σημαίνει ότι η θέση είναι booked και false ότι δεν είναι booked. Αρχικά όλες οι θέσεις πρέπει να είναι non-booked.
 */
public class Exercise105 {

    // Make theater array universal to be accesed by all
    static boolean[][] theater = new boolean[30][12];

    public static void main(String[] args) {
//        boolean[][] theater = new boolean[30][12];
        theater = arrayInitialiser(theater);
        Scanner in = new Scanner(System.in);
        boolean reRun = true;
        char input1;


        while (reRun){
            clearScreen();
            System.out.println("the theater is empty: " + isEmpty());
            displayBooked();
            int[] input = userInt(in, theater);
            if (input[0] == 1) {
                input1 = (char) numLetterConventor(input[1]);
                book(input1, input[2]);
            } else if (input[0] == 2){
                input1 = (char) numLetterConventor(input[1]);
                cancel(input1, input[2]);
            }
            reRun = reRun(in);
        }
    }

    public static boolean reRun(Scanner in) {
        boolean reRun = true;

        System.out.println("Exit: 0, Rerun: 1");
        while (true) {
            try {
                int input = Integer.valueOf(in.nextLine());
                if (input == 0){
                    reRun = false;
                    return reRun;
                } else if (input == 1) {
                    reRun = true;
                    return reRun;
                } else {
                    continue;
                }

            } catch (NumberFormatException e) {
                System.out.println("invalid input. Please enter a valid number (0 or 1)");
            }
        }
    }

    public static int[] userInt(Scanner in, boolean[][] theater){
        boolean run = true;
        boolean innerRun = true;
        int choice = -1;
        int rowNum = -1;
        int columnCharNum = -1;
        boolean isAvailable;

        System.out.println("Do you want to book (1) or Cancel (2)");

        while(run) {
            try {
                choice = Integer.valueOf(in.nextLine());
                if (choice == 1 || choice == 2) {
                    if (choice == 2 && isEmpty()) {
                        System.out.println("The theater is empty. No cancellation possible.");
                        System.exit(-1);
                    }
                    while (innerRun) {
                        int[] place = columnNumInterface(in);
                        columnCharNum = place[0];
                        rowNum = place[1];

                        isAvailable = checkAvailability(columnCharNum, rowNum, theater);
                        if (choice == 1) {
                            if (!isAvailable) {
                                System.out.println("I'm sorry, the seat is already booked. Please choose another seat.");
                                innerRun = true;
                                continue;
                            } else {
                                //TODO
                                System.out.println("seat booked");
                                innerRun = false;
                            }
                        }
                        if (choice == 2) {
                            if (isAvailable) {
                                System.out.println("I'm sorry, the seat is not booked. Please choose another seat.");
                                innerRun = true;
                                continue;
                            } else {
                                //TODO
                                System.out.println("seat canceled");
                                innerRun = false;
                            }
                        }
                    }
                    run = false;
                } else {
                    System.out.println("invalid input. Please enter a valid number (1 or 2)");
                }
            } catch (NumberFormatException  e) {
                System.out.println("invalid input. Please enter a valid number (1 or 2)");
            }
        }
        int[] input = {choice, columnCharNum, rowNum};
        return input;
    }

    public static int[] columnNumInterface (Scanner in) {
        boolean innerRun = true;
        int rowNum = -1;
        int columnCharNum = -1;

        innerRun = true;
        while(innerRun) {
            System.out.println("Please give column letter (A-L)");
            String columnChar = in.nextLine().trim().toLowerCase();
            if (columnChar.length() == 1 && columnChar.matches("[a-l]")) {
                columnCharNum = letterNumConventor(columnChar);
                innerRun = false;
            } else {
                System.out.println("Invalid input. Please enter a valid column letter (A-Z).");
            }
        }

        innerRun = true;
        while(innerRun) {
            System.out.println("Please give row number (1-30)");

            try {
                rowNum = Integer.valueOf(in.nextLine());
            } catch (NumberFormatException  e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                continue;
            }


            if (rowNum >= 1 && rowNum <= 30) {
                innerRun = false;
            } else {
                System.out.println("Invalid input. Please enter a valid row number (1-30).");
            }
        }
        int[] place = {columnCharNum, rowNum};
        return place;
    }

    public static int letterNumConventor (String ch){
        //Gets the character at index 0. 'a' (97 in Unicode). +1 because 0indexing
        int columnCharNum = ch.charAt(0) - 'a' +1;
        return columnCharNum;
    }

    public static char numLetterConventor (int num) {
        return (char) ('a' + num - 1);
    }

    public static boolean checkAvailability (int column, int row, boolean[][] theater){
        boolean isAvailable = true;

//        if (!theater[row-1][column-1]){
//            isAvailable = false;
//        }
//        return isAvailable;
        return theater[row - 1][column - 1];
    }

    public static boolean[][] arrayInitialiser (boolean[][] theater){

        for (int i = 0; i < theater.length; i++) {
            for (int j = 0; j < theater[i].length; j++) {
                theater[i][j] = true;
            }
        }
        return theater;
    }

    public static void book(char column, int row) {
        String col = Character.toString(column);
        int colNum = letterNumConventor(col);
        theater[row - 1][colNum - 1] = false;
    }

    public static void cancel(char column, int row) {
        String col = Character.toString(column);
        int colNum = letterNumConventor(col);
        theater[row - 1][colNum - 1] = true;
    }

    public static void clearScreen() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }

    public static void displayBooked(){
        System.out.println("Booked seats: ");
        for (int i = 0; i < theater.length; i++) {
            for (int j = 0; j < theater[i].length; j++) {
                if (theater[i][j] == false){
                    // GPT: Adjusting column number to letter manually
                    char columnLetter = (char) ('A' + j);
                    System.out.print("" + columnLetter + (i + 1) + " | ");
                }
            }
        }
        System.out.println();
    }

    public static boolean isEmpty() {
        for (boolean[] row: theater) {
            for (boolean el: row) {
                if (!el) {
                    return false;
                }
            }
        }
        return true;
    }
}
