import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class MoveableGameObject {
    private final GameObject gameObject;

    private final Moveable parent;
    MoveableGameObject(BufferedImage image, Moveable parent) {
        gameObject = new GameObject(image, parent);
        this.parent = parent;
    }

    public void move() {
        parent.move();
    }

    public void turnLeft() {
         parent.turnLeft();
    }

    public void turnRight() {
        parent.turnRight();
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

    public void setDirection(double x, double y) {
        parent.setDirection(x, y);
    }

    public Point2D.Double getDirection() {
        return parent.getDirection();
    }

    public Moveable getParent() {
        return parent;
    }
}
