package model;

import java.awt.geom.Point2D;
import java.util.Stack;


class RepairShop<T extends Car> implements Loadable<T> {

    private final int capacity;

    private final String repairShopName;

    private Point2D.Double position;

    private Stack<T> loadedCars;

    private String type;

    private double width = 0;

    private double length = 0;

    private boolean hasDefinedSize = false;


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

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getNumberOfLoaded() {
        return loadedCars.size();
    }

    public String getRepairShopName() {
        return repairShopName;
    }

    public boolean getIsRepairShopFull() {
        return loadedCars.size() >= capacity;
    }

    public double getWidth() {
        return width;

    }

    public double getLength() {
        return length;
    }

    public boolean hasDefinedSize() {
        return hasDefinedSize;
    }

    void setHasDefinedSize(boolean hasDefinedSize) {
        this.hasDefinedSize = hasDefinedSize;
    }

    void setLength(int length) {
        this.length = length;
    }

    void setWidth(int width) {
        this.width = width;
    }

    @Override
    public T unload() {
        T car = loadedCars.pop();
        car.stopEngine();
        car.setIsDriveable();
        return car;
    }

    @Override
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

}

