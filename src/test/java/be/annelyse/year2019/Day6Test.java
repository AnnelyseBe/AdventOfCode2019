package be.annelyse.year2019;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static be.annelyse.year2019.Day6.getInput;
import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    @Test
    void solve1() throws IOException {
        int result = Day6.solve1(getInput("Day6_test"));

        Assertions.assertEquals(42, result);
    }

    @Test
    void solve2() throws IOException {
        int result1 = Day6.solve1(getInput("Day6_test2"));
        int result2 = Day6.solve2();
        System.out.println("testresult2: " + result2);

        Assertions.assertEquals(4, result2);
    }
}