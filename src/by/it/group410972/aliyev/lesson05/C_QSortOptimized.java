package by.it.group410972.aliyev.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/




public class C_QSortOptimized {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_QSortOptimized.class.getResourceAsStream("dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Читаем количество отрезков и точек
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        // Чтение отрезков
        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        // Чтение точек
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // Сортируем отрезки по началу отрезка
        Arrays.sort(segments);

        // Для каждой точки находим подходящие отрезки
        for (int i = 0; i < m; i++) {
            result[i] = findSegmentsForPoint(segments, points[i]);
        }

        return result;
    }

    // Метод для бинарного поиска первого отрезка, который содержит точку
    private int findSegmentsForPoint(Segment[] segments, int point) {
        int low = 0, high = segments.length - 1;
        // Бинарный поиск для первого подходящего отрезка
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (segments[mid].start <= point) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Теперь нужно пройти по всем отрезкам, которые содержат точку
        int count = 0;
        for (int i = high; i >= 0; i--) {
            if (segments[i].start <= point && segments[i].stop >= point) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    // Класс для представления отрезков
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment other) {
            return Integer.compare(this.start, other.start);
        }
    }
}
