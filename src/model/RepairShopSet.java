package model;

import model.objects.RepairShop;
import java.util.*;
import model.objects.Car;

public class RepairShopSet implements Iterable<RepairShop<Car>>{
    private final List<RepairShop<Car>> repairShops = new ArrayList<>();
    public void addRepairShop(RepairShop<Car> r){
        repairShops.add(r);
    }
    @Override
    public Iterator<RepairShop<Car>> iterator() {
        return repairShops.iterator();
    }
}


