import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    ArrayList<MoveableGameObject> cars = new ArrayList<>();
    ArrayList<RepairShopGameObject> repairshops = new ArrayList<>();

    int x_border = 0;
    int y_border = 0;


    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        Car myVolvo = new Volvo240();
        Car mySaab = new Saab95();
        mySaab.setPosition(0, 100);
        Car myScania = new Scania();
        myScania.setPosition(0, 200);
        RepairShop<Volvo240> volvo240Repairshop = new RepairShop<>(5, "VolvoRepair");
        volvo240Repairshop.setPosition(300.0, 0.0);

        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            BufferedImage volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            BufferedImage saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            BufferedImage scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            BufferedImage volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));

            cc.cars.add(new MoveableGameObject(volvoImage, myVolvo));
            cc.cars.add(new MoveableGameObject(saabImage, mySaab));
            cc.cars.add(new MoveableGameObject(scaniaImage, myScania));
            cc.repairshops.add(new RepairShopGameObject(volvoWorkshopImage, volvo240Repairshop));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);
        cc.x_border = cc.frame.getPanelXBorder();
        cc.y_border = cc.frame.getPanelYBorder();
        cc.setUpButtons();
        // Start the timer
        cc.timer.start();
    }
    private void setUpButtons(){

        frame.gasButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            gas(frame.gasAmount);
        }
        });

        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                brake(frame.gasAmount);
            }
        });

        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                turboOn();
            }
        });

        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                turboOff();
            }
        });


        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                liftBed();
            }
        });

        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                lowerBed();
            }
        });

        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                startAllCars();
            }
        });

        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                stopAllCars();
            }
        });}

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<MoveableGameObject> loaded_cars = new ArrayList<>();
            for (MoveableGameObject car : cars) {
                car.move();
                // edge collision
                detectEdgeCollision(car);

                //repairshop collision
                for(RepairShopGameObject repairshop : repairshops){
                    if((Car) car.getParent() instanceof repairshop.getParent().getClass()){
                        detectRepairShopCollision(car, repairshop, loaded_cars);}
                }


                //frame.drawPanel.moveit(x1, y1);
               // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();

            }
            for(MoveableGameObject car: loaded_cars) {
                cars.remove(car);
            }
        }

        private void detectEdgeCollision(MoveableGameObject car) {
            int width = car.getWidth();
            int height = car.getHeight();

            int x0 = (int) Math.round(car.getX());
            int y0 = (int) Math.round(car.getY());
            if(x0 + width > x_border || x0 < 0) {
                car.setDirection(-car.getDirection().getX(), car.getDirection().getY());
            }
            if(y0 + height > y_border || y0 < 0) {
                car.setDirection(car.getDirection().getX(), -car.getDirection().getY());
            }
        }

        private void detectRepairShopCollision(MoveableGameObject car, RepairShopGameObject repairshop, ArrayList<MoveableGameObject> loaded_cars) {
            int width = repairshop.getWidth();
            int height = repairshop.getHeight();
            //From left
            if (abs(((car.getX() + width)- repairshop.getX())) < 5){
                repairshop.loadOn((Car) car.getParent());
                loaded_cars.add(car);
            }
            // From right
            else if (abs(((car.getX())- repairshop.getX()+width)) < 5){
                repairshop.loadOn((Car) car.getParent());
                loaded_cars.add(car);
            }
            //From bottom
            else if (abs(((car.getY())- repairshop.getY()+height)) < 5){
                repairshop.loadOn((Car) car.getParent());
                loaded_cars.add(car);
            }
            //From top
            else if (abs(((car.getY()+ height)- repairshop.getY())) < 5){
                repairshop.loadOn((Car) car.getParent());
                loaded_cars.add(car);
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (MoveableGameObject car : cars
                ) {
            ((Car)car.getParent()).gas(gas);
        }
    }

    //la till brake
    void brake(int amount){
        double brake = ((double) amount) / 100;
        for (MoveableGameObject car : cars) {
            ((Car) car.getParent()).brake(brake);
        }
    }

    void turboOn(){
        for (MoveableGameObject carobject: cars) {
            Car car = (Car) carobject.getParent();
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }

    }

    void turboOff(){
        for (MoveableGameObject carobject: cars) {
            Car car = (Car) carobject.getParent();
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void liftBed(){
        for(MoveableGameObject car: cars){
            if(car.getParent() instanceof Scania){
                ((Scania) car.getParent()).raiseBed();
            }
        }
    }

    void lowerBed(){
        for(MoveableGameObject car: cars){
            if(car.getParent() instanceof Scania){
                ((Scania) car.getParent()).lowerBed();
            }
    }
    }
    void startAllCars() {
        for (MoveableGameObject car: cars) {
            ((Car)car.getParent()).startEngine();
        }
    }
    void stopAllCars() {
        for (MoveableGameObject car: cars) {
            ((Car)car.getParent()).stopEngine();
        }
    }
}
