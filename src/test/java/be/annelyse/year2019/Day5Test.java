package be.annelyse.year2019;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    @Test
    void solvePart1() throws IOException {
        int lastOutput = Day5.solvePart1(Day5.getIntCodeInput("Day5_test5"), 8);
        Assertions.assertEquals(1000, lastOutput);
    }
}