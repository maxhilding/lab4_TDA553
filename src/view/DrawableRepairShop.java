package view;

import model.objects.ICar;
import model.objects.IRepairShop;
import model.objects.RepairShop;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class DrawableRepairShop extends Component implements IRepairShop {
    public final RepairShop<ICar> wrappedRepairShop;

    private final BufferedImage image;

    public DrawableRepairShop(RepairShop<ICar> wrappedRepairShop, BufferedImage image) {
        this.wrappedRepairShop = wrappedRepairShop;
        this.image = image;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, (int) wrappedRepairShop.getPosition().getX(), (int) wrappedRepairShop.getPosition().getY(), null);
    }

    public String getModelName(){
        return wrappedRepairShop.getModelName();
    }

    public int getWidth(){
        return image.getWidth();
    }

    public int getHeight(){
        return image.getHeight();
    }

    public String getRepairShopName(){
        return wrappedRepairShop.getRepairShopName();
    }

    public Point2D.Double getPosition(){
        return wrappedRepairShop.getPosition();
    }

    public int getCapacity(){
        return wrappedRepairShop.getCapacity();
    }

    public void load(ICar car){
        wrappedRepairShop.load(car);

    }

    public ICar unload(){
        return wrappedRepairShop.unload();
    }

    public boolean repairShopFull() {
        return wrappedRepairShop.repairShopFull();
    }
}
