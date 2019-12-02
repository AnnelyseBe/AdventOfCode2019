package be.annelyse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {


    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/main/resources/input", "Day2"));
        List<Integer> intCodeInput = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println("\n********************************************Part1********************************************");
        List<Integer> intCodeInitialized = initialize(intCodeInput, 1, 12, 2, 2);
        solvePart1(intCodeInitialized);


    }

    static List<Integer> solvePart1(List<Integer> input) {

        List<Integer> intCode = input;
        int opCode = intCode.get(0);
        for (int i = 0; opCode != 99; i = i + 4) {
            if (opCode == 1) {
                intCode = optcode1(intCode, i);
            } else if (opCode == 2) {
                intCode = optcode2(intCode, i);
            }
            opCode = intCode.get(i + 4);
        }

        System.out.println("\nThe result is: ");
        intCode.forEach(value -> System.out.print(value + ", "));

        return intCode;
    }

    static void solvePart2(List<Integer> input) {

    }


    static List<Integer> optcode1(List<Integer> intCode, int position) {

        int value1 = intCode.get(intCode.get(position + 1));
        int value2 = intCode.get(intCode.get(position + 2));
        intCode.set(intCode.get(position + 3), value1 + value2);

        return intCode;
    }

    static List<Integer> optcode2(List<Integer> intCode, int position) {
        int value1 = intCode.get(intCode.get(position + 1));
        int value2 = intCode.get(intCode.get(position + 2));
        int result = intCode.set(intCode.get(position + 3), value1 * value2);

        return intCode;
    }

    static List<Integer> initialize(List<Integer> intCode, Integer... initializationPositionAndValue) {

        for (int i = 0; i < initializationPositionAndValue.length; i = i + 2) {
            //cast naar int, anders wordt de waarde 1 verwijderd ipv de index 1 !!!!
            intCode.remove((int) initializationPositionAndValue[i]);
            intCode.add(initializationPositionAndValue[i], initializationPositionAndValue[i + 1]);
        }

        return intCode;
    }

}
