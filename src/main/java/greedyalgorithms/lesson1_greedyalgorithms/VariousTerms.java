package greedyalgorithms.lesson1_greedyalgorithms;

/*
 * По данному числу 1≤n≤10^9 найдите максимальное число k, для которого
 * n можно представить как сумму k различных натуральных слагаемых.
 * Выведите в первой строке число k, во второй — k слагаемых.
 *
 * Sample Input 1:
 * 4
 *
 * Sample Output 1:
 * 2
 * 1 3
 *
 * Sample Input 2:
 * 6
 *
 * Sample Output 2:
 * 3
 * 1 2 3
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VariousTerms {

    public static void main(String[] args) {
        VariousTerms variousTerms = new VariousTerms();
        variousTerms.start();
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        long data = scanner.nextLong();
        List<Long> result = optimize(data);
        System.out.println(result.size());
        StringBuilder stringResult = new StringBuilder();
        for (Long term : result) {
            stringResult.append(term);
            if (result.indexOf(term) != result.size() - 1) {
                stringResult.append(" ");
            }
        }
        System.out.println(stringResult);
    }

    private List<Long> optimize(long data) {
        ArrayList<Long> result = new ArrayList<>();
        long restData = data;
        for (long i = 1; i <= data; i++) {
            if (restData >= i) {
                result.add(i);
                restData -= i;
            } else {
                result.set(result.size() - 1, i - 1 + restData);
                break;
            }
        }
        return result;
    }

}
