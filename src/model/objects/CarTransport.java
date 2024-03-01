package model.objects;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Stack;

public class CarTransport implements ITruck, ILoadable<ICar>{

    private boolean bedIsUp = true;
    private Stack<ICar> loadedCars;

    private final BaseCar wrappedCar;

    private final int maxNumberOfLoadedCars;

    CarTransport(int maxLoad, String name, double x, double y) {
        wrappedCar = new BaseCar(2, 800, Color.green, name, x, y); //a specific type of car transport, like Saab95
        loadedCars = new Stack<>();
        maxNumberOfLoadedCars = maxLoad;
        raiseBed();
    }

    // Car Transport Specific
    public boolean getBedIsUp(){
        return bedIsUp;
    }

    /*
    assumes that you can't load Car Transport with trucks, and that those are the types of cars
    too big to be loaded
     */
    public void load(ICar car){
        if (!bedIsUp && !(car instanceof ITruck) &&
                (maxNumberOfLoadedCars>loadedCars.size()) &&
                (car.getPosition().distance(this.getPosition()) < 2)) {
            loadedCars.push(car);
            car.stopEngine();
            car.setPosition(wrappedCar.getPosition().getX(), wrappedCar.getPosition().getY());
        }
        else {
            throw new RuntimeException("Can't load car");
        }

    }


    public ICar unload(){
        if (!bedIsUp){
            ICar releasedCar = loadedCars.pop();
            releasedCar.setPosition(wrappedCar.getPosition().getX()-1, wrappedCar.getPosition().getY()-1);
            releasedCar.stopEngine();
            return releasedCar;
        }
        else{
            throw new RuntimeException("Can't unload car");
        }
    }

    @Override
    public void gas(double amount){
        if (bedIsUp) {
            wrappedCar.gas(amount);
        }
    }
    @Override
    public void move() {
        double newX = getCurrentSpeed() * wrappedCar.getDirection().getX();
        double newY = getCurrentSpeed() * wrappedCar.getDirection().getY();
        wrappedCar.setPosition(newX, newY);

        for (ICar nextCar : loadedCars) {
            nextCar.setPosition(newX, newY);
        }
    }

    // Truck specific
    public void raiseBed(){
        if(wrappedCar.getCurrentSpeed()==0){
            bedIsUp = true;
        }
    }

    public void lowerBed(){
        if (wrappedCar.getCurrentSpeed()==0) {
            bedIsUp=false;
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

    public boolean getIsDriveable(){return wrappedCar.getIsDriveable();}

    public void setIsUnDriveable(){
        wrappedCar.setIsUnDriveable();
    }

    public void setIsDriveable(){
        wrappedCar.setIsDriveable();
    }

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
    public void decrementSpeed(double amount){wrappedCar.decrementSpeed(amount);}


    public void brake(double amount){wrappedCar.brake(amount);}

    public double getCurrentSpeed(){return wrappedCar.getCurrentSpeed();}

    // Position
    public Point2D.Double getPosition(){return wrappedCar.getPosition();}

    public void setPosition(double newX, double newY){wrappedCar.setPosition(newX, newY);}

    // Direction
    public Point2D.Double getDirection(){ return wrappedCar.getDirection();}

    public void setDirection(double newX, double newY){wrappedCar.setDirection(newX, newY);}

    // Movement

    public void turnLeft() {
        wrappedCar.turnLeft();
    }

    public void turnRight() {
        wrappedCar.turnRight();
    }

}
