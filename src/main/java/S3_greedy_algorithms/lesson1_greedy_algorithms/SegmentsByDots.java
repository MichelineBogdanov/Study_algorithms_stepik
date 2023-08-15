package S3_greedy_algorithms.lesson1_greedy_algorithms;

/**
 * По данным n отрезкам необходимо найти множество точек минимального размера,
 * для которого каждый из отрезков содержит хотя бы одну из точек.
 * В первой строке дано число 1≤n≤100 отрезков. Каждая из последующих n строк содержит
 * по два числа 0≤l≤r≤10^9, задающих начало и конец отрезка. Выведите оптимальное число
 * m точек и сами m точек. Если таких множеств точек несколько, выведите любое из них.
 * ---
 * Sample Input 1:
 * 3
 * 1 3
 * 2 5
 * 3 6
 * ---
 * Sample Output 1:
 * 1
 * 3
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SegmentsByDots {

    public static void main(String[] args) {
        SegmentsByDots segmentsByDots = new SegmentsByDots();
        segmentsByDots.start();
    }

    private void start() {
        List<LineSegment> inputData = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < size; i++) {
            String stringData = scanner.nextLine();
            long[] arrayData = Arrays.stream(stringData.split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();
            inputData.add(new LineSegment(arrayData[0], arrayData[1]));
        }
        List<Long> result = optimize(inputData);
        System.out.println(result.size());
        StringBuilder stringResult = new StringBuilder();
        for (Long collisionDot : result) {
            stringResult.append(collisionDot);
            if (result.indexOf(collisionDot) != result.size() - 1) {
                stringResult.append(" ");
            }
        }
        System.out.println(stringResult);
    }

    private List<Long> optimize(List<LineSegment> data) {
        List<Long> result = new ArrayList<>();
        List<LineSegment> sortedLineSegments = data.stream()
                .sorted(Comparator.comparing(LineSegment::getEnd))
                .toList();

        result.add(sortedLineSegments.get(0).getEnd());
        int count;
        for (int i = 0; i < sortedLineSegments.size() - 1; i += count) {
            count = 1;
            for (int j = i + 1; j < sortedLineSegments.size(); j++) {
                if (sortedLineSegments.get(j).getStart() <= result.get(result.size() - 1)) {
                    break;
                } else {
                    result.add(sortedLineSegments.get(j).getEnd());
                    count++;
                }
            }
        }
        return result;
    }

    private static class LineSegment {
        private long start;
        private long end;

        public LineSegment(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public long getStart() {
            return start;
        }

        public long getEnd() {
            return end;
        }


        @Override
        public String toString() {
            return "LineSegment{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

}
