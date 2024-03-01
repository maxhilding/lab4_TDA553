package model.objects;

import java.awt.*;

public class Saab95 extends Car{

    private boolean turboOn;
    
    Saab95(double x, double y){
        super(x, y, 2, 125, Color.red, "Saab95");
	    turboOn = false;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    public boolean getTurboOn() { return turboOn; }

    @Override
    public double getSpeedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return Math.max(getEnginePower() * 0.01 * turbo,0);
    }

}
