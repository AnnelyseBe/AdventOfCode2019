package be.annelyse.year2019;

import be.annelyse.util.Transformers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntCodeComputer2 implements Runnable {

    private List<Long> memory;
    /**
     * This list had the priority for working with position or immediate mode. For values we write we always work in (forgot, look in the function)
     * 0 = position mode (parameter interpreted as position)
     * 1 = immediate mode (parameter interpreted as value)
     * 2 = relative mode (parameter interpreted as a relative position with a relativeBase
     */
    private int instructionPointer;
    private int relativeBase;
    private LinkedBlockingQueue<Long> input;
    private LinkedBlockingQueue<Long> output;

    public IntCodeComputer2(List<Long> computerSoftware) {
        this.memory = computerSoftware;
        instructionPointer = 0;
        relativeBase = 0;
    }

    public IntCodeComputer2(List<Long> computerSoftware, LinkedBlockingQueue<Long> input, LinkedBlockingQueue<Long> output) {
        this.memory = new ArrayList<>();
        memory.addAll(computerSoftware);
        this.input = input;
        this.output = output;
        instructionPointer = 0;
        relativeBase = 0;
    }

    @Override
    public void run() {

        while (true) {
            int opCode = getOpCode(instructionPointer);

//           System.out.println("\n***********************************************************************************************************************");
//            for (int i = 0; i < memory.size(); i++) {
//                System.out.print(i + ":" + memory.get(i) + " ");
//            }
//            System.out.println(" ");
//            System.out.println("***********************************************************************************************************************");
//            System.out.print("Instruction: " + memory.get(instructionPointer) + " OPcode: " + opCode + " InstructionPointer: " + instructionPointer);
//            System.out.println("***********************************************************************************************************************");


            switch (opCode) {
                case 1:
                    opCode1();
                    break;
                case 2:
                    opCode2();
                    break;
                case 3:
                    opCode3();
                    break;
                case 4:
                    opCode4();
                    break;
                case 5:
                    opCode5();
                    break;
                case 6:
                    opCode6();
                    break;
                case 7:
                    opCode7();
                    break;
                case 8:
                    opCode8();
                    break;
                case 9:
                    opCode9();
                    break;
                case 99:
                    return;
                default:
                    throw new RuntimeException("not a valid OptCode: " + opCode + " InstructionPointer: " + instructionPointer);
            }
        }
    }

    /**
     * adding
     */
    private void opCode1() {
        long parameter1 = getValueOfParameter(instructionPointer, 1);
        long parameter2 = getValueOfParameter(instructionPointer, 2);

        long result = parameter1 + parameter2;

        writeResult(instructionPointer, 3, result);

        instructionPointer += 4;
    }

    /**
     * multiplication
     */
    private void opCode2() {

        long parameter1 = getValueOfParameter(instructionPointer, 1);
        long parameter2 = getValueOfParameter(instructionPointer, 2);

        long result = parameter1 * parameter2;

        writeResult(instructionPointer, 3, result);
        instructionPointer += 4;
    }

    /**
     * save input
     */
    private void opCode3() {
        long result = getComputerInput();
        writeResult(instructionPointer, 1, result);
        instructionPointer += 2;
    }

    /**
     * generate output
     */
    private void opCode4() {
        long result = getValueOfParameter(instructionPointer, 1);
        output.add(result);
        instructionPointer += 2;
    }

    /**
     * jump if true
     */
    private void opCode5() {
        Long parameter1 = getValueOfParameter(instructionPointer, 1);
        Long parameter2 = getValueOfParameter(instructionPointer, 2);

        if (!parameter1.equals(0L)) {
            instructionPointer = parameter2.intValue();
            //System.out.println("\nOPT5: New value instructionPointer: " + parameter2 + " max value is: " + memory.size());
        } else {
            instructionPointer += 3;
            //System.out.println("\nOPT5: instructionPointer +3 (normal) ");
        }
    }

    /**
     * jump if false
     */
    private void opCode6() {
        Long parameter1 = getValueOfParameter(instructionPointer, 1);
        Long parameter2 = getValueOfParameter(instructionPointer, 2);

        if (parameter1.equals(0L)) {
            instructionPointer = parameter2.intValue();
            //System.out.println("\nNew value instructionPointer: " + parameter2 + " max value is: " + memory.size());
        } else {
            instructionPointer += 3;
        }
    }

    /**
     * less than
     */
    private void opCode7() {
        long parameter1 = getValueOfParameter(instructionPointer, 1);
        long parameter2 = getValueOfParameter(instructionPointer, 2);

        if (parameter1 < parameter2) {
            writeResult(instructionPointer, 3, 1);
        } else {
            writeResult(instructionPointer, 3, 0);
        }
        instructionPointer += 4;
    }

    /**
     * equals
     */
    private void opCode8() {
        Long parameter1 = getValueOfParameter(instructionPointer, 1);
        Long parameter2 = getValueOfParameter(instructionPointer, 2);

//        System.out.println("---------------------------------");
//        System.out.println("parameter1: " + parameter1);
//        System.out.println("parameter2: " + parameter2);
//        System.out.println("---------------------------------");


        if (parameter1.equals(parameter2)) {
            writeResult(instructionPointer, 3, 1);
        } else {
            writeResult(instructionPointer, 3, 0);
        }
        instructionPointer += 4;
    }

    /**
     * adjust relative base
     */
    private void opCode9() {

        long parameter1 = getValueOfParameter(instructionPointer, 1); //debug ... is dit immediatMode, of hangt het er ook vanaf
        //System.out.print("\n     relative base: " + relativeBase + " -> " + (relativeBase + parameter1));
        relativeBase += parameter1;
        instructionPointer += 2;
    }

    private Long getValueOfParameter(int instructionPointer, int parameterNr) {
        int positionMode = getPositionModeOfParameter(instructionPointer, parameterNr);
//        System.out.print("\n     positionMode is: " + positionMode);

        int indexOfParameter = assurePosition(instructionPointer + parameterNr);
        int indexToRead;

        switch (positionMode){
            case 0:
                indexToRead = assurePosition(memory.get(indexOfParameter).intValue());
                return memory.get(indexToRead);
            case 1:
                return memory.get(indexOfParameter);
            case 2:
                indexToRead = assurePosition(memory.get(indexOfParameter).intValue() + relativeBase);
                //System.out.print("\n     indexToRead is: " + indexToRead);
                return memory.get(indexToRead);
            default: throw new RuntimeException("PositionMode: " + positionMode + "does not exist");
        }
    }

    private Long getValueOfParameterImmediateMode(int instructionPointer, int parameterNr) {
        return memory.get(assurePosition(instructionPointer + parameterNr));
    }

    private int getPositionModeOfParameter(int instructionPointer, int parameterNr) {
        int instructionCode = memory.get(instructionPointer).intValue();
        List<Integer> digits = Transformers.transformNumberToDigitList(instructionCode, 5);
        return digits.get(3 - parameterNr);
    }

    private void setPositionModeOfParameter(int instructionPointer, int parameterNr, int positionMode) {
        int instructionCode = memory.get(instructionPointer).intValue();
        List<Integer> digits = Transformers.transformNumberToDigitList(instructionCode, 5);
        digits.set((3 - parameterNr), positionMode);
        Integer newInstructionCode = Transformers.transformDigitListToInt(digits);
        memory.set(instructionPointer, newInstructionCode.longValue());
    }

    private void writeResult(int instructionPointer, int parameterNr, long result) {

        int positionMode = getPositionModeOfParameter(instructionPointer, parameterNr);
        int indexOfParameter = assurePosition(instructionPointer + parameterNr);

        int indexToWrite = switch (positionMode) {
            case 0 -> assurePosition(memory.get(indexOfParameter).intValue());
            case 1 -> indexOfParameter;
            case 2 -> assurePosition(memory.get(indexOfParameter).intValue() + relativeBase);
            default -> throw new RuntimeException("PositionMode: " + positionMode + "does not exist");
        };

        memory.set(indexToWrite, result);

        //Day 5: Parameters that an instruction writes to will never be in immediate mode.
        if (getPositionModeOfParameter(instructionPointer, parameterNr) == 1) {
            setPositionModeOfParameter(instructionPointer, parameterNr, 0);
        }
    }

    /**
     * gets the computerinput -> each algorithm gets a first and a following input
     * the first input is the amplifierPhaseSetting, the next is 0 or the output of the previous amplifier algorithm
     */
    public long getComputerInput() {

        while (input.isEmpty()) {
            try {
                Thread.sleep(1);
                //System.out.println("....................waiting for input");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return input.poll();
    }

    private int getOpCode(int instructionPointer) {
        int instructionCode = memory.get(assurePosition(instructionPointer)).intValue();
        List<Integer> digits = Transformers.transformNumberToDigitList(instructionCode, 5);
        return Integer.parseInt(String.valueOf(digits.get(3)) + digits.get(4));
    }

    private int assurePosition(int position){
        int numberOfExtraPositions;
        if (position > memory.size()){
            numberOfExtraPositions = position + 5 - memory.size();
            List<Long> memoryExtension = Stream.generate(() -> 0L).limit(numberOfExtraPositions).collect(Collectors.toList());
            memory.addAll(memoryExtension);
        }
        return position;
    }
}
