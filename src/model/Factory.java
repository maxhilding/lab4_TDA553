package model;

public class Factory {

    public static Car createSaab95(double x, double y) {
        return new Saab95(x, y);
    }

    public static Car createVolvo240(double x, double y) {
        return new Volvo240(x, y);
    }

    public static Truck createScania(double x, double y) {
        return new Scania(x, y);
    }

    public static Truck createCarTransport(int maxLoad, String name, double x, double y) {
        return new CarTransport(x, y, maxLoad, name);
    }

    public static RepairShop<Volvo240> createVolvoRepairShop(int maxLoad, String repairShopName, double x, double y) {
        return new RepairShop<>(maxLoad, repairShopName, x, y, "Volvo240");
    }
}


