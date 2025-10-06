package es.uvigo.esei.aed2.activity6.HashMap;

import es.uvigo.esei.aed2.map.Map;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class HashMap<K, V> implements Map<K, V> {
    private static final int CAPACITY = 50;

    private int size;
    private List<Pair<K, V>>[] map;

    public HashMap() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    private HashMap(int capacity) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Métodos lanzan excepción
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public V get(K key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(K key, V value) throws NullPointerException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<K> getKeys() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<V> getValues() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
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
