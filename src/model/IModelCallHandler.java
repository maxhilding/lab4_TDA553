package model;

public interface IModelCallHandler {

    // Controller calls these
    public void gas(int amount);

    //la till brake
    public void brake(int amount);

    public void turboOn();

    public void turboOff();

    public void raiseBed();

    public void lowerBed();
    public void startAllCars();
    public void stopAllCars();

    public void addACar();

    public void removeACar();
}
