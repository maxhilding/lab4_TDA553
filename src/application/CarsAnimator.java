package application;
import model.*;

import model.ModelUpdateListener;
import view.CarView;
import view.DrawableCar;

import java.util.ArrayList;
import java.util.List;

class CarsAnimator {

    private final Model model;

    private final CarView view;

    public CarsAnimator(CarView view, Model model) {
        this.model = model;
        this.view = view;
    }

    public void update(){
        // Move the cars
        model.move();
        //Check for collision
        checkEdgeCollision();

        //Check for cars loading into repairshop
        notifyListeners();

    }

    private void checkEdgeCollision() {
        for(DrawableCar car: view.carList) {
            int width = car.getWidth();
            int height = car.getHeight();

            int x0 = (int) Math.round(car.getPosition().getX());
            int y0 = (int) Math.round(car.getPosition().getY());
            if(x0 + width > view.getX() || x0 < 0) {
                car.invertX();

            }
            if(y0 + height > view.getY() || y0 < 0) {
                car.invertY();
            }
        }
    }

    public void animate(){
        try {
            while (true) {
                Thread.sleep(1);
                update();
            }
        } catch (InterruptedException e) {}
    }

    private final List<ModelUpdateListener> listeners = new ArrayList<>();
    public void addListener(ModelUpdateListener l){
        listeners.add(l);
    }
    protected void notifyListeners(){
        for (ModelUpdateListener l : listeners)
            l.actOnModelUpdate();
    }

}
