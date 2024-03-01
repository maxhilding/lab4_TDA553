package model.objects;

import java.awt.*;

public class Factory {

    public static Car createSaab95(double x, double y){
        return new Saab95(x, y);
    }

    public static Car createVolvo240(double x, double y){
        return new Volvo240(x, y);
    }

    public static Truck createScania(double x, double y){
        return new Scania(x, y);
    }

    public static Truck createCarTransport(int maxLoad, String name, double x, double y){
        return new CarTransport(x, y, maxLoad, name);
    }

    /*public static <T extends ICar> RepairShop<T> createRepairShop(int maxLoad, String repairShopName, double x, double y){
        return new RepairShop<>(maxLoad, repairShopName, x, y);
    }*/

    public static RepairShop<Volvo240> createVolvoRepairShop(int maxLoad, String repairShopName, double x, double y){
        return new RepairShop<>(maxLoad, repairShopName, x, y, "Volvo240");
    }



   /*public static Car createCar(int nrDoors, double enginePower, Color color, String modelName, double x, double y){
        return new BaseCar(nrDoors, enginePower, color, modelName, x, y);*/
    }


