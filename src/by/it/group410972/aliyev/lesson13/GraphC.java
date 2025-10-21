package by.it.group410972.aliyev.lesson13;

import java.util.*;

public class GraphC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        sc.close();

        Map<String, List<String>> graph = new HashMap<>();
        Map<String, List<String>> reverse = new HashMap<>();
        Set<String> allNodes = new HashSet<>();

        // Пример входа: C->B, C->I, I->A, A->D, D->I, ...
        String[] parts = input.split(",");
        for (String part : parts) {
            part = part.trim();
            if (part.isEmpty()) continue;

            String[] edge = part.split("->");
            if (edge.length != 2) continue;

            String from = edge[0].trim();
            String to = edge[1].trim();

            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            reverse.computeIfAbsent(to, k -> new ArrayList<>()).add(from);

            allNodes.add(from);
            allNodes.add(to);
        }

        for (String v : allNodes) {
            graph.putIfAbsent(v, new ArrayList<>());
            reverse.putIfAbsent(v, new ArrayList<>());
        }

        // Шаг 1: порядок обхода (по времени выхода)
        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();
        for (String v : allNodes) {
            if (!visited.contains(v))
                dfs1(v, graph, visited, stack);
        }

        // Шаг 2: обход транспонированного графа
        visited.clear();
        List<List<String>> sccs = new ArrayList<>();
        while (!stack.isEmpty()) {
            String v = stack.pop();
            if (!visited.contains(v)) {
                List<String> comp = new ArrayList<>();
                dfs2(v, reverse, visited, comp);
                Collections.sort(comp);
                sccs.add(comp);
            }
        }

        // Вывод: каждая компонента — строка, без пробелов
        for (List<String> comp : sccs) {
            for (String node : comp) System.out.print(node);
            System.out.println();
        }
    }

    private static void dfs1(String v, Map<String, List<String>> g, Set<String> visited, Deque<String> stack) {
        visited.add(v);
        for (String u : g.get(v))
            if (!visited.contains(u))
                dfs1(u, g, visited, stack);
        stack.push(v);
    }

    private static void dfs2(String v, Map<String, List<String>> g, Set<String> visited, List<String> comp) {
        visited.add(v);
        comp.add(v);
        for (String u : g.get(v))
            if (!visited.contains(u))
                dfs2(u, g, visited, comp);
    }
}
