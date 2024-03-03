package model;

import java.util.*;
import java.awt.*;


public class Model{

    private CarSet cars;
    private RepairShopSet repairShops;
    
    private int X = 2147483647;
    
    private int Y = 2147483647;


    public Model() {
        this.cars = new CarSet();
        this.repairShops = new RepairShopSet();
    }//constructor

    // All the public methods
    public void setWorldSize(int x, int y) {
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

    public void addRepairShop(RepairShop<? extends Car> repairShop){
        repairShops.addRepairShop(repairShop);
    }

    public void addCar(Car car){
        cars.addCar(car);
    }

    public void removeCar(Car car){
        cars.removeCar(car);
    }

    public void addSizes(Map<String, Point> sizes){
        cars.addSizes(sizes);
        repairShops.addSizes(sizes);
    }

    // Observer pattern
    public void addListener(ModelUpdateListener l){
        listeners.add(l);
    }

    // Rest of the methods are private or package private
    private final ArrayList<ModelUpdateListener> listeners = new ArrayList<>();

    private void notifyListeners(ArrayList<Presentable> presentables){
        for (ModelUpdateListener l : listeners)
            l.actOnModelUpdate(presentables);
    }


    // Controller calls these
    void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        cars.gas(gasAmount);
    }

    void brake(int amount){
        double brakeAmount = ((double) amount) / 100;
        cars.brake(brakeAmount);
    }

    void turboOn(){
        cars.turboOn();
    }

    void turboOff(){
        cars.turboOff();
    }

    void raiseBed(){
        cars.raiseBed();
    }

    void lowerBed(){
        cars.lowerBed();
    }
    void startAllCars() {
        cars.startAllCars();
    }

    void stopAllCars() {
        cars.stopAllCars();
    }


    void addACar() {
        if (cars.size() < 10){
        Car volvo = Factory.createVolvo240(0,100);
        cars.addCar(volvo);
        }
    }

    void removeACar() {
        Random r = new Random();
        int low = 0;
        int high = cars.size();
        int result = r.nextInt(high-low) + low;
        Car removedCar = cars.remove(result);
    }

    // Game logic
    void move(){
        cars.move();
    }

    private void update() {
        move();
        detectCollisions();
        ArrayList<Presentable> presentables = createPresentables();
        notifyListeners(presentables);
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
        if(car.hasDefinedSize()){
            if ((x+car.getWidth()) > this.X || x < 0) {
                car.invertX();
            }
            if ((y + car.getLength()) > this.Y|| y < 0) {
                car.invertY();
            }
        }
        else {
            if (x > this.X || x < 0) {
                car.invertX();
            }
            if (y > this.Y|| y < 0) {
                car.invertY();
            }
        }
    }




    private void detectRepairShopCollision(Car car, ArrayList<Car> loadedCars) {
        for (RepairShop repairshop : repairShops) {
            if ((Objects.equals(car.getModelName(), repairshop.getModelName())
                    || Objects.equals(repairshop.getModelName(), "Car"))
                    && !repairshop.getIsRepairShopFull()) {

                double carX = car.getPosition().getX();
                double carY = car.getPosition().getY();
                double repairshopX = repairshop.getPosition().getX();
                double repairshopY = repairshop.getPosition().getY();

                if(car.hasDefinedSize() && repairshop.hasDefinedSize()){
                    if (detectObjectCollisionWithRectangles(carX, carY, car.getWidth(), car.getLength(), repairshopX, repairshopY, repairshop.getWidth(), repairshop.getLength())) {
                        repairshop.load(car);
                        loadedCars.add(car);
                    }
                }
                else {
                    if (detectObjectCollisionWithCircles(carX, carY, repairshopX, repairshopY)) {
                        repairshop.load(car);
                        loadedCars.add(car);
                    }
                }
            }
        }
    }

    private static boolean detectObjectCollisionWithRectangles(double carX, double carY, double carWidth, double carHeight, double rX, double rY, double rWidth, double rHeight) {
        Rectangle r1 = new Rectangle((int) carX, (int) carY, (int) carWidth, (int) carHeight);
        Rectangle r2 = new Rectangle((int) rX, (int) rY, (int) rWidth, (int) rHeight);
        return r1.intersects(r2);
    }

    private static boolean detectObjectCollisionWithCircles(double carX, double carY, double repairShopX, double repairShopY){
        //If circles intersect
        return circleIntersects((int)carX, (int)carY, (int)repairShopX, (int)repairShopY, 20, 20);
    }
    private static boolean circleIntersects(int x1, int y1, int x2, int y2,
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


    private ArrayList<Presentable> createPresentables(){
        ArrayList<Presentable> presentables = new ArrayList<>();
        for(Car car: cars){
            presentables.add(new PresentableCar(car));
        }
        for(RepairShop<? extends Car> repairshop: repairShops){
            presentables.add(new PresentableRepairShop(repairshop));
        }
        return presentables;
    }
}
