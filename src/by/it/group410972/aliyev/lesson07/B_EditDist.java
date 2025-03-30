package by.it.group410972.aliyev.lesson07;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/



public class B_EditDist {

    int getDistanceEdinting(String one, String two) {
        int len1 = one.length();
        int len2 = two.length();

        // Инициализируем таблицу для динамического программирования
        int[][] dp = new int[len1 + 1][len2 + 1];

        // Заполняем таблицу на базе базовых случаев
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i; // Удаления всех символов из первой строки
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j; // Вставки всех символов во вторую строку
        }

        // Заполняем таблицу для остальных случаев
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // Если символы равны, не требуется операций
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    // Вставка, удаление, замена — выбираем минимальную операцию
                }
            }
        }

        // Результат — это минимальное количество операций для преобразования всей первой строки во вторую
        return dp[len1][len2];
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_EditDist.class.getResourceAsStream("dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);

        // Чтение строк и вывод результата для каждой пары строк
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
