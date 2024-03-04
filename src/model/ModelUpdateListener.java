package model;

import java.util.ArrayList;

public interface ModelUpdateListener {
    void actOnModelUpdate(ArrayList<Presentable> presentables);
}
