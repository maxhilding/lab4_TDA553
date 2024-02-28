package model.objects;

import java.awt.*;
import java.awt.geom.Point2D;

public class Volvo240 implements ICar {
    private final BaseCar wrappedCar;

    private final double trimFactor = 1.25;

    public Volvo240(double x, double y) {
        wrappedCar = new BaseCar(4, 100, Color.black, "Volvo240", x, y);
    }

    //Volvo240 specific


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
        wrappedCar.incrementSpeed(amount);
    }

    public void decrementSpeed(double amount){wrappedCar.decrementSpeed(amount);}

    public void gas(double amount){wrappedCar.gas(amount);}

    public void brake(double amount){wrappedCar.brake(amount);}

    public double getCurrentSpeed(){return wrappedCar.getCurrentSpeed();}

    // Position
    public Point2D.Double getPosition(){return wrappedCar.getPosition();}

    public void setPosition(double newX, double newY){wrappedCar.setPosition(newX, newY);}

    // Direction
    public Point2D.Double getDirection(){ return wrappedCar.getDirection();}

    public void setDirection(double newX, double newY){wrappedCar.setDirection(newX, newY);}
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
