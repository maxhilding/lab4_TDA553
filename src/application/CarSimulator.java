package application;

import model.Model;
import controller.CarFrame;
import model.objects.Factory;
import view.BufferedImages;
import view.CarView;
import view.DrawableCar;
import controller.*;

import java.util.ArrayList;

public class CarSimulator {
    public static void main(String[] args) {
        Model model = initModel();
        CarView view = initViewForModel(model);
        CarFrame frame  = initUIForView(view);
        CarController controller = new CarController(model, frame);
        //model.animate();

        
        try {
            while (true) {
                Thread.sleep(10);
                update(model, view);
            }
        } catch (InterruptedException e) {}

    }

    private static void update(Model model, CarView view) {
        model.move();
        detectEdgeCollision(view);
        //detectLoad(model);

        model.actOnModelUpdate();
    }

    private static void detectEdgeCollision(CarView view) {
        ArrayList<DrawableCar> cars = view.getCars();
        for (DrawableCar car: cars) {

            int width = car.getWidth();
            int height = car.getHeight();

            int x0 = (int) Math.round(car.getPosition().getX());
            int y0 = (int) Math.round(car.getPosition().getY());
            if(x0 + width > view.getWidth() || x0 < 0) {
                car.invertX();
            }
            if(y0 + height > view.getHeight() || y0 < 0) {
                car.invertY();
            }
        }
    }

    public static Model initModel(){
        Model model = new Model();

        model.addCar(Factory.createVolvo240(0, 100));
        model.addCar(Factory.createSaab95(0, 300));
        model.addCar(Factory.createScania(0, 200));
        model.addRepairShop(Factory.createRepairShop(4, "VolvoRepairShop", 300, 100));

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

}
