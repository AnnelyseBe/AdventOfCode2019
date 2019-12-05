package be.annelyse.year2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Day5 {

    private static List<Integer> intCode;
    private static List<Integer> intCodeMode;
    private static int computerCodeInput;

    public static void main(String[] args) throws IOException {
        System.out.println("\n********************************************Part1********************************************");
        System.out.println("\nSolution part 1: ");
        solvePart1(getIntCodeInput(), 1);

/*        System.out.println("\n********************************************Part2********************************************");
        System.out.println("\nSolution part 2: " + solvePart2(19690720));*/
    }

    static List<Integer> getIntCodeInput() throws IOException {
        String input = Files.readString(Paths.get("src/main/resources/input/year2019", "Day5"));
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static void solvePart1(List<Integer> intCodeinput, int computerCode) {
        computerCodeInput = computerCode;
        solvingAlgorithm(intCodeinput);
    }

    static List<Integer> solvingAlgorithm(List<Integer> input) throws IndexOutOfBoundsException {

        int instructionPointer = 0;
        intCode = input;
        intCodeMode = input.stream().map(value -> 9).collect(Collectors.toList());
        int opCode;
        int postionMode;

        while (intCode.get(instructionPointer) != 99){


            switch(getOpCode(instructionPointer)){
                case 1:
                    optcode1(instructionPointer);
                    instructionPointer += 4;
                    break;
                case 2:
                    optcode2(instructionPointer);
                    instructionPointer += 4;
                    break;
                case 3:
                    optcode3(instructionPointer);
                    instructionPointer += 2;
                    break;
                case 4:
                    optcode4(instructionPointer);
                    instructionPointer += 2;
                    break;
            }
            instructionPointer += 4;
        }
        return intCode;
    }


    private static int getOpCode(int instructionPointer){
        int instructionCode = intCode.get(instructionPointer);
        List<Integer> digits = transformNumberTo5DigitList(instructionCode);

        int opCode = Integer.parseInt(String.valueOf(digits.get(1))+ digits.get(0));
        return opCode;
    }

    private static List<Integer> getReversedPositionMode(int instructionPointer){
        int instructionCode = intCode.get(instructionPointer);

        List<Integer> digits = transformNumberTo5DigitList(instructionCode);
        List<Integer> positionModes = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            positionModes.set(i, digits.get(2-i));
        }
        return positionModes;
    }


    static void optcode1(int instructionPointer) {

        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        int result = parameter1 + parameter2;

        setValueOfParameter(instructionPointer, 3, result);
    }

    static void optcode2(int instructionPointer) {

        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        int result = parameter1 * parameter2;

        setValueOfParameter(instructionPointer, 3, result);
    }

    static void optcode3(int instructionPointer) {
        int result = computerCodeInput;
        setValueOfParameter(instructionPointer, 1, result);
    }

    static void optcode4(int instructionPointer) {

        int result = getValueOfParameter(instructionPointer, 1);
        System.out.println("output: ");
    }


    static int getValueOfParameter(int instructionPointer, int parameterNr){
        if (getPositionModeOfParameter(instructionPointer, parameterNr) == 0)
        {
            return intCode.get(intCode.get(instructionPointer + parameterNr));
        }
        else {
            return intCode.get(instructionPointer+parameterNr);
        }
    }

    static void setValueOfParameter(int instructionPointer, int parameterNr, int result){
        if (getPositionModeOfParameter(instructionPointer, parameterNr) == 0)
        {
            intCode.set(intCode.get(instructionPointer+parameterNr), result);
        }
        else {
            intCode.set(instructionPointer+parameterNr, result);
        }
    }

    private static int getPositionModeOfParameter(int instructionPointer, int parameterNr){
        List<Integer> positionModes = getReversedPositionMode(instructionPointer);
        if(intCodeMode.get(instructionPointer + parameterNr) == 0){
            return 0;
        } else {
            return positionModes.get(parameterNr);
        }
    }

    private static List<Integer> transformNumberTo5DigitList(int code){
        List<Integer> digits = new ArrayList<>();

        for (int k = 0; k < 5; k++){
            digits.add(k, code % 10 );
            code = code / 10;
        }



//        System.out.println("digits ");
//        digits.forEach(digit -> System.out.print(digit + " "));
//
//        Collections.sort(digits, Collections.reverseOrder());
//        System.out.println(" digits reversed order");
//        digits.forEach(digit -> System.out.print(digit + " "));

        return digits;
    }

    private static int transformDigitListToCode (LinkedList<Integer> digitList){
        int result = 0;
        int multiplier = 1;

        while (!digitList.isEmpty()){
            int digit = digitList.pollLast();
            result += digit*multiplier;
            multiplier *= 10;
        }
        return result;
    }


/*


    static Integer solvePart2(Integer output) throws IOException {
        for (int i = 0; i < 10000; i++) {
            int result = 0;
            List<Integer> input = getIntCodeInput();
            try {
                result = solvePart1(input, i);
                System.out.println("iteratie "+i +": " + result);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("iteratie "+i +": " + e.getMessage());
            }
            if (result == output) {
                return i;
            }
        }
        throw new RuntimeException("No valid noun and verb found");
    }

*/
/*
    static List<Integer> initializeWithComputerCode(List<Integer> intCode, Integer computerCode) {

        int noun = computerCode / 100;
        int verb = computerCode % 100;

        intCode.set(1, noun);
        intCode.set(2, verb);

        return intCode;
    }
    */
}
