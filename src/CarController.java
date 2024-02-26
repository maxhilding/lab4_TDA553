import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static java.lang.Math.abs;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    RepairShop<Volvo240> volvoRepairShop;


    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        Car myVolvo = new Volvo240();
        Car mySaab = new Saab95();
        mySaab.setPosition(0, 100);
        Car myScania = new Scania();
        myScania.setPosition(0, 200);
        cc.cars.add(myVolvo);
        cc.cars.add(mySaab);
        cc.cars.add(myScania);

        cc.volvoRepairShop = new RepairShop<>(5, "VolvoRepair");
        cc.volvoRepairShop.setPosition(300.0, 0.0);


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<Volvo240> loaded_cars = new ArrayList<>();
            for (Car car : cars) {
                car.move();
                // edge collision
                int x_border = frame.getPanelXBorder();
                int y_border = frame.getPanelYBorder();
                int width = 20;
                int height = 20;
                if(car instanceof Volvo240){
                    width = frame.drawPanel.volvoImage.getWidth();
                    height = frame.drawPanel.volvoImage.getHeight();
                }
                else if(car instanceof Saab95){
                    width = frame.drawPanel.saabImage.getWidth();
                    height = frame.drawPanel.saabImage.getHeight();
                }
                else if(car instanceof Scania){
                    width = frame.drawPanel.scaniaImage.getWidth();
                    height = frame.drawPanel.scaniaImage.getHeight();
                }

                int x0 = (int) Math.round(car.getPosition().getX());
                int y0 = (int) Math.round(car.getPosition().getY());
                if(x0 + width > x_border || x0 < 0) {
                    car.setDirection(-car.getDirection().getX(), car.getDirection().getY());
                }
                if(y0 + height > y_border || y0 < 0) {
                    car.setDirection(car.getDirection().getX(), -car.getDirection().getY());
                }

                //repairshop collision

                if (car instanceof Volvo240) {
                    int workshopWidth = frame.drawPanel.volvoWorkshopImage.getWidth();
                    int workshopHeight = frame.drawPanel.volvoWorkshopImage.getHeight();
                    //From left
                    if (abs(((car.getPosition().getX() + width)- volvoRepairShop.getPosition().getX())) < 5){
                        volvoRepairShop.loadOn((Volvo240) (car));
                        loaded_cars.add((Volvo240) car);
                    }
                    // From right
                    else if (abs(((car.getPosition().getX())- volvoRepairShop.getPosition().getX()+workshopWidth)) < 5){
                        volvoRepairShop.loadOn((Volvo240) (car));
                        loaded_cars.add((Volvo240) car);
                    }
                    //From bottom
                    else if (abs(((car.getPosition().getY())- volvoRepairShop.getPosition().getY()+workshopHeight)) < 5){
                        volvoRepairShop.loadOn((Volvo240) (car));
                        loaded_cars.add((Volvo240) car);
                    }
                    //From top
                    else if (abs(((car.getPosition().getY()+height)- volvoRepairShop.getPosition().getY())) < 5){
                        volvoRepairShop.loadOn((Volvo240) (car));
                        loaded_cars.add((Volvo240) car);
                    }
                }


                //frame.drawPanel.moveit(x1, y1);
               // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();

            }
            for(Car car: loaded_cars) {
                cars.remove(car);
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars
                ) {
            car.gas(gas);
        }
    }

    //la till brake
    void brake(int amount){
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    void turboOn(){
        for (Car car: cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }

    }

    void turboOff(){
        for (Car car: cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void liftBed(){
        for(Car car: cars){
            if(car instanceof Scania){
                ((Scania) car).raiseBed();
            }
        }
    }

    void lowerBed(){
        for(Car car: cars){
            if(car instanceof Scania){
                ((Scania) car).lowerBed();
            }
    }
    }
    void startAllCars() {
        for (Car car: cars) {
            car.startEngine();
        }
    }
    void stopAllCars() {
        for (Car car: cars) {
            car.stopEngine();
        }
    }
}
