package by.it.group410972.aliyev.lesson04;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/

public class B_MergeSort {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_MergeSort.class.getResourceAsStream("dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        int[] result = instance.getMergeSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // размер массива
        int n = scanner.nextInt();
        // сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // Реализация сортировки слиянием
        mergeSort(a, 0, a.length - 1);

        return a;
    }

    // Функция сортировки слиянием
    void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Найдем середину массива
            int mid = (left + right) / 2;

            // Рекурсивно сортируем левую и правую части массива
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Сливаем отсортированные части
            merge(arr, left, mid, right);
        }
    }

    // Функция слияния двух отсортированных частей массива
    void merge(int[] arr, int left, int mid, int right) {
        // Размеры подмассивов
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Временные массивы
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Копируем данные в временные массивы
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        // Индексы для L, R и основного массива
        int i = 0, j = 0, k = left;

        // Слияние
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Копируем оставшиеся элементы из L, если они есть
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Копируем оставшиеся элементы из R, если они есть
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
