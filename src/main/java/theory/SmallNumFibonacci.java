package theory;

/*
Дано целое число 1≤n≤40, необходимо вычислить n-е число Фибоначчи
*/

import java.util.Scanner;

public class SmallNumFibonacci {

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        System.out.print(fib(i));
    }

    public static int fib(int n) {
        if (n > 2) {
            int[] arr = new int[n];
            arr[0] = 1;
            arr[1] = 1;
            for (int i = 2; i < n; i++) {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
            return arr[n - 1];
        }
        return 1;
    }
}
