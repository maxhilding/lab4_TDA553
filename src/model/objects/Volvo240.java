package model.objects;

import java.awt.*;

public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;
    
    Volvo240(double x, double y){
        super(x, y, 4, 100, Color.black, "Volvo240");
    }
    @Override
    public double getSpeedFactor(){
        return Math.max(getEnginePower() * 0.01 * trimFactor,0);
    }

}
