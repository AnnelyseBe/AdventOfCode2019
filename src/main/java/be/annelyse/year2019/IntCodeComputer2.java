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
    private List<Integer> memoryMode;
    private int instructionPointer;
    private int relativeBase;
    private LinkedBlockingQueue<Long> input;
    private LinkedBlockingQueue<Long> output;

    public IntCodeComputer2(List<Long> computerSoftware) {
        this.memory = computerSoftware;
        memoryMode = memory.stream().map(value -> 9).collect(Collectors.toList());
        instructionPointer = 0;
        relativeBase = 0;
    }

    public IntCodeComputer2(List<Long> computerSoftware, LinkedBlockingQueue<Long> input, LinkedBlockingQueue<Long> output) {
        this.memory = new ArrayList<>();
        memory.addAll(computerSoftware);
        this.input = input;
        this.output = output;
        memoryMode = memory.stream().map(value -> 9).collect(Collectors.toList());
        instructionPointer = 0;
        relativeBase = 0;
    }

    @Override
    public void run() {

        while (true) {
            int opCode = getOpCode(instructionPointer);

            System.out.println(" ");
            System.out.print("Instruction: "+ memory.get(instructionPointer) +" OPcode: " + opCode + " InstructionPointer: " + instructionPointer);

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
        System.out.print("\n     output: " + result);
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
            System.out.println("\nNew value instructionPointer: " + parameter2 + " max value is: " + memory.size());
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
        System.out.print("\n     relative base: " + relativeBase + " -> " + (relativeBase + parameter1));
        relativeBase += parameter1;

        instructionPointer += 2;
    }

    private Long getValueOfParameter(int instructionPointer, int parameterNr) {
        int positionMode = getPositionModeOfParameter(instructionPointer, parameterNr);
        System.out.print("\n     positionMode is: " + positionMode);

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
                System.out.print("\n     indexToRead is: " + indexToRead);
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

        //only position mode is writen to the memorymode (till now)
        if (memoryMode.get(instructionPointer + parameterNr).equals(0)) {
            return 0;
            //todo I don't like this feature. I'd prefer just to override the parameter mode in the instructionValue (refactor)
        } else {
            return digits.get(3 - parameterNr);
        }
    }

    private void writeResult(int instructionPointer, int parameterNr, long result) {

        int positionMode = getPositionModeOfParameter(instructionPointer, parameterNr);
        int indexOfParameter = assurePosition(instructionPointer + parameterNr);
        int indexToWrite;


        switch (positionMode){
            case 0:
                indexToWrite = assurePosition(memory.get(indexOfParameter).intValue());

                memory.set(indexToWrite, result);
                memoryMode.set(indexToWrite, 0);  //todo refactor, rather override the parameter mode in the instructionValue (refactor)
                break;
            case 1:
                memory.set(indexOfParameter, result);
                memoryMode.set((indexOfParameter), 0);
                break;
            case 2:
                indexToWrite = assurePosition(memory.get(indexOfParameter).intValue() + relativeBase);
                memory.set(indexToWrite, result);
                break;
            default:
                throw new RuntimeException("PositionMode: " + positionMode + "does not exist");
        }
    }

    /**
     * gets the computerinput -> each algorithm gets a first and a following input
     * the first input is the amplifierPhaseSetting, the next is 0 or the output of the previous amplifier algorithm
     */
    public long getComputerInput() {

        while (input.isEmpty()) {
            try {
                Thread.sleep(10);
                System.out.println("....................waiting for input");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long newInput = input.poll();
        System.out.println("\ninput: " + newInput + "...............................................");
        return newInput;
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

            List<Integer> memoryModeExtension = Stream.generate(() -> 9).limit(numberOfExtraPositions).collect(Collectors.toList());
            memoryMode.addAll(memoryModeExtension);
        }
        return position;
    }
}
