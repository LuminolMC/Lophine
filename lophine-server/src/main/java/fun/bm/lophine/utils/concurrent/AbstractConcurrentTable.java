package fun.bm.lophine.utils.concurrent;

import java.util.List;
import java.util.Map;

public interface AbstractConcurrentTable<X, Y, Z> {
    void put(X x, Y y, Z z);

    void remove(X x, Y y, Z z);

    List<Z> getZ(X x, Y y);

    List<Y> getY(X x, Z z);

    List<X> getX(Y y, Z z);

    Map<X, Y> getXY(Z z);

    Map<Y, Z> getYZ(X x);

    Map<X, Z> getXZ(Y y);

    List<X> getAllX();

    List<Y> getAllY();

    List<Z> getAllZ();

    void clearXY(Z z);

    void clearYZ(X x);

    void clearXZ(Y y);

    void clearAll();
}
