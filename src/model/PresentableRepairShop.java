package model;

import java.awt.geom.Point2D;

public class PresentableRepairShop implements Presentable{

    private RepairShop wrappedRepairShop;

    PresentableRepairShop(RepairShop repairShop){
        this.wrappedRepairShop = repairShop;
    }

    @Override
    public Point2D.Double getPosition(){
        return wrappedRepairShop.getPosition();
    }
    @Override
    public String getModelName(){
        return wrappedRepairShop.getModelName();
    }


    public int getCapacity() {
        return wrappedRepairShop.getCapacity();
    }

    public int getNumberOfLoaded() {
        return wrappedRepairShop.getNumberOfLoaded();
    }

    public String getRepairShopName() {
        return wrappedRepairShop.getRepairShopName();
    }

    public boolean getIsRepairShopFull() {
        return wrappedRepairShop.getIsRepairShopFull();
    }

}
