package be.annelyse.year2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws IOException {
        solvePart1(getInput("Day3"));
    }

    private static List<String> getInput(String fileName) throws IOException {
        List<String> input = Files
                .readAllLines(Paths.get("src/main/resources/input/year2018", fileName));
        input.sort(String::compareTo);

        input.stream().forEach(System.out::println);

        return input;
    }

    private static void solvePart1(List<String> input) {
        fillLoggingTable(input);


    }

    private static List<Day3Log> fillLoggingTable(List<String> input) {

        List<Day3Log> loggingTable = new ArrayList<>();
        int guardId = 0;
        int day = 0;
        List<Integer> hourTimeRange = Collections.nCopies(60, 0);
        hourTimeRange.forEach(System.out::print);


        for (String inputLine : input) {
/*

            String dateOfInputLine = inputLine.substring(6,11);
            System.out.println("dateOfInputLine: " + dateOfInputLine);
            String hours = inputLine.substring(12, 14);
            System.out.println("hours " + hours);
            int minutes = Integer.parseInt(inputLine.substring(15,17));
            System.out.println("minutes " + minutes);

            //todo ... aan het sukkelen
            if (date!= null && dateOfInputLine != date){
                loggingTable.add(new Day3Log(date, guardId, hourTimeRange));
                hourTimeRange = Collections.nCopies(60, 0);
            }
            if (inputLine.contains("Guard")){
                int beginIndex = inputLine.lastIndexOf("#") + 1;
                int endIndex = inputLine.indexOf(" begins shift");
                guardId = Integer.parseInt(inputLine.substring(beginIndex, endIndex));
                System.out.println("guardId: "+ guardId);

                if (hours == "23"){
                    date = dateOfInputLine + 1;
                } else {
                    date = dateOfInputLine;
                }
                System.out.println("dataOfInputLine: " + dateOfInputLine + "dataOfInputLine + 1: " + dateOfInputLine + 1);
                System.out.println("date: " + date);

            } else if (inputLine.contains("falls asleep")){
                for (int i = 0; i < hourTimeRange.size(); i++){
                    if (i >= minutes) {
                        hourTimeRange.set(i, 1);
                    }
                }
            } else if (inputLine.contains("wakes up")){
                for (int i = 0; i < hourTimeRange.size(); i++){
                    if (i >= minutes) {
                        hourTimeRange.set(i, 0);
                    }
                }
            }

*/

        }
        return loggingTable;
    }

    private static void solvePart2() {

    }

    private static class Day3Log {
        int day;
        int guardId;
        List<Integer> timeRange;

        public Day3Log(int day, int guardId, List<Integer> timeRange) {
            this.day = day;
            this.guardId = guardId;
            this.timeRange = timeRange;
        }
    }


}
