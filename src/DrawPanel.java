import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;



// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    BufferedImage volvoWorkshopImage;

    ArrayList<MoveableGameObject> cars;

    ArrayList<RepairShopGameObject> repairshops;


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<MoveableGameObject> cars, ArrayList<RepairShopGameObject> repairshops) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.cars = cars;
        this.repairshops = repairshops;
        }


    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(MoveableGameObject car: cars){
            g.drawImage(car.getImage(), (int) car.getX(), (int) car.getY(), null);
        }
        for(RepairShopGameObject repairshop: repairshops){
            g.drawImage(repairshop.getImage(), (int) repairshop.getX(), (int) repairshop.getY(), null);
    }}
}

