package greedyalgorithms.lesson2_huffmancodes;

/**
 * По данной непустой строке s длины не более 10^4, состоящей из строчных букв латинского алфавита,
 * постройте оптимальный беспрефиксный код. В первой строке выведите количество различных букв
 * k, встречающихся в строке, и размер получившейся закодированной строки. В следующих
 * k строках запишите коды букв в формате "letter: code". В последней строке выведите закодированную строку.
 * ---
 * Sample Input 1:
 * a
 * ---
 * Sample Output 1:
 * 1 1
 * a: 0
 * 0
 * ---
 * Sample Input 2:
 * abacabad
 * ---
 * Sample Output 2:
 * 4 14
 * a: 0
 * b: 10
 * c: 110
 * d: 111
 * 01001100100111
 */

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
        Comparator<NodeC> nodeComparator = Comparator.comparingLong(NodeC::getValue);
        PriorityQueue<NodeC> priorityQueue = new PriorityQueue<>(nodeComparator);

        List<NodeC> nodeList = input.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.groupingBy(letter -> letter, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new NodeC(entry.getKey(), entry.getValue()))
                .sorted(nodeComparator)
                .toList();

        priorityQueue.addAll(nodeList);

        while (priorityQueue.size() > 1) {
            NodeC poll1 = priorityQueue.poll();
            NodeC poll2 = priorityQueue.poll();
            long valNew = poll1.getValue() + poll2.getValue();
            NodeC node = new NodeC(null, valNew, poll2, poll1);
            priorityQueue.add(node);
        }

        NodeC poll = priorityQueue.poll();
        StringBuilder stringBuilder = new StringBuilder();
        setCodeToNodes(poll, stringBuilder);
        List<NodeC> leafs = new ArrayList<>();
        getLeafs(poll, leafs);

        long size = 0;
        for (NodeC leaf : leafs) {
            size += leaf.getCode().length() * leaf.getValue();
        }
        System.out.println(leafs.size() + " " + size);
        Map<String, String> map = new HashMap<>();
        for (NodeC leaf : leafs) {
            System.out.println(leaf.getLetter() + ": " + leaf.getCode());
            map.put(leaf.getLetter(), leaf.getCode());
        }
        String collect = input.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .map(map::get)
                .collect(Collectors.joining());
        System.out.println(collect);

    }

    public void setCodeToNodes(NodeC node, StringBuilder code) {
        if (node.getLetter() != null) {
            String resultCode = code.isEmpty() ? "0" : code.toString();
            node.setCode(resultCode);
            return;
        }
        if (node.checkLeft()) {
            code.append("0");
            NodeC left = node.getLeft();
            setCodeToNodes(left, code);
        }
        if (node.checkRight()) {
            code.deleteCharAt(code.length() - 1);
            code.append("1");
            NodeC right = node.getRight();
            setCodeToNodes(right, code);
        }
        code.deleteCharAt(code.length() - 1);
    }

    public void getLeafs(NodeC node, List<NodeC> list) {
        if (node.getLetter() != null) {
            list.add(node);
            return;
        }
        if (node.checkLeft()) {
            NodeC left = node.getLeft();
            getLeafs(left, list);
        }
        if (node.checkRight()) {
            NodeC right = node.getRight();
            getLeafs(right, list);
        }
    }
}

class NodeC {

    private String letter;
    private long value;
    private String code;

    private NodeC right;
    private NodeC left;

    public NodeC(String letter, long value, NodeC right, NodeC left) {
        this.letter = letter;
        this.value = value;
        this.right = right;
        this.left = left;
    }

    public NodeC(String letter, long value) {
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

    public NodeC getRight() {
        return right;
    }

    public NodeC getLeft() {
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
