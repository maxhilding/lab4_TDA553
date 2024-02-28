package model.objects;

public interface ILoadable {
    ICar unload();

    void load(ICar car);
}
