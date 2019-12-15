package be.annelyse.util;

public class Coordinate2D {
    int x;
    int y;
    Direction direction;

    public Coordinate2D(int x, int y) {
        this(x,y,null);
    }

    public Coordinate2D(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Coordinate2D moveLeft(int steps){
        x -= steps;
        return this;
    }

    public Coordinate2D moveRight(int steps){
        x += steps;
        return this;
    }

    public Coordinate2D moveUp(int steps){
        y += steps;
        return this;
    }

    public Coordinate2D moveDown(int steps){
        y -= steps;
        return this;
    }

    public Coordinate2D moveForward(int steps){
        switch (this.direction){
            case UP:
                y += steps;
                break;
            case DOWN:
                y -= steps;
                break;
            case LEFT:
                x -= steps;
                break;
            case RIGHT:
                x += steps;
                break;
        }
        return this;
    }

    public Coordinate2D turnLeft(){
        this.direction = direction.turnLeft();
        return this;
    }

    public Coordinate2D turnRight(){
        this.direction = direction.turnRight();
        return this;
    }

    public Coordinate2D turnLeftAndStep(int steps){
        this.turnLeft();
        this.moveForward(steps);
        return this;
    }

    public Coordinate2D turnRightAndStep(int steps){
        this.turnRight();
        this.moveForward(steps);
        return this;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate2D that = (Coordinate2D) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }



    @Override
    public String toString() {
        return "x=" + x + " y=" + y + " direction=" + direction;
    }



}
