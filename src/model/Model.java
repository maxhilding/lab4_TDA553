package model;

import model.objects.*;
import view.CarView;
import view.DrawableCar;
import view.DrawableRepairShop;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Model{

    public CarSet cars;
    public static RepairShopSet repairShops;
    
    private int X = 2147483647;
    
    private int Y = 2147483647;


    public Model() {
        this.cars = new CarSet();
        this.repairShops = new RepairShopSet();
    }//constructor

    public void setSize(int x, int y) {
        X = x;
        Y = y;
    }
    public void run(){
        try {
            while (true) {
                Thread.sleep(10);
                update();
            }
        } catch (InterruptedException e) {
        }
    }

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

    public void addCar(Car car){
        cars.addCar(car);
    }

    public void removeCar(Car car){
        cars.removeCar(car);
    }

    public Iterator<Car> getCars(){
        return cars.iterator();
    }
    
    public Iterator<RepairShop<Car>> getRepairShops(){
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
        Car volvo = Factory.createVolvo240(0,100);
        cars.addCar(volvo);
    }}


    //chain of responsibility here aswell
    public void removeACar() {
        Random r = new Random();
        int low = 0;
        int high = cars.size();
        int result = r.nextInt(high-low) + low;
        Car removedCar = cars.remove(result);
    }

    private void update() {
        move();
        detectCollisions();
        actOnModelUpdate();
    }


    private void detectCollisions() {
        ArrayList<Car> loadedCars = new ArrayList<>();
        for (Car car : cars) {
            detectEdgeCollision(car);
            detectRepairShopCollision(car, loadedCars);
        }
        for (Car lCar : loadedCars) {
            cars.removeCar(lCar);
        }
    }

    private void detectEdgeCollision(Car car) {
        int x = (int) Math.round(car.getPosition().getX());
        int y = (int) Math.round(car.getPosition().getY());
        if (x > this.X || x < 0) {
            car.invertX();
        }
        if (y > this.Y|| y < 0) {
            car.invertY();
        }
    }


    private void detectRepairShopCollision(Car car, ArrayList<Car> loadedCars) {
        for (RepairShop repairShop : repairShops) {
            if ((Objects.equals(car.getModelName(), repairShop.getModelName()) || Objects.equals(repairShop.getModelName(), "Car")) && !repairShop.repairShopFull()) {

                double carX = car.getPosition().getX();
                double carY = car.getPosition().getY();
                double repairShopX = repairShop.getPosition().getX();
                double repairShopY = repairShop.getPosition().getY();

                if (detectObjectCollision(carX, carY, repairShopX, repairShopY)) {
                    repairShop.load(car);
                    loadedCars.add(car);
                }
            }
        }
    }
    public static boolean detectObjectCollision(double carX, double carY, double repairShopX, double repairShopY){
        //If circles intersect
        return circleIntersects((int)carX, (int)carY, (int)repairShopX, (int)repairShopY, 20, 20);
    }
    public static boolean circleIntersects(int x1, int y1, int x2, int y2,
           int r1, int r2)
    {
        double d = Math.sqrt((x1 - x2) * (x1 - x2)
                + (y1 - y2) * (y1 - y2));

        if ((d <= r1 - r2) || (d <= r2 - r1) || (d < r1 + r2) || (d == r1 + r2)) {
            return true;
        }
        else {
            return false;
        }

    }
}
