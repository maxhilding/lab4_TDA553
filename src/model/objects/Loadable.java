package model.objects;

public interface Loadable<T> {
    T unload();
    void load(T t);
}