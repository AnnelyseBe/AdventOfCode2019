package be.annelyse.year2019;

import be.annelyse.util.Asteroid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;



public class Day10 {
    static List<Asteroid> asteroids;

    public static void main(String[] args) {

      System.out.println(calculateDirectAstroidsForPointTest("Day10_test1",4,4));


      System.out.println("Solution partA:" + solveA("Day10"));
    }

    //check n° of direct astroids to point with coordinates
    private static int calculateDirectAstroidsForPointTest(String inputfileName, int x, int y){
        asteroids = getAsteroids(inputfileName);
        Asteroid asteroidToCheck = new Asteroid(x,y);
        return getDirectAsteroidCount(asteroidToCheck);
    }

    private static int solveA(String inputFileName){
        asteroids = getAsteroids(inputFileName);

        asteroids.forEach(System.out::println);

        int maxDirectAsteroids = 0;

        for (Asteroid asteroid : asteroids) {
            int asteroidCount = getDirectAsteroidCount(asteroid);

            if (asteroidCount > maxDirectAsteroids) {
                maxDirectAsteroids = asteroidCount;
            }
        }

        return maxDirectAsteroids;
    }

    private static int getDirectAsteroidCount (Asteroid asteroid){

        int directAsteroids = (int) asteroids.stream().filter(anAstroid -> isDirectAsteroid(asteroid, anAstroid)).count();
        asteroids.get(asteroids.indexOf(asteroid)).setDirectAsteroidCount(directAsteroids);
        return directAsteroids;
    }

    private static boolean isDirectAsteroid (Asteroid asteroidBase, Asteroid asteroidToCheck){
        if (asteroidBase.equals(asteroidToCheck) ){
            System.out.println("*****************************");
            return false;
        }

        long numberBlockingAstroids = asteroids.stream()
                .filter(asteroid -> !asteroid.equals(asteroidBase))
                .filter(asteroid -> !asteroid.equals(asteroidToCheck))
                .filter(asteroid -> asteroid.collinearAndBetween(asteroidBase, asteroidToCheck)).count();

        System.out.println("number of blocking astroids: " + numberBlockingAstroids);
        boolean directAstroid = numberBlockingAstroids == 0L;

        System.out.println("directAstroid: " + directAstroid);
        return (directAstroid);
    }


    private static List<Asteroid> getAsteroids (String inputFileName){

        List<Asteroid> asteroidList = new ArrayList<>();
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get("src/main/resources/input/year2019",inputFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int y = 0; y < lines.size(); y++ ){
            String[] row = lines.get(y).split("");

            for (int x = 0; x < row.length; x++){

                if(row[x].equals("#")){
                    asteroidList.add(new Asteroid(x,y));
                }
            }
        }
        return asteroidList;
    }

    public void printAstroidMapCount(){

    }

}
