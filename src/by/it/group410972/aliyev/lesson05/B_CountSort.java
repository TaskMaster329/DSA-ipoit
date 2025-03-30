package by.it.group410972.aliyev.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Первая строка содержит число 1<=n<=10000, вторая - n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

При сортировке реализуйте метод со сложностью O(n)

Пример: https://karussell.wordpress.com/2010/03/01/fast-integer-sorting-algorithm-on/
Вольный перевод: http://programador.ru/sorting-positive-int-linear-time/
*/

public class B_CountSort {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_CountSort.class.getResourceAsStream("dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] countSort(InputStream stream) throws FileNotFoundException {
        // подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // размер массива
        int n = scanner.nextInt();
        int[] points = new int[n];

        // читаем числа в массив
        int max = 0;  // максимальное число, которое мы будем сортировать
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
            if (points[i] > max) {
                max = points[i];  // находим максимальное число
            }
        }

        // создаем массив подсчётов для чисел от 0 до max
        int[] count = new int[max + 1];

        // Подсчитываем количество вхождений каждого числа
        for (int i = 0; i < n; i++) {
            count[points[i]]++;
        }

        // Восстанавливаем отсортированный массив
        int[] sorted = new int[n];
        int index = 0;
        for (int i = 0; i <= max; i++) {
            while (count[i] > 0) {
                sorted[index++] = i;
                count[i]--;
            }
        }

        return sorted;
    }
}
