package fun.bm.lophine.utils.concurrent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class OptimizedConcurrentTable<X, Y, Z> extends ConcurrentTable<X, Y, Z> {
    private final ConcurrentHashMap<X, ConcurrentHashMap<Y, Set<Z>>> xyIndex = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Y, ConcurrentHashMap<Z, Set<X>>> yzIndex = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Z, ConcurrentHashMap<X, Set<Y>>> zxIndex = new ConcurrentHashMap<>();

    @Override
    public void putOrUpdate(X x, Y y, Z z) {
        boolean updated = false;

        for (TableEntry<X, Y, Z> entry : data) {
            if (entry.getX().equals(x) && entry.getY().equals(y)) {
                removeFromIndex(xyIndex, entry.getX(), entry.getY(), entry.getZ());
                removeFromIndex(yzIndex, entry.getY(), entry.getZ(), entry.getX());
                removeFromIndex(zxIndex, entry.getZ(), entry.getX(), entry.getY());

                entry.setZ(z);

                putData(x, y, z);

                updated = true;
                break;
            }
        }

        if (!updated) {
            this.put(x, y, z);
        }
    }

    private void putData(X x, Y y, Z z) {
        xyIndex.computeIfAbsent(x, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(y, k -> ConcurrentHashMap.newKeySet()).add(z);
        yzIndex.computeIfAbsent(y, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(z, k -> ConcurrentHashMap.newKeySet()).add(x);
        zxIndex.computeIfAbsent(z, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(x, k -> ConcurrentHashMap.newKeySet()).add(y);
    }


    @Override
    public void put(X x, Y y, Z z) {
        super.put(x, y, z);
        putData(x, y, z);
    }

    @Override
    public void remove(X x, Y y, Z z) {
        super.remove(x, y, z);
        removeFromIndex(xyIndex, x, y, z);
        removeFromIndex(yzIndex, y, z, x);
        removeFromIndex(zxIndex, z, x, y);
    }

    private <K, V, T> void removeFromIndex(ConcurrentHashMap<K, ConcurrentHashMap<V, Set<T>>> index,
                                           K key1, V key2, T value) {
        index.computeIfPresent(key1, (k, map) -> {
            map.computeIfPresent(key2, (k2, set) -> {
                set.remove(value);
                return set.isEmpty() ? null : set;
            });
            return map.isEmpty() ? null : map;
        });
    }

    public void removeAll(Predicate<TableEntry<X, Y, Z>> predicate) {
        data.removeIf(entry -> {
            boolean shouldRemove = predicate.test(entry);
            if (shouldRemove) {
                removeFromIndex(xyIndex, entry.getX(), entry.getY(), entry.getZ());
                removeFromIndex(yzIndex, entry.getY(), entry.getZ(), entry.getX());
                removeFromIndex(zxIndex, entry.getZ(), entry.getX(), entry.getY());
            }
            return shouldRemove;
        });
    }

    public boolean putIfAbsent(X x, Y y, Z z) {
        if (data.stream().anyMatch(entry ->
                Objects.equals(entry.getX(), x) &&
                        Objects.equals(entry.getY(), y) &&
                        Objects.equals(entry.getZ(), z))) {
            return false;
        }
        put(x, y, z);
        return true;
    }

    @Override
    public List<Z> getZ(X x, Y y) {
        Set<Z> result = xyIndex.getOrDefault(x, new ConcurrentHashMap<>()).get(y);
        return result != null ? new ArrayList<>(result) : new ArrayList<>();
    }

    @Override
    public List<Y> getY(X x, Z z) {
        Set<Y> result = zxIndex.getOrDefault(z, new ConcurrentHashMap<>()).get(x);
        return result != null ? new ArrayList<>(result) : new ArrayList<>();
    }

    public List<X> getX(Y y, Z z) {
        Set<X> result = yzIndex.getOrDefault(y, new ConcurrentHashMap<>()).get(z);
        return result != null ? new ArrayList<>(result) : new ArrayList<>();
    }

    @Override
    public List<Map.Entry<X, Y>> getXY(Z z) {
        return buildDataFromIndex(zxIndex.get(z));
    }

    @Override
    public List<Map.Entry<Y, Z>> getYZ(X x) {
        return buildDataFromIndex(xyIndex.get(x));
    }

    @Override
    public List<Map.Entry<X, Z>> getXZ(Y y) {
        return reverseDataFromIndex(yzIndex.get(y));
    }

    @Override
    public List<X> getAllX() {
        Set<X> resultSet = new HashSet<>(xyIndex.keySet());
        return new ArrayList<>(resultSet);
    }

    @Override
    public List<Y> getAllY() {
        Set<Y> resultSet = new HashSet<>(yzIndex.keySet());
        return new ArrayList<>(resultSet);
    }

    @Override
    public List<Z> getAllZ() {
        Set<Z> resultSet = new HashSet<>(zxIndex.keySet());
        return new ArrayList<>(resultSet);
    }


    @Override
    public void clearXY(Z z) {
        super.clearXY(z);
        zxIndex.remove(z);
    }

    @Override
    public void clearYZ(X x) {
        super.clearYZ(x);
        xyIndex.remove(x);
    }

    @Override
    public void clearXZ(Y y) {
        super.clearXZ(y);
        yzIndex.remove(y);
    }

    @Override
    public void clearAll() {
        super.clearAll();
        xyIndex.clear();
        yzIndex.clear();
        zxIndex.clear();
    }


    private <K, V, R> List<R> buildEntriesFromIndex(ConcurrentHashMap<K, Set<V>> indexMap,
                                                    BiFunction<K, V, R> entryCreator) {
        List<R> result = new ArrayList<>();
        if (indexMap != null) {
            for (Map.Entry<K, Set<V>> entry : indexMap.entrySet()) {
                K key = entry.getKey();
                Set<V> valueSet = entry.getValue();
                if (valueSet != null && !valueSet.isEmpty()) {
                    for (V value : valueSet) {
                        result.add(entryCreator.apply(key, value));
                    }
                }
            }
        }
        return result;
    }

    private <K, V> List<Map.Entry<K, V>> buildDataFromIndex(ConcurrentHashMap<K, Set<V>> indexMap) {
        return buildEntriesFromIndex(indexMap, AbstractMap.SimpleEntry::new);
    }

    private <K, V> List<Map.Entry<V, K>> reverseDataFromIndex(ConcurrentHashMap<K, Set<V>> indexMap) {
        return buildEntriesFromIndex(indexMap, (key, value) -> new AbstractMap.SimpleEntry<>(value, key));
    }
}
