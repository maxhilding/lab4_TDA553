package model;

import java.awt.*;
import java.awt.geom.Point2D;

abstract class Car implements Moveable {

    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name
    private Point2D.Double position; // The position in the world
    private Point2D.Double direction; // Direction as
    private final double handling = 90; // To what degree the direction changes when turning
    private double currentDegree = 90; // The degree in the unit circle, i.e 90 degrees equals the unit vector (0,1)
    private boolean isDriveable = true; // Is the car in a driveable or undirveable mode

    private int width = 0;

    private int length = 0;

    private boolean hasDefinedSize = false;

    private boolean engineIsOn = false;

    Car(double x, double y, int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        position = new Point2D.Double(x, y);
        direction = new Point2D.Double(1, 0);
        stopEngine();
    }

    // Getters for the attributes
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

    public double getHandling(){return handling;}

    public double getCurrentDegree(){return currentDegree;}

    //Defensive copying
    public Point2D.Double getPosition(){
        return new Point2D.Double(position.getX(), position.getY());
    }

    public Point2D.Double getDirection(){
        return new Point2D.Double(direction.getX(), direction.getY());
    }

    // Subclasses must implement own calculation for speedfactor
    protected abstract double getSpeedFactor();

    public int getWidth() {
        return width;

    }

    public int getLength() {
        return length;
    }

    public boolean hasDefinedSize() {
        return hasDefinedSize;
    }

    void setHasDefinedSize(boolean hasDefinedSize) {
        this.hasDefinedSize = hasDefinedSize;
    }

    void setLength(int length) {
        this.length = length;
    }

    void setWidth(int width) {
        this.width = width;
    }

    //Setters
    void setPosition(double x, double y){this.position.setLocation(x, y);};

    void setIsUnDriveable(){
        isDriveable = false;
    }

    void setIsDriveable(){
        isDriveable = true;
    }

    // Can only start engine if mode isDriveable and engine not already on
    void startEngine(){
        if(isDriveable && currentSpeed==0) {
            engineIsOn = true;
            currentSpeed = 0.1;
        }
    }

    void stopEngine(){
        engineIsOn = false;
        currentSpeed = 0;
    }

    // Inverts the direction the car is heading
    void invertX(){
        direction.setLocation(-direction.getX(), direction.getY());
    }

    void invertY(){
        direction.setLocation(direction.getX(), -direction.getY());
    }


    // Methods for changing speed and acceleration
    private void incrementSpeed(double amount){
        currentSpeed = Math.min(currentSpeed + this.getSpeedFactor() * amount,enginePower);
    }
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(currentSpeed - this.getSpeedFactor() * amount,0);
    }

    void gas(double amount){
        if(amount>=0 && amount<= 1){
            if(engineIsOn){
                incrementSpeed(amount);
            }
        }
        else {
            throw new IllegalArgumentException(amount + " not allowed as an argument");
        }
    }

    void brake(double amount){
        if(amount>=0 && amount<= 1){
            decrementSpeed(amount);
        }
        else {
            throw new IllegalArgumentException(amount + " not allowed as an argument");
        }
    }

    //Movement
    @Override
    public void move(){
        if(currentSpeed > 0){
            double newX = currentSpeed * direction.getX() + position.getX();
            double newY = currentSpeed * direction.getY() + position.getY();
            position.setLocation(newX, newY);}
    }
    @Override
    public void turnLeft(){
        currentDegree += handling;
        direction.setLocation(Math.round(Math.cos(Math.toRadians(currentDegree))),
                Math.round(Math.sin(Math.toRadians(currentDegree))));
    }
    @Override
    public void turnRight() {
        currentDegree -= handling;
        direction.setLocation(Math.round(Math.cos(Math.toRadians(currentDegree))),
                Math.round(Math.sin(Math.toRadians(currentDegree))));
    }


}




