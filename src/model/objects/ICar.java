package model.objects;

import java.awt.*;
import java.awt.geom.Point2D;

public interface ICar {

    // Attributes
    String getModelName();

    int getNrDoors();

    double getEnginePower();
    Color getColor();

    double getHandling();

    double getCurrentDegree();

    boolean getIsDriveable();

    void setIsUnDriveable();

    void setIsDriveable();


    // Engine
    void startEngine();

    void stopEngine();

    // Speed and acceleration

    void incrementSpeed(double amount);
    void decrementSpeed(double amount);

    void gas(double amount);

    void brake(double amount);

    double getCurrentSpeed();

    // Position
    Point2D.Double getPosition();

    void setPosition(double x, double y);

    void setDirection(double x, double y);

    // Direction
    Point2D.Double getDirection();

    // Movement
    void move();

    void turnLeft();

    void turnRight();
}
