package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class CarView extends JPanel implements ModelUpdateListener {
    BufferedImages images;

    //Defensive copying later
    public CarView(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    public boolean hasImages(){
        return !(images == null);
    }

    public void addImages(BufferedImages images) {
        this.images = images;
    }

    public BufferedImages getImages() {
        return images;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Component c : getComponents()) {
            c.paint(g);
        }
    }

    @Override
    public void actOnModelUpdate(ArrayList<Presentable> presentables) {
        removeAll();
        addDrawables(presentables);
        repaint();

    }

    private void addDrawables(ArrayList<Presentable> presentables) {
        for(Presentable presentable: presentables){
            if(presentable instanceof PresentableCar){
                if (presentable.getModelName() == "Volvo240"){
                    add(new Drawable(presentable.getPosition(), images.volvoImage));
                }
                else if (presentable.getModelName() == "Saab95"){
                    add(new Drawable(presentable.getPosition(), images.saabImage));
                }
                else if (presentable.getModelName() == "Scania"){
                    add(new Drawable(presentable.getPosition(), images.scaniaImage));
                }
            }
            else if (presentable instanceof PresentableRepairShop){
                if(presentable.getModelName() == "Volvo240"){
                    add(new Drawable(presentable.getPosition(), images.volvoWorkshopImage));
                }
                else if (presentable.getModelName() == "Saab95"){
                    add(new Drawable(presentable.getPosition(), images.volvoWorkshopImage));
                }
                else if (presentable.getModelName() == "Car"){
                    add(new Drawable(presentable.getPosition(), images.volvoWorkshopImage));
                }
            }
        }

    }

}
