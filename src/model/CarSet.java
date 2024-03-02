package model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

class CarSet implements Iterable<Car>{
    private final List<Car> cars = new ArrayList<>();

    void addCar(Car c){
        cars.add(c);
    }

    void removeCar(Car c){cars.remove(c);}

    void addSizes(Map<String, Point> sizes){
        for(Car car: cars){
            Point size = sizes.get(car.getModelName());
            if(!(size==null)){
                car.setHasDefinedSize(true);
                car.setWidth((int) size.getX());
                car.setLength((int) size.getY());
            }
        }
    }

    void move(){
        for (Car car : cars) {
            car.move();
        }
    };

    @Override
    public Iterator<Car> iterator() {
        return cars.iterator();
    }

    // Controller calls these
    void gas(double amount) {
        for (Car car : cars) {
            car.gas(amount);
        }
    }

    void brake(double amount){
        for (Car car : cars) {
            car.brake(amount);
        }
    }

    void turboOn(){
        for (Car car : cars) {
            if(car instanceof Turbo){
                ((Turbo) car).setTurboOn();
            }
        }
    }

    void turboOff(){
        for (Car car : cars) {
            if(car instanceof Turbo){
                ((Turbo) car).setTurboOff();
            }
        }
    }

    void raiseBed(){
        for (Car car : cars) {
            if(car instanceof Truck){
                ((Truck) car).raiseBed();
            }
        }
    }

    void lowerBed(){
        for (Car car : cars) {
            if(car instanceof Truck){
                ((Truck) car).lowerBed();
            }
        }
    }
    void startAllCars() {
        for (Car car : cars) {
            car.startEngine();
        }
    }
    void stopAllCars() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    int size(){
        return cars.size();
    }

    Car remove(int i){
        Car removedCar = cars.get(i);
        cars.remove(i);
        return removedCar;
    }


}
