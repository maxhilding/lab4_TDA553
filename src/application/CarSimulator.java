package application;

import model.Model;
import controller.CarFrame;
import model.objects.Factory;
import model.objects.Car;

import view.BufferedImages;
import view.CarView;
import view.DrawableCar;
import controller.*;
import view.DrawableRepairShop;
import model.objects.RepairShop;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class CarSimulator {
    public static void main(String[] args) {
        int x = 800;
        int y = 560;

        Model model = initModel();
        model.setSize(x ,y);
        CarView view = initViewForModel(model, x, y);
        CarFrame frame = initUIForView(view);
        CarController controller = new CarController(model, frame);
        model.run();
    }

    public static Model initModel() {
        Model model = new Model();

        model.addCar(Factory.createVolvo240(0, 300));
        model.addCar(Factory.createSaab95(0, 100));
        model.addCar(Factory.createScania(0, 200));
        model.addRepairShop(Factory.createVolvoRepairShop(4, "VolvoRepairShop", 300, 100));

        return model;
    }

    private static CarView initViewForModel(Model model, int x, int y) {
        CarView view = new CarView(x, y);
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


