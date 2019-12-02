package be.annelyse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1 {


    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/input","Day1"));

        solvePart1(input);
        solvePart2(input);
    }

    private static void solvePart1(List<String> input) {

        Long result = input.stream()
                .map((Long::parseLong))
                .map(Day1::calculateFuel)
                .reduce(0L, Long::sum);

        System.out.println("**********************Part1**********************");
        System.out.println("Benodigde fuel is " + result);
    }

    private static void solvePart2(List<String> input) {
        Long result = input.stream()
                .map((Long::parseLong))
                .map(Day1::calculateFuelExtended)
                .reduce(0L, Long::sum);

        System.out.println("**********************Part2**********************");
        System.out.println("Benodigde fuel is " + result);
    }



    static Long calculateFuel(Long mass) {
        return (mass/3)-2L;
    }

    static Long calculateFuelExtended(Long mass){
        long extraFuel = 0L;
        long totalFuel = 0L;

        for (Long toCompensate = mass; extraFuel >= 0L; toCompensate= extraFuel ){
            totalFuel += extraFuel;
            extraFuel = calculateFuel(toCompensate);
        }
        return totalFuel;
    }

    static Long calculateFuelExtendedStreaming(Long mass){

        //todo implement solution with streams
        return null;
    }
}
