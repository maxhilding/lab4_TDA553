package model;

import model.objects.*;

import java.util.*;

public class Model implements ModelUpdateListener{

    private final CarSet cars;
    private final RepairShopSet repairShops;


    public Model() {
        this.cars = new CarSet();
        this.repairShops = new RepairShopSet();
    }//constructor

    private final List<ModelUpdateListener> listeners = new ArrayList<>();

    public void addListener(ModelUpdateListener l){
        listeners.add(l);
    }

    public void actOnModelUpdate(){
        for (ModelUpdateListener l : listeners)
            l.actOnModelUpdate();
    }

    public void move(){
        cars.move();
    }


    public void addRepairShop(RepairShop repairShop){
        repairShops.addRepairShop(repairShop);
    }

    public void addCar(ICar car){
        cars.addCar(car);
    }

    public void removeCar(ICar car){
        cars.removeCar(car);
    }

    public Iterator<ICar> getCars(){
        return cars.iterator();
    }
    
    public Iterator<RepairShop<ICar>> getRepairShops(){
        return repairShops.iterator();
    }

    // Controller calls these
    public void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        cars.gas(gasAmount);
    }

    //la till brake
    public void brake(int amount){
        double brakeAmount = ((double) amount) / 100;
        cars.brake(brakeAmount);
    }

    public void turboOn(){
        cars.turboOn();
    }

    public void turboOff(){
        cars.turboOff();
    }

    public void raiseBed(){
        cars.raiseBed();
    }

    public void lowerBed(){
        cars.lowerBed();
    }
    public void startAllCars() {
        cars.startAllCars();
    }
    public void stopAllCars() {
        cars.stopAllCars();
    }


    //chain of responsibility
    public void addACar() {
        if (cars.size() < 10){
        ICar volvo = Factory.createVolvo240(0,100);
        cars.addCar(volvo);
        actOnCarAdded(volvo); //right here
    }}


    //chain of responsibility here aswell
    public void removeACar() {
        Random r = new Random();
        int low = 0;
        int high = cars.size();
        int result = r.nextInt(high-low) + low;
        ICar removedCar = cars.remove(result);
        actOnCarRemoved(removedCar);
    }

    public void actOnCarAdded(ICar car){
        for (ModelUpdateListener l : listeners)
            l.actOnCarAdded(car);
    }

    public void actOnCarRemoved(ICar car){
        for (ModelUpdateListener l : listeners)
            l.actOnCarRemoved(car);
    }

}
