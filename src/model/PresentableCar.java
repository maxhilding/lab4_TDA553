package model;

import java.awt.*;
import java.awt.geom.Point2D;

public class PresentableCar implements Presentable{

    private Car wrappedCar;

    PresentableCar(Car wrappedCar){
        this.wrappedCar = wrappedCar;
    }

    //Defensive copying
    @Override
    public Point2D.Double getPosition(){
        return new Point2D.Double(wrappedCar.getPosition().getX(), wrappedCar.getPosition().getY());
    }

    // Getters for the car specific attributes, Open-closed principle
    public int getNrDoors(){
        return wrappedCar.getNrDoors();
    }

    public double getEnginePower(){
        return wrappedCar.getEnginePower();
    }

    public double getCurrentSpeed(){
        return wrappedCar.getCurrentSpeed();
    }
    public Color getColor(){
        return wrappedCar.getColor();
    }

    public String getModelName() {return wrappedCar.getModelName();}

    public boolean getIsDriveable(){return wrappedCar.getIsDriveable();}

    public double getHandling(){return wrappedCar.getHandling();}

    public double getCurrentDegree(){return wrappedCar.getCurrentDegree();}

}
