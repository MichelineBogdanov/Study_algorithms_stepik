package S5_dynamic_programming.lesson4_bag;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Первая строка входа содержит целые числа 1≤W≤10^4 и 1≤n≤300 — вместимость рюкзака и число золотых слитков.
 * Следующая строка содержит n целых чисел 0≤w1,w2,...,wn≤10^5, задающих веса слитков.
 * Найдите максимальный вес золота, который можно унести в рюкзаке.
 * ---
 * Sample Input:
 * 10 3
 * 1 4 8
 * ---
 * Sample Output 1:
 * 9
 */

public class Bag {

    public static void main(String[] args) {
        Bag bag = new Bag();
        Scanner scanner = new Scanner(System.in);
        int w = Integer.parseInt(scanner.nextLine().split(" ")[0]);
        int[] weight = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int result = bag.knapsackWithoutRep(w, weight);
        System.out.println(result);
    }

    private int knapsackWithoutRep(int w, int[] weight) {
        int n = weight.length;
        int[][] d = new int[w + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                d[j][i] = d[j][i - 1];
                if (weight[i - 1] <= j) {
                    d[j][i] = Math.max(d[j][i], d[j - weight[i - 1]][i - 1] + weight[i - 1]);
                }
            }
        }
        return d[w][n];
    }
}
