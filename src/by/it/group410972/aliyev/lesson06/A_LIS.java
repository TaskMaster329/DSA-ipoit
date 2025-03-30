package by.it.group410972.aliyev.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая возрастающая подпоследовательность
см.     https://ru.wikipedia.org/wiki/Задача_поиска_наибольшей_увеличивающейся_подпоследовательности
        https://en.wikipedia.org/wiki/Longest_increasing_subsequence

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    где каждый элемент A[i[k]] больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]<A[i[j+1]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    1 3 3 2 6

    Sample Output:
    3
*/



public class A_LIS {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_LIS.class.getResourceAsStream("dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }

    int getSeqSize(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];

        // Чтение самой последовательности
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        // Массив для хранения длин наибольших возрастающих подпоследовательностей
        int[] dp = new int[n];
        // Изначально каждый элемент является возрастающей подпоследовательностью длины 1
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Основной цикл для вычисления длины наибольшей возрастающей подпоследовательности
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[i] > m[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Ответ - максимальная длина возрастающей подпоследовательности
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}
