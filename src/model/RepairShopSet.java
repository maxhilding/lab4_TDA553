package model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

class RepairShopSet implements Iterable<RepairShop<? extends Car>>{
    private final List<RepairShop<? extends Car>> repairShops = new ArrayList<>();
    void addRepairShop(RepairShop<? extends Car> r){
        repairShops.add(r);
    }

    void addSizes(Map<String, Point> sizes){
        for(RepairShop<? extends Car> r: repairShops){
            Point size = sizes.get(r.getRepairShopName());
            if(!(size==null)){
                r.setHasDefinedSize(true);
                r.setWidth((int) size.getX());
                r.setLength((int) size.getY());
            }
        }
    }

    @Override
    public Iterator<RepairShop<? extends Car>> iterator() {
        return repairShops.iterator();
    }
}


