import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    String[] lines;
    HashMap<Integer, HashMap<Integer, ArrayList<String>>> monthReport;

    MonthlyReport() {
        monthReport = new HashMap<>();

    }


    void LoadMonth(String yearNumber) {
        for (int i = 1; i <= 12; i++) {
            String monthNumber;
            HashMap<Integer, ArrayList<String>> month = new HashMap<>();
            try {

                if (i < 9) {
                    monthNumber = "0" + i;
                    String put = "resources/m." + yearNumber + monthNumber + ".csv";
                    lines = (Files.readString(Path.of(put)).split("\r?\n|\r"));
                } else {
                    Integer o = i;
                    monthNumber = o.toString();
                    String put = "resources/m." + yearNumber + monthNumber + "csv";
                    lines = (Files.readString(Path.of(put)).split("\r?\n|\r"));

                }
            } catch (IOException e) {


                System.out.println("\nЗагружены отчеты за  " + (i - 1) + " месяц(ев)\n");
                return;
            }
            for (int k = 1; k < lines.length; k++) {

                ArrayList<String> testArray = new ArrayList<>();
                String[] lineContents = lines[k].split(",");

                for (int j = 0; j < lineContents.length; j++) {
                    testArray.add(j, lineContents[j]);
                }

                month.put(k, testArray);

            }
            monthReport.put(Integer.parseInt(monthNumber), month);

        }

        System.out.println("\nЗагружены отчеты за 12 месяц(ев)\n");

    }

    public void printMonthMinAndMax(String yearNumber) {
        System.out.println("Помесячный отчет за: " + yearNumber+ " год");
        for (int i = 1; i <= monthReport.size(); i++) {
            int maxTovar = 0;
            String maxTovarName = "";
            int minTovar = 0;
            String minTovarName = "";
            for (int j = 1; j <= monthReport.get(i).size(); j++) {
                int max;
                if (monthReport.get(i).get(j).get(1).equals("FALSE")) {
                    max = Integer.parseInt(monthReport.get(i).get(j).get(2)) * Integer.parseInt(monthReport.get(i).get(j).get(3));
                    if (max > maxTovar) {
                        maxTovar = max;
                        maxTovarName = monthReport.get(i).get(j).get(0);
                    }
                } else if (monthReport.get(i).get(j).get(1).equals("TRUE")) {
                    int min;

                    min = Integer.parseInt(monthReport.get(i).get(j).get(2)) * Integer.parseInt(monthReport.get(i).get(j).get(3));
                    if (min > minTovar) {
                        minTovar = min;
                        minTovarName = monthReport.get(i).get(j).get(0);
                    }
                }
            }

            System.out.println("\n"+monthToString(i) + ":");
            if (maxTovar != 0) {
                System.out.println("         Максимальный доход:"+"\n"+"    "+maxTovarName+ " - " + maxTovar+"р.");
            }
            if (minTovar != 0) {
                System.out.println("         Максимальный расход:"+"\n"+"    "+minTovarName+ " - " + minTovar+"р.");
            }
            System.out.println("");
        }
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