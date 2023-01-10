package gr.aueb.cf.projects;

import java.util.List;

/**
 * implementation of Kadane's algorithm
 */
/*
Kadane's algorithm is an efficient algorithm for finding the maximum subarray
of a given array, as it has a time complexity that is linear in the size of the input.
This makes it suitable for use in situations where the input array is large and the runtime
needs to be minimized.
Kadane's algorithm has a time complexity of O(n), where n is the number of elements in the input array.
This means that the algorithm's runtime grows linearly with the size of the input.

Kadane's algorithm has a time complexity of O(n) because it processes each element of the input
array exactly once. In each iteration of the loop, it performs a constant number of
operations, including updating the values of localMaxima and globalMaxima, checking if
localMaxima is greater than globalMaxima, and checking if localMaxima is less than 0.
Since the number of iterations is equal to the size of the input array, the time complexity is O(n).

*/

    /*
    this implementation assumes that the input array is not empty and has at
    least one positive element. If the array is empty or contains only
    negative elements, the start and end indices will not be set correctly.
     */

public class ContiguousSubarrayApp {

    public static void main(String[] args) {
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int globalMaxima = Integer.MIN_VALUE;
        int localMaxima = 0;
        int start = 0;
        int end = 0;
        int tempStart = 0;

         for (int i = 0; i < array.length; i++) {
            localMaxima += array[i];
            if (localMaxima > globalMaxima) {
                globalMaxima = localMaxima;
                start = tempStart;
                end = i;
            }
            if (localMaxima < 0) {
                localMaxima = 0;
                tempStart = i + 1;
            }
        }

        System.out.printf("The max contiguous array starts at index %d,\n" +
                "ends at index %d,\n" +
                "sum of subarray is %d", start, end, globalMaxima);

    }
}
/****************************************************************************************/
    /*
     * This implementation first checks if the array is empty.
     * If it is, it prints a message and returns. It then iterates through the array and
     * checks if each element is Double.NaN. If it is, it throws an IllegalArgumentException
     * with a message. Otherwise, it proceeds with the algorithm as before. After the loop
     * completes, it checks if globalMaxima is still equal to the negative infinity, which indicates
     *that the array contains only negative numbers or Double.NaN values. If it is, it prints
     * a message. Otherwise, it prints the maximum sum and the start and end indices of the
     * maximum subarray.
     *
     * The implementation includes a try-catch block to handle the IllegalArgumentException
     * that is thrown if the array contains Double.NaN values. If the exception is thrown,
     * the message is printed and the program returns.
     *
     ***********************************************************************/
 /*
    public class ContiguousSubarrayApp {

        public static void main(String[] args) {
            List<Double> array = List.of(-2.0, 1.0, Double.NaN, 4.0, -1.0, 2.0, 1.0, -5.0, 4.0);
            double globalMaxima = Double.NEGATIVE_INFINITY;
            double localMaxima = 0.0;
            int start = 0;
            int end = 0;
            int tempStart = 0;
            if (array.isEmpty()) {
                System.out.println("Array is empty");
                return;
            }
            try {
                for (int i = 0; i < array.size(); i++) {
                    if (Double.isNaN(array.get(i))) {
                        throw new IllegalArgumentException("Array contains NaN values");
                    }
                    localMaxima += array.get(i);
                    if (localMaxima > globalMaxima) {
                        globalMaxima = localMaxima;
                        start = tempStart;
                        end = i;
                    }
                    if (localMaxima < 0) {
                        localMaxima = 0;
                        tempStart = i + 1;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
            if (globalMaxima == Double.NEGATIVE_INFINITY) {
                System.out.println("Array contains only negative numbers or NaN values");
            } else {
                System.out.printf("The max contiguous array starts at index %d,\n" +
                        "ends at index %d,\n" +
                        "sum of subarray is %d", start, end, globalMaxima);
            }
        }
    }
}

  */
