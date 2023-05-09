package greedyalgorithms.lesson1_greedyalgorithms;

/*
 * Первая строка содержит количество предметов 1≤n≤10^3 и вместимость рюкзака
 * 0≤W≤2⋅10^6. Каждая из следующих n строк задаёт стоимость 0≤ci≤2⋅10^6 и объём
 * 0<wi≤2⋅10^6 предмета (n,W,ci,wi — целые числа).
 * Выведите максимальную стоимость частей предметов (от каждого предмета можно
 * отделить любую часть, стоимость и объём при этом пропорционально уменьшатся),
 * помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.
 *
 * Sample Input:
 * 3 50
 * 60 20
 * 100 50
 * 120 30
 *
 * Sample Output:
 * 180.000
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class InfinityBag {

    public static void main(String[] args) {
        InfinityBag infinityBag = new InfinityBag();
        infinityBag.start();
    }

    private void start() {
        List<Thing> inputData = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String firstData = scanner.nextLine();
        String[] data = firstData.split(" ");
        long countOfThings = Long.parseLong(data[0]);
        long bagVolume = Long.parseLong(data[1]);
        for (int i = 0; i < countOfThings; i++) {
            String stringData = scanner.nextLine();
            double[] arrayData = Arrays.stream(stringData.split(" "))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            inputData.add(new Thing(arrayData[0], arrayData[1]));
        }
        Double result = optimize(bagVolume, inputData);
        System.out.println(result);
    }

    private Double optimize(double bagVolume, List<Thing> inputData) {
        List<Thing> sortedByPrice = inputData.stream()
                .sorted(Comparator.comparing(Thing::getPrice).reversed())
                .toList();
        double maxPriceBag = 0.0;
        for (Thing thing : sortedByPrice) {
            double volume = thing.getVolume();
            if (bagVolume > 0) {
                if (bagVolume >= volume) {
                    maxPriceBag += thing.getPriceByVolume(volume);
                    bagVolume -= volume;
                } else {
                    maxPriceBag += thing.getPriceByVolume(bagVolume);
                    bagVolume = 0;
                }
            }
        }
        return maxPriceBag;
    }

    private static class Thing {
        private double count;
        private double volume;
        private final double price;

        public Thing(double count, double volume) {
            this.count = count;
            this.volume = volume;
            if (count == 0 || volume == 0) {
                this.price = 0;
            } else {
                this.price = count / volume;
            }
        }

        public double getPriceByVolume(Double volume) {
            return volume * price;
        }

        public double getVolume() {
            return volume;
        }

        public Double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Thing{" +
                    "count=" + count +
                    ", volume=" + volume +
                    ", price=" + price +
                    '}';
        }
    }
}
