package be.annelyse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {

    public static void main(String[] args) throws IOException {
        System.out.println("\n********************************************Part1********************************************");
        System.out.println("\nSolution part 1: " + solvePart1(getIntCodeInput(), 1202));

        System.out.println("\n********************************************Part2********************************************");
        System.out.println("\nSolution part 2: " + solvePart2(19690720));
    }

    static List<Integer> getIntCodeInput() throws IOException {
        String input = Files.readString(Paths.get("src/main/resources/input", "Day2"));
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static int solvePart1(List<Integer> intCodeinput, int computerCode) {
        List<Integer> initializedInputCode = initializeWithComputerCode(intCodeinput, computerCode);
        List<Integer> transformedIntCode = solvingAlgoritm(initializedInputCode);
        return transformedIntCode.get(0);
    }

    static List<Integer> solvingAlgoritm(List<Integer> input) throws IndexOutOfBoundsException {

        List<Integer> intCode = input;
        int opCode = intCode.get(0);
        for (int instructionPointer = 0; opCode != 99; ) {
            if (opCode == 1) {
                intCode = optcode1(intCode, instructionPointer);
            } else if (opCode == 2) {
                intCode = optcode2(intCode, instructionPointer);
            }
            opCode = intCode.get(instructionPointer += 4);
        }
        return intCode;
    }

    static int solvePart2(Integer output) throws IOException {
        for (int i = 0; i < 10000; i++) {
            int result;
            List<Integer> input = getIntCodeInput();
            try {
                result = solvePart1(input, i);
                System.out.println("iteratie "+i +": " + result);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("iteratie "+i +": " + e.getMessage());
                result = 88888888;
            }
            if (result == output) {
                return i;
            }
        }
        return 666666;
    }


    static List<Integer> optcode1(List<Integer> intCode, int position) throws IndexOutOfBoundsException {

        int noun = intCode.get(intCode.get(position + 1));
        int verb = intCode.get(intCode.get(position + 2));
        intCode.set(intCode.get(position + 3), noun + verb);

        return intCode;
    }

    static List<Integer> optcode2(List<Integer> intCode, int position) throws IndexOutOfBoundsException {
        int value1 = intCode.get(intCode.get(position + 1));
        int value2 = intCode.get(intCode.get(position + 2));
        intCode.set(intCode.get(position + 3), value1 * value2);

        return intCode;
    }

    static List<Integer> initializeWithComputerCode(List<Integer> intCode, Integer computerCode) {

        int noun = computerCode / 100;
        int verb = computerCode % 100;

        intCode.set(1, noun);
        intCode.set(2, verb);

        return intCode;
    }



}
