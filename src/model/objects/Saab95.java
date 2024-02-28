package model.objects;

import java.awt.*;
import java.awt.geom.Point2D;

public class Saab95 implements ICar {

    private final BaseCar wrappedCar;

    private boolean turboOn;

    public Saab95(double x, double y){
        wrappedCar = new BaseCar(2, 125, Color.red, "Saab95", x, y);
        turboOn = false;

    }


    // Saab95 specific
    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){
        turboOn = false;
    }

    public boolean getTurboOn() { return turboOn; }



    // General car methods
    // Attributes
    public String getModelName(){
        return wrappedCar.getModelName();
    };

    public int getNrDoors(){
        return wrappedCar.getNrDoors();
    };

    public double getEnginePower(){
        return wrappedCar.getEnginePower();
    };
    public Color getColor(){return wrappedCar.getColor();};

    public double getHandling(){
        return wrappedCar.getHandling();}

    public double getCurrentDegree(){
        return wrappedCar.getCurrentDegree();
    }

    // Engine
    public void startEngine(){
        wrappedCar.startEngine();
    };

    public void stopEngine(){
        wrappedCar.stopEngine();
    };

    // Speed and acceleration

    public void incrementSpeed(double amount){
        double turbo = 1;
        if (turboOn) {turbo = 30;}
        wrappedCar.speedFactor = Math.max(getEnginePower() * 0.01 * turbo,0);
        wrappedCar.incrementSpeed(amount);
    }
    public void decrementSpeed(double amount){wrappedCar.decrementSpeed(amount);}

    public void gas(double amount){
        if(amount>=0 && amount<= 1 && getCurrentSpeed() > 0 ){
            incrementSpeed(amount);
        }
        else {
            throw new IllegalArgumentException(amount + " not allowed as an argument");
    }}

    public void brake(double amount){wrappedCar.brake(amount);}

    public double getCurrentSpeed(){return wrappedCar.getCurrentSpeed();}

    // Position
    public Point2D.Double getPosition(){return wrappedCar.getPosition();}

    public void setPosition(double newX, double newY){wrappedCar.setPosition(newX, newY);}

    public void setDirection(double newX, double newY){wrappedCar.setDirection(newX, newY);}

    // Direction
    public Point2D.Double getDirection(){ return wrappedCar.getDirection();}

    // Movement
    public void move() {
        wrappedCar.move();
    }

    public void turnLeft() {
        wrappedCar.turnLeft();
    }

    public void turnRight() {
        wrappedCar.turnRight();
    }

}
