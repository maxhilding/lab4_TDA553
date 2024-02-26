import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class RepairShopGameObject {
    private final GameObject gameObject;

    private final RepairShop parent;
    RepairShopGameObject(BufferedImage image, RepairShop parent) {
        gameObject = new GameObject(image, parent);
        this.parent = parent;
    }

    public double getX(){
        return gameObject.getX();
    }

    public double getY() {
        return gameObject.getY();
    }

    public BufferedImage getImage() {
        return gameObject.getImage();
    }

    public int getWidth(){
        return gameObject.getWidth();
    }

    public int getHeight() {
        return gameObject.getHeight();
    }

    public void loadOn(Car car){
        parent.loadOn(car);
    }
    public Car loadOff(){
        return parent.loadOff();
    }

    public RepairShop getParent(){
        return (RepairShop) gameObject.getParent();
    }

}