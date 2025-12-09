package fun.bm.lophine.utils.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentTable<X, Y, Z> {
    ConcurrentLinkedDeque<TableEntry<X, Y, Z>> data = new ConcurrentLinkedDeque<>();

    public void put(X x, Y y, Z z) {
        data.add(new TableEntry<>(x, y, z));
    }

    public void remove(X x, Y y, Z z) {
        data.removeIf(entry -> entry.getX().equals(x) && entry.getY().equals(y) && entry.getZ().equals(z));
    }

    public Z getZ(X x, Y y) {
        for (TableEntry<X, Y, Z> entry : data) {
            if (entry.getX().equals(x) && entry.getY().equals(y)) {
                return entry.getZ();
            }
        }
        return null;
    }

    public Y getY(X x, Z z) {
        for (TableEntry<X, Y, Z> entry : data) {
            if (entry.getX().equals(x) && entry.getZ().equals(z)) {
                return entry.getY();
            }
        }
        return null;
    }

    public X getX(Y y, Z z) {
        for (TableEntry<X, Y, Z> entry : data) {
            if (entry.getY().equals(y) && entry.getZ().equals(z)) {
                return entry.getX();
            }
        }
        return null;
    }

    public Map<X, Y> getXY(Z z) {
        HashMap<X, Y> map = new HashMap<>();
        for (TableEntry<X, Y, Z> entry : data) {
            if (entry.getZ().equals(z)) {
                map.put(entry.getX(), entry.getY());
            }
        }
        return map;
    }

    public Map<Y, Z> getYZ(X x) {
        HashMap<Y, Z> map = new HashMap<>();
        for (TableEntry<X, Y, Z> entry : data) {
            if (entry.getX().equals(x)) {
                map.put(entry.getY(), entry.getZ());
            }
        }
        return map;
    }

    public Map<X, Z> getXZ(Y y) {
        HashMap<X, Z> map = new HashMap<>();
        for (TableEntry<X, Y, Z> entry : data) {
            if (entry.getY().equals(y)) {
                map.put(entry.getX(), entry.getZ());
            }
        }
        return map;
    }

    public List<X> getAllX() {
        List<X> xList = new ArrayList<>();
        for (TableEntry<X, Y, Z> entry : data) {
            xList.add(entry.getX());
        }
        return xList;
    }

    public List<Y> getAllY() {
        List<Y> yList = new ArrayList<>();
        for (TableEntry<X, Y, Z> entry : data) {
            yList.add(entry.getY());
        }
        return yList;
    }

    public List<Z> getAllZ() {
        List<Z> zList = new ArrayList<>();
        for (TableEntry<X, Y, Z> entry : data) {
            zList.add(entry.getZ());
        }
        return zList;
    }

    public void clearXY(Z z) {
        data.removeIf(entry -> entry.getZ().equals(z));
    }

    public void clearYZ(X x) {
        data.removeIf(entry -> entry.getX().equals(x));
    }

    public void clearXZ(Y y) {
        data.removeIf(entry -> entry.getY().equals(y));
    }

    public void clearAll() {
        data.clear();
    }
}
