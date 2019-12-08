package be.annelyse.year2019;

import be.annelyse.util.Transformers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.LinkedBlockingQueue;

public class IntCodeComputer implements Runnable {

    private List<Integer> memory;
    /**
     * This list had the priority for working with position or immediate mode. For values we write we always work in (forgot, look in the function)
     * 0 = position mode (parameter interpreted as position)
     * 1 = immediate mode (parameter interpreted as value)
     */
    private List<Integer> memoryMode;
    private int instructionPointer;
    private LinkedBlockingQueue<Integer> input;
    private LinkedBlockingQueue<Integer> output;

    public IntCodeComputer(List<Integer> computerSoftware) {
        this.memory = computerSoftware;
        this.input = input;
        this.output = output;
        memoryMode = memory.stream().map(value -> 9).collect(Collectors.toList());
        instructionPointer = 0;
    }

    public IntCodeComputer(List<Integer> computerSoftware, LinkedBlockingQueue<Integer> input, LinkedBlockingQueue<Integer> output) {
        this.memory = new ArrayList<>();
        memory.addAll(computerSoftware);

        this.input = input;
        this.output = output;
        memoryMode = memory.stream().map(value -> 9).collect(Collectors.toList());
        instructionPointer = 0;
    }

    @Override
    public void run() {

        while (true) {
            int opCode = getOpCode(instructionPointer);

            System.out.println(" ");
            System.out.print("OPcode: " + opCode + " InstructionPointer: " + instructionPointer);

            switch (opCode) {
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
                case 99:
                    return;
                default:
                    throw new RuntimeException("not a valid OptCode: " + opCode + " InstructionPointer: " + instructionPointer);
            }
        }
    }

    /**adding*/
    private void optcode1() {
        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        int result = parameter1 + parameter2;

        writeResult(instructionPointer, 3, result);

        instructionPointer += 4;
    }

    /**multiplication*/
    private void optcode2() {

        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        int result = parameter1 * parameter2;

        writeResult(instructionPointer, 3, result);
        instructionPointer += 4;
    }

    /**
     * save input
     */

    private void optcode3() {
        int result = getComputerInput();
        writeResult(instructionPointer, 1, result);
        instructionPointer += 2;
    }

    /**
     * generate output
     */
    private void optcode4() {
        int result = getValueOfParameter(instructionPointer, 1);
        System.out.println(" OPT4: Amplifier change-over----------------------------------output: " + result);
        output.add(result);
        instructionPointer += 2;
    }

/**    jump if true*/
    private void optcode5() {
        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        if (parameter1 != 0) {
            instructionPointer = parameter2;
            //System.out.println("\nOPT5: New value instructionPointer: " + parameter2 + " max value is: " + memory.size());
        } else {
            instructionPointer += 3;
            //System.out.println("\nOPT5: instructionPointer +3 (normal) ");
        }
    }

/**
    jump if false
*/
    private void optcode6() {
        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        if (parameter1 == 0) {
            instructionPointer = parameter2;
            System.out.println("\nNew value instructionPointer: " + parameter2 + " max value is: " + memory.size());
        } else {
            instructionPointer += 3;
        }
    }

/**   less than*/
    private void optcode7() {
        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        if (parameter1 < parameter2) {
            writeResult(instructionPointer, 3, 1);
        } else {
            writeResult(instructionPointer, 3, 0);
        }
        instructionPointer += 4;
    }

/**    equals*/
    private void optcode8() {
        int parameter1 = getValueOfParameter(instructionPointer, 1);
        int parameter2 = getValueOfParameter(instructionPointer, 2);

        if (parameter1 == parameter2) {
            writeResult(instructionPointer, 3, 1);
        } else {
            writeResult(instructionPointer, 3, 0);
        }
        instructionPointer += 4;
    }

    private int getValueOfParameter(int instructionPointer, int parameterNr) {
        if (getPositionModeOfParameter(instructionPointer, parameterNr) == 0) {
            return memory.get(memory.get(instructionPointer + parameterNr));
        } else {
            return memory.get(instructionPointer + parameterNr);
        }
    }

    private int getPositionModeOfParameter(int instructionPointer, int parameterNr) {
        int instructionCode = memory.get(instructionPointer);
        List<Integer> digits = Transformers.transformNumberToDigitList(instructionCode, 5);

        if (memoryMode.get(instructionPointer + parameterNr) == 0) {
            return 0;
        } else {
            return digits.get(3 - parameterNr);
        }
    }

    private void writeResult(int instructionPointer, int parameterNr, int result) {
        if (getPositionModeOfParameter(instructionPointer, parameterNr) == 0) {
            int writePosition = memory.get(instructionPointer + parameterNr);
            memory.set(writePosition, result);
            memoryMode.set((writePosition), 0);
        } else {
            memory.set(instructionPointer + parameterNr, result);
            memoryMode.set((instructionPointer + parameterNr), 0);
        }
    }

    /**
     * gets the computerinput -> each algorithm gets a first and a following input
     * the first input is the amplifierPhaseSetting, the next is 0 or the output of the previous amplifier algorithm
     */
    public int getComputerInput() {

        while (input.isEmpty()){
            try {
                Thread.sleep(10);
                System.out.println("....................waiting for input");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int newInput = input.poll();
        System.out.println("\ninput: " + newInput + "...............................................");
        return newInput;
    }

    private int getOpCode(int instructionPointer) {
        int instructionCode = memory.get(instructionPointer);
        List<Integer> digits = Transformers.transformNumberToDigitList(instructionCode, 5);

        int opCode = Integer.parseInt(String.valueOf(digits.get(3)) + digits.get(4));
        return opCode;
    }




}
