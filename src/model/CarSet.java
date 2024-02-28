package model;
import model.objects.ICar;
import model.objects.*;

import java.util.*;

class CarSet implements Iterable<ICar>{
    private final List<ICar> cars = new ArrayList<>();
    public void addCar(ICar c){
        cars.add(c);
    }

    public void removeCar(ICar c){cars.remove(c);}

    public void move(){
        for (ICar car : cars) {
            car.move();
        }
    };

    @Override
    public Iterator<ICar> iterator() {
        return cars.iterator();
    }

    // Controller calls these
    public void gas(double amount) {
        for (ICar car : cars) {
            car.gas(amount);
        }
    }

    public void brake(double amount){
        for (ICar car : cars) {
            car.brake(amount);
        }
    }

    public void turboOn(){
        for (ICar car : cars) {
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }

    public void turboOff(){
        for (ICar car : cars) {
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }

    public void raiseBed(){
        for (ICar car : cars) {
            if(car instanceof Scania){
                ((Scania) car).raiseBed();
            }
        }
    }

    public void lowerBed(){
        for (ICar car : cars) {
            if(car instanceof Scania){
                ((Scania) car).lowerBed();
            }
        }
    }
    public void startAllCars() {
        for (ICar car : cars) {
            car.startEngine();
        }
    }
    public void stopAllCars() {
        for (ICar car : cars) {
            car.stopEngine();
        }
    }

    public int size(){
        return cars.size();
    }

    public ICar remove(int i){
        ICar removedCar = cars.get(i);
        cars.remove(i);
        return removedCar;
    }
}
