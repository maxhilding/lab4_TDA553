package model;

import java.awt.*;

class Saab95 extends Car implements Turbo {

    private boolean turboOn;
    
    Saab95(double x, double y){
        super(x, y, 2, 125, Color.red, "Saab95");
	    turboOn = false;
    }

    @Override
    public boolean getIsTurboOn() { return turboOn; }

    //Setters
    @Override
    public void setTurboOn(){
	    turboOn = true;
    }

    @Override
    public void setTurboOff(){
	    turboOn = false;
    }


    @Override
    protected double getSpeedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return Math.max(getEnginePower() * 0.01 * turbo, 0);
    }

}
