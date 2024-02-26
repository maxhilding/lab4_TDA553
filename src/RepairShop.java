import javax.imageio.ImageReadParam;
import java.awt.geom.Point2D;
import java.util.*;

public class RepairShop<T extends Car> implements Loadable<T>{

    private final int maxNumberOfLoadedCars;

    private final String repairShopName;

    private Point2D.Double position;

    private Stack<T> loadedCars;

    public RepairShop(int maxLoad, String repairShopName) {
        maxNumberOfLoadedCars = maxLoad;
        loadedCars = new Stack<>();
        this.repairShopName = repairShopName;
        this.position = new Point2D.Double(0.0, 0.0);
    }

    public Point2D.Double getPosition(){
        return position;
    }
    public void setPosition(double newX, double newY){
        position.setLocation(newX, newY);
    }
    public int getMaxNumberOfLoadedCars() {
        return maxNumberOfLoadedCars;
    }

    public Stack<T> getLoadedCars() {
        return loadedCars;
    }

    public String getRepairShopName() {
        return repairShopName;
    }

    @Override
    public T loadOff() {
        T car = loadedCars.pop();
        car.setIsDriveable(true);
        return car;
    }

    public void loadOn(T car) {
        if (getLoadedCars().size() < getMaxNumberOfLoadedCars()) {
            getLoadedCars().push(car);
            car.setIsDriveable(false);
        }
        else {
            throw new RuntimeException("The repairshop is full"); //throwing 'wrong' exception here hehe

        }
    }

}

