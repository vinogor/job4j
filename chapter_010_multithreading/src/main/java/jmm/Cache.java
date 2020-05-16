package jmm;

// Double Check
public final class Cache {

    private static volatile Cache cache;

    private Cache() {
    }

    private static Cache instOf() {
        Cache tempCache = cache;
        if (tempCache == null) {
            synchronized (Cache.class) {
                tempCache = cache;
                if (tempCache == null) {
                    cache = tempCache = new Cache();
                }
            }
        }
        return tempCache;
    }
}

// On Demand Holder
final class Cache2 {

    public static class SingletonHolder {
        public static final Cache2 INSTANCE = new Cache2();
    }

    public static Cache2 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}