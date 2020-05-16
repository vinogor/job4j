package jmm;

public final class Cache {
    private static Cache cache;

    private static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}