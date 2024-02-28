package model.objects;

import java.awt.*;

public class Factory {

    public static ICar createSaab95(double x, double y){
        return new Saab95(x, y);
    }

    public static ICar createVolvo240(double x, double y){
        return new Volvo240(x, y);
    }

    public static ITruck createScania(double x, double y){
        return new Scania(x, y);
    }

    public static ITruck createCarTransport(int maxLoad, String name, double x, double y){
        return new CarTransport(maxLoad,name, x, y);
    }

    /*public static <T extends ICar> RepairShop<T> createRepairShop(int maxLoad, String repairShopName, double x, double y){
        return new RepairShop<>(maxLoad, repairShopName, x, y);
    }*/

    public static RepairShop<Volvo240> createVolvoRepairShop(int maxLoad, String repairShopName, double x, double y){
        return new RepairShop<>(maxLoad, repairShopName, x, y, "Volvo240");
    }



    public static ICar createCar(int nrDoors, double enginePower, Color color, String modelName, double x, double y){
        return new BaseCar(nrDoors, enginePower, color, modelName, x, y);
    }

}
