
/*
В данной задаче требуется вычислить сумму двух входных целых чисел, лежащих в отрезке от нуля до десяти.
Никаких подвохов, это очевидная задача, предназначенная для того, чтобы познакомить вас с проверяющей системой.
На следующем степе приведены решения данной задачи на нескольких языках программирования (вы можете прямо сейчас
перейти туда и скопировать решение оттуда). В этой задаче, как и во всех задачах на программирование,
не нужно проверять, что входные данные удовлетворяют требованиям, заявленным в условии.
Другими словами, во всех тестах, на которых будет проверяться ваша программа, на вход будут подаваться
два целых числа от 0 до 10.
Sample Input:
7 3
Sample Output:
10
*/

package introduction;

import java.util.Scanner;

public class TestTask {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] arr = s.split(" ");
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[1]);
        System.out.println(add(a, b));
    }

    public static int add(int a, int b) {
        return a + b;
    }
}
