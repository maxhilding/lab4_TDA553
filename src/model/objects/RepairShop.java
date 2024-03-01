package model.objects;

import java.awt.geom.Point2D;
import java.util.Stack;


public class RepairShop<T extends ICar> implements IRepairShop, ILoadable<T>{

    private final int capacity;

    private final String repairShopName;

    private Point2D.Double position;

    private Stack<T> loadedCars;

    private String type;


    RepairShop(int maxLoad, String repairShopName, double x, double y, String type) {
        capacity = maxLoad;
        loadedCars = new Stack<>();
        this.repairShopName = repairShopName;
        this.position = new Point2D.Double(x, y);
        this.type = type;
    }



    public String getModelName(){
        return type;
    }

    public Point2D.Double getPosition(){
        return new Point2D.Double(position.getX(), position.getY());
    }
    public void setPosition(double newX, double newY){
        position.setLocation(newX, newY);
    }
    public int getCapacity() {
        return capacity;
    }

    public String getRepairShopName() {
        return repairShopName;
    }


    public T unload() {
        T car = loadedCars.pop();
        car.stopEngine();
        return car;
    }

    public void load(T car) {
        if (loadedCars.size() < capacity) {
            loadedCars.push(car);
            car.stopEngine();
            car.setIsUnDriveable();
        }
        else {
            throw new RuntimeException("The repairshop is full"); //throwing 'wrong' exception here hehe

        }
    }

    public boolean repairShopFull() {
        return loadedCars.size() >= capacity;
    }
}
