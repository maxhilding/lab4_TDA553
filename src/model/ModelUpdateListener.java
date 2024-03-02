package model;
import model.objects.*;

import java.util.ArrayList;

public interface ModelUpdateListener {
    void actOnModelUpdate(ArrayList<Presentable> presentables);
}
