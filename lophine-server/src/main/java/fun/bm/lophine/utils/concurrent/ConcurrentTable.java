package fun.bm.lophine.utils.concurrent;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Predicate;

public class ConcurrentTable<X, Y, Z> extends AbstractConcurrentTable<X, Y, Z> {
    protected final ConcurrentLinkedDeque<TableEntry<X, Y, Z>> data = new ConcurrentLinkedDeque<>();

    @Override
    public void put(X x, Y y, Z z) {
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
    public List<Map.Entry<X, Y>> getXY(Z z) {
        return filterAndMap(
                entry -> entry.getZ().equals(z),
                TableEntry::getX,
                TableEntry::getY
        );
    }

    @Override
    public List<Map.Entry<Y, Z>> getYZ(X x) {
        return filterAndMap(
                entry -> entry.getX().equals(x),
                TableEntry::getY,
                TableEntry::getZ
        );
    }

    @Override
    public List<Map.Entry<X, Z>> getXZ(Y y) {
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

    private <K, V> List<Map.Entry<K, V>> filterAndMap(Predicate<TableEntry<X, Y, Z>> filter,
                                                      java.util.function.Function<TableEntry<X, Y, Z>, K> keyMapper,
                                                      java.util.function.Function<TableEntry<X, Y, Z>, V> valueMapper) {
        List<Map.Entry<K, V>> list = new ArrayList<>();
        for (TableEntry<X, Y, Z> entry : data) {
            if (filter.test(entry)) {
                list.add(new AbstractMap.SimpleEntry<>(keyMapper.apply(entry), valueMapper.apply(entry)));
            }
        }
        return list;
    }

    private <T> List<T> collectAll(java.util.function.Function<TableEntry<X, Y, Z>, T> mapper) {
        List<T> result = new ArrayList<>();
        for (TableEntry<X, Y, Z> entry : data) {
            result.add(mapper.apply(entry));
        }
        return result;
    }
}
