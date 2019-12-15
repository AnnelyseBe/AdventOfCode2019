package be.annelyse.year2019;

import be.annelyse.util.Color;
import be.annelyse.util.Coordinate2D;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class PaintRobot implements Runnable {

    List<Integer> inputListForDebugging = new ArrayList<>();
    List<Integer> outputListForDebugging = new ArrayList<>();
    int counter = 0;

    Coordinate2D position;
    Boolean active;

    List<Pannel> paintedPannels;

    private LinkedBlockingQueue<Long> input;
    private LinkedBlockingQueue<Long> output;

    public PaintRobot(Coordinate2D startPosition, LinkedBlockingQueue<Long> input, LinkedBlockingQueue<Long> output) {
        this.position = startPosition;
        this.paintedPannels = new ArrayList<>();
        this.input = input;
        this.output = output;
    }


    @Override
    public void run() {
        this.active = true;


        while (active){
            checkColorAndSend();
            paintPannel(getInput().intValue());
            move(getInput().intValue());
            counter++;
        }

      //  paintedPannels.forEach(pannel -> System.out.println(pannel));
    }

    private boolean startPositionReached(){
        return (position.getX() == paintedPannels.get(0).getX()) && (position.getY() == paintedPannels.get(0).getY());
    }

    private void checkColorAndSend(){
        Color colorOfPannel;

        int indexOfPaintedPannel = paintedPannels.indexOf(new Pannel(position.getX(), position.getY()));

        if(counter == 0) {
            colorOfPannel = Color.WHITE;
        } else if (indexOfPaintedPannel > -1){
            colorOfPannel = paintedPannels.get(indexOfPaintedPannel).getColor();
        } else {
            colorOfPannel = Color.BLACK;
        }
//        System.out.println("output painter: " + colorOfPannel.getIntCode() + " " + colorOfPannel + "...............................................");

        Long myOutput = colorOfPannel.getIntCode().longValue();
        output.add(myOutput);

        //for debugging
        outputListForDebugging.add(myOutput.intValue());
    }

    private void move(int moveCode){

//        System.out.println("............................................... input painter: " + moveCode + " moveCode ");

        switch (moveCode){

            case 0:
                position = position.turnLeftAndStep(1);
                break;
            case 1:
                position = position.turnRightAndStep(1);
                break;
        }
    }

    private void paintPannel(int colorCode){

        Color colorToPaint;
        switch (colorCode){
            case 0:
//                System.out.println("............................................... input painter: " + colorCode + " BLACK  ...............................................");
                colorToPaint = Color.BLACK;
                break;
            case 1:
//                System.out.println("............................................... input painter: " + colorCode + " WHITE  ...............................................");
                colorToPaint = Color.WHITE;
                break;
            default:
                throw new RuntimeException("ColorCode: " + colorCode + " is not valid");
        }

        int indexOfPannelToPaint = paintedPannels.indexOf(new Pannel(position.getX(), position.getY()));

        if (indexOfPannelToPaint > -1){
            paintedPannels.get(indexOfPannelToPaint).setColor(colorToPaint);
            //System.out.println("\n     Pannel: " + paintedPannels.get(indexOfPannelToPaint) +" painted in: " + colorToPaint.getIntCode() + " " + colorToPaint);
        } else {
            Pannel paintedPannel = new Pannel(position.getX(), position.getY(), null, colorToPaint);
            paintedPannels.add(paintedPannel);
            //System.out.println("\n     Pannel: " + paintedPannel +" painted in: " + colorToPaint.getIntCode() + " " + colorToPaint);

        }
    }

    public Long getInput() {

        while (input.isEmpty()) {
            try {
                Thread.sleep(1);
                //System.out.println("....................waiting for input");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Long myInput = input.poll();

        //for debugging
        inputListForDebugging.add(myInput.intValue());

        return myInput;
    }

    public List<Pannel> getPaintedPannels() {
        return paintedPannels;
    }

    public void setPaintedPannels(List<Pannel> paintedPannels) {
        this.paintedPannels = paintedPannels;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
