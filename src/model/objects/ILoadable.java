package model.objects;

public interface ILoadable<T extends ICar> {
    T unload();

    void load(T t);
}
