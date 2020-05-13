package cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class SoftCache<K, V> {

    private Map<K, SoftReference<V>> map = new HashMap<>();

    public V get(K key) {
        SoftReference<V> softValue = map.get(key);
        return softValue != null ? softValue.get() : null;
    }

    public V put(K key, V value) {
        SoftReference<V> softValue = new SoftReference<>(value);
        SoftReference<V> exSoftValue = map.put(key, softValue);
        return exSoftValue != null ? exSoftValue.get() : null;
    }
}