package view;
import model.objects.Car;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class DrawableCar extends Component{
    private final Car wrappedCar;

    private final BufferedImage image;

    public DrawableCar(Car wrappedCar, BufferedImage image) {
        this.wrappedCar = wrappedCar;
        this.image = image;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, (int) wrappedCar.getPosition().getX(), (int) wrappedCar.getPosition().getY(), null);
    }





}
