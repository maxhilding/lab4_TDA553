package model;

import controller.CarFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

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
