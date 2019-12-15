package be.annelyse.year2019;

import be.annelyse.util.Coordinate2D;
import be.annelyse.util.Direction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


//todo ... de threads nog beter op elkaar afstemmen, zodat we het eindresultaat kunnen ophalen
public class Day11 {

    private static LinkedBlockingQueue<Long> inputPaintRobot = new LinkedBlockingQueue<>();
    private static LinkedBlockingQueue<Long> outputPaintRobot = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

//        System.out.println("********************************************************************");
//        System.out.println("The testSolution is: " + solveWithoutIntComputer("Day11_test1"));

        System.out.println("********************************************************************");
        System.out.println("The solution with my computer is is: " + solveA("Day11"));


    }

    private static int solveWithoutIntComputer(String inputFileName) {
        List<Long> input = getInput(inputFileName);
        LinkedBlockingQueue<Long> testInput = new LinkedBlockingQueue<>(input);

        PaintRobot testPaintRobot = new PaintRobot(new Coordinate2D(0, 0, Direction.UP), testInput, outputPaintRobot);

        Thread paintRobotThread = new Thread(testPaintRobot);
        paintRobotThread.start();

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testPaintRobot.setActive(false);  //todo ... deze komt te laat!!!! deze zou moeten komen voor hij alweer wacht op een nieuwe input. Misschien hier te implementeren als de laatste input wordt genomen ofzo???
        try {
            paintRobotThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return testPaintRobot.getPaintedPannels().size();

    }

    private static int solveA(String inputFileName) {

        PaintRobot ourPaintRobot = new PaintRobot(new Coordinate2D(0, 0, Direction.UP), inputPaintRobot, outputPaintRobot);
        IntCodeComputer2 intCodeComputer2 = new IntCodeComputer2(getInput(inputFileName), outputPaintRobot, inputPaintRobot);

        Thread paintRobotThread = new Thread(ourPaintRobot);
        paintRobotThread.start();

        Thread intComputerThread = new Thread(intCodeComputer2);
        intComputerThread.start();

        try {
            intComputerThread.join();
            ourPaintRobot.setActive(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ourPaintRobot.getPaintedPannels().size();

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

