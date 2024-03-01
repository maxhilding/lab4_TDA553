package view;


import model.objects.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class DrawableRepairShop extends Component {
    private final RepairShop<Car> wrappedRepairShop;

    private final BufferedImage image;

    public DrawableRepairShop(RepairShop<Car> wrappedRepairShop, BufferedImage image) {
        this.wrappedRepairShop = wrappedRepairShop;
        this.image = image;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, (int) wrappedRepairShop.getPosition().getX(), (int) wrappedRepairShop.getPosition().getY(), null);
    }

}
