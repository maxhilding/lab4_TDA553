package model.objects;

import java.awt.geom.Point2D;

public interface IRepairShop {

    String getRepairShopName();

    Point2D.Double getPosition();

    int getCapacity();
}
