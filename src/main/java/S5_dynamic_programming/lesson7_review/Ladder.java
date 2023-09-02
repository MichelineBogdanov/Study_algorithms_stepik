package S5_dynamic_programming.lesson7_review;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Даны число 1≤n≤10^2 ступенек лестницы и целые числа -10^4≤a1≤,...,≤an≤10^4, которыми помечены ступеньки.
 * Найдите максимальную сумму, которую можно получить, идя по лестнице снизу вверх (от нулевой до n-й ступеньки),
 * каждый раз поднимаясь на одну или две ступеньки.
 * ---
 * Sample Input 1:
 * 2
 * 1 2
 * ---
 * Sample Output 1:
 * 3
 * ---
 * Sample Input 2:
 * 2
 * 2 -1
 * ---
 * Sample Output 2:
 * 1
 * ---
 * Sample Input 3:
 * 3
 * -1 2 1
 * ---
 * Sample Output 3:
 * 3
 * */

public class Ladder {

    public static void main(String[] args) {
        Ladder ladder = new Ladder();
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        long[] stair = Arrays.stream(scanner.nextLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        long result = ladder.getResult(n, stair);
        System.out.println(result);
    }

    private long getResult(int n, long[] stair) {
        long[] d = new long[n + 1];
        d[1] = stair[0];
        for (int i = 1; i < n; i++) {
            d[i + 1] = Math.max(d[i - 1] + stair[i], d[i] + stair[i]);
        }
        return d[n];
    }

}
