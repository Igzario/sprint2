import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
// Создал два класса, методы разнес, стало более читаемо
//
// "Объявление и инициализация данной переменной здесь лишняя. Вполне можно обойтись и без этого." - исправлено
// "Здесь второй параметр будет не нужен, если убрать его из метода." - соответственно)
// конструкцию switch-case пока не проходили, посмотрю что это такое
// "String userInputString как входной параметр не нужен." - почистил не нужное
// "Попробуй ввести "exit"? Приложение завершится?" - исправил
//
// "После считывания информации через scanner.nextLine() лучше удалить из полученной строки пробельные символы при помощи метода trim()"
// Использовал уже этот метод для обработки строки при вводе exit, к изначальному вводу применить не додумался, исправил
//
//
//"Это же константа." перенес ее в main и передаю в методы, хотел сделать считывание из консоли
//
//"Для чего идет здесь инициализация поля monthNumber?" - исправил
//
// "Правильно, что ты удаляешь перенос на новую строку, но не на всех операционных системах он именно "\n"." - исправил
//
// "Здесь и далее излишне использование класса-обертки." - исправил
//
// "Зачем здесь прописываешь условие? Разве оно не противоположно первоначальному?" - совершенно верно, уже сам наше это)
//
//


public class Main {

    public static void main(String[] args) throws IOException {
        // Поехали!
        Scanner scanner = new Scanner(System.in);
        MonthlyReport reportMonth = new MonthlyReport();
        YearlyReport reportYear = new YearlyReport();
        String yearNumber = "2021";


        while (true) {
            int userInput = printMenuAndRead(scanner);
            if (userInput == 1) {
                reportMonth.LoadMonth(yearNumber);
            }
            if (userInput == 2) {
                reportYear.LoadYear(yearNumber);
            }
            if (userInput == 3) {
                if (reportMonth.monthReport.get(1) == null && reportMonth.monthReport.get(1) == null) {
                    System.out.println("Внимание! Пожалуйста, загрузите отчёты ");
                    continue;
                } else if (reportYear.yearReport.get(1) == null) {
                    System.out.println("Внимание! Пожалуйста, загрузите годовой отчёт");
                    continue;
                } else if (reportYear.yearReport.get(1) == null) {
                    System.out.println("Внимание! Пожалуйста, загрузите месячные отчёты");
                    continue;
                } else {
                    printCheckReport(reportMonth, reportYear);
                }
            }
            if (userInput == 4) {
                if (reportMonth.monthReport.get(1) == null) {
                    System.out.println("Внимание! Пожалуйста, загрузите месячные отчёты");
                    continue;
                } else {
                    reportMonth.printMonthMinAndMax(yearNumber);
                }
            }
            if (userInput == 5) {
                if (reportYear.yearReport.get(1) == null) {
                    System.out.println("Внимание! Пожалуйста, загрузите годовой отчёт");
                    continue;
                } else {
                    reportYear.printYearReport(yearNumber);
                }
            }
            if (userInput == 0) {
                break;
            }
        }


    }

    static int printMenuAndRead(Scanner scanner) {
        String userInputString;
        while (true) {
            System.out.println("Что вы хотите сделать? ");
            System.out.println("1 - Считать все месячные отчёты");
            System.out.println("2 - Считать годовой отчёт");
            System.out.println("3 - Сверить отчёты");
            System.out.println("4 - Вывести информацию о всех месячных отчётах");
            System.out.println("5 - Вывести информацию о годовом отчёте");
            System.out.println("exit - Выход");
            userInputString = scanner.nextLine().trim();
            try {
                if (userInputString.trim().toLowerCase().equals("exit")) {
                    return 0;
                } else {

                    int userInputInt = Integer.parseInt(userInputString);

                    if (userInputInt == 1 || userInputInt == 2 || userInputInt == 3 || userInputInt == 4 || userInputInt == 5) {
                        return userInputInt;
                    } else {
                        System.out.println("Не верная команда");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Не верная команда");

            }
        }
    }

    static void printCheckReport(MonthlyReport reportMonth, YearlyReport yearReport) {
        String month = "01";
        int monthUpMoney = 0;
        int monthDownMoney = 0;
        for (int i = 1; i <= yearReport.yearReport.size(); i++) {
            ArrayList<Integer> monthMoney = new ArrayList<>();

            if (yearReport.yearReport.get(i).get(0).equals(month)) {
                if (yearReport.yearReport.get(i).get(2).equals("false")) {
                    monthUpMoney = Integer.parseInt(yearReport.yearReport.get(i).get(1));

                } else {
                    monthDownMoney = Integer.parseInt(yearReport.yearReport.get(i).get(1));
                }

            } else {
                month = yearReport.yearReport.get(i).get(0);
                if (yearReport.yearReport.get(i).get(2).equals("false")) {
                    monthUpMoney = Integer.parseInt(yearReport.yearReport.get(i).get(1));

                } else {
                    monthDownMoney = Integer.parseInt(yearReport.yearReport.get(i).get(1));
                }

            }

            if (i % 2 == 0) {

                monthMoney.add(monthUpMoney);
                monthMoney.add(monthDownMoney);

                yearReport.yearMonthMoney.put(Integer.parseInt(month), monthMoney);
            }

        }

        for (int i = 1; i <= reportMonth.monthReport.size(); i++) {
            int monthDohod = 0;
            int monthRashod = 0;

            for (int j = 1; j <= reportMonth.monthReport.get(i).size(); j++) {

                if (reportMonth.monthReport.get(i).get(j).get(1).equals("FALSE")) {
                    int dohod = Integer.parseInt(reportMonth.monthReport.get(i).get(j).get(2)) * Integer.parseInt(reportMonth.monthReport.get(i).get(j).get(3));
                    ;
                    monthDohod += dohod;
                } else if (reportMonth.monthReport.get(i).get(j).get(1).equals("TRUE")) {
                    int rashod = Integer.parseInt(reportMonth.monthReport.get(i).get(j).get(2)) * Integer.parseInt(reportMonth.monthReport.get(i).get(j).get(3));
                    monthRashod += rashod;
                }
            }
            int monthDohodFromYearHash = yearReport.yearMonthMoney.get(i).get(0);
            int monthRashodFromYearHash = yearReport.yearMonthMoney.get(i).get(1);
            System.out.println("\n" + reportMonth.monthToString(i) + ":");
            System.out.println("               Отчет за месяц              Отчет за год");
            System.out.println("Доходы:          " + monthDohod + "                    " + monthDohodFromYearHash);
            System.out.println("Расходы:         " + monthRashod + "                     " + monthRashodFromYearHash);

            if (monthDohod == monthDohodFromYearHash) {
                System.out.println("Расхождений между месячным отчетом и годовым за " + reportMonth.monthToString(i) + " не выявлено");
            } else if (monthRashod != monthRashodFromYearHash) {
                System.out.println("Внимание! Выявлено расхождение расходов");
            } else if (monthDohod != monthDohodFromYearHash) {
                System.out.println("Внимание! Выявлено расхождение доходов");
            }
        }
        System.out.println("");
    }


}
