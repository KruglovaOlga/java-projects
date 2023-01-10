package gr.aueb.cf.projects;

/**
 * The copyOf() method with all its overloaded forms was introduced in Java 1.6 version.
 * This method throws NullPointerException if the passed original array is null.
 * Therefore, it is better to check if(original != null) and then call copyOf().
 * It also throws NegativeArraySizeException if the passed length (i.e. newLength) is negative.
 */

import java.util.Arrays;

public class CopyArrayApp {
    public static void main(String[] args) {
        int[][] array = new int[2][5];

        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                array[i][j] = i + j;
                //System.out.print(array[i][j] + " ");
            }
        }

        System.out.println("The original array");
        printArray(array);
        int[][] array2 = shallowCopy(array);
        System.out.println("The shallow copy of original array");
        printArray(array2);
        int[][] array3 = deepCopy(array);
        System.out.println("The deep copy of original array");
        printArray(array3);

        //modification of original array
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                array[i][j] = i + j + 2;
               // System.out.print(array[i][j] + " ");
            }
        }

        System.out.println();

        System.out.println("The original array after modification");
        printArray(array);
        System.out.println("The shallow copy of original array after modification ");
        printArray(array2);
        System.out.println("The deep copy of original array after modification");
        printArray(array3);


    }
    public static int[][] shallowCopy(int[][] arr){

        int[][] matrix = Arrays.copyOf(arr, 2);
        return matrix;
    }

    public static int[][] deepCopy(int[][] arr){
        // create 2D array and assign size of parent array
        int temp[][] = new int[arr.length][];

        for (int i = 0; i < arr.length; i++) {
            // assign size of child array
            temp[i] = new int[arr[i].length];

            for (int j = 0; j < arr[i].length; j++) {
                // copy element
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }

    public static void printArray(int[][] array) {

        System.out.println(Arrays.deepToString(array));

    }
}
