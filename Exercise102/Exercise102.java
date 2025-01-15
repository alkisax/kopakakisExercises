package gr.aueb.cf.ch10.Exercises.Exercise102;


/**
 * Έστω ένας πίνακας n ακεραίων. Τότε ο maximum sum subarray ο είναι ο συνεχόμενος υποπίνακας (contiguous subarray - δυνητικά κενό) με το μεγαλύτερο άθροισμα. Σχεδιάστε έναν γραμμικό αλγόριθμο (με πολυπλοκότητα O(n)) για να επιλύσετε τα παραπάνω πρόβλημα. Για παράδειγμα, αν έχουμε τον πίνακα {−2, 1, −3, 4, −1, 2, 1, −5, 4} τότε ο συνεχόμενος υποπίνακας με το μέγιστο άθροισμα είναι ο {4, −1, 2, 1}, του οποίου το άθροισμα είναι 6.
 * Hint. Χρησιμοποιήστε δυναμικό προγραμματισμό (βασική αρχή στον δυναμικό προγραμματισμό είναι όταν υπολογίζουμε κάτι να το αποθηκεύουμε, ώστε αν το ξαναχρειαστούμε να μην το ξαναυπολογίζουμε). Μην υπολογίζετε ξανά και ξανά το 4 άθροισμα για όλους τους δυνατούς υποπίνακες. Αν βρείτε ένα τοπικό μέγιστο (το μέγιστο από τη θέση 0 μέχρι μία θέση i του πίνακα arr) τότε για τη θέση i + 1 το μέγιστο θα είναι το max(τοπικό μέγιστο (i – 1) + arr[i], arr[i]). Παρατηρήστε στον παρακάτω πίνακα ότι αν έχουμε υπολογίσει το τοπικό μέγιστο για τη θέση arr[4] όπου το τοπικό μέγιστο είναι το 3, τότε για την επόμενη θέση του πίνακα, την θέση arr[5], το τοπικό μέγιστο είναι max(local-max(i – 1) + arr[i], i), δηλαδή max(3 + 2, 2) = 5. Ο πιο εύκολος τρόπος να λυθεί το πρόβλημα είναι ο επαναληπτικός με μία for. Για την αναδρομική λύση μπορούμε ξεκινώντας από το τελευταίο στοιχείο (n-1) να υπολογίσουμε αναδρομικά τα local maxima. Το local maximum του arr[0] είναι η τιμή του arr[0]. Στο παράδειγμα είναι arr[0] = -2. Μπορούμε επίσης να έχουμε μία μεταβλητή static int σε επίπεδο κλάσης που να είναι το globalMaximum και να ενημερώνεται από όλες τις μεθόδους στο βάθος της αναδρομής, όταν το localMaximum είναι μεγαλύτερο από το globalMaximum.
 * */

public class Exercise102 {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Working on Array:");
        printerArr(arr);
        System.out.println();

        int[] arr1= maxSubArrFirst(arr);
        System.out.println("Subarray first method");
        printerArr(arr1);
        System.out.println();
        int[] arr2= maxSubArrSecond(arr);
        System.out.println("Subarray second method");
        printerArr(arr2);

        System.out.println("(arr1.length - arr2.length) = " + (arr1.length - arr2.length) );
        System.out.println("expect 0");
    }


    public static int[] maxSubArrFirst (int[] arr) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 0; i < arr.length; i++){
            sum = 0;
            tempStart = i;

            for (int j = i; j < arr.length; j++){
                sum += arr[j];

                if (sum > maxSum) {
                    maxSum = sum;
                    start = tempStart;
                    end = j;
                }
            }
        }
        int[] result = new int[(end - start) + 1];
        for (int k = start; k <= end; k++) {
            result[k - start] = arr[k];
        }
        return result;
    }

    public static int[] maxSubArrSecond (int[] arr){

        int bestSum = Integer.MIN_VALUE;
        int currentSum = 0;
        int start = 0, end = 0, tempStart = 0;

        // Iterate through each number in the array
        for (int i = 0; i < arr.length; i++) {
            // Update the current sum
            if (currentSum + arr[i] > arr[i]) {
                currentSum += arr[i];
            } else {
                currentSum = arr[i];
                tempStart = i;  // Start a new subarray
            }

            // Update the best sum if the current sum is greater
            if (currentSum > bestSum) {
                bestSum = currentSum;
                start = tempStart; // Update the start index of the subarray
                end = i;           // Update the end index of the subarray
            }
        }

        // Extract the subarray from the input array using the start and end indices
        int[] result = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            result[i - start] = arr[i];
        }

        return result; // Return the subarray with the largest sum

    }


    public static void printerArr(int[] arr){
        for (int el: arr ){
            System.out.print(el + "| ");
        }
    }
}
