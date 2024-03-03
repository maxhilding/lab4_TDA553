package model;

public class ModelCallHandler {

    private Model model;

    public ModelCallHandler(Model model) {
        this.model = model;
    }

    // Controller calls these
    public void gas(int amount) {
        model.gas(amount);
    }

    //la till brake
    public void brake(int amount){
        model.brake(amount);
    }

    public void turboOn(){
        model.turboOn();
    }

    public void turboOff(){
        model.turboOff();
    }

    public void raiseBed(){
        model.raiseBed();
    }

    public void lowerBed(){
        model.lowerBed();
    }
    public void startAllCars() {
        model.startAllCars();
    }
    public void stopAllCars() {
        model.stopAllCars();
    }

    public void addACar() {
        model.addACar();
    }

    public void removeACar() {
        model.removeACar();
    }
}
