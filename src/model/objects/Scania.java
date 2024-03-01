package model.objects;

import java.awt.*;

public class Scania extends Truck {
    private double bedAngle;
    private final double bedSensitivity = 10;

    Scania(double x, double y) {
        super(x, y, 2, 770, Color.red, "ScaniaV8");
        bedAngle = 0;
    }

    public double getBedAngle() {
        return bedAngle;
    }

    @Override
    public void raiseBed(){
        if (getCurrentSpeed() == 0){
        bedAngle = Math.min(getBedAngle() + bedSensitivity, 70);
        }

    }
    @Override
    public void lowerBed(){
        if(getCurrentSpeed()==0) {
            bedAngle = Math.max(getBedAngle() - bedSensitivity,0);
        }
    }

    @Override
    public void gas(double amount){
        if (getBedAngle() == 0){
            super.gas(amount);
        }
    }
}
