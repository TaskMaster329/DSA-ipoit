package by.it.group410972.aliyev.lesson13;

import java.util.*;

public class GraphB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        sc.close();

        // Пример входа: "1 -> 2, 1 -> 3, 2 -> 3"
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

        // Добавляем вершины без исходящих рёбер
        for (String v : allNodes) graph.putIfAbsent(v, new ArrayList<>());

        Map<String, Integer> color = new HashMap<>();
        for (String v : allNodes) color.put(v, 0); // 0 - не посещён

        boolean hasCycle = false;

        for (String v : allNodes) {
            if (color.get(v) == 0) {
                if (dfs(v, graph, color)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        System.out.println(hasCycle ? "yes" : "no");
    }

    private static boolean dfs(String v, Map<String, List<String>> graph, Map<String, Integer> color) {
        color.put(v, 1); // 1 - в стеке
        for (String u : graph.get(v)) {
            int c = color.getOrDefault(u, 0);
            if (c == 1) return true; // цикл
            if (c == 0 && dfs(u, graph, color)) return true;
        }
        color.put(v, 2); // 2 - обработан
        return false;
    }
}
