package gr.aueb.cf.ch10.Exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Αναπτύξτε ένα πρόγραμμα σε Java που να διαβάζει από ένα αρχείο ακέραιους
 * αριθμούς (το αρχείο πρέπει να περιέχει περισσότερους από 6 αριθμούς και το πολύ
 * 49 αριθμούς) με τιμές από 1 έως 49. Τους αριθμούς αυτούς τους εισάγει σε ένα
 * πίνακα, τον οποίο ταξινομεί (π.χ. με την Arrays.sort()). Στη συνέχεια, το πρόγραμμα
 * παράγει όλες τις δυνατές εξάδες (συνδυασμούς 6 αριθμών). Ταυτόχρονα και αμέσως
 * μετά την παραγωγή κάθε εξάδας ‘φιλτράρει’ κάθε εξάδα ώστε να πληροί τα
 * παρακάτω κριτήρια: 1) Να περιέχει το πολύ 4 άρτιους, 2) να περιέχει το πολύ 4
 * περιττούς, 3) να περιέχει το πολύ 2 συνεχόμενους, 4) να περιέχει το πολύ 3 ίδιους
 * λήγοντες, 5) να περιέχει το πολύ 3 αριθμούς στην ίδια δεκάδα.
 * Τέλος, εκτυπώνει τις τελικές εξάδες σε ένα αρχείο με όνομα της επιλογής σας και
 * κατάληξη.txt.
 * https://github.com/a8anassis/CF7-Testbed/blob/main/src/gr/aueb/cf/solutions/ch6/LottoApp.java
 */


public class Exercise101 {
    public static void main(String[] args) {
        String path = "C:\\Users\\User\\IdeaProjects\\CF7-TestBed\\src\\gr\\aueb\\cf\\ch10\\Exercises\\numbers.txt";
        int fileSize = 0;

        try {
            System.out.println("Starting fileSizer...");
            fileSize = fileSizer(path);

            System.out.println("Starting sizeTester...");
            sizeTester(path);

            System.out.println("Starting fileReader...");
//            int[] arr = new int[fileSize];
            int [] arr = fileReader(path);

            System.out.println("Sorting array...");
            Arrays.sort(arr);

            System.out.println("Starting sixSetCreator...");
            sixSetCreator(arr);

        } catch (FileNotFoundException e) {
            System.err.println("ERROR");
            e.printStackTrace();
        }
    }

    public static int fileSizer (String path) throws FileNotFoundException {
        int i = 0;
        File fd = new File(path);

        try (Scanner in = new Scanner(fd)) {
            while (in.hasNextLine()) {
                in.nextLine();
                i++;
                System.out.println("Reading line " + i);  // Debugging: Prints the line count.
            }
            System.out.println("Total lines in file: " + i);  // Debugging: Prints the final line count.
            return i;

        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found");
            e.printStackTrace();
            throw e;
        }
    }

    public static void sizeTester (String path) {
        int size;
        try {
            size = fileSizer(path);
            System.out.println("File size: " + size);  // Debugging: Prints the file size.
            if (size < 7 || size > 49) {
                throw new Exception ("INVALID FILE SIZE");
            }

        } catch (FileNotFoundException e) {
            System.err.println("file not found" + e.getMessage());

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int[] fileReader (String path) throws FileNotFoundException  {
        String line;
        File fd = new File(path);
        int lineValue = 0;
        int i = 0;
        int size = fileSizer(path);

        if (size == 0) {
            return new int[0];
        }

        int[] arr = new int[size];
        System.out.println("Debugging fileReader function..."); //debug print

        try (Scanner in = new Scanner(fd)) {
            while (in.hasNextLine()) {
                line = in.nextLine();
                lineValue = Integer.valueOf(line);
                arr[i] = lineValue;
                i++;
            }
            return arr;

        } catch (FileNotFoundException e) {
            System.err.println("ERROR");
            e.printStackTrace();
            throw e;
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid integer in file");
            e.printStackTrace();
            throw e;
        }
    }

    public static void printToFile(int[] toPrint) {
        String outPath = "C:\\Users\\User\\IdeaProjects\\CF7-TestBed\\src\\gr\\aueb\\cf\\ch10\\Exercises\\approvedOutput.txt";
        File outFile = new File(outPath);

        try (
                PrintStream printStrm = new PrintStream(outFile, StandardCharsets.UTF_8);
        ){
            for (int i = 0; i < toPrint.length; i++){
                System.out.print(toPrint[i]);
                printStrm.print(toPrint[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR");
        }
    }

    public static void sixSetCreator(int[] arr) {
        final int SIZE = 6;
        int[] result = new int[SIZE];
        int window;

        window = arr.length - SIZE;
        for (int i = 0; i <= window; i++) {
            for (int j = i + 1; j <= window + 1; j++) {
                for (int k = j + 1; k <= window + 2; k++) {
                    for (int l = k + 1; l <= window + 3; l++) {
                        for (int m = l + 1; m <= window + 4; m++) {
                            for (int n = m + 1; n <= window + 5; n++) {
                                result[0] = arr[i];
                                result[1] = arr[j];
                                result[2] = arr[k];
                                result[3] = arr[l];
                                result[4] = arr[m];
                                result[5] = arr[n];

                                // Debug print to show each combination generated
//                                System.out.println("Generated combination: " + Arrays.toString(result));

                                System.out.println("Checking combination: " + Arrays.toString(result));

//                                if (maxFourEven(result)) {
//                                    System.out.println("Passed maxFourEven");
//                                } else {
//                                    System.out.println("Failed maxFourEven");
//                                }
//
//                                if (maxFourOdd(result)) {
//                                    System.out.println("Passed maxFourOdd");
//                                } else {
//                                    System.out.println("Failed maxFourOdd");
//                                }
//
//                                if (isContiguous(result)) {
//                                    System.out.println("Passed isContiguous");
//                                } else {
//                                    System.out.println("Failed isContiguous");
//                                }
//
//                                if (isSameEnding(result)) {
//                                    System.out.println("Passed isSameEnding");
//                                } else {
//                                    System.out.println("Failed isSameEnding");
//                                }
//
//                                if (isSameTen(result)) {
//                                    System.out.println("Passed isSameTen");
//                                } else {
//                                    System.out.println("Failed isSameTen");
//                                }

                                if (maxFourEven(result) && maxFourOdd(result) && isContiguous(result) && isSameEnding(result) && isSameTen(result)) {
                                    System.out.println("passed all tests OK");
                                    printToFile(result);
                                } else {
                                    System.out.println("did NOT pass all tests");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean maxFourEven(int[] arr) {
        int i = 0;

        for (int el: arr){
            if (el % 2 == 0) {
                i++;
                if (i > 4) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean maxFourOdd(int[] arr) {
        int i = 0;

        for (int el: arr){
            if (el % 2 != 0) {
                i++;
                if (i > 4) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isContiguous(int[] arr) {
        int consecutiveCount  = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] == arr[i] + 1 || arr[i + 1] == arr[i] - 1){
                consecutiveCount ++;
                if (consecutiveCount  > 2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isSameEnding(int[] arr) {
        int[] resultArr = new int[10];
        int mod;
        for (int el: arr) {
            mod = el % 10;
            resultArr[mod]++;
        }
        for (int el: resultArr) {
            if (el > 3) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSameTen(int[] arr) {
        int[] resultArr = new int[10];
        int mod;
        for (int el: arr) {
            mod = el / 10;
            resultArr[mod]++;
        }
        for (int el: resultArr) {
            if (el > 3) {
                return false;
            }
        }
        return true;
    }


}
