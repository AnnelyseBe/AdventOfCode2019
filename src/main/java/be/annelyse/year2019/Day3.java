package be.annelyse.year2019;

import be.annelyse.util.Coordinate2D;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.StrictMath.abs;

public class Day3 {

    //todo contains() works faster for hashSet as for a List
    //todo testing (add day3 test input)
    private static List<Coordinate2D> wireCoordinatesListA;
    private static List<Coordinate2D> wireCoordinatesListB;
    private static List<Coordinate2D> commonWireCoordinates;

    public static void main(String[] args) throws IOException {
        Instant start = Instant.now();
        System.out.println("\n********************************************Part1********************************************");
        System.out.println("\n\nSolution part 1: " + solvePart1());
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        System.out.println("Processing time: " + timeElapsed + " millis or " + timeElapsed / 1000 + " seconds");

        start = Instant.now();
        System.out.println("\n********************************************Part2********************************************");
        System.out.println("\nSolution part 2: " + solvePart2());
        finish = Instant.now();
        timeElapsed = Duration.between(start, finish).toMillis();

        System.out.println("Processing time: " + timeElapsed + " millis or " + timeElapsed / 1000 + " seconds");
    }

    private static List<String> getInputLine(int lineIndex) throws IOException {
        List<String> inputLines = Files.readAllLines(Paths.get("src/main/resources/input/year2019", "Day3"));
        List<List<String>> input = inputLines.stream().map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
        return input.get(lineIndex);
    }


    static int solvePart1() throws IOException {
        wireCoordinatesListA = getWireCoordinates(getInputLine(0));
        wireCoordinatesListB = getWireCoordinates(getInputLine(1));
        commonWireCoordinates = getCommonWireCoordinates(wireCoordinatesListA, wireCoordinatesListB);
        return getClosedDistance(commonWireCoordinates);
    }

    static int solvePart2() {
        return calculateMinimumDistanceToACommonCoordinate(wireCoordinatesListA, wireCoordinatesListB, commonWireCoordinates);
    }


    private static List<Coordinate2D> getWireCoordinates(List<String> wireInput) {
        Coordinate2D coordinates = new Coordinate2D(0, 0);
        List<Coordinate2D> wireCoordinatesList = new ArrayList<>();

        for (String input : wireInput) {
            String direction = input.substring(0, 1);
            int steps = Integer.parseInt(input.substring(1));

            while (steps > 0) {
                switch (direction) {
                    case "L":
                        coordinates.moveLeft(1);
                        break;
                    case "R":
                        coordinates.moveRight(1);
                        break;
                    case "U":
                        coordinates.moveUp(1);
                        break;
                    case "D":
                        coordinates.moveDown(1);
                        break;
                    default:
                        System.out.println("oooops, something went wrong");
                }
                steps--;
                wireCoordinatesList.add(new Coordinate2D(coordinates.getX(), coordinates.getY()));
            }
        }
        return wireCoordinatesList;
    }

    static List<Coordinate2D> getCommonWireCoordinates(List<Coordinate2D> wireCoordinatesListA, List<Coordinate2D> wireCoordinatesListB) {
        return wireCoordinatesListA.stream()
                .filter(wireCoordinatesListB::contains)
                .collect(Collectors.toList());
    }

    private static int getClosedDistance(List<Coordinate2D> commonWireCoordinates) {
        return commonWireCoordinates.stream()
                .map(coord -> abs(coord.getX()) + abs(coord.getY()))
                .min(Comparator.comparing(Integer::intValue))
                .orElseThrow(NoSuchElementException::new);
    }

    private static int stepCountToReachCoordinate(Coordinate2D coordinate, List<Coordinate2D> wireCoordinatesList) {
        return wireCoordinatesList.indexOf(coordinate) + 1;
    }

    private static int calculateMinimumDistanceToACommonCoordinate(List<Coordinate2D> wireCoordinatesListA, List<Coordinate2D> wireCoordinatesListB, List<Coordinate2D> commonWireCoordinates) {
        int minDistance = 0;

        for (Coordinate2D common : commonWireCoordinates) {
            int distance = stepCountToReachCoordinate(common, wireCoordinatesListA) + stepCountToReachCoordinate(common, wireCoordinatesListB);
            if (minDistance == 0 || minDistance > distance) {
                minDistance = distance;
            }
        }
        return minDistance;
    }
}
