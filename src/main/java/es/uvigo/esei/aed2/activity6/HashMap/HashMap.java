package es.uvigo.esei.aed2.activity6.HashMap;

import es.uvigo.esei.aed2.map.Map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    private static final int CAPACITY = 50;

    private int size;
    private List<Pair<K, V>>[] map;

    public HashMap() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    private HashMap(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) throw new IllegalArgumentException("The map size cannot be zero or lower");

        this.size = 0;
        map = (List<Pair<K, V>>[]) new List[CAPACITY];

        for (int i = 0; i < CAPACITY; i++) {
            map[i] = new LinkedList<>();
        }
    }

    private int hashKey(K key) {
        return Math.abs(key.hashCode()) % map.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public V get(K key) {
        if(key == null) return null;
        int index = hashKey(key);

        for (Pair<K, V> pair : map[index]) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    @Override
    public void add(K key, V value) throws NullPointerException {
        int index = hashKey(key);
        List<Pair<K, V>> bucket = map[index];

        // check if key already exists
        for (Pair<K, V> pair : bucket) {
            if (pair.getKey().equals(key)) {
                pair.setValue(value); // replace value
                return;
            }
        }

        bucket.add(new Pair<>(key, value)); // add new pair
        size++;
    }

    @Override
    public V remove(K key) {
        if (key == null) return null;

        int index = hashKey(key);
        List<Pair<K, V>> bucket = map[index];

        Iterator<Pair<K, V>> iterator = bucket.iterator();

        while (iterator.hasNext()) {
            Pair<K, V> pair = iterator.next();
            if (pair.getKey().equals(key)) {
                iterator.remove();
                size--;
                return pair.getValue();
            }
        }

        return null; // key not found
    }

    @Override
    public Set<K> getKeys() {
        Set<K> foundKeys = new HashSet<>();

        for (List<Pair<K, V>> bucket : map) {
            for (Pair<K, V> entry : bucket) {
                foundKeys.add(entry.k);
            }
        }

        return foundKeys;
    }


    @Override
    public Iterator<V> getValues() {
        List<V> foundValues = new ArrayList<>();

        for (List<Pair<K, V>> bucket : map) {
            for (Pair<K, V> entry : bucket) {
                foundValues.add(entry.v);
            }
        }

        return foundValues.iterator();
    }

    @Override
    public void clear() {
        for(List<Pair<K, V>> bucket: map) {
            bucket.clear();
        }
    }

    private static class Pair<K, V> {

        private final K k;
        private V v;

        public Pair(K k, V v) {
            this.k = k;
            this.v = v;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

        public void setValue(V v) {
            this.v = v;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Pair) {
                Pair<?, ?> hp = (Pair<?, ?>) other;
                return this.k.equals(hp.k) && this.v.equals(hp.v);
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.k) ^ Objects.hashCode(this.v);
        }
    }

}
