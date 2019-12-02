package be.annelyse.year2018;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


class Day1Test {

    @Test
    void solvePart1() {
    }

    @Test
    void solvePart2() {
        List<Integer> input1 = Arrays.asList(1,-1);
        List<Integer> input2 = Arrays.asList(3,3,4,-2,-4);
        List<Integer> input3 = Arrays.asList(-6, 3, 8, 5, -6, -3);
        List<Integer> input4 = Arrays.asList(7, 7, -2, -7, -4);

        Assertions.assertEquals(0, Day1.solvePart2(input1));
        Assertions.assertEquals(10, Day1.solvePart2(input2));
        Assertions.assertEquals(5, Day1.solvePart2(input3));
        Assertions.assertEquals(14, Day1.solvePart2(input4));
    }
}