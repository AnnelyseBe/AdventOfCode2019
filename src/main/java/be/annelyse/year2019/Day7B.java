package be.annelyse.year2019;

import be.annelyse.util.Transformers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//todo ... make part A running again and cleanup
public class Day7B {

    private static int numberOfComputers = 5;
    private static boolean programrunning;
    private static int loopCount = 0;

    public static void main(String[] args) {

/*        List<List<Integer>> phaseSettings = generatePossiblePhaseSettingCombinations(numberOfComputers, 1);
        System.out.println("possible phaseSettings: " + phaseSettings.size());
        int result = phaseSettings.stream().map(phaseSetting -> runProgramWithAmplifiersInSerie(phaseSetting, 0)).max(Integer::compareTo).get();
        System.out.println("result A is: "+ result);*/


        List<List<Integer>> phaseSettings2 = generatePossiblePhaseSettingCombinations(numberOfComputers, 2);


        System.out.println("possible phaseSettings: " + phaseSettings2.size());
        int result2 = phaseSettings2.stream().map(phaseSetting2 -> runProgramWithLoopedAmplifiers(phaseSetting2, 0)).max(Integer::compareTo).get();
        System.out.println("result B is: " + result2);
    }

    private static Integer runProgramWithAmplifiersInSerie(List<Integer> phaseSetting, int inputSignal) {
/*        List<Integer> computerSoftware = getInput("Day7");
        List<IntCodeComputer> computers = Stream.generate(() -> new IntCodeComputer(computerSoftware)).limit(numberOfComputers).collect(Collectors.toList());
        Optional<Integer> output;
        int lastOutputLastComputer = Integer.MIN_VALUE;
        programrunning = true;

        for(int i = 0; i < numberOfComputers; i++){

            System.out.print("\nloop: " + i);

            output = computers.get(i % numberOfComputers).executeAlgorithm(phaseSetting.get(i % numberOfComputers), inputSignal);

            if (output.isPresent()){
                inputSignal = output.get();

                if( i == numberOfComputers-1){
                    lastOutputLastComputer = output.get();
                }

            } else {
                programrunning = false;
            }
        }

        System.out.println("\nPhasesetting:" + phaseSetting.get(0) + phaseSetting.get(1) + phaseSetting.get(2) + phaseSetting.get(3) + phaseSetting.get(4) + " loop: " + loopCount++ + "/119 -> output: " + lastOutputLastComputer + ".....................................................................................................");
        return lastOutputLastComputer;*/
        return 0;
    }

    private static Integer runProgramWithLoopedAmplifiers(List<Integer> phaseSetting, int inputSignal) {
        List<Integer> computerSoftware = getInput("Day7");

        LinkedBlockingQueue<Integer> inputA = new LinkedBlockingQueue<>();
        inputA.add((phaseSetting.get(0)));
        inputA.add(inputSignal);

        LinkedBlockingQueue<Integer> inputB = new LinkedBlockingQueue<>();
        inputB.add(phaseSetting.get(1));

        LinkedBlockingQueue<Integer> inputC = new LinkedBlockingQueue<>();
        inputC.add(phaseSetting.get(2));

        LinkedBlockingQueue<Integer> inputD = new LinkedBlockingQueue<>();
        inputD.add(phaseSetting.get(3));

        LinkedBlockingQueue<Integer> inputE = new LinkedBlockingQueue<>();
        inputE.add(phaseSetting.get(4));

        IntCodeComputer computerA = new IntCodeComputer(computerSoftware, inputA, inputB);
        IntCodeComputer computerB = new IntCodeComputer(computerSoftware, inputB, inputC);
        IntCodeComputer computerC = new IntCodeComputer(computerSoftware, inputC, inputD);
        IntCodeComputer computerD = new IntCodeComputer(computerSoftware, inputD, inputE);
        IntCodeComputer computerE = new IntCodeComputer(computerSoftware, inputE, inputA);

        Thread threadA = new Thread(computerA);
        threadA.start();

        Thread threadB = new Thread(computerB);
        threadB.start();

        Thread threadC = new Thread(computerC);
        threadC.start();

        Thread threadD = new Thread(computerD);
        threadD.start();

        Thread threadE = new Thread(computerE);
        threadE.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
            threadE.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return inputA.poll();


    }


    //todo --> resolve duplicate code
    static List<List<Integer>> generatePossiblePhaseSettingCombinations(int numberOfComputers, int phaseSettingsGenerationMethod) {

        if (phaseSettingsGenerationMethod == 1) {
            return IntStream.range(0, (int) Math.pow(10, numberOfComputers))
                    .mapToObj(i -> Transformers.transformNumberToDigitList(i, numberOfComputers))
                    .filter(Day7B::listWithUniqueParameters)
                    .filter(Day7B::noDigitsAbove5)
                    .collect(Collectors.toList());
        } else if (phaseSettingsGenerationMethod == 2) {
            return IntStream.range(0, (int) Math.pow(10, numberOfComputers))
                    .mapToObj(i -> Transformers.transformNumberToDigitList(i, numberOfComputers))
                    .filter(Day7B::listWithUniqueParameters)
                    .filter(Day7B::noDigitsUnder5)
                    .collect(Collectors.toList());
        }
        return null; //todo foei ... maak dan tenminste een exception, of los dat met die int anders op
    }

    private static Boolean listWithUniqueParameters(List<Integer> digitList) {
        int listSize = digitList.size();
        Set<Integer> digitSet = new HashSet<>(digitList);
        int setSize = digitSet.size();
        return (listSize == setSize);
    }

    private static Boolean noDigitsAbove5(List<Integer> digitList) {
        for (int i = 0; i < digitList.size(); i++) {
            if (digitList.get(i) > 4) {
                return false;
            }
        }
        return true;
    }

    private static boolean noDigitsUnder5(List<Integer> digitList) {
        for (int i = 0; i < digitList.size(); i++) {
            if (digitList.get(i) < 5) {
                return false;
            }
        }
        return true;
    }

    static List<Integer> getInput(String inputFileName) {
        String input = null;
        try {
            input = Files.readString(Paths.get("src/main/resources/input/year2019", inputFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}