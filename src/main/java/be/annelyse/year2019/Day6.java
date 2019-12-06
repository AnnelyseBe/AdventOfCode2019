package be.annelyse.year2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 {

    private static Map<String, List<String>> orbitMap;

    public static void main(String[] args) throws IOException {
        Instant start = Instant.now();
        System.out.println("solution part 1 ***************************************************");
        System.out.println("\nthe solution is: "+ solve1(getInput("Day6")) + " ***************************************************");

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Processing time: " + timeElapsed + " millis or " + timeElapsed / 1000 + " seconds");
        start = Instant.now();
        System.out.println("\nsolution part 2 ***************************************************");
        System.out.println("\nthe solution is: "+ solve2() + " ***************************************************");

        finish = Instant.now();
        timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Processing time: " + timeElapsed + " millis or " + timeElapsed / 1000 + " seconds");
    }

    static int solve1(List<String> inputPairs){
        orbitMap = mapOrbitWithAllItsPrecedents(inputPairs);
        return calculateDirectAndIndirectOrbitsChecksum(orbitMap);
    }

    static int solve2(){
        List<String> precedentsOfYOU = orbitMap.get("YOU");
        List<String> precedentsOfSAN = orbitMap.get("SAN");

        return findMinimalNumberOfOrbitalTransfers(precedentsOfYOU, precedentsOfSAN);
    }

    private static int findMinimalNumberOfOrbitalTransfers(List<String> precedentsOfYOU, List<String> precedentsOfSAN) {

        String closestConnection = null;

        for (int i = 0; closestConnection == null && i < precedentsOfYOU.size(); i++){
            String toCheckYOUprecendent = precedentsOfYOU.get(i);

            for (String toCheckSANprecendent : precedentsOfSAN) {
                if (toCheckSANprecendent.equals(toCheckYOUprecendent)) {
                    closestConnection = toCheckSANprecendent;
                    break;
                }
            }
        }
        //todo ... we houden geen rekening met het feit dat je in dezelfde lijn kan zitten !!!

        int distanceYOUtoClosestConnection = precedentsOfYOU.indexOf(closestConnection);
        int distanceSANtoClosestConnection = precedentsOfSAN.indexOf(closestConnection);


        return distanceSANtoClosestConnection + distanceYOUtoClosestConnection;

    }

    private static int calculateDirectAndIndirectOrbitsChecksum(Map<String, List<String>> orbitMap) {
        return orbitMap.values().stream().map(List::size).reduce(0, Integer::sum);
    }

    private static Map<String, List<String>> mapOrbitWithAllItsPrecedents(List<String> inputPairs) {

        Map<String, List<String>> orbitsWithPrecedents = new HashMap<>();

        for (String inputPair : inputPairs) {

            String moonOrbit = getMoonOrbit(inputPair);

            String centralOrbit = getCentralOrbit(inputPair);
            List<String> listProceedingOrbits = new ArrayList<>();
            listProceedingOrbits.add(centralOrbit);


            if (orbitsWithPrecedents.get(centralOrbit) != null) {
                listProceedingOrbits.addAll(orbitsWithPrecedents.get(centralOrbit));
            }

            orbitsWithPrecedents.put(moonOrbit, listProceedingOrbits);
        }

        Set<String> lastvalues = orbitsWithPrecedents.values().stream().map(list -> list.get(list.size()-1)).collect(Collectors.toSet());

        while(lastvalues.size() != 1){

            for (Map.Entry<String, List<String>> entry : orbitsWithPrecedents.entrySet()){

                List<String> precedents = entry.getValue();
                String lastPrecedent = precedents.get(precedents.size()-1);

                List<String> precedentsOfLastPrecedent = orbitsWithPrecedents.get(lastPrecedent);
                if (precedentsOfLastPrecedent != null) {
                    precedents.addAll(precedentsOfLastPrecedent);
                }
            }
            lastvalues = orbitsWithPrecedents.values().stream().map(list -> list.get(list.size()-1)).collect(Collectors.toSet());
        }

        return orbitsWithPrecedents;
    }

    private static String getCentralOrbit (String orbitPair){
        String[] orbits = orbitPair.split("\\)");
        return orbits[0];
    }

    private static String getMoonOrbit (String orbitPair){
        String[] orbits = orbitPair.split("\\)");
        return orbits[1];
    }


    static List<String> getInput(String fileName) throws IOException {
        return Files.readAllLines(Paths.get("src/main/resources/input/year2019", fileName));
    }
    }
