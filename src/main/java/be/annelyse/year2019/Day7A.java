package be.annelyse.year2019;

import be.annelyse.util.Transformers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Day7A {

/*
    */
/**
     * This is the controller software or the original input. This does not change
     *//*

    private static List<Integer> controllerSoftware;

    */
/**
     * The input we give at the start
     *//*

    private static int firstInputInstruction;

    */
/**
     * This is the modified input, this changes during the execution
     *//*

    private static List<Integer> intCode;

    */
/**
     * This list had the priority for working with position or immediate mode. For values we write we always work in (forgot, look in the function)
     * 0 = position mode (parameter interpreted as position)
     * 1 = immediate mode (parameter interpreted as value)
     *//*

    private static List<Integer> intCodeMode;

    */
/**
     * used by optCode
     * 3
     *//*

    private static int algoritmExecutionNumber;
    private static int amplifierIndex;
    private static int amplifierPhaseSetting;
    private static boolean firstInputOfAlgorithm;
    private static boolean changeOverAmplifier = false;

    private static int instructionPointer;
    private static int lastOutput;
    private static int lastAmplifierOutput;
    private static boolean programStop = false;

    private static int methodpart;


    public static void main(String[] args) throws IOException {
        //part1
*/
/*
        Instant start = Instant.now();
        System.out.println("solution part 1 ***************************************************");
        int solutionPart1 = solvePart1(getInput("Day7"), 0, 5, 1);
        System.out.println("\nthe solution is: " + solutionPart1 + " ***************************************************");
        if (solutionPart1 != 567045) {
            throw new RuntimeException("teveel gefoefeld, part1 werkt niet meer !!!");
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Processing time: " + timeElapsed + " millis or " + timeElapsed / 1000 + " seconds");
        *//*


        //part2
        Instant start2 = Instant.now();
        System.out.println("solution part 2 ***************************************************");
        System.out.println("\nthe solution is: " + solvePart2(getInput("Day7_testB1"), 0, 5, 2) + " ***************************************************");
        Instant finish2 = Instant.now();
        long timeElapsed2 = Duration.between(start2, finish2).toMillis();
        System.out.println("Processing time: " + timeElapsed2 + " millis or " + timeElapsed2 / 1000 + " seconds");
    }

    //todo if possible make sure that part1 can also run with this
    static int solvePart2(List<Integer> controllerSoftwareStart, int inputInstruction , int numberOfAmplifiers, int methodPart2) {

        //initialisation static parameters
        firstInputInstruction = inputInstruction;
        controllerSoftware = controllerSoftwareStart;
        intCode = controllerSoftwareStart;
        intCodeMode = controllerSoftware.stream().map(value -> 9).collect(Collectors.toList());
        amplifierIndex = 0;
        amplifierPhaseSetting = 0;
        firstInputOfAlgorithm = true;
        instructionPointer = 0;
        lastOutput = Integer.MIN_VALUE;
        lastAmplifierOutput  = Integer.MIN_VALUE;;
        programStop = false;
        changeOverAmplifier = false;
        methodpart = methodPart2;

        //initialisation local parameters
        List<List<Integer>> phaseSettingCombinations = generatePossiblePhaseSettingCombinations(5, methodpart);
        List<Integer> winningPhaseSettingSequence = phaseSettingCombinations.get(0);
        int maxToThrusters = Integer.MIN_VALUE;

        // uittesten van de sequenties
        for (int i = 0; i < phaseSettingCombinations.size(); i++) {

            List<Integer> phaseSettingSequence = phaseSettingCombinations.get(i);
            int lastAmplifierOutputOfLastAmplifier = Integer.MIN_VALUE;


            //initialisation instructionpointer & intcodemode
            intCode = controllerSoftwareStart; //controllerSoftware moet ook goed zijn
            intCodeMode = controllerSoftware.stream().map(value -> 9).collect(Collectors.toList());
            instructionPointer = 0;
            programStop = false;
            firstInputInstruction = inputInstruction;
            controllerSoftware = controllerSoftwareStart;
            amplifierIndex = 0;
            amplifierPhaseSetting = 0;
            firstInputOfAlgorithm = true;
            changeOverAmplifier = false;
            lastOutput = Integer.MIN_VALUE;
            lastAmplifierOutput  = Integer.MIN_VALUE;;
            methodpart = methodPart2;

            System.out.print("Phase setting sequence " + i + ": ");
            phaseSettingSequence.forEach(System.out::print);
            System.out.print("************************************************************************************************");
            System.out.println("");


            //looping over the amplifiers untill programStop
            for (algoritmExecutionNumber = 0; !programStop; algoritmExecutionNumber++) {

                System.out.println("\nPhasesequentie: "+ i + ", algoritmExecutionNumber (instructioncode:optcode): " + algoritmExecutionNumber);

                amplifierIndex = algoritmExecutionNumber % numberOfAmplifiers;
                amplifierPhaseSetting = phaseSettingSequence.get(amplifierIndex);
                firstInputOfAlgorithm = true;
                changeOverAmplifier = false;
                lastAmplifierOutput = solvingAlgorithm(intCode, intCodeMode);
                //System.out.println("----------------------Amplifier at index: "+ amplifierIndex + " produces output " + lastAmplifierOutput);
                if (amplifierIndex == numberOfAmplifiers - 1) {
                    lastAmplifierOutputOfLastAmplifier = lastAmplifierOutput;
                    System.out.println("lastAmplifierOutputOfLastAmplifier: " + lastAmplifierOutputOfLastAmplifier);
                }
            }
            System.out.println("this lastAmplifierOutputOfLastAmplifier: " + lastAmplifierOutputOfLastAmplifier + " in # iterations: " + algoritmExecutionNumber);
            if (lastAmplifierOutputOfLastAmplifier >= maxToThrusters) {
                maxToThrusters = lastAmplifierOutputOfLastAmplifier;
                winningPhaseSettingSequence = phaseSettingSequence;
            }
        }
        System.out.println("winningPhaseSettingSequence: " + Transformers.transformDigitListToCode(winningPhaseSettingSequence) + " gives maxToThrusters: " + maxToThrusters);
        return maxToThrusters;
    }

   */
/* //todo ... probeer met stream???
    static int solvePart1(List<Integer> controllerSoftwareStart, int firstInputInstruction, int numberOfAmplifiers, int methodPart1) {

        controllerSoftware = controllerSoftwareStart;
        methodpart = methodPart1;
        int maxToThrusters = Integer.MIN_VALUE;
        List<Integer> winningPhaseSettingSequence;

        List<List<Integer>> phaseSettingCombinations = generatePossiblePhaseSettingCombinations(5, methodpart);
        System.out.println("\n\nwe have " + phaseSettingCombinations.size() + " phaseSettingCombinations");

        //todo om te initialiseren -> niet mooi
        winningPhaseSettingSequence = phaseSettingCombinations.get(0);

        for (int i = 0; i < phaseSettingCombinations.size(); i++) {

            List<Integer> phaseSettingSequence = phaseSettingCombinations.get(i);
            algoritmExecutionNumber = 0;

            //initialiseren van de sequenties
            instructionPointer = 0;
            intCodeMode = controllerSoftware.stream().map(value -> 9).collect(Collectors.toList());

            System.out.print("Phase setting sequence " + i + ": ");
            phaseSettingSequence.forEach(System.out::print);
            System.out.print("************************************************************************************************");
            System.out.println("");

            for (int k = 0; k < numberOfAmplifiers; k++, algoritmExecutionNumber++) {
                amplifierIndex = k;
                amplifierPhaseSetting = phaseSettingSequence.get(amplifierIndex);
                firstInputOfAlgorithm = true;
                lastAmplifierOutput = solvingAlgorithm(controllerSoftware, intCodeMode);
                System.out.println("\n----------------------Amplifier at index: " + amplifierIndex + " produces output " + lastAmplifierOutput);
            }

            if (lastAmplifierOutput > maxToThrusters) {
                maxToThrusters = lastAmplifierOutput;
                winningPhaseSettingSequence = phaseSettingSequence;
            }

            System.out.println("this lastAmplifierOutput: " + lastAmplifierOutput);
            System.out.println("max maxToThrusters: " + maxToThrusters);

        }
        System.out.println("winningPhaseSettingSequence: " + Transformers.transformDigitListToCode(winningPhaseSettingSequence) + " gives maxToThrusters: " + maxToThrusters);
        return maxToThrusters;
    }*//*


    */
/**
     * gets the computerinput -> each algorithm gets a first and a following input
     * the first input is the amplifierPhaseSetting, the next is 0 or the output of the previous amplifier algorithm
     *//*

    static int getComputerInput() {
        int computerInput = firstInputInstruction;

        //System.out.print("Your computerinput for amplifierIndex: " + amplifierIndex + " and firstinput: " + firstInput);
        if (algoritmExecutionNumber == 0 && firstInputOfAlgorithm) {
            computerInput = amplifierPhaseSetting;
            firstInputOfAlgorithm = false;
        } else if (algoritmExecutionNumber == 0 && !firstInputOfAlgorithm) {
            computerInput = firstInputInstruction;
        } else if (algoritmExecutionNumber != 0 && firstInputOfAlgorithm) {
            computerInput = amplifierPhaseSetting;
            firstInputOfAlgorithm = false;
        }else if (algoritmExecutionNumber != 0 && !firstInputOfAlgorithm) {
            computerInput = lastAmplifierOutput;
            firstInputOfAlgorithm = false;
        }
        // System.out.println(" is: " + computerInput);
        return computerInput;
    }

    */
/**
     * produces diagnostical code
     * gets an inputSoftware which changes during the algorithm
     * receives input in optCode3
     * works 1 cycle on 1 computer
     * this is for 1
     *//*

    static int solvingAlgorithm(List<Integer> inputSoftware, List<Integer> intCodeModeGiven) throws IndexOutOfBoundsException {
        if (methodpart == 1) {
            instructionPointer = 0;
        }
        intCode = inputSoftware;

        while (getOpCode(instructionPointer) != 99 && !programStop && !changeOverAmplifier) {
            //System.out.println("optCode: " + getOpCode(instructionPointer));
            System.out.print(instructionPointer + ":" + getOpCode(instructionPointer) + " ");

            switch (getOpCode(instructionPointer)) {
                case 1:
                    optcode1();
                    break;
                case 2:
                    optcode2();
                    break;
                case 3:
                    optcode3();
                    break;
                case 4:
                    optcode4();
                    break;
                case 5:
                    optcode5();
                    break;
                case 6:
                    optcode6();
                    break;
                case 7:
                    optcode7();
                    break;
                case 8:
                    optcode8();
                    break;
                default: throw new RuntimeException("not a valid OptCode");
            }
            if (instructionPointer >= controllerSoftware.size()) {
                System.out.println("program stopped, end of intcode");
                programStop = true;
            }
            if(getOpCode(instructionPointer) == 99){
                System.out.println("program stopped, optCode = 99");
                programStop = true;
            }
        }
        return lastOutput;
    }


    private static int getOpCode(int instructionPointer) {
        int instructionCode = intCode.get(instructionPointer);
        List<Integer> digits = Transformers.transformNumberToDigitList(instructionCode, 5);

        int opCode = Integer.parseInt(String.valueOf(digits.get(3)) + digits.get(4));
        return opCode;
    }

    //adding
    static void optcode1() {
        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        int result = parameter1 + parameter2;

        writeResult(instructionPointer, 3, result);

        instructionPointer += 4;
    }

    //multiplication
    static void optcode2() {

        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        int result = parameter1 * parameter2;

        writeResult(instructionPointer, 3, result);
        instructionPointer += 4;
    }

    //save input
    static void optcode3() {
        int result = getComputerInput();
        writeResult(instructionPointer, 1, result);
        instructionPointer += 2;
    }

    //generate output
    static void optcode4() {
        int result = getValueOfParameter(instructionPointer, 1);
        System.out.println("\nOPT4: Amplifier change-over----------------------------------output: " + result);
        lastOutput = result;
        instructionPointer += 2;
        changeOverAmplifier = true;
    }

    //jump if true
    private static void optcode5() {
        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        if (parameter1 != 0) {
            instructionPointer = parameter2;
            System.out.println("\nOPT5: New value instructionPointer: " + parameter2 + " max value is: " + controllerSoftware.size());
        } else {
            instructionPointer += 3;
            System.out.println("\nOPT5: instructionPointer +3 (normal) ");
        }
    }

    //jump if false
    private static void optcode6() {
        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        if (parameter1 == 0) {
            instructionPointer = parameter2;
            System.out.println("\nNew value instructionPointer: " + parameter2 + " max value is: " + controllerSoftware.size());
        } else {
            instructionPointer += 3;
        }
    }

    //less than
    private static void optcode7() {
        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        if (parameter1 < parameter2) {
            writeResult(instructionPointer, 3, 1);
        } else {
            writeResult(instructionPointer, 3, 0);
        }
        instructionPointer += 4;
    }

    //equals
    private static void optcode8() {
        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        if (parameter1 == parameter2) {
            writeResult(instructionPointer, 3, 1);
        } else {
            writeResult(instructionPointer, 3, 0);
        }
        instructionPointer += 4;
    }

    static int getValueOfParameter(int instructionPointer, int parameterNr) {
        if (getPositionModeOfParameter(instructionPointer, parameterNr) == 0) {
            return intCode.get(intCode.get(instructionPointer + parameterNr));
        } else {
            return intCode.get(instructionPointer + parameterNr);
        }
    }

    static void writeResult(int instructionPointer, int parameterNr, int result) {
        if (getPositionModeOfParameter(instructionPointer, parameterNr) == 0) {
            int writePosition = intCode.get(instructionPointer + parameterNr);
            intCode.set(writePosition, result);
            intCodeMode.set((writePosition), 0);
        } else {
            intCode.set(instructionPointer + parameterNr, result);
            intCodeMode.set((instructionPointer + parameterNr), 0);
        }
    }

    private static int getPositionModeOfParameter(int instructionPointer, int parameterNr) {
        int instructionCode = intCode.get(instructionPointer);
        List<Integer> digits = Transformers.transformNumberToDigitList(instructionCode, 5);

        if (intCodeMode.get(instructionPointer + parameterNr) == 0) {
            return 0;
        } else {
            return digits.get(3 - parameterNr);
        }
    }

    static List<Integer> getInput(String inputFileName) throws IOException {
        String input = Files.readString(Paths.get("src/main/resources/input/year2019", inputFileName));
        controllerSoftware = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return controllerSoftware;
    }
*/

}