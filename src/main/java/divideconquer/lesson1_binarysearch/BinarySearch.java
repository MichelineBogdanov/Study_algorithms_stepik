package divideconquer.lesson1_binarysearch;

/*
 * В первой строке даны целое число 1≤n≤10^5 и массив A[1…n] из n различных натуральных чисел,
 * не превышающих 10^9, в порядке возрастания, во второй — целое число 1≤k≤10^5 и k натуральных чисел
 * b1,...,bk, не превышающих 10^9. Для каждого i от 1 до k необходимо вывести индекс 1≤j≤n, для которого
 * A[j]=bi, или −1, если такого j нет.
 *
 * Sample Input:
 * 5 1 5 8 12 13
 * 5 8 1 23 1 11
 *
 * Sample Output:
 * 3 1 -1 1 -1
 * */

import java.util.Scanner;

public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch main = new BinarySearch();
        Scanner scanner = new Scanner(System.in);
        int elementSearch = scanner.nextInt();
        int[] elementData = new int[elementSearch];
        for (int i = 0; i < elementSearch; i++) {
            elementData[i] = scanner.nextInt();
        }
        scanner.nextLine();
        int searchCount = scanner.nextInt();
        int[] searchData = new int[searchCount];
        for (int i = 0; i < searchCount; i++) {
            searchData[i] = scanner.nextInt();
        }

        String[] result = new String[searchData.length];
        for (int i = 0; i < searchData.length; i++) {
            result[i] = String.valueOf(main.binarySearch(elementData, searchData[i]));
        }
        String resultStr = String.join(" ", result);
        System.out.println(resultStr);
    }

    private int binarySearch(int[] elementData, int search) {
        int start = 1;
        int end = elementData.length;
        while (start <= end) {
            int current = (start + end) / 2;
            int check = elementData[current - 1];
            if (check == search) {
                return current;
            } else if (check < search) {
                start = current + 1;
            } else {
                end = current - 1;
            }
        }
        return -1;
    }

}
