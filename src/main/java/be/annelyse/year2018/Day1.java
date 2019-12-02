package be.annelyse.year2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day1 {

    public static void main(String[] args) throws IOException {
        solvePart1(getInput());
        solvePart2(getInput());
    }

    private static List<Integer> getInput() throws IOException {
        return Files
                .readAllLines(Paths.get("src/main/resources/input/year2018","Day1"))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static int solvePart1(List<Integer> input) {
        Integer result = input.stream()
                .reduce(0, Integer::sum);

        System.out.println("**********************Part1**********************");
        System.out.println("Eindfrequentie is: " + result);
        return result;
    }

    static int solvePart2(List<Integer> input) {
        Set<Integer> frequencies = new HashSet<>();
        Integer frequency = 0;
        int index = 0;

        while(frequencies.add(frequency)){
            frequency += input.get(index % input.size());
            index++;
        }
        System.out.println("**********************Part2**********************");
        System.out.println("De frequency that appears twice first is: " + frequency);
        return frequency;
    }

}
