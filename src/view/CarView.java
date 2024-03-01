package view;

import model.Model;
import model.ModelUpdateListener;
import model.objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import model.*;


public class CarView extends JPanel implements ModelUpdateListener {
    BufferedImages images;

    private Model model; //johannes sa vi skulle göra såhär
    //Defensive copying later
    public CarView(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    public void addModel(Model model, BufferedImages images) {
        this.images = images;
        this.model = model;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Component c : getComponents()) {
            c.paint(g);
        }

    }

    @Override
    public void actOnModelUpdate() {
        removeAll();
        for(Car car: model.cars){
            if (car instanceof Volvo240) {
                DrawableCar drawableCar = new DrawableCar(car, images.getVolvoImage());
                add(drawableCar);
            }
            else if (car instanceof Saab95){
                DrawableCar drawableCar = new DrawableCar(car, images.getSaabImage());
                add(drawableCar);
            }
            else if (car instanceof Scania){
                DrawableCar drawableCar = new DrawableCar(car, images.getScaniaImage());
                add(drawableCar);
            }
        }
        for(RepairShop repairShop: model.repairShops){
            add(new DrawableRepairShop(repairShop, images.getVolvoWorkshopImage()));
        }
        repaint();
    }

}
