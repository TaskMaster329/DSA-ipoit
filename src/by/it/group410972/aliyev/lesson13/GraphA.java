package by.it.group410972.aliyev.lesson13;
import java.util.*;

public class GraphA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        sc.close();

        // Пример входа: "0 -> 2, 1 -> 3, 2 -> 3, 0 -> 1" или "A -> B, B -> C"
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> allNodes = new HashSet<>();

        String[] parts = input.split(",");
        for (String part : parts) {
            part = part.trim();
            if (part.isEmpty()) continue;

            String[] sides = part.split("->");
            if (sides.length != 2) continue;

            String from = sides[0].trim();
            String to = sides[1].trim();

            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            allNodes.add(from);
            allNodes.add(to);
        }

        // Добавим вершины без исходящих рёбер
        for (String v : allNodes) graph.putIfAbsent(v, new ArrayList<>());

        // Подсчёт входящих рёбер
        Map<String, Integer> indegree = new HashMap<>();
        for (String v : allNodes) indegree.put(v, 0);
        for (String v : graph.keySet()) {
            for (String u : graph.get(v)) {
                indegree.put(u, indegree.get(u) + 1);
            }
        }

        // Очередь с приоритетом (лексикографический порядок)
        PriorityQueue<String> q = new PriorityQueue<>();
        for (String v : indegree.keySet()) {
            if (indegree.get(v) == 0) q.add(v);
        }

        List<String> result = new ArrayList<>();
        while (!q.isEmpty()) {
            String v = q.poll();
            result.add(v);
            for (String u : graph.get(v)) {
                indegree.put(u, indegree.get(u) - 1);
                if (indegree.get(u) == 0) q.add(u);
            }
        }

        // Вывод
        System.out.println(String.join(" ", result));
    }
}
