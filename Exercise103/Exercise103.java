package gr.aueb.cf.ch10.Exercises.Exercise103;


import java.io.File;
import java.io.FileNotFoundException;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

/**
 * Αναπτύξτε μία εφαρμογή που διαβάζει έναν-έναν τους χαρακτήρες ενός αρχείου και τους εισάγει σε ένα πίνακα 128x2. Υποθέστε ότι οι χαρακτήρες είναι λατινικοί. Κάθε θέση του πίνακα είναι επομένως ένας πίνακας δύο θέσεων, όπου στην 1η θέση αποθηκεύεται ο χαρακτήρας που έχει διαβαστεί (αν δεν υπάρχει ήδη στον πίνακα) και στην 2η θέση αποθηκεύεται το πλήθος των φορών που έχει διαβαστεί (βρεθεί) κάθε χαρακτήρας. Αγνοήστε τα κενά και τις αλλαγές γραμμής και γενικά τα whitespaces. Στο τέλος η main() παρουσιάζει στατιστικά στοιχεία για κάθε χαρακτήρα όπως η συχνότητα εμφάνισής του στο κείμενο ταξινομημένα ανά χαρακτήρα και ανά συχνότητα εμφάνισης.
 */
public class Exercise103 {
    public static void main(String[] args) {
        String path = "C:\\Users\\User\\IdeaProjects\\CF7-TestBed\\src\\gr\\aueb\\cf\\ch10\\Exercises\\Exercise103\\loremIpsum.txt";

        try {
            System.out.println("starting filesizer...");
            int size = fileSizer(path);

            System.out.println("starting file reader...");
            char[] fullTextChar = fileReader(path, size);

            System.out.println("starting space remover...");
            char[] fullTextCharClean = spaceRemover(fullTextChar);

            System.out.println(("starting array indexing..."));
            int[][] charMap = arrayIndexer(fullTextCharClean);

            System.out.println("starting printer...");
            printer(charMap);



        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("ERROR: ArrayIndexOutOfBoundsException");
            e.printStackTrace();
        }
    }

    public static char[] fileReader(String path, int size) throws FileNotFoundException, ArrayIndexOutOfBoundsException {

        int index = 0;
        String line;
        File fd = new File(path);
        char[] fullTextChar = new char[size];

        try (Scanner in = new Scanner(fd)) {
            while (in.hasNextLine()) {
                line = in.nextLine();
                char[] ch = line.toCharArray();
                for (int i = 0; i < ch.length; i++){
                    fullTextChar[index] = ch[i];
                    index++;
                }
            }
            System.out.println("fullTextChar length: " + fullTextChar.length);
            return fullTextChar;

        } catch (FileNotFoundException e) {
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public static int fileSizer (String path) throws FileNotFoundException {
        int totalChars = 0;
        String line;
        File fd = new File(path);

        try (Scanner in = new Scanner(fd)) {
            while (in.hasNextLine()) {
                line = in.nextLine();
                totalChars += line.length();
            }
            System.out.println("Total chars in file: " + totalChars);
            System.out.println("size: " + totalChars);
            return totalChars;
        } catch (FileNotFoundException e){
            System.err.println("File not found: " + path);
            throw e;
        }
    }

    public static int whitespaceLength(char[] fullTextChar) throws ArrayIndexOutOfBoundsException{
        int i = 0;

        try {
            for (char c: fullTextChar){
                if (Character.isWhitespace(c)){
                    i++;
                }
            }
            System.out.println("number of whitespaces: " + i);
            return i;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public static char[] spaceRemover(char[] fullTextChar) throws ArrayIndexOutOfBoundsException {
        char[] fullTextCharClean = new char[fullTextChar.length - whitespaceLength(fullTextChar)];
        int cleanIndex = 0;

        try {
            for (int i = 0; i < fullTextChar.length; i++){
                if (!Character.isWhitespace(fullTextChar[i])) {
                    fullTextCharClean[cleanIndex] = fullTextChar[i];
                    cleanIndex++;
                }
            }
            System.out.println("length of fulTextCharClean: " + fullTextCharClean.length);
            return fullTextCharClean;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public static int[][] arrayIndexer (char[] fullTextCharClean) throws ArrayIndexOutOfBoundsException {
        int[][] charMap = new int[128][1];
        try {
            for (char c: fullTextCharClean) {
                int asciiValue = (int) c;
                charMap[asciiValue][0]++;
            }
            return charMap;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public static void printer(int[][] charMap) {

        charMap= bubbleSort(charMap);

        for (int i = 0; i < charMap.length; i++) {
            int count = charMap[i][0];
            if (count != 0){
                char character = (char) i;
                System.out.println("Char: " + character + ". No.: " + count + " | ");
            }
        }
    }

    /*
    C:\Users\User\IdeaProjects\CF7-TestBed\src\gr\aueb\cf\ch6\BubbleSort.java
     */
    public static int[][] bubbleSort(int[][] arr) {
        for (int i = arr.length - 1; i > 0; i--){
            for (int j = 0; j < i; j++) {

                if (arr[j].length < 2 || arr[j + 1].length < 2) {
                    continue;
                }

                if (arr[j][1] > arr[j+1][1]) {
                    swap(arr, j, j+1);
                }
            }
        }
        return arr;
    }

    public static void swap(int[][] arr, int i, int j){
        int[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
