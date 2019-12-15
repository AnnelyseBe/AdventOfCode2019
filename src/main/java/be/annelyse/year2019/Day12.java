package be.annelyse.year2019;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day12 {

    private static List<Moon> moons;
    private static List<List<Moon>> previousStates = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("solution is ................................................");
        System.out.println(solveA());
    }

    private static long solveA(){
        moons = getInput();
        long counter = 0;
        boolean previousState = false;

        while(!previousState){
            counter++;
            System.out.println(counter);

            for(int i = 0; i < moons.size(); i++){
                moons.get(i).calculateVelocityByGravity(moons);
            }

            for (Moon moon : moons) {
                moon.calculatePositionAfterTimeUnit();
            }

            for (List<Moon> state : previousStates) {

                for (int i = 0; i < moons.size(); i++) {
                    previousState = moons.get(i).equals(state.get(i));
                }
            }

            List<Moon> inventory = moons.stream().map(Moon::copyMoon).collect(Collectors.toList());
            previousStates.add(inventory);

        }
        long totalEnergy = moons.stream().map(Moon::calculateTotalEnergy).mapToLong(Long::longValue).sum();
        return counter;
    }

    private static List<Moon> getInput(){
        List<Moon> moons = new ArrayList<>();
        Moon moon1 = new Moon(5L,4L,4L);
        Moon moon2 = new Moon(-11L,-11L,-3L);
        Moon moon3 = new Moon(0L,7L,0L);
        Moon moon4 = new Moon(-13L,2L,10L);
        moons.add(moon1);
        moons.add(moon2);
        moons.add(moon3);
        moons.add(moon4);
        return moons;
    }




}
