package model;
import model.objects.*;

public interface ModelUpdateListener {
    void actOnModelUpdate();
    void actOnCarAdded(ICar car);
    void actOnCarRemoved(ICar car);
}
