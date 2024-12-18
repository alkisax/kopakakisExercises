package gr.aueb.cf.ch10.Exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
 * Hint. Ακολουθήστε τη διαδικασία που είχαμε δει για την παραγωγή 4άδων. Κάθε
 * παραγόμενη εξάδα μπορεί να αποθηκεύεται σε ένα πίνακα ο οποίος στη συνέχεια να
 * ελέγχεται από κάθε μία από τις αναφερόμενες μεθόδους (φίλτρα). Αν για
 * παράδειγμα μία εξάδα έχει αποθηκευτεί στον πίνακα arr, τότε για να ‘περάσει’ τα
 * φίλτρα που είναι ταυτόχρονα περιορισμοί, θα πρέπει να ελεγχθεί. Π.χ.
 * if (!isEven(arr)) && (!isOfdd(arr)) && (!isContiguous(arr)) && (!isSameEnding(arr)) &&
 * (!isSameTen), γράψε την εξάδα στο αρχείο εξόδου.
 */

public class Exercise101 {
    public static void main(String[] args) {
        String path = "gr/aueb/cf/ch10/Exercises/numbers.txt";
        int fileSize = 0;

        try {
            fileSize = fileSizer(path);
            sizeTester(path);
            int[] arr = new int[fileSize];
            arr = fileReader(path);

            Arrays.sort(arr);

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
                i++;
            }
            return i;

        } catch (FileNotFoundException e) {
            System.err.println("ERROR");
            e.printStackTrace();
            throw e;
        }
    }

    public static void sizeTester (String path) {
        int size;
        try {
            size = fileSizer(path);
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
                            }
                        }
                    }
                }
            }
        }
    }
}
