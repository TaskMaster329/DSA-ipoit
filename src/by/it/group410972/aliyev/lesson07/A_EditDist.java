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
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

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

public class A_EditDist {

    // Мемоизация для хранения уже вычисленных значений
    private int[][] memo;

    int getDistanceEdinting(String one, String two) {
        // Инициализация мемоизации
        memo = new int[one.length() + 1][two.length() + 1];

        // Заполнение массива значений -1 (значения еще не вычислены)
        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                memo[i][j] = -1;
            }
        }

        // Рекурсивный вызов для расчета расстояния Левенштейна
        return calcDistance(one, two, one.length(), two.length());
    }

    // Рекурсивный метод для вычисления расстояния Левенштейна с мемоизацией
    private int calcDistance(String one, String two, int i, int j) {
        // Если один из индексов дошел до 0, расстояние - это длина другой строки
        if (i == 0) return j;
        if (j == 0) return i;

        // Если результат уже вычислен, возвращаем его из мемоизации
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Если символы на текущих позициях одинаковы, не нужно ничего менять
        if (one.charAt(i - 1) == two.charAt(j - 1)) {
            return memo[i][j] = calcDistance(one, two, i - 1, j - 1);
        }

        // Если символы разные, пробуем все три операции
        int insert = calcDistance(one, two, i, j - 1); // Вставить
        int delete = calcDistance(one, two, i - 1, j); // Удалить
        int replace = calcDistance(one, two, i - 1, j - 1); // Заменить

        // Возвращаем минимальное из операций
        return memo[i][j] = 1 + Math.min(Math.min(insert, delete), replace);
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_EditDist.class.getResourceAsStream("dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);

        // Чтение строк и вывод результата для каждой пары строк
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
