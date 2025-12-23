package fun.bm.lophine.utils.concurrent;

public class TableEntry<X, Y, Z> {
    private final X x;
    private final Y y;
    private Z z;

    public TableEntry(X x, Y y, Z z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    public Z getZ() {
        return z;
    }

    public void setZ(Z z) {
        this.z = z;
    }
}
