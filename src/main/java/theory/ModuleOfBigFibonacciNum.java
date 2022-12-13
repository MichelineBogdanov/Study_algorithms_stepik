package theory;

/*
Даны целые числа 1≤n≤10^{18} 2≤m≤10^{5}, необходимо найти остаток от деления nn-го числа Фибоначчи на m.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ModuleOfBigFibonacciNum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long i1 = scanner.nextLong();
        long i2 = scanner.nextLong();
        System.out.print(modFib(i1, i2));
    }

    public static long modFib(long fibNum, long mod) {
        long period = 0;
        ArrayList<Long> ints = new ArrayList<>(Arrays.asList(0L, 1L));
        for (int i = 1; i < 6 * mod; i++) {
            long cur = ints.get(i);
            long prev = ints.get(i - 1);
            long next = (cur + prev) % mod;
            ints.add(next);
            if (ints.get(ints.size() - 1) == 1 && ints.get(ints.size() - 2) == 0) {
                period = ints.size() - 2;
                break;
            }
        }
        return ints.get((int) (fibNum % period));
    }

}
