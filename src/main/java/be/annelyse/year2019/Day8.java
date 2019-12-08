package be.annelyse.year2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//todo ..... aaarch, veel. Smerige code
public class Day8 {

    /*0: black
     * 1: white
     * 2: transparent*/

    public static void main(String[] args) throws IOException {
        Instant start = Instant.now();
        System.out.println("solution part 1 ***************************************************");
        System.out.println("\nthe solution is: " + solve1("Day8") + " ***************************************************");

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("\nProcessing time: " + timeElapsed + " millis or " + timeElapsed / 1000 + " seconds");
        start = Instant.now();
        System.out.println("\nsolution part 2 ***************************************************");
        List<Integer> result = solve2("Day8", 25, 6);
        System.out.println("\nthe solution is: " + " ***************************************************");
        //result.forEach(color -> System.out.print(color));

        finish = Instant.now();
        timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("\nProcessing time: " + timeElapsed + " millis or " + timeElapsed / 1000 + " seconds");
    }

    public static int solve1(String fileName) throws IOException {

        List<Integer> input = getInput(fileName);
        List<List<Integer>> layeredInput = devideInputInLayers(input, 25, 6);
        List<Integer> layerWithFewest0 = findLayerWithFewest0(layeredInput);

        int numberOf1 = getNumberOfDigit(layerWithFewest0, 1);
        int numberOf2 = getNumberOfDigit(layerWithFewest0, 2);

        return numberOf1 * numberOf2;
    }

    public static List<Integer> solve2(String fileName, int pixdelsWide, int pixelsTall) throws IOException {

        List<Integer> input = getInput(fileName);
        List<List<Integer>> layeredInput = devideInputInLayers(input, pixdelsWide, pixelsTall);

        List<Integer> resultList = new ArrayList<>();

        for (int position = 0; position < layeredInput.get(0).size(); position++) {
            int colorRemaining = 2;
            for (int layer = 0; layer < layeredInput.size(); layer++) {
                int colorNew = layeredInput.get(layer).get(position);
                colorRemaining = reduceColors(colorRemaining, colorNew);
            }
            resultList.add(position, colorRemaining);
        }

        for (int i = 0; i< resultList.size(); i++){
            String toPrint;

            if(resultList.get(i)==1){
                toPrint = "X";
            } else {
                toPrint = " ";
            }

            System.out.print(toPrint);
            if (i % pixdelsWide == pixdelsWide - 1) System.out.println();
        }

        return resultList;


    }


    private static int reduceColors(int color1, int color2) {
        if (color1 == 2) {
            return color2;
        } else return color1;
    }


    static List<Integer> findLayerWithFewest0(List<List<Integer>> layerList) {

        List<List<Integer>> sortedLayerList = layerList.stream()
                .sorted(Comparator.comparing(stream -> getNumberOfDigit(stream, 0)))
                .collect(Collectors.toList());

        System.out.println("numberOf0: " + getNumberOfDigit(sortedLayerList.get(0), 0));

        return sortedLayerList.get(0);
    }

    private static int getNumberOfDigit(List<Integer> layer, int digitValue) {
        return (int) layer.stream().filter(digit -> digit == digitValue).count();
    }


    private static List<List<Integer>> devideInputInLayers(List<Integer> input, int pixelsWide, int pixelsTall) {
        int layerSize = pixelsWide * pixelsTall;
        List<List<Integer>> layerList = new ArrayList();
        List<Integer> layerTemp = new ArrayList();

        for (int i = 0; i < input.size(); i++) {
            layerTemp.add(input.get(i));
            if (i % layerSize == layerSize - 1) {
                layerList.add(layerTemp);
                layerTemp = new ArrayList();
            }
        }
        return layerList;
    }

    static List<Integer> getInput(String inputFileName) throws IOException {
        String input = Files.readString(Paths.get("src/main/resources/input/year2019", inputFileName));
        return Arrays.stream(input.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
