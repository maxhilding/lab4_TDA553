package application;

import model.Model;
import controller.CarFrame;
import model.Factory;
import model.ModelCallHandler;
import view.BufferedImages;
import view.CarView;
import controller.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CarSimulator {
    public static void main(String[] args) {
        int x = 800;
        int y = 560;

        Model model = initModel();
        model.setWorldSize(x ,y);
        CarView view = initViewForModel(model, x, y);
        if(view.hasImages()){
            provideModelWithSizes(model,view.getImages());
        }
        CarFrame frame = initUIForView(view);
        ModelCallHandler callHandler = new ModelCallHandler(model);
        CarController controller = new CarController(callHandler, frame);
        model.run();
    }

    public static Model initModel() {
        Model model = new Model();
        
        model.addCar(Factory.createVolvo240(0, 100));
        model.addCar(Factory.createSaab95(0, 300));
        model.addCar(Factory.createScania(0, 200));
        model.addRepairShop(Factory.createVolvoRepairShop(4, "VolvoRepairShop", 500, 100));
        model.addRepairShop(Factory.createSaabRepairShop(5, "VolvoRepairShop", 500, 300));
        model.addRepairShop(Factory.createGenericRepairShop(5, "VolvoRepairShop", 500, 200));
        return model;
    }

    private static CarView initViewForModel(Model model, int x, int y) {
        CarView view = new CarView(x, y);
        view.addImages(new BufferedImages());
        model.addListener(view);
        return view;
    }

    private static CarFrame initUIForView(CarView view) {
        CarFrame frame = new CarFrame("CarSim");
        frame.add(view);
        frame.finishSetUp();
        return frame;
    }

    private static void provideModelWithSizes(Model model, BufferedImages images){
        Map<String, Point> sizes = new HashMap<>();
        // can make this more extensible in the future by looping through images and assigning their original filename as key
        sizes.put("Volvo240", new Point(images.getVolvoImage().getWidth(), images.getVolvoImage().getHeight()));
        sizes.put("Saab95", new Point(images.getSaabImage().getWidth(), images.getSaabImage().getHeight()));
        sizes.put("Scania", new Point(images.getScaniaImage().getWidth(), images.getScaniaImage().getHeight()));
        sizes.put("VolvoRepairShop", new Point(images.getVolvoWorkshopImage().getWidth(), images.getVolvoWorkshopImage().getHeight()));
        model.addSizes(sizes);
    }

}


