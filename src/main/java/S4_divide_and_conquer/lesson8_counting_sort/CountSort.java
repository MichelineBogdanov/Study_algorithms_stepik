package S4_divide_and_conquer.lesson8_counting_sort;

import java.util.*;

/**
 * Первая строка содержит число 1≤n≤10^4, вторая — n натуральных чисел, не превышающих 10.
 * Выведите упорядоченную по неубыванию последовательность этих чисел.
 * ---
 * Sample Input:
 * 5
 * 2 3 9 2 9
 * ---
 * Sample Output:
 * 2 2 3 9 9
 */

public class CountSort {

    public static void main(String[] args) {
        CountSort countSort = new CountSort();
        int[] arrayData = countSort.getInputData();
        int[] result = countSort.countSort(arrayData);
        countSort.print(result);
    }

    private int[] getInputData() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String data = scanner.nextLine();
        return Arrays.stream(data.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private int[] countSort(int[] arrayData) {
        int[] ints = new int[11];
        for (int arrayDatum : arrayData) {
            ints[arrayDatum]++;
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int j = 0; j < ints.length; j++) {
            for (int i = 0; i < ints[j]; i++) {
                result.add(j);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void print(int[] result) {
        List<Integer> list = Arrays.stream(result)
                .boxed()
                .toList();
        StringBuilder stringResult = new StringBuilder();
        for (Integer integer : list) {
            stringResult.append(integer);
            if (list.indexOf(integer) != list.size() - 1) {
                stringResult.append(" ");
            }
        }
        System.out.println(stringResult);
    }

}
