package greedyalgorithms.lesson2_huffmancodes;

/*
 * По данной непустой строке s длины не более 10^4, состоящей из строчных букв латинского алфавита,
 * постройте оптимальный беспрефиксный код. В первой строке выведите количество различных букв
 * k, встречающихся в строке, и размер получившейся закодированной строки. В следующих
 * k строках запишите коды букв в формате "letter: code". В последней строке выведите закодированную строку.
 *
 * Sample Input 1:
 * a
 *
 * Sample Output 1:
 * 1 1
 * a: 0
 * 0
 * *
 * Sample Input 2:
 * abacabad
 *
 * Sample Output 2:
 * 4 14
 * a: 0
 * b: 10
 * c: 110
 * d: 111
 * 01001100100111
 * */

import java.util.*;
import java.util.stream.Collectors;

class HuffmanCode {
    public static void main(String[] args) {
        HuffmanCode main = new HuffmanCode();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        main.huffmanCode(input);
    }

    private void huffmanCode(String input) {
        Comparator<Node> nodeComparator = Comparator.comparingLong(Node::getValue);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(nodeComparator);

        List<Node> nodeList = input.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.groupingBy(letter -> letter, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new Node(entry.getKey(), entry.getValue()))
                .sorted(nodeComparator)
                .toList();

        priorityQueue.addAll(nodeList);

        while (priorityQueue.size() > 1) {
            Node poll1 = priorityQueue.poll();
            Node poll2 = priorityQueue.poll();
            long valNew = poll1.getValue() + poll2.getValue();
            Node node = new Node(null, valNew, poll2, poll1);
            priorityQueue.add(node);
        }

        Node poll = priorityQueue.poll();
        StringBuilder stringBuilder = new StringBuilder();
        setCodeToNodes(poll, stringBuilder);
        List<Node> leafs = new ArrayList<>();
        getLeafs(poll, leafs);

        long size = 0;
        for (Node leaf : leafs) {
            size += leaf.getCode().length() * leaf.getValue();
        }
        System.out.println(leafs.size() + " " + size);
        Map<String, String> map = new HashMap<>();
        for (Node leaf : leafs) {
            System.out.println(leaf.getLetter() + ": " + leaf.getCode());
            map.put(leaf.getLetter(), leaf.getCode());
        }
        String collect = input.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .map(map::get)
                .collect(Collectors.joining());
        System.out.println(collect);

    }

    public void setCodeToNodes(Node node, StringBuilder code) {
        if (node.getLetter() != null) {
            String resultCode = code.isEmpty() ? "0" : code.toString();
            node.setCode(resultCode);
            return;
        }
        if (node.checkLeft()) {
            code.append("0");
            Node left = node.getLeft();
            setCodeToNodes(left, code);
        }
        if (node.checkRight()) {
            code.deleteCharAt(code.length() - 1);
            code.append("1");
            Node right = node.getRight();
            setCodeToNodes(right, code);
        }
        code.deleteCharAt(code.length() - 1);
    }

    public void getLeafs(Node node, List<Node> list) {
        if (node.getLetter() != null) {
            list.add(node);
            return;
        }
        if (node.checkLeft()) {
            Node left = node.getLeft();
            getLeafs(left, list);
        }
        if (node.checkRight()) {
            Node right = node.getRight();
            getLeafs(right, list);
        }
    }
}

class Node {

    private String letter;
    private long value;
    private String code;

    private Node right;
    private Node left;

    public Node(String letter, long value, Node right, Node left) {
        this.letter = letter;
        this.value = value;
        this.right = right;
        this.left = left;
    }

    public Node(String letter, long value) {
        this.letter = letter;
        this.value = value;
    }

    public boolean checkLeft() {
        return this.getLeft() != null;
    }

    public boolean checkRight() {
        return this.getRight() != null;
    }

    public String getLetter() {
        return letter;
    }

    public long getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Node getRight() {
        return right;
    }

    public Node getLeft() {
        return left;
    }

    @Override
    public String toString() {
        return "Node{" +
                "letter='" + letter + '\'' +
                ", value=" + value +
                ", code='" + code + '\'' +
                '}';
    }
}
