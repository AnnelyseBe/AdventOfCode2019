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

        while (getOpCode(instructionPointer) != 99){
            System.out.println("optCode: " + getOpCode(instructionPointer));

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
        }
        return intCode;
    }


    private static int getOpCode(int instructionPointer){
        int instructionCode = intCode.get(instructionPointer);
        List<Integer> digits = transformNumberTo5DigitList(instructionCode);

        int opCode = Integer.parseInt(String.valueOf(digits.get(3))+ digits.get(4));
        return opCode;
    }

    static void optcode1(int instructionPointer) {
        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        int result = parameter1 + parameter2;

        writeResult(instructionPointer, 3, result);
    }

    static void optcode2(int instructionPointer) {

        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        int result = parameter1 * parameter2;

        writeResult(instructionPointer, 3, result);
    }

    static void optcode3(int instructionPointer) {
        int result = computerCodeInput;
        writeResult(instructionPointer, 1, result);
    }

    static void optcode4(int instructionPointer) {

        int result = getValueOfParameter(instructionPointer, 1);
        System.out.println("----------------------------------output: " + result);
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

    static void writeResult(int instructionPointer, int parameterNr, int result){
        if (getPositionModeOfParameter(instructionPointer, parameterNr) == 0)
        {
            int writePosition = intCode.get(instructionPointer+parameterNr);
            intCode.set(writePosition, result);
            intCodeMode.set((writePosition),0); //todo was dit 1 of was dit 0
        }
        else {
            intCode.set(instructionPointer+parameterNr, result);
            intCodeMode.set((instructionPointer+parameterNr),0); //todo was dit 1 of was dit 0
        }
    }

    private static int getPositionModeOfParameter(int instructionPointer, int parameterNr){
        int instructionCode = intCode.get(instructionPointer);
        List<Integer> digits = transformNumberTo5DigitList(instructionCode);

        if(intCodeMode.get(instructionPointer + parameterNr) == 0){
            return 0;
        } else {
            return digits.get(3-parameterNr);
        }
    }

    /**
    3 becomes 00003 -> digits.get(4);
    */
    private static List<Integer> transformNumberTo5DigitList(int code){
        List<Integer> digits = new ArrayList<>();

        for (int k = 0; k < 5; k++){
            digits.add(0,code % 10 );
            code = code / 10;
        }
        return digits;
    }
}
