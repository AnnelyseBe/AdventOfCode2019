package be.annelyse.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransformersTest {

    @Test
    void transformNumberToDigitList() {

        List<Integer> result = Transformers.transformNumberToDigitList(3,5);
        Assertions.assertEquals(3,result.get(4));
        Assertions.assertEquals(5, result.size());

        result = Transformers.transformNumberToDigitList(99999,5);
        Assertions.assertEquals(9,result.get(0));
        Assertions.assertEquals(9,result.get(1));
        Assertions.assertEquals(9,result.get(2));
        Assertions.assertEquals(9,result.get(3));
        Assertions.assertEquals(9,result.get(4));
        Assertions.assertEquals(5, result.size());

        result = Transformers.transformNumberToDigitList(15482,5);
        Assertions.assertEquals(1,result.get(0));
        Assertions.assertEquals(5,result.get(1));
        Assertions.assertEquals(4,result.get(2));
        Assertions.assertEquals(8,result.get(3));
        Assertions.assertEquals(2,result.get(4));
        Assertions.assertEquals(5, result.size());
    }
}