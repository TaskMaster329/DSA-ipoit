package by.it.group410972.aliyev.lesson04;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

Sample Input:
5
2 3 9 2 9
Sample Output:
2
*/

public class C_GetInversions {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_GetInversions.class.getResourceAsStream("dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        int result = instance.calc(stream);
        System.out.print(result);
    }

    int calc(InputStream stream) throws FileNotFoundException {
        // подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // размер массива
        int n = scanner.nextInt();
        // сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // массив для копирования данных и подсчета инверсий
        int[] temp = new int[n];

        // вычисление инверсий
        return mergeSortAndCount(a, temp, 0, n - 1);
    }

    // Модифицированный mergeSort, который также подсчитывает инверсии
    int mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = (left + right) / 2;

            // считаем инверсии для левой и правой половин
            count += mergeSortAndCount(arr, temp, left, mid);
            count += mergeSortAndCount(arr, temp, mid + 1, right);

            // сливаем две половины и считаем инверсии, произошедшие при слиянии
            count += mergeAndCount(arr, temp, left, mid, right);
        }
        return count;
    }

    // Функция слияния двух отсортированных частей массива с подсчетом инверсий
    int mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;   // Начало левой половины
        int j = mid + 1; // Начало правой половины
        int k = left;   // Индекс для временного массива
        int invCount = 0;

        // Слияние
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                // Все элементы в левой половине (arr[i], arr[i+1], ..., arr[mid])
                // больше чем arr[j], т.е. каждый из них образует инверсию с arr[j]
                invCount += (mid - i + 1);
            }
        }

        // Копируем оставшиеся элементы из левой половины, если они есть
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Копируем оставшиеся элементы из правой половины, если они есть
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Копируем отсортированные элементы обратно в оригинальный массив
        System.arraycopy(temp, left, arr, left, right - left + 1);

        return invCount;
    }
}
