package be.annelyse.util;

import java.util.function.Function;

public class Asteroid extends Coordinate2D {

    private Integer directAsteroidCount;

    public Asteroid(int x, int y) {
        super(x, y);
    }

    public Integer getDirectAsteroidCount() {
        return directAsteroidCount;
    }

    public void setDirectAsteroidCount(Integer directAsteroidCount) {
        this.directAsteroidCount = directAsteroidCount;
    }

    public boolean collinearAndBetween(Asteroid asteroid1, Asteroid asteroid2) {

        boolean result;
        result = collinearWith(asteroid1, asteroid2) && between(asteroid1, asteroid2, Asteroid::getX) && between(asteroid1, asteroid2, Asteroid::getY);
        return result;
    }

    private boolean collinearWith(Asteroid asteroid1, Asteroid asteroid2) {
        int a = this.getX() * (asteroid1.getY() - asteroid2.getY()) +
                asteroid1.getX() * (asteroid2.getY() - this.getY()) +
                asteroid2.getX() * (this.getY() - asteroid1.getY());


        return a == 0;
    }

    private boolean between(Asteroid asteroid1, Asteroid asteroid2, Function<Asteroid, Integer> dimension) {
        int p = dimension.apply(this);
        int p1 = dimension.apply(asteroid1);
        int p2 = dimension.apply(asteroid2);

        return (p <= p1 && p >= p2) || (p >= p1 && p <= p2);
    }


    @Override
    public String toString() {
        return "x=" + x + " y=" + y + " directs=" + directAsteroidCount;
    }


    public String toSimpleString() {
        return "x=" + x + " y=" + y;
    }
}
