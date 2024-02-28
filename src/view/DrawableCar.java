package view;
import model.objects.ICar;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class DrawableCar extends Component implements ICar{
    public final ICar wrappedCar;

    private final BufferedImage image;

    public DrawableCar(ICar wrappedCar, BufferedImage image) {
        this.wrappedCar = wrappedCar;
        this.image = image;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image, (int) wrappedCar.getPosition().getX(), (int) wrappedCar.getPosition().getY(), null);
    }

    public int getWidth(){
        return image.getWidth();
    }

    public int getHeight(){
        return image.getHeight();
    }

    public void invertX() {
        wrappedCar.setDirection(-wrappedCar.getDirection().getX(), wrappedCar.getDirection().getY());
    }

    public void invertY() {
        wrappedCar.setDirection(wrappedCar.getDirection().getX(), -wrappedCar.getDirection().getY());
    }

    public void setDirection(double x, double y) {
        wrappedCar.setDirection(x, y);
    }

    //General car
    public String getModelName(){
        return wrappedCar.getModelName();
    };

    public int getNrDoors(){
        return wrappedCar.getNrDoors();
    };

    public double getEnginePower(){
        return wrappedCar.getEnginePower();
    };
    public Color getColor(){return wrappedCar.getColor();};

    public double getHandling(){
        return wrappedCar.getHandling();}

    public double getCurrentDegree(){
        return wrappedCar.getCurrentDegree();
    }

    // Engine
    public void startEngine(){
        wrappedCar.startEngine();
    };

    public void stopEngine(){
        wrappedCar.stopEngine();
    };

    public boolean getIsDriveable(){
        return wrappedCar.getIsDriveable();
    }

    public void setIsUnDriveable(){
        wrappedCar.setIsUnDriveable();
    }

    public void setIsDriveable(){
        wrappedCar.setIsDriveable();
    }

    // Speed and acceleration


    public void incrementSpeed(double amount){wrappedCar.incrementSpeed(amount);}
    public void decrementSpeed(double amount){wrappedCar.decrementSpeed(amount);}

    public void gas(double amount){wrappedCar.gas(amount);}

    public void brake(double amount){wrappedCar.brake(amount);}

    public double getCurrentSpeed(){return wrappedCar.getCurrentSpeed();}

    // Position
    public Point2D.Double getPosition(){return wrappedCar.getPosition();}

    public void setPosition(double newX, double newY){wrappedCar.setPosition(newX, newY);}

    // Direction
    public Point2D.Double getDirection(){ return wrappedCar.getDirection();}

    // Movement
    public void move() {
        wrappedCar.move();
    }

    public void turnLeft() {
        wrappedCar.turnLeft();
    }

    public void turnRight() {
        wrappedCar.turnRight();
    }



}
