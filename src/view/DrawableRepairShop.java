package view;

import model.objects.ICar;
import model.objects.RepairShop;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawableRepairShop extends Component {
    private final RepairShop<ICar> wrappedRepairShop;

    private final BufferedImage image;

    public DrawableRepairShop(RepairShop<ICar> wrappedRepairShop, BufferedImage image) {
        this.wrappedRepairShop = wrappedRepairShop;
        this.image = image;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, (int) wrappedRepairShop.getPosition().getX(), (int) wrappedRepairShop.getPosition().getY(), null);
    }

    public int getWidth(){
        return image.getWidth();
    }

    public int getHeight(){
        return image.getHeight();
    }
}
