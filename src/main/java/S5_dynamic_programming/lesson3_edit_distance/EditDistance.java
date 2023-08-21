package S5_dynamic_programming.lesson3_edit_distance;

import java.util.Scanner;

/**
 * Вычислите расстояние редактирования двух данных непустых строк длины не более 10^2,
 * содержащих строчные буквы латинского алфавита.
 * ---
 * Sample Input 1:
 * ab
 * ab
 * ---
 * Sample Output 1:
 * 0
 * ---
 * Sample Input 2:
 * short
 * ports
 * ---
 * Sample Output 2:
 * 3
 */

public class EditDistance {

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        Scanner scanner = new Scanner(System.in);
        String first = scanner.nextLine();
        String second = scanner.nextLine();
        int result = editDistance.editDistBU(first, second);
        System.out.println(result);
    }

    private int editDistBU(String first, String second) {
        int n = first.length();
        int m = second.length();
        int[][] d = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                d[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < n + 1; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            d[0][j] = j;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int c = first.charAt(i - 1) == second.charAt(j - 1) ? 0 : 1;
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + c);
            }
        }
        return d[n][m];
    }

}
