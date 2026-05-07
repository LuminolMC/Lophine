package fun.bm.lophine.utils.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Predicate;

public class ConcurrentTable<X, Y, Z> extends AbstractConcurrentTable<X, Y, Z> {
    protected final ConcurrentLinkedDeque<TableEntry<X, Y, Z>> data = new ConcurrentLinkedDeque<>();
    protected final boolean flagX;
    protected final boolean flagY;
    protected final boolean flagZ;

    public ConcurrentTable() {
        this(false, false, false);
    }

    public ConcurrentTable(boolean flagX, boolean flagY, boolean flagZ) {
        this.flagX = flagX;
        this.flagY = flagY;
        this.flagZ = flagZ;
    }

    @Override
    public void put(X x, Y y, Z z) {
        put(x, y, z, false);
    }

    public void put(X x, Y y, Z z, boolean flag) {
        if (!flag) {
            if (flagX) {
                List<X> datas = getX(y, z);
                for (X x1 : datas) {
                    if (!x1.equals(x)) {
                        remove(x1, y, z);
                    }
                }
            }
            if (flagY) {
                List<Y> datas = getY(x, z);
                for (Y y1 : datas) {
                    if (!y1.equals(y)) {
                        remove(x, y1, z);
                    }
                }
            }
            if (flagZ) {
                List<Z> datas = getZ(x, y);
                for (Z z1 : datas) {
                    if (!z1.equals(z)) {
                        remove(x, y, z1);
                    }
                }
            }
        }
        data.add(new TableEntry<>(x, y, z));
    }

    @Override
    public void remove(X x, Y y, Z z) {
        data.removeIf(entry -> entry.getX().equals(x) && entry.getY().equals(y) && entry.getZ().equals(z));
    }

    @Override
    public List<Z> getZ(X x, Y y) {
        return filterAndCollect(
                entry -> entry.getX().equals(x) && entry.getY().equals(y),
                TableEntry::getZ
        );
    }

    @Override
    public List<Y> getY(X x, Z z) {
        return filterAndCollect(
                entry -> entry.getX().equals(x) && entry.getZ().equals(z),
                TableEntry::getY
        );
    }

    @Override
    public List<X> getX(Y y, Z z) {
        return filterAndCollect(
                entry -> entry.getY().equals(y) && entry.getZ().equals(z),
                TableEntry::getX
        );
    }

    @Override
    public Map<X, Y> getXY(Z z) {
        return filterAndMap(
                entry -> entry.getZ().equals(z),
                TableEntry::getX,
                TableEntry::getY
        );
    }

    @Override
    public Map<Y, Z> getYZ(X x) {
        return filterAndMap(
                entry -> entry.getX().equals(x),
                TableEntry::getY,
                TableEntry::getZ
        );
    }

    @Override
    public Map<X, Z> getXZ(Y y) {
        return filterAndMap(
                entry -> entry.getY().equals(y),
                TableEntry::getX,
                TableEntry::getZ
        );
    }

    @Override
    public List<X> getAllX() {
        return collectAll(TableEntry::getX);
    }

    @Override
    public List<Y> getAllY() {
        return collectAll(TableEntry::getY);
    }

    @Override
    public List<Z> getAllZ() {
        return collectAll(TableEntry::getZ);
    }

    @Override
    public void clearXY(Z z) {
        data.removeIf(entry -> entry.getZ().equals(z));
    }

    @Override
    public void clearYZ(X x) {
        data.removeIf(entry -> entry.getX().equals(x));
    }

    @Override
    public void clearXZ(Y y) {
        data.removeIf(entry -> entry.getY().equals(y));
    }

    @Override
    public void clearAll() {
        data.clear();
    }

    private <T> List<T> filterAndCollect(Predicate<TableEntry<X, Y, Z>> filter,
                                         java.util.function.Function<TableEntry<X, Y, Z>, T> mapper) {
        List<T> result = new ArrayList<>();
        for (TableEntry<X, Y, Z> entry : data) {
            if (filter.test(entry)) {
                result.add(mapper.apply(entry));
            }
        }
        return result;
    }

    private <K, V> Map<K, V> filterAndMap(Predicate<TableEntry<X, Y, Z>> filter,
                                          java.util.function.Function<TableEntry<X, Y, Z>, K> keyMapper,
                                          java.util.function.Function<TableEntry<X, Y, Z>, V> valueMapper) {
        Map<K, V> map = new HashMap<>();
        for (TableEntry<X, Y, Z> entry : data) {
            if (filter.test(entry)) {
                map.put(keyMapper.apply(entry), valueMapper.apply(entry));
            }
        }
        return map;
    }

    private <T> List<T> collectAll(java.util.function.Function<TableEntry<X, Y, Z>, T> mapper) {
        List<T> result = new ArrayList<>();
        for (TableEntry<X, Y, Z> entry : data) {
            result.add(mapper.apply(entry));
        }
        return result;
    }
}
