package gr.aueb.cf.projects;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * διαβάζει από ένα αρχείο ακέραιους αριθμούς (το αρχείο πρέπει να περιέχει περισσότερους
 * από 6 αριθμούς και το πολύ 49 αριθμούς) με τιμές από 1 έως 49. Τους αριθμούς αυτούς
 * τους εισάγει σε ένα πίνακα, τον οποίο ταξινομεί (π.χ. με την Arrays.sort()). Στη συνέχεια,
 * το πρόγραμμα παράγει όλες τις δυνατές εξάδες (συνδυασμούς 6 αριθμών). Ταυτόχρονα και αμέσως
 * μετά την παραγωγή κάθε εξάδας ‘φιλτράρει’ κάθε εξάδα ώστε να πληροί τα παρακάτω κριτήρια:
 * 1) Να περιέχει το πολύ 4 άρτιους, 2) να περιέχει το πολύ 4 περιττούς, 3) να περιέχει το
 * πολύ 2 συνεχόμενους, 4) να περιέχει το πολύ 3 ίδιους λήγοντες, 5) να περιέχει το πολύ 3
 * αριθμούς στην ίδια δεκάδα.
 * Τέλος, εκτυπώνει τις τελικές εξάδες, που δεν ανήκουν σε κανένα κριτήρια,
 * σε ένα αρχείο με κατάληξη.txt.
 */


public class ProjectLottoApp {
    public static int[] array = new int[49];
    public static int[] sortedArray = new int[49];
    public static ArrayList<int[]> numbers = new ArrayList<>();
    public static ArrayList<int[]> sixLast = new ArrayList<>();


    public static void main(String args[]) throws IOException {
        File file = new File("C:/temp/numbers49.txt");
        final int N = 6;

        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
            if (file.exists()) {
                String input;

                while ((input = reader.readLine()) != null) {
                    String[] inputNumbers = input.split(" ");

                    try {
                        for (int i = 0; i < array.length - 1; i++) {
                            array[i] = Integer.parseInt(inputNumbers[i]);
                            System.out.print(array[i] + " ");
                        }
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    }

                }
            } else {
                System.out.println("Can not open file. I will generate numbers for you!");
                array = generateRandom();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sortedArray = Arrays.copyOf(array,49);
        Arrays.sort(sortedArray);

        System.out.println();
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(array[i] + " ");
        }

        //combinations of six are stored in ArrayList numbers
        int[] row = new int[6];
        for (int i = 0; i <= array.length - N; i++) {
            for (int j = i + 1; j <= array.length - N + 1; j++) {
                for (int k = j + 1; k <= array.length - N + 2; k++) {
                    for (int l = k + 1; l <= array.length - N + 2; l++) {
                        for (int m = l + 1; m <= array.length - N + 2; m++) {
                            for (int n = m + 1; n < array.length; n++) {
                                row[0] = array[i];
                                //System.out.print(row[0] +" ");
                                row[1] = array[j];
                               // System.out.print(row[1]+" ");
                                row[2] = array[k];
                               // System.out.print(row[2]+" ");
                                row[3] = array[l];
                               // System.out.print(row[3]+" ");
                                row[4] = array[m];
                               // System.out.print(row[4]+" ");
                                row[5] = array[n];
                                //System.out.print(row[5]+" ");
                               // System.out.println();

                                numbers.add(row);
                            }
                        }
                    }
                }
            }
        }
        //System.out.println();
        // System.out.println("numbers"+numbers.size());

        /*
        διατρέχει την λιστα, που καθε στοιχειο της ειναι πινακας με 6 αριθμους.
         εαν η εξάδα πληροι όλους τους περιορισμούς, την αποθηκευει στην ArrayList sixLast
         και την γράφει στο αρχειο combinationsSix.txt
         */
         for (int i = 0; i < numbers.size(); i++) {
            if (isSixLast(numbers.get(i)))
            sixLast.add(numbers.get(i));
            //writeCombinations(numbers.get(i));
        }

       // System.out.println();
       // System.out.println("last"+sixLast.size());

    }


    //calc if there are at least 4 even numbers in array
    public static boolean maximum4Even(int[] arr) {
        int count = 0;
        boolean flag = false;

        for (int i = 0; i < arr.length; i++) {
            if (isEven(arr[i])) count++;
        }
        if (count >= 4) flag = true;
        return flag;
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    //calc if there are at least 4 odd numbers in array
    public static boolean maximum4Odd(int[] arr) {
        int count = 0;
        boolean flag = false;

        for (int i = 0; i < arr.length; i++) {
            if (isOdd(arr[i])) count++;
        }
        if (count >= 4) flag = true;
        return flag;
    }

    public static boolean isOdd(int num) {
        return num % 2 != 0;
    }

    //calc if there are at least 2 numbers contiguous
    public static boolean isContiguous(int[] arr) {
        int count = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            if ((arr[i] == arr[i] + 1)) count++;

        }
        if (count >= 2) return true;
            return false;
    }

    //calc if there are at least 3 same numbers in the end of array
    public static boolean isSameEnding(int[] arr) {
        int count = 0;
        for(int i = arr.length - 1; i > arr.length - 4; i--) {
            if (arr[i] == arr[i - 1]) count++;
        }
        if (count >= 3) return true;
        return false;
    }

    //calc if there are at least 3 numbers that belong same decade
    public static boolean isSameTen(int[] arr) {
        int count = 0;
        int[] decade = new int[6];
        for (int i = 0; i < arr.length; i++) {
            decade[i] = decadeOf(arr[i]);
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if(decade[i] == decade[i + 1]) count++;
        }
        if (count >= 3) return true;
        return false;
    }

    //Returns the decade in which belongs the number
    public static int decadeOf (int a) {
        int decade = a / 10;
        return decade;
    }

    //Generate 49 random int value from 1 to 49
    public static int[] generateRandom() {
        //int[] arr = new int[49];
        int min = 1;
        int max = 49;

        for (int i = min; i <= max; i++) {
            //System.out.println("Random value in int from " + min + " to " + max + ":");
            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
            //System.out.print(random_int+" ");
            array[i] = random_int;
        }
        return array;
    }

    public static boolean isSixLast(int[] six) {

        if ((!maximum4Even(six))
                && (!maximum4Odd(six))
                && (!isContiguous(six))
                && (!isSameEnding(six))
                && (!isSameTen(six))) {
            return true;
        }
        return false;
    }

    //grafei thn idia exada?!
    public static void writeCombinations(int[] six) throws IOException {
        File file = new File ("C:/temp/combinationsSix.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

            try (FileWriter fw = new FileWriter(file, true);
                 BufferedWriter bw = new BufferedWriter(fw);) {
                    for(int i = 0; i < six.length; i++) {

                    bw.write(six[i] + "\n");

                }
            } catch (IOException e) {
                System.err.println("IOException: " + e.getMessage());
        }
    }
}

/*****************************************************************************
 *  //Ελέγχει αν ένα String είναι ακέραιος
 *     public static boolean isInt(String s) {
 *         try {
 *             Integer.parseInt(s);
 *             return true;
 *         } catch (NumberFormatException e) {
 *             return false;
 *         }
 *     }
 */


