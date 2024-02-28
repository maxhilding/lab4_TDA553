package application;

import model.Model;
import controller.CarFrame;
import model.objects.Factory;
import model.objects.ICar;
import model.objects.Volvo240;
import view.BufferedImages;
import view.CarView;
import view.DrawableCar;
import controller.*;
import view.DrawableRepairShop;
import model.objects.RepairShop;
import java.util.ArrayList;
import java.util.Objects;

public class CarSimulator {
    public static void main(String[] args) {
        Model model = initModel();
        CarView view = initViewForModel(model);
        CarFrame frame  = initUIForView(view);
        CarController controller = new CarController(model, frame);
        
        try {
            while (true) {
                Thread.sleep(10);
                update(model, view);
            }
        } catch (InterruptedException e) {}

    }

    public static Model initModel(){
        Model model = new Model();

        model.addCar(Factory.createVolvo240(0, 100));
        model.addCar(Factory.createSaab95(0, 300));
        model.addCar(Factory.createScania(0, 200));
        model.addRepairShop(Factory.createVolvoRepairShop(4, "VolvoRepairShop", 300, 100));

        return model;
    }

    private static CarView initViewForModel(Model model) {
        CarView view = new CarView(800, 560);
        BufferedImages images = new BufferedImages();
        view.addModel(model, images);
        model.addListener(view);
        return view;
    }

    private static CarFrame initUIForView(CarView view) {
        CarFrame frame = new CarFrame("CarSim");
        frame.add(view);
        frame.finishSetUp();
        return frame;
    }

    private static void update(Model model, CarView view) {
        model.move();
        detectCollisions(view, model);
        model.actOnModelUpdate();
    }


    private static void detectCollisions(CarView view, Model model) {
        ArrayList<DrawableCar> loadedCars = new ArrayList<>();
        ArrayList<DrawableCar> cars = view.getCars();
        ArrayList<DrawableRepairShop> repairShops = view.getRepairShops();
        for (DrawableCar car: cars) {
            detectEdgeCollision(car, view);
            detectRepairShopCollision(car, repairShops, loadedCars);
        }

        for(DrawableCar lCar: loadedCars){
            cars.remove(lCar);
            model.removeCar(lCar.wrappedCar);
        }
    }
    private static void detectEdgeCollision(DrawableCar car, CarView view) {
        int width = car.getWidth();
        int height = car.getHeight();

        int x = (int) Math.round(car.getPosition().getX());
        int y = (int) Math.round(car.getPosition().getY());
        if(x + width > view.getWidth() || x < 0) {
            car.invertX();
        }
        if(y + height > view.getHeight() || y < 0) {
            car.invertY();
            }
        }

    
    private static void detectRepairShopCollision(DrawableCar car, ArrayList<DrawableRepairShop> repairShops, ArrayList<DrawableCar> loadedCars) {
        for (DrawableRepairShop repairShop : repairShops) {
            if(Objects.equals(car.getModelName(), repairShop.getModelName())){
                int width = repairShop.getWidth();
                int height = repairShop.getHeight();

                double carX = car.getPosition().getX();
                double carY = car.getPosition().getY();

                double repairShopX = repairShop.getPosition().getX();
                double repairShopY = repairShop.getPosition().getY();

                //From left
                if (Math.abs(carX + width - repairShopX) < 5) {
                    repairShop.load(car);
                    car.setPosition(repairShop.getPosition().getX(), repairShop.getPosition().getY()+ height - car.getHeight());
                    loadedCars.add(car);
                }
                // From right
                else if (Math.abs((carX - repairShopX + width)) < 5) {
                    repairShop.load(car);
                    car.setPosition(repairShop.getPosition().getX(), repairShop.getPosition().getY()+ height - car.getHeight());
                    loadedCars.add(car);
                }
                //From bottom
                else if (Math.abs(carY - repairShopY + height) < 5) {
                    repairShop.load(car);
                    car.setPosition(repairShop.getPosition().getX(), repairShop.getPosition().getY()+ height - car.getHeight());
                    loadedCars.add(car);
                }
                //From top
                else if (Math.abs((carY + height - repairShopY)) < 5) {
                    repairShop.load(car);
                    car.setPosition(repairShop.getPosition().getX(), repairShop.getPosition().getY()+ height - car.getHeight());
                    loadedCars.add(car);
                }

            }
        }
    }
}



