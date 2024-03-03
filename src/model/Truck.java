package model;

import java.awt.*;

abstract class Truck extends Car {

    Truck(double x, double y, int nrDoors, int enginePower, Color color, String modelName){
        super(x, y, nrDoors, enginePower, color, modelName);
    }

    @Override
    protected double getSpeedFactor(){
        return Math.max(getEnginePower() * 0.01,0);
    }

    public abstract void raiseBed();

    public abstract void lowerBed();

}
