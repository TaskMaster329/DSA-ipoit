package by.it.group410972.aliyev.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/




public class C_LongNotUpSubSeq {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_LongNotUpSubSeq.class.getResourceAsStream("dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        instance.getNotUpSeqSize(stream);
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        // Подготовка к чтению данных
        Scanner scanner = new Scanner(stream);

        // Читаем длину последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];

        // Читаем саму последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        // Динамическое программирование (dp[i] - длина наибольшей невозрастающей подпоследовательности до элемента i)
        int[] dp = new int[n];
        int[] prev = new int[n];  // Для восстановления последовательности
        int maxLength = 1;
        int lastIndex = 0;

        // Изначально каждый элемент является последовательностью длины 1
        dp[0] = 1;
        prev[0] = -1;  // Нет предыдущего элемента

        for (int i = 1; i < n; i++) {
            dp[i] = 1;  // Минимальная длина подпоследовательности - сам элемент
            prev[i] = -1;  // Пока нет предшествующего элемента

            for (int j = 0; j < i; j++) {
                // Если текущий элемент меньше или равен предыдущему, обновляем dp[i]
                if (m[i] <= m[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;  // Запоминаем индекс предыдущего элемента
                }
            }

            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }

        // Восстановление самой подпоследовательности
        ArrayList<Integer> sequence = new ArrayList<>();
        for (int i = lastIndex; i >= 0; i = prev[i]) {
            sequence.add(i + 1);  // Индексы начинаются с 1
            if (prev[i] == -1) break;
        }

        // Печать результата
        Collections.reverse(sequence);  // Мы восстанавливали последовательность от конца
        System.out.println(maxLength);
        for (int index : sequence) {
            System.out.print(index + " ");
        }
        return maxLength;
    }
}
