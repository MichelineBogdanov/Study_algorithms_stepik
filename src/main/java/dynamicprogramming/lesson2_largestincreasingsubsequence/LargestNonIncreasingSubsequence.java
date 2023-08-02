package dynamicprogramming.lesson2_largestincreasingsubsequence;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Дано целое число 1≤n≤10^5 и массив A[1…n], содержащий неотрицательные целые числа, не превосходящих 10^9.
 * Найдите наибольшую невозрастающую подпоследовательность в A. В первой строке выведите её длину k,
 * во второй — её индексы 1≤i1≤i2≤...≤ik≤n (таким образом, A[i1]≥A[i2]≥…≥A[in]).
 * ---
 * Sample Input:
 * 5
 * 5 3 4 4 2
 * ---
 * Sample Output:
 * 4
 * 1 3 4 5
 * */

public class LargestNonIncreasingSubsequence {

    public static void main(String[] args) {
        LargestNonIncreasingSubsequence largestNonIncreasingSubsequence = new LargestNonIncreasingSubsequence();
        largestNonIncreasingSubsequence.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int[] data = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}
