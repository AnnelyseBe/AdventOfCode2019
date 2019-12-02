package be.annelyse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


class Day2Test {

    @Test
    void solvingAlgorithm() {
        List<Integer> input1 = Arrays.asList(1, 0, 0, 0, 99);
        List<Integer> output1 = Arrays.asList(2, 0, 0, 0, 99);
        Assertions.assertEquals(output1, Day2.solvingAlgoritm(input1));

        List<Integer> input2 = Arrays.asList(2, 3, 0, 3, 99);
        List<Integer> output2 = Arrays.asList(2, 3, 0, 6, 99);
        Assertions.assertEquals(output2, Day2.solvingAlgoritm(input2));

        List<Integer> input3 = Arrays.asList(2, 4, 4, 5, 99, 0);
        List<Integer> output3 = Arrays.asList(2, 4, 4, 5, 99, 9801);
        Assertions.assertEquals(output3, Day2.solvingAlgoritm(input3));

        List<Integer> input4 = Arrays.asList(1,1,1,4,99,5,6,0,99);
        List<Integer> output4 = Arrays.asList(30,1,1,4,2,5,6,0,99);
        Assertions.assertEquals(output4, Day2.solvingAlgoritm(input4));
    }

    @Test
    void solvePart1() throws IOException {
        Assertions.assertEquals(3895705, Day2.solvePart1(Day2.getIntCodeInput(),1202));
    }
}