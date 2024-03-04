package model;

public interface Loadable<T> {
    T unload();
    void load(T t);

    int getCapacity();

    int getNumberOfLoaded();
}
