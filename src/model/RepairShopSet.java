package model;

import model.objects.Car;
import model.objects.RepairShop;

import java.util.*;

class RepairShopSet implements Iterable<RepairShop<Car>>{
    private final List<RepairShop<Car>> repairShops = new ArrayList<>();
    void addRepairShop(RepairShop<Car> r){
        repairShops.add(r);
    }

    @Override
    public Iterator<RepairShop<Car>> iterator() {
        return repairShops.iterator();
    }
}


