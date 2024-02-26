import java.awt.geom.Point2D;

public interface Moveable extends Positioned {
    void move();
    void turnLeft();
    void turnRight();

    Point2D.Double getDirection();

    void setDirection(double x, double y);
}
