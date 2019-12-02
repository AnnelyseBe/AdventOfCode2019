package be.annelyse.year2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day2 {

    public static void main(String[] args) throws IOException {
        solvePart1(getInput());

        System.out.println("**********************Part2**********************");
        System.out.println("Solution: " + solvePart2(getInput()));

    }

    private static List<String> getInput() throws IOException {
        return Files.readAllLines(Paths.get("src/main/resources/input/year2018","Day2"));
    }

    static int solvePart1(List<String> inputList) {

        int contains2TheSame = 0;
        int contains3theSame = 0;

        for (String s : inputList) {
            Map<String, Boolean> map = contains2or3theSame(s);
            if (map.get("contains2ofTheSame")) {
                contains2TheSame++;
            }
            if (map.get("contains3ofTheSame")) {
                contains3theSame++;
            }
        }

        int result = contains2TheSame*contains3theSame;

        System.out.println("**********************Part1**********************");
        System.out.println("Solution: " + result);
        return result;
    }

    static String solvePart2(List<String> input) {
        String common;
        for (int i = 0; i< input.size(); i++){
            String string1 = input.get(i);

            for (String string2 : input) {
                common = "";
                int identicalCharCount = 0;
                if (string1.equals(string2)) {
                    continue;
                }
                for (int k = 0; k < string1.length(); k++) {
                    if (string1.charAt(k) == string2.charAt(k)) {
                        identicalCharCount++;
                        common = common + string1.charAt(k);
                    }
                }
                if (identicalCharCount == string1.length() - 1) {
                    return common;
                }
            }
        }
        return "something is wrong";

    }

    private static Map<String, Boolean> contains2or3theSame(String input){
        List<String> lettersList = Arrays.asList(input.split(""));
        boolean contains2ofTheSame = false;
        boolean contains3ofTheSame = false;

        while(!lettersList.isEmpty() && (!contains2ofTheSame || !contains3ofTheSame)){
            String toCount = lettersList.get(0);
            int count = (int) lettersList.stream().filter(letter -> letter.equals(toCount)).count();
            lettersList = lettersList.stream()
                    .filter(letter -> !letter.equals(toCount))
                    .collect(Collectors.toList());
            switch(count){
                case 2:
                    contains2ofTheSame = true;
                    break;
                case 3:
                    contains3ofTheSame = true;
                    break;
                default:
                    break;
            }
        }
        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("contains2ofTheSame", contains2ofTheSame);
        resultMap.put("contains3ofTheSame", contains3ofTheSame);

        return resultMap;

    }

}
