package model.objects;

import java.awt.*;
import java.util.*;
public class CarTransport extends Truck implements Loadable<Car> {
    private boolean bedIsUp = true;
    private Stack<Car> loadedCars;
    
    private final int capacity;

    CarTransport(double x, double y, int maxLoad, String name) {
        super(x, y, 2, 800, Color.green, name); //a specific type of car transport, like Saab95
        loadedCars = new Stack<Car>();
        capacity = maxLoad;
        raiseBed();
    }

    //Getters
    public boolean getBedIsUp(){
        return bedIsUp;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getNumberOfLoaded(){
        return loadedCars.size();
    }

    public void raiseBed(){
        if(getCurrentSpeed()==0){
            bedIsUp = true;
        }
    }

    public void lowerBed(){
        if (getCurrentSpeed()==0) {
            bedIsUp=false;
        }
    }

    @Override
    public void gas(double amount){
        if (bedIsUp) {
            super.gas(amount);
        }
    }
    /*
    assumes that you can't load Car Transport with trucks, and that those are the types of cars
    too big to be loaded
     */
    @Override
    public void load(Car car){
        if (!bedIsUp && !(car instanceof Truck) &&
                (capacity>loadedCars.size()) &&
                (car.getPosition().distance(this.getPosition()) < 2)) {
            loadedCars.push(car);
            car.setPosition(this.getPosition().getX(), this.getPosition().getY());
        }
        else {
                throw new RuntimeException("Can't load car");
            }

    }

    @Override
    public Car unload(){
        if (!bedIsUp){
            Car releasedCar = loadedCars.pop();
            releasedCar.setPosition(this.getPosition().getX()-1, this.getPosition().getY()-1);
            return releasedCar;
        }
        else{
            throw new RuntimeException("Can't unload car");
        }
    }

    @Override
    public void move() {
        double newX = getCurrentSpeed() * getDirection().getX();
        double newY = getCurrentSpeed() * getDirection().getY();
        this.setPosition(newX, newY);

        for (Car nextCar : loadedCars) {
            nextCar.setPosition(newX, newY);
        }
    }

}
