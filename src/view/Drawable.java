package view;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Drawable extends Component{

    Point2D.Double position;

    BufferedImage image;

    Drawable(Point2D.Double position, BufferedImage image){
        this.position = position;
        this.image = image;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, (int) position.getX(), (int) position.getY(), null);
    }
}
