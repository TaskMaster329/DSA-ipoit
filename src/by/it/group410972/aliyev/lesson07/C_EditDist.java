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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/



public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        int len1 = one.length();
        int len2 = two.length();

        // Инициализируем таблицу для динамического программирования
        int[][] dp = new int[len1 + 1][len2 + 1];
        char[][] operation = new char[len1 + 1][len2 + 1]; // Для отслеживания операций

        // Заполняем таблицу на базе базовых случаев
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
            operation[i][0] = '-';  // Для удаления символа
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
            operation[0][j] = '+';  // Для вставки символа
        }

        // Заполняем таблицу для остальных случаев
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    operation[i][j] = '#';  // Совпадение
                } else {
                    // Выбираем минимальную операцию и записываем её
                    int insert = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    int replace = dp[i - 1][j - 1] + 1;

                    dp[i][j] = Math.min(Math.min(insert, delete), replace);

                    if (dp[i][j] == insert) {
                        operation[i][j] = '+';  // Вставка
                    } else if (dp[i][j] == delete) {
                        operation[i][j] = '-';  // Удаление
                    } else {
                        operation[i][j] = '~';  // Замена
                    }
                }
            }
        }

        // Прослеживаем операции, начиная с dp[len1][len2]
        StringBuilder result = new StringBuilder();
        int i = len1, j = len2;
        while (i > 0 || j > 0) {
            if (operation[i][j] == '#') {
                result.insert(0, "#,");
                i--;
                j--;
            } else if (operation[i][j] == '-') {
                result.insert(0, "-" + one.charAt(i - 1) + ",");
                i--;
            } else if (operation[i][j] == '+') {
                result.insert(0, "+" + two.charAt(j - 1) + ",");
                j--;
            } else if (operation[i][j] == '~') {
                result.insert(0, "~" + two.charAt(j - 1) + ",");
                i--;
                j--;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_EditDist.class.getResourceAsStream("dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);

        // Чтение строк и вывод результата для каждой пары строк
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
