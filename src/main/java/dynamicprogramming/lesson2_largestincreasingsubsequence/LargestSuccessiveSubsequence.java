package dynamicprogramming.lesson2_largestincreasingsubsequence;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Дано целое число 1≤n≤10^3 и массив A[1…n] натуральных чисел, не превосходящих 2⋅10^9.
 * Выведите максимальное 1≤k≤n, для которого найдётся подпоследовательность 1≤i1≤i2≤...≤ik≤in длины
 * k, в которой каждый элемент делится на предыдущий (формально: для  всех 1≤j≤k, A[ij]/A[i(j+1)])
 * ---
 * Sample Input:
 * 4
 * 3 6 7 12
 * ---
 * Sample Output:
 * 3
 */

public class LargestSuccessiveSubsequence {

    public static void main(String[] args) {
        LargestSuccessiveSubsequence largestSuccessiveSubsequence = new LargestSuccessiveSubsequence();
        largestSuccessiveSubsequence.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int[] data = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int result = LISBottomUP(data);
        System.out.println(result);
    }

    private int LISBottomUP(int[] data) {
        int length = data.length;
        int[] d = new int[length];
        d[0] = 1;
        for (int i = 1; i < length; i++) {
            d[i] = 1;
            for (int j = 0; j <= i - 1; j++) {
                if ((data[i] % data[j] == 0) && (d[j] + 1 > d[i])) {
                    d[i] = d[j] + 1;
                }
            }
        }
        return Arrays.stream(d).max().orElse(0);
    }

}
