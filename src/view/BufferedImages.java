package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImages {

    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    BufferedImage volvoWorkshopImage;
    public BufferedImages() {
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(CarView.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(CarView.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(CarView.class.getResourceAsStream("pics/Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(CarView.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public BufferedImage getVolvoImage(){
        return volvoImage;
    }

    public BufferedImage getSaabImage(){
        return saabImage;
    }

    public BufferedImage getScaniaImage(){
        return scaniaImage;
    }

    public BufferedImage getVolvoWorkshopImage(){
        return volvoWorkshopImage;
    }
}
