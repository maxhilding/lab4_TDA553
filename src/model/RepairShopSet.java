package model;

import model.objects.RepairShop;
import java.util.*;
import model.objects.ICar;

class RepairShopSet implements Iterable<RepairShop<ICar>>{
    private final List<RepairShop<ICar>> repairShops = new ArrayList<>();
    public void addRepairShop(RepairShop<ICar> r){
        repairShops.add(r);
    }
    @Override
    public Iterator<RepairShop<ICar>> iterator() {
        return repairShops.iterator();
    }
}


