package model.objects;

import java.awt.*;
import java.awt.geom.Point2D;

public class Scania implements ITruck{

    private final BaseCar wrappedCar;

    private final double bedSensitivity = 10;

    private double bedAngle;

    public Scania(double x, double y) {
        wrappedCar = new BaseCar(2, 770, Color.red, "ScaniaV8", x, y);
        bedAngle = 0;
    }

    // Truck specific
    public double getBedAngle() {
        return bedAngle;
    }


    public void raiseBed(){
        if (wrappedCar.getCurrentSpeed() == 0){
            bedAngle = Math.min(bedAngle + bedSensitivity, 70);
            wrappedCar.stopEngine();
            wrappedCar.isDriveable = false;
            System.out.println("Bed is raised");
        }

    }

    public void lowerBed(){
        if(wrappedCar.getCurrentSpeed()==0) {
            bedAngle = Math.max(bedAngle - bedSensitivity,0);
            if (bedAngle == 0) {
                wrappedCar.stopEngine();
                wrappedCar.isDriveable = true;
            }


        }
    }


    public void gas(double amount){
        System.out.println(bedAngle);
        if (bedAngle == 0){
            wrappedCar.gas(amount);
        }
    }

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

    public void incrementSpeed(double amount){wrappedCar.incrementSpeed(amount);}
    public void decrementSpeed(double amount){
        wrappedCar.decrementSpeed(amount);}

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
