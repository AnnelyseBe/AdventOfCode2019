package be.annelyse.year2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;


public class Day9 {


    public static void main(String[] args) {
        partA();
        partB();
    }

    public static void test1(){
        LinkedBlockingQueue<Long> input = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Long> output = new LinkedBlockingQueue<>();

        IntCodeComputer2 myComputerTest1 =  new IntCodeComputer2(getInput("Day9_test1"), input, output);
        myComputerTest1.run();
        System.out.println("\n output test 1: ");
        output.forEach(outputValue -> System.out.print(outputValue + "   "));
    }

    public static void test2(){
        LinkedBlockingQueue<Long> input = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Long> output = new LinkedBlockingQueue<>();

        IntCodeComputer2 myComputerTest2 =  new IntCodeComputer2(getInput("Day9_test2"), input, output);
        myComputerTest2.run();
        System.out.println("\n output test 2: ");
        output.forEach(outputValue -> System.out.print(outputValue + "   "));
    }

    public static void test3(){
        LinkedBlockingQueue<Long> input = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Long> output = new LinkedBlockingQueue<>();

        IntCodeComputer2 myComputerTest3 =  new IntCodeComputer2(getInput("Day9_test3"), input, output);
        myComputerTest3.run();
        System.out.println("\n output test 3: ");
        output.forEach(outputValue -> System.out.print(outputValue + "   "));
    }

    public static void partA(){
        LinkedBlockingQueue<Long> input = new LinkedBlockingQueue<>();
        input.add(1L);
        LinkedBlockingQueue<Long> output = new LinkedBlockingQueue<>();

        IntCodeComputer2 myComputerPartA =  new IntCodeComputer2(getInput("Day9"), input, output);
        myComputerPartA.run();
        System.out.println("\n output part A: ");
        output.forEach(outputValue -> System.out.print(outputValue + "   "));
    }

    public static void partB(){
        LinkedBlockingQueue<Long> input = new LinkedBlockingQueue<>();
        input.add(2L);
        LinkedBlockingQueue<Long> output = new LinkedBlockingQueue<>();

        IntCodeComputer2 myComputerPartB =  new IntCodeComputer2(getInput("Day9"), input, output);
        myComputerPartB.run();
        System.out.println("\n output part A: ");
        output.forEach(outputValue -> System.out.print(outputValue + "   "));
    }

    static List<Long> getInput(String inputFileName) {
        String input = null;
        try {
            input = Files.readString(Paths.get("src/main/resources/input/year2019", inputFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.stream(input.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}