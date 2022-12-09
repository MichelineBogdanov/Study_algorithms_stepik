package introduction;

import java.util.Scanner;

public class Lesson1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] arr = s.split(" ");
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[1]);
        System.out.print(add(a, b));
    }

    public static int add(int a, int b) {
        return a + b;
    }
}
