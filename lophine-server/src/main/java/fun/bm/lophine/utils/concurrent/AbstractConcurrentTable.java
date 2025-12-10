package fun.bm.lophine.utils.concurrent;

import java.util.List;
import java.util.Map;

public abstract class AbstractConcurrentTable<X, Y, Z> {
    public abstract void put(X x, Y y, Z z);
    public abstract void remove(X x, Y y, Z z);
    public abstract List<Z> getZ(X x, Y y);
    public abstract List<Y> getY(X x, Z z);
    public abstract List<X> getX(Y y, Z z);
    public abstract Map<X, Y> getXY(Z z);
    public abstract Map<Y, Z> getYZ(X x);
    public abstract Map<X, Z> getXZ(Y y);
    public abstract List<X> getAllX();
    public abstract List<Y> getAllY();
    public abstract List<Z> getAllZ();
    public abstract void clearXY(Z z);
    public abstract void clearYZ(X x);
    public abstract void clearXZ(Y y);
    public abstract void clearAll();
}
