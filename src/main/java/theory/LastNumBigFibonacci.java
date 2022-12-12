package theory;

/*Дано число 1≤n≤10^7, необходимо найти последнюю цифру n-го числа Фибоначчи.
Как мы помним, числа Фибоначчи растут очень быстро, поэтому при их вычислении нужно быть аккуратным с переполнением.
В данной задаче, впрочем, этой проблемы можно избежать, поскольку нас интересует только последняя цифра числа
Фибоначчи: если 0≤a,b≤9 — последние цифры чисел F_i и F_i+1 соответственно,
то (a+b) mod 10 — последняя цифра числа F_i+2.
*/

import java.util.Scanner;

public class LastNumBigFibonacci {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        System.out.print(lastNum(i));
    }

    public static int lastNum(int n) {
        int current = 0;
        int next = 1;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            result = ((current % 10) + (next % 10)) % 10;
            current = next;
            next = result;
        }
        return result;
    }
}
