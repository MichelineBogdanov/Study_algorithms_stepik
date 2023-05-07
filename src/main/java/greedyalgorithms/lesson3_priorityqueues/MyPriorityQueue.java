package greedyalgorithms.lesson3_priorityqueues;

/*
 * Первая строка входа содержит число операций 1≤n≤10^5. Каждая из последующих
 * n строк задают операцию одного из следующих двух типов:
 * Insert(x), где 0≤x≤10^9 — целое число;
 * ExtractMax.
 * Первая операция добавляет число x в очередь с приоритетами,
 * вторая — извлекает максимальное число и выводит его.
 *
 * Sample Input:
 * 6
 * Insert 200
 * Insert 10
 * ExtractMax
 * Insert 5
 * Insert 500
 * ExtractMax
 *
 * Sample Output:
 * 200
 * 500
 */

import java.util.*;

public class MyPriorityQueue {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String count = scanner.nextLine();
        int parseCount = Integer.parseInt(count);
        Deque<String> inputOperations = new ArrayDeque<>();
        for (int i = 0; i < parseCount; i++) {
            String data = scanner.nextLine();
            String[] splitData = data.split(" ");
            if (splitData.length == 2) {
                inputOperations.add(splitData[1]);
            } else {
                inputOperations.add("e");
            }
        }
        makeDeal(inputOperations);
    }

    private static void makeDeal(Deque<String> inputOperations) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> -o));
        for (String inputOperation : inputOperations) {
            if (inputOperation.equals("e")) {
                Integer poll = queue.poll();
                System.out.println(poll);
            } else {
                queue.add(Integer.parseInt(inputOperation));
            }
        }
    }

    /*private static void makeDeal(Deque<String> inputOperations) {
        List<Integer> list = new ArrayList<>();
        for (String inputOperation : inputOperations) {
            if (inputOperation.equals("e")) {
                list.sort(Comparator.comparingInt(o -> -o));
                System.out.println(list.get(0));
                list.remove(0);
            } else {
                list.add(Integer.parseInt(inputOperation));
            }
        }
    }*/
}
