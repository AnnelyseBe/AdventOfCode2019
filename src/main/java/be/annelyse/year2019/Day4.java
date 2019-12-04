package be.annelyse.year2019;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;

public class Day4 {

    public static void main(String[] args) {
        Instant start = Instant.now();
        System.out.println("solution part 1 ***************************************************");
        System.out.println("\nthe solution is: "+ solve1(240298, 784956) + "***************************************************");

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Processing time: " + timeElapsed + " millis or " + timeElapsed / 1000 + " seconds");
        start = Instant.now();
        System.out.println("solution part 2 ***************************************************");
        System.out.println("\nthe solution is: "+ solve2(240298, 784956) + "***************************************************");

        finish = Instant.now();
        timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Processing time: " + timeElapsed + " millis or " + timeElapsed / 1000 + " seconds");
    }

    private static int solve1(int lowestCode, int highestCode){
        int count = 0;
        int code = lowestCode;

        while (code >= lowestCode && code <= highestCode){

            LinkedList<Integer> digits = transformNumberToDigitList(code);

            if (neverDecrease(digits) && contains2adjacentIdenticals(digits)){
                count++;
            }
            code = transformDigitListToCode(digits) + 1;

        }
        return count;
    }

    private static int solve2(int lowestCode, int highestCode){
        int count = 0;
        int code = lowestCode;

        while (code >= lowestCode && code <= highestCode){

            LinkedList<Integer> digits = transformNumberToDigitList(code);

            if (neverDecrease(digits) && containsPairOfExactly2adjacentIdenticals(digits)){
                count++;
                System.out.print("\ncorrect: ");
                digits.stream().forEach(i -> System.out.print(i));
            }
            code = transformDigitListToCode(digits) + 1;


        }
        return count;
    }

    private static boolean neverDecrease(LinkedList<Integer> digitList){

        int lastDigit = 0;

        for (int digit : digitList){
            if (digit < lastDigit) {
                return false;
            } else {
                lastDigit = digit;
            }
        }
        return true;
    }

    private static boolean contains2adjacentIdenticals (LinkedList<Integer> digitList){

        for (int i = 1; i < digitList.size(); i++){
            int lastDigit = digitList.get(i-1);
            int digit = digitList.get(i);
            if(digit == lastDigit){
                return true;
            }
        }
        return false;
    }

    static boolean containsPairOfExactly2adjacentIdenticals (LinkedList<Integer> digitList){

        for (int i = 1; i < digitList.size(); i++){
            int lastDigit = digitList.get(i-1);
            int digit = digitList.get(i);
            if(digit == lastDigit){
                if (i+1 == digitList.size() || digitList.get(i+1) != digit){
                    if ((i-2 == -1) || digitList.get(i-2) != digit){
                        return true;
                    }

                }
            }
        }
        return false;
    }






    private static LinkedList<Integer> transformNumberToDigitList (int code){
        LinkedList<Integer> digits = new LinkedList<Integer>();
        while (code > 0) {
            digits.push( code % 10 );
            code = code / 10;
        }
        return digits;
    }

    private static int transformDigitListToCode (LinkedList<Integer> digitList){
        int result = 0;
        int multiplier = 1;

        while (!digitList.isEmpty()){
            int digit = digitList.pollLast();
            result += digit*multiplier;
            multiplier *= 10;
        }
        return result;
    }
}
