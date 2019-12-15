package be.annelyse.year2019;

import be.annelyse.util.Coordinate2D;
import be.annelyse.util.Color;
import be.annelyse.util.Direction;


public class Pannel extends Coordinate2D {

    Color color;
    int paintCount;

    public Pannel(int x, int y) {
        this(x,y,null, null);
    }

    public Pannel(int x, int y, Direction direction) {
        this(x, y, direction, null);
    }

    public Pannel(int x, int y, Direction direction, Color color) {
        super(x, y, direction);
        this.color = color;
        this.paintCount = 0;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPaintCount() {
        return paintCount;
    }

    public void setPaintCount(int paintCount) {
        this.paintCount = paintCount;
    }




}
