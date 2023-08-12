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
 */

public class LargestNonIncreasingSubsequence {

    public static void main(String[] args) {
        LargestNonIncreasingSubsequence largestNonIncreasingSubsequence = new LargestNonIncreasingSubsequence();
        largestNonIncreasingSubsequence.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int[] data = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] res = LNISBottomUP2(data);
        int max = res.length;
        System.out.println(max);
        String answer = Arrays.toString(res).replaceAll("[\\[\\],]", "");
        System.out.println(answer);
    }

    /**************************************
     * O(n^2)
     **************************************/
    private int[] LNISBottomUP1(int[] data) {
        int length = data.length;
        int[] d = new int[length];
        d[0] = 1;
        for (int i = 1; i < length; i++) {
            d[i] = 1;
            for (int j = 0; j <= i - 1; j++) {
                if ((data[i] <= data[j]) && (d[j] + 1 > d[i])) {
                    d[i] = d[j] + 1;
                }
            }
        }
        return d;
    }

    private int[] getLNIS1(int[] data, int[] d, int max) {
        int[] answer = new int[max];
        int j = 0;
        int prevVal = Integer.MIN_VALUE;
        int lastIndex = findLastIndexOfMax(d, max);
        for (int i = lastIndex; i >= 0; i--) {
            if ((max - d[i] == 1 || max - d[i] == 0) && prevVal <= data[i]) {
                answer[answer.length - j - 1] = i + 1;
                max = d[i];
                prevVal = data[i];
                j++;
            }
        }
        return answer;
    }

    private int findLastIndexOfMax(int[] arr, int max) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив должен содержать элементы");
        }
        int lastIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == max) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    /**************************************
     * O(n*log(n))
     **************************************/

    //5
    //5 3 4 4 2
    private int[] LNISBottomUP2(int[] data) {
        int length = data.length;
        int[] d = new int[length];
        Arrays.fill(d, Integer.MIN_VALUE);
        d[0] = data[0];
        int[] indexes = new int[length];
        Arrays.fill(indexes, Integer.MAX_VALUE);
        indexes[0] = 0;
        int lengthD = 1;
        for (int i = 1; i < length; i++) {
            int index = binarySearch(d, data[i]);
            if (d[lengthD - 1] >= data[i]) {
                d[lengthD] = data[i];
                indexes[i] = lengthD;
                lengthD++;
            } else if (index < lengthD) {
                d[index] = data[i];
                indexes[i] = index;
            }
        }
        d = Arrays.stream(d).filter(i -> i != Integer.MIN_VALUE).toArray();
        return getLNIS2(data, d, indexes);
    }

    private int[] getLNIS2(int[] data, int[] d, int[] indexes) {
        int max = d.length;
        int last = d[max - 1];
        int[] answer = new int[max];
        int j = 0;
        for (int i = data.length - 1; i >= 0; i--) {
            if (indexes[i] == max - 1 && last <= data[i]) {
                max--;
                answer[answer.length - j - 1] = i + 1;
                last = data[i];
                j++;
            }
        }
        return answer;
    }

    private int binarySearch(int[] data, int value) {
        int start = 1;
        int end = data.length;
        while (start <= end) {
            int current = (start + end) / 2;
            int check = data[current - 1];
            if (check == value) {
                while (current + 1 < data.length && value == data[current]) {
                    current++;
                }
                return current;
            } else if (check > value) {
                start = current + 1;
            } else {
                end = current - 1;
            }
        }
        return start - 1;
    }

}
