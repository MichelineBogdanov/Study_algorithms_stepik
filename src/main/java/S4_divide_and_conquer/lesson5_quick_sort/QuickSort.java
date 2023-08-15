package S4_divide_and_conquer.lesson5_quick_sort;

/**
 * В первой строке задано два целых числа 1≤n≤50000 и 1≤m≤50000 — количество отрезков
 * и точек на прямой, соответственно. Следующие n строк содержат по два целых числа ai и bi
 * (ai≤bi) — координаты концов отрезков. Последняя строка содержит m целых чисел — координаты точек.
 * Все координаты не превышают 10^8 по модулю. Точка считается принадлежащей отрезку,
 * если она находится внутри него или на границе.
 * Для каждой точки в порядке появления во вводе выведите, скольким отрезкам она принадлежит.
 * ---
 * Sample Input:
 * 2 3
 * 0 5
 * 7 10
 * 1 6 11
 * ---
 * Sample Output:
 * 1 0 0
 */

import java.util.*;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.start();
    }

    private void start() {
        List<LineSegment> lineSegments = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String firstData = scanner.nextLine();
        String[] data = firstData.split(" ");
        int countOfLines = Integer.parseInt(data[0]);
        for (int i = 0; i < countOfLines; i++) {
            String lineData = scanner.nextLine();
            long[] arrayData = Arrays.stream(lineData.split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();
            lineSegments.add(new LineSegment(arrayData[0], arrayData[1]));
        }
        String dotData = scanner.nextLine();
        long[] dots = Arrays.stream(dotData.split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        List<Integer> result = countIntersections(lineSegments, dots);
        printResult(Objects.requireNonNull(result));

    }

    private static void printResult(List<Integer> result) {
        StringBuilder stringResult = new StringBuilder();
        for (Integer countOfIntersections : result) {
            stringResult.append(countOfIntersections);
            if (result.indexOf(countOfIntersections) != result.size() - 1) {
                stringResult.append(" ");
            }
        }
        System.out.println(stringResult);
    }

    private List<Integer> countIntersections(List<LineSegment> lineSegments, long[] dots) {
        long[] startList = lineSegments.stream()
                .mapToLong(ls -> ls.start)
                .toArray();
        long[] endList = lineSegments.stream()
                .mapToLong(ls -> ls.end)
                .toArray();
        quickSort(startList, 0, startList.length - 1);
        System.out.println(Arrays.toString(startList));
        quickSort(endList, 0, endList.length - 1);
        System.out.println(Arrays.toString(endList));
        List<Integer> result = new ArrayList<>();
        for (long dot : dots) {
            int collision = binarySearchForStart(startList, dot);
            int nonCollision = binarySearchForEnd(endList, dot);
            result.add(collision - nonCollision);
        }
        return result;
    }

    private void quickSort(long[] dots, int low, int high) {
        if (dots.length == 0 || low >= high) {
            return;
        }
        Result partition = partition(dots, low, high);
        if (low <= partition.left - 1) {
            quickSort(dots, low, partition.left - 1);
        }
        if (partition.right + 1 <= high) {
            quickSort(dots, partition.right + 1, high);
        }
    }

    private Result partition(long[] dots, int left, int right) {
        int equalsCounter = 0;
        int pivotPosition = left;
        long pivot = dots[pivotPosition];
        int startPos = left + 1;
        int endPos = right;
        while (startPos <= endPos) {
            if (dots[startPos] < pivot) {
                int realPos = pivotPosition - equalsCounter;
                long termPivot = dots[realPos];
                dots[realPos] = dots[startPos];
                dots[startPos] = termPivot;
                pivotPosition++;
                startPos++;
            } else if (dots[startPos] == pivot) {
                equalsCounter++;
                pivotPosition++;
                startPos++;
            } else {
                long termDot = dots[startPos];
                dots[startPos] = dots[endPos];
                dots[endPos] = termDot;
                endPos--;
            }
        }
        return new Result(pivotPosition - equalsCounter, pivotPosition);
    }

    private int binarySearchForStart(long[] elementData, long search) {
        int start = 0;
        int end = elementData.length - 1;
        while (start <= end) {
            int current = (start + end) / 2;
            long check = elementData[current];
            if (check == search) {
                while (current < elementData.length && search == elementData[current]) {
                    current++;
                }
                return current;
            } else if (check < search) {
                start = current + 1;
            } else {
                end = current - 1;
            }
        }
        return start;
    }

    private int binarySearchForEnd(long[] elementData, long search) {
        int start = 0;
        int end = elementData.length - 1;
        while (start <= end) {
            int current = (start + end) / 2;
            long check = elementData[current];
            if (check == search) {
                while (current > 0 && search == elementData[current - 1]) {
                    current--;
                }
                return current;
            } else if (check < search) {
                start = current + 1;
            } else {
                end = current - 1;
            }
        }
        return start;
    }

    private record LineSegment(long start, long end) {
    }

    private record Result(int left, int right) {
    }

}

