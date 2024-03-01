package model.objects;

import java.awt.*;
import java.awt.geom.Point2D;

public class BaseCar implements ICar{

    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name

    private Point2D.Double position;

    private Point2D.Double direction;

    private final double handling = 90;

    private double currentDegree = 90;

    private double speedFactor = 1;

    private boolean isDriveable = true;

    BaseCar(int nrDoors, double enginePower, Color color, String modelName, double x, double y) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        position = new Point2D.Double(x, y);
        direction = new Point2D.Double(1, 0);
        stopEngine();
    }


    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }
    public Color getColor(){
        return color;
    }

    public String getModelName() {return modelName;}

    public boolean getIsDriveable(){return isDriveable;}

    public void setIsUnDriveable(){
        isDriveable = false;
    }

    public void setIsDriveable(){
        isDriveable = true;
    }

    public void setSpeedFactor(double newSpeedFactor){
        speedFactor = newSpeedFactor;
    }

    public void startEngine(){
        if(isDriveable && currentSpeed==0) {
            currentSpeed = 0.1;
        }
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public double getHandling(){return handling;}

    public double getCurrentDegree(){return currentDegree;}


    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor * amount,enginePower);
    }
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor * amount,0);
    }

    public void gas(double amount){
        if(amount>=0 && amount<= 1 && currentSpeed > 0 ){
            incrementSpeed(amount);
        }
        else {
            throw new IllegalArgumentException(amount + " not allowed as an argument");
        }
    }

    public void brake(double amount){
        if(amount>=0 && amount<= 1){
            decrementSpeed(amount);
        }
        else {
            throw new IllegalArgumentException(amount + " not allowed as an argument");
        }
    }

    public Point2D.Double getPosition(){
        return new Point2D.Double(position.getX(), position.getY());
    }

    public Point2D.Double getDirection(){
        return new Point2D.Double(direction.getX(), direction.getY());
    }

    public void setDirection(double newX, double newY) {
        direction.setLocation(newX, newY);
    }

    public void setPosition(double newX, double newY){
        position.setLocation(newX, newY);
    }


    public void move(){
        if(currentSpeed > 0){
            double newX = currentSpeed * direction.getX() + position.getX();
            double newY = currentSpeed * direction.getY() + position.getY();
            setPosition(newX, newY);}
    }
    public void turnLeft(){
        currentDegree += handling;
        direction.setLocation(Math.round(Math.cos(Math.toRadians(currentDegree))),
                Math.round(Math.sin(Math.toRadians(currentDegree))));
    }
    public void turnRight() {
        currentDegree -= handling;
        direction.setLocation(Math.round(Math.cos(Math.toRadians(currentDegree))),
                Math.round(Math.sin(Math.toRadians(currentDegree))));
    }


}


