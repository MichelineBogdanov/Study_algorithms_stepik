package greedyalgorithms.lesson2_huffmancodes;

/*
 * Восстановите строку по её коду и беспрефиксному коду символов.
 * В первой строке входного файла заданы два целых числа k и
 * l через пробел — количество различных букв, встречающихся в строке,
 * и размер получившейся закодированной строки, соответственно. В следующих
 * k строках записаны коды букв в формате "letter: code".
 * Ни один код не является префиксом другого.
 * Буквы могут быть перечислены в любом порядке.
 * В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
 * каждая из этих букв встречается в строке хотя бы один раз.
 * Наконец, в последней строке записана закодированная строка.
 * Исходная строка и коды всех букв непусты. Заданный код таков,
 * что закодированная строка имеет минимальный возможный размер.
 * В первой строке выходного файла выведите строку s.
 * Она должна состоять из строчных букв латинского алфавита.
 * Гарантируется, что длина правильного ответа не превосходит 10^4 символов.
 *
 * Sample Input 1:
 * 1 1
 * a: 0
 * 0
 *
 * Sample Output 1:
 * a
 *
 * Sample Input 2:
 * 4 14
 * a: 0
 * b: 10
 * c: 110
 * d: 111
 * 01001100100111
 *
 * Sample Output 2:
 * abacabad
 */

import java.util.*;

public class HuffmanDecode {

    public static void main(String[] args) {
        HuffmanDecode huffmanDecode = new HuffmanDecode();
        Scanner scanner = new Scanner(System.in);
        String linesCount = scanner.nextLine();
        String[] linesCountArr = linesCount.split(" ");
        int count = Integer.parseInt(linesCountArr[0]);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String codes = scanner.nextLine();
            String[] code = codes.split(": ");
            map.put(code[1], code[0]);
        }
        String textCode = scanner.nextLine();
        String decode = huffmanDecode.decode(map, textCode);
        System.out.println(decode);
    }

    public String decode(Map<String, String> map, String textCode) {
        StringBuilder result = new StringBuilder();
        StringBuilder checker = new StringBuilder();
        List<String> list = textCode.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .toList();
        for (String s : list) {
            checker.append(s);
            if (map.containsKey(checker.toString())) {
                String found = map.get(checker.toString());
                result.append(found);
                checker.delete(0, checker.length());
            }
        }
        return result.toString();
    }
}
