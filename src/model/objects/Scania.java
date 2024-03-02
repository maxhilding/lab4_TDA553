package model.objects;

import java.awt.*;

class Scania extends Truck {
    private double bedAngle;
    private final double bedSensitivity = 10;

    Scania(double x, double y) {
        super(x, y, 2, 770, Color.red, "Scania");
        bedAngle = 0;
    }

    public double getBedAngle() {
        return bedAngle;
    }

    @Override
    public void raiseBed(){
        if (getCurrentSpeed() == 0){
        bedAngle = Math.min(bedAngle + bedSensitivity, 70);
        stopEngine();
        setIsUnDriveable();
        }
    }
    @Override
    public void lowerBed(){
        if(getCurrentSpeed()==0) {
            bedAngle = Math.max(bedAngle - bedSensitivity,0);
            if (bedAngle == 0) {
                stopEngine();
                setIsDriveable();
            }
        }
    }

    @Override
    public void gas(double amount){
        if (getBedAngle() == 0){
            super.gas(amount);
        }
    }
}
