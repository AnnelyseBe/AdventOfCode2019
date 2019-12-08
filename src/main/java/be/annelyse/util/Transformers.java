package be.annelyse.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Transformers {

    /**
     3 becomes 0,0,0,0,3 -> digits.get(4);
     */
    public static List<Integer> transformNumberToDigitList(int code, int numberOfDigits){
        List<Integer> digits = new ArrayList<>();

        for (int k = 0; k < numberOfDigits; k++){
            digits.add(0,code % 10 );
            code = code / 10;
        }
        return digits;
    }

    /**
     0,0,0,0,3 becomes 3 -> digits.get(4);
     */
    public static int transformDigitListToCode (List<Integer> digitList){
        int result = 0;
        int multiplier = 1;

        while (!digitList.isEmpty()){
            int digit = digitList.remove(digitList.size()-1);
            result += digit*multiplier;
            multiplier *= 10;
        }
        return result;
    }
}
