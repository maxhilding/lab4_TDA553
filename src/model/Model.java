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


    public void addRepairShop(RepairShop<ICar> repairShop){
        repairShops.addRepairShop(repairShop);
    }

    public void addCar(ICar car){
        cars.addCar(car);
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
        cars.turboOn();
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

}
