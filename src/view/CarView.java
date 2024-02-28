package view;

import model.Model;
import model.ModelUpdateListener;
import model.objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class CarView extends JPanel implements ModelUpdateListener {
    BufferedImages images;
    //Defensive copying later
    public ArrayList<DrawableCar> carList = new ArrayList<>();
    public ArrayList<DrawableRepairShop> repairShopList = new ArrayList<>();
    public CarView(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    public ArrayList<DrawableCar> getCars(){
        return carList;
    }

    public ArrayList<DrawableRepairShop> getRepairShops() {return repairShopList;}

    public void addModel(Model model, BufferedImages images) {
        this.images = images;
        for (Iterator<ICar> it = model.getCars(); it.hasNext(); ) {
            ICar car = it.next();
            actOnCarAdded(car);
        }
        for (Iterator<RepairShop<ICar>> it = model.getRepairShops(); it.hasNext();) {
            RepairShop<ICar> repairShop =it.next();
            actOnAddedRepairShop(repairShop, images);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Component c : getComponents()) {
            c.paint(g);
        }
    }

    @Override
    public void actOnModelUpdate() {
        repaint();
    }

    public void actOnCarAdded(ICar car) {
        if (car instanceof Volvo240) {
            DrawableCar drawableCar = new DrawableCar(car, images.getVolvoImage());
            add(drawableCar);
            carList.add(drawableCar);
        }
        else if (car instanceof Saab95){
            DrawableCar drawableCar = new DrawableCar(car, images.getSaabImage());
            add(drawableCar);
            carList.add(drawableCar);

        }
        else if (car instanceof Scania){
            DrawableCar drawableCar = new DrawableCar(car, images.getScaniaImage());
            add(drawableCar);
            carList.add(drawableCar);

        }
    }

    public void actOnCarRemoved(ICar car){
        for (DrawableCar drawableCar : carList){
            if (drawableCar.wrappedCar == car) {
                drawableCar.setPosition(-drawableCar.getWidth(), -drawableCar.getHeight());
                carList.remove(drawableCar);
            }
        }
    }

    private void actOnAddedRepairShop(RepairShop<ICar> repairShop, BufferedImages images) {
        DrawableRepairShop drawableRepairShop = new DrawableRepairShop(repairShop, images.getVolvoWorkshopImage());
        add(drawableRepairShop);
        repairShopList.add(drawableRepairShop);
    }

}
