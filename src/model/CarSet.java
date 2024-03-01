package model;
import model.objects.*;

import java.util.*;

public class CarSet implements Iterable<Car>{
    private final List<Car> cars = new ArrayList<>();
    public void addCar(Car c){
        cars.add(c);
    }

    public void removeCar(Car c){cars.remove(c);}

    public void move(){
        for (Car car : cars) {
            car.move();
        }
    };

    @Override
    public Iterator<Car> iterator() {
        return cars.iterator();
    }

    // Controller calls these
    public void gas(double amount) {
        for (Car car : cars) {
            car.gas(amount);
        }
    }

    public void brake(double amount){
        for (Car car : cars) {
            car.brake(amount);
        }
    }

    public void turboOn(){
        for (Car car : cars) {
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }

    public void turboOff(){
        for (Car car : cars) {
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }

    public void raiseBed(){
        for (Car car : cars) {
            if(car instanceof Scania){
                ((Scania) car).raiseBed();
            }
        }
    }

    public void lowerBed(){
        for (Car car : cars) {
            if(car instanceof Scania){
                ((Scania) car).lowerBed();
            }
        }
    }
    public void startAllCars() {
        for (Car car : cars) {
            car.startEngine();
        }
    }
    public void stopAllCars() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    public int size(){
        return cars.size();
    }

    public Car remove(int i){
        Car removedCar = cars.get(i);
        cars.remove(i);
        return removedCar;
    }


}
