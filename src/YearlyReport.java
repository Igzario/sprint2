import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    HashMap<Integer, ArrayList<String>> yearReport;
    HashMap<Integer, ArrayList<Integer>> yearMonthMoney;
    String[] lines;

    YearlyReport() {
        yearReport = new HashMap<>();
        yearMonthMoney = new HashMap<>();
    }

    void LoadYear(String yearNumber) {

        try {
            String put = "resources/y." +yearNumber + ".csv";
            lines = (Files.readString(Path.of(put)).split("\r?\n|\r"));
        } catch (IOException e) {
            System.out.println("Ошибка. Отчет не загружен. Возможно, файл находится не в нужной директории");
            return;
        }
        for (int k = 1; k < lines.length; k++) {

            ArrayList<String> testArray = new ArrayList<>();
            String[] lineContents = lines[k].split(",");

            for (int j = 0; j < lineContents.length; j++) {
                testArray.add(j, lineContents[j]);
            }

            yearReport.put(k, testArray);
        }

        System.out.println("\nЗагружен отчет за: " + yearNumber+ " год\n");


    }

    void printYearReport(String yearNumber) {

        System.out.println("Годовой отчет за: "+yearNumber+" год");
        String month = "01";
        int monthUpMoney = 0;
        int monthDownMoney = 0;
        int upMoney = 0;
        int downMoney = 0;
        int monthHowMuch = 0;
        for (int i = 1; i <= yearReport.size(); i++) {

            if (yearReport.get(i).get(0).equals(month)) {
                if (yearReport.get(i).get(2).equals("false")) {
                    monthUpMoney = Integer.parseInt(yearReport.get(i).get(1));

                } else {
                    monthDownMoney = Integer.parseInt(yearReport.get(i).get(1));
                }

            } else {
                month = yearReport.get(i).get(0);
                if (yearReport.get(i).get(2).equals("false")) {
                    monthUpMoney = Integer.parseInt(yearReport.get(i).get(1));

                } else {
                    monthDownMoney = Integer.parseInt(yearReport.get(i).get(1));
                }

            }

            if (i % 2 == 0) {
                System.out.println("\nПрибыль за " +   monthToString(Integer.parseInt(month)) + ": " + (monthUpMoney - monthDownMoney));
                upMoney = upMoney + monthUpMoney;
                downMoney = downMoney + monthDownMoney;
                monthHowMuch++;
            }

        }
        System.out.println("\nСредний расход за " + monthHowMuch + " месяц(ев): " + (downMoney / monthHowMuch));
        System.out.println("\nСредний доход за " + monthHowMuch + " месяц(ев): " + (upMoney / monthHowMuch)+ "\n");
    }
    public String monthToString(Integer month) {
        if (month == 1) {
            return "Январь";
        } else if (month == 2) {
            return "Февраль";
        } else if (month == 3) {
            return "Март";
        } else if (month == 4) {
            return "Апрель";
        } else if (month == 5) {
            return "Май";
        } else if (month == 6) {
            return "Июнь";
        } else if (month == 7) {
            return "Июль";
        } else if (month == 8) {
            return "Август";
        } else if (month == 9) {
            return "Сентябрь";
        } else if (month == 10) {
            return "Октябрь";
        } else if (month == 11) {
            return "Ноябрь";
        } else if (month == 12) {
            return "Декабрь";
        } else return "ERROR";

    }


}