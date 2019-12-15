package be.annelyse.year2019;

import be.annelyse.util.Color;
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

public class Day11 {

    private static LinkedBlockingQueue<Long> inputPaintRobot = new LinkedBlockingQueue<>();
    private static LinkedBlockingQueue<Long> outputPaintRobot = new LinkedBlockingQueue<>();
    private static List<Pannel> paintedPannels;

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
            paintedPannels = ourPaintRobot.getPaintedPannels();
            ourPaintRobot.setActive(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printPaintedPannels(paintedPannels);
        return paintedPannels.size();
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

    static String[][] printPaintedPannels(List<Pannel> paintedPannels) {

        int xMin = paintedPannels.stream().map(Coordinate2D::getX).min(Integer::compareTo).get();
        int xMax = paintedPannels.stream().map(Coordinate2D::getX).max(Integer::compareTo).get();
        int yMin = paintedPannels.stream().map(Coordinate2D::getY).min(Integer::compareTo).get();
        int yMax = paintedPannels.stream().map(Coordinate2D::getY).max(Integer::compareTo).get();

        System.out.println("xMin: " + xMin + "  xMax: " + xMax + "  yMin: " + yMin + "  yMax: " + yMax);

        int columnCount = xMax - xMin + 1;
        int rowCount = yMax - yMin + 1;

        String[][] print = new String[columnCount][rowCount];

        for (int y = rowCount-1; y >= 0; y--){
            System.out.println();

            for (int x = 0; x < columnCount; x++){

                int indexOfPannel = paintedPannels.indexOf(new Pannel(x+xMin,y+yMin));
                if(indexOfPannel < 0){
                    print[x][y] = " ";
                } else {
                    Color pannelColor = paintedPannels.get(indexOfPannel).getColor();

                    print[x][y] = pannelColor.toString();
                }
                System.out.print(print[x][y]);
            }
        }
        return print;

    }
}

