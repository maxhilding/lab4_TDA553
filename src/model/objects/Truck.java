package model.objects;

import java.awt.*;

public abstract class Truck extends Car{

    Truck(double x, double y, int nrDoors, int enginePower, Color color, String modelName){
        super(x, y, nrDoors, enginePower, color, modelName);
    }

    public double getSpeedFactor(){
        return Math.max(getEnginePower() * 0.01,0);
    }

    public abstract void raiseBed();

    public abstract void lowerBed();

}
