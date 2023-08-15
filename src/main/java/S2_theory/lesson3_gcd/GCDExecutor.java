package S2_theory.lesson3_gcd;

import java.util.Scanner;

/**
 * По данным двум числам 1≤a,b≤2⋅10^{9} найдите их наибольший общий делитель.
 */

public class GCDExecutor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long i1 = scanner.nextLong();
        long i2 = scanner.nextLong();
        System.out.println(maxGCD(i1, i2));
    }

    public static long maxGCD(long a, long b) {
        long gcd;
        if (a == b) {
            return a;
        }
        if (a == 0 || b == 0) {
            return Math.max(a, b);
        }
        if (a % b == 0 || b % a == 0) {
            return Math.min(a, b);
        }
        while (true) {
            if (a >= b) {
                a = a % b;
                gcd = a;
                if (b % a == 0) {
                    return gcd;
                }
            } else {
                b = b % a;
                gcd = b;
                if (a % b == 0) {
                    return gcd;
                }
            }
        }
    }

}
