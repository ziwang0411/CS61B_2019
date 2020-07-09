import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V>{
    private int size =0;
    private HashSet<K> keySet;
    private ArrayList<Entry>[] maps;
    private int initialSize;
    private double loadFactor;
    public MyHashMap() {
        this.initialSize = 16;
        this.loadFactor = 0.75;
        initializeMap(initialSize);
        this.keySet = new HashSet<>();
    }
    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        this.loadFactor = 0.75;
        initializeMap(initialSize);
        this.keySet = new HashSet<>();

    }
    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        initializeMap(initialSize);
        this.keySet = new HashSet<>();
    }

    private void initializeMap(int capacity) {
        maps = new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            maps[i] = new ArrayList<Entry>();
        }
    }

    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public void clear() {
        this.size = 0;
        this.keySet = new HashSet<>();
        initializeMap(initialSize);

    }

    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {return null;}
        int hash = (key.hashCode() & 0x7fffffff) % maps.length;
        for (Entry map: maps[hash]) {
            if (map.key.equals(key)) {
                return map.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        int hash = (key.hashCode() & 0x7fffffff) % maps.length;
        if (containsKey(key)) {
            for (Entry map: maps[hash]) {
                if (map.key.equals(key)) {
                    map.value = value;
                    return;
                }
            }
        }
        keySet.add(key);
        maps[hash].add(new Entry(key,value));
        this.size +=1;
        if ((double) size / maps.length >loadFactor) {
            resize(maps.length*2);
        }
    }

    private void resize(int capacity) {
        ArrayList<Entry> temp = new ArrayList<>();
        for (K key: keySet) {
            temp.add(new Entry(key, get(key)));
        }
        initializeMap(capacity);
        size = 0;
        for (Entry item: temp) {
            put(item.key, item.value);
        }
    }

    @Override
    public Set keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
//        throw new UnsupportedOperationException("Not supported");
        if (containsKey(key)) {
            int hash = (key.hashCode() & 0x7fffffff) % maps.length;
            for (int i = 0; i < maps[hash].size(); i++ ) {
                if (maps[hash].get(i).key.equals(key)) {
                    V res = maps[hash].get(i).value;
                    maps[hash].remove(i);
                    size--;
                    keySet.remove(key);
                    return res;
                }
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        if (containsKey(key)) {
            int hash = (key.hashCode() & 0x7fffffff) % maps.length;
            for (int i = 0; i < maps[hash].size(); i++ ) {
                if (maps[hash].get(i).key.equals(key) && maps[hash].get(i).value.equals(value)) {
                    maps[hash].remove(i);
                    size--;
                    keySet.remove(key);
                    return value;
                }
                return null;
            }
        }
        return null;
    }

    @Override
    public Iterator iterator() {
        return keySet.iterator();
    }
}
