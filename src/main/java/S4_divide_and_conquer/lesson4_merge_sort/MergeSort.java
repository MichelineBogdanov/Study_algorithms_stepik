package S4_divide_and_conquer.lesson4_merge_sort;

/**
 * Первая строка содержит число 1≤n≤10^5, вторая — массив A[1…n],
 * содержащий натуральные числа, не превосходящие 10^9. Необходимо посчитать число пар индексов 1≤i<j≤n,
 * для которых A[i]>A[j] (такая пара элементов называется инверсией массива).
 * Количество инверсий в массиве является в некотором смысле его мерой неупорядоченности:
 * например, в упорядоченном по неубыванию массиве инверсий нет вообще, а в массиве,
 * упорядоченном по убыванию, инверсию образуют каждые два элемента.
 * ---
 * Sample Input:
 * 5
 * 2 3 9 2 9
 * ---
 * Sample Output:
 * 2
 */

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {

    private static long resultCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String stringData = scanner.nextLine();
        int[] data = Arrays.stream(stringData.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(data);
        System.out.println(resultCount);
    }

    private int[] mergeSort(int[] data) {
        int length = data.length;
        int[] resultArray = data;
        if (length > 1) {
            int m = (length / 2);
            int[] leftPart = Arrays.copyOfRange(data, 0, m);
            int[] rightPart = Arrays.copyOfRange(data, m, length);
            resultArray = merge(mergeSort(leftPart), mergeSort(rightPart));
        }
        return resultArray;
    }

    private int[] merge(int[] leftPart, int[] rightPart) {
        int count = leftPart.length + rightPart.length;
        int[] resultArray = new int[count];
        int left = 0;
        int right = 0;
        for (int i = 0; i < count; i++) {
            if (leftPart.length == left) {
                resultArray[i] = rightPart[right];
                right++;
            } else if (rightPart.length == right) {
                resultArray[i] = leftPart[left];
                left++;
            } else {
                if (leftPart[left] <= rightPart[right]) {
                    resultArray[i] = leftPart[left];
                    left++;
                } else {
                    resultArray[i] = rightPart[right];
                    right++;
                    resultCount += (leftPart.length - left);
                }
            }
        }
        return resultArray;
    }

}
