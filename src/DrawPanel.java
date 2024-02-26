import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;



// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;

    BufferedImage saabImage;

    BufferedImage scaniaImage;

    // To keep track of a single car's position
    //Point volvoPoint = new Point(0, 100);

    BufferedImage volvoWorkshopImage;

    RepairShop<Volvo240> volvoWorkshop;

    ArrayList<Car> cars;


    // TODO: Make this general for all cars
    /*void moveit(int x, int y){
            saabPoint.x = x;
            saabPoint.y = y;

    }*/

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Car> cars, RepairShop<Volvo240> volvoRepairShop) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        this.cars = cars;
        volvoWorkshop = volvoRepairShop;
        }


    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Car car: cars){
            if (car instanceof Volvo240) {
                g.drawImage(volvoImage, (int) car.getPosition().getX(), (int) car.getPosition().getY(), null);
            }
            else if (car instanceof Saab95) {
                g.drawImage(saabImage, (int) car.getPosition().getX(), (int) car.getPosition().getY(), null);
            }
            else if(car instanceof Scania){
                g.drawImage(scaniaImage, (int) car.getPosition().getX(), (int) car.getPosition().getY(), null);
            }
        }
        g.drawImage(volvoWorkshopImage, (int) volvoWorkshop.getPosition().getX(), (int) volvoWorkshop.getPosition().getY(), null);
    }
}

