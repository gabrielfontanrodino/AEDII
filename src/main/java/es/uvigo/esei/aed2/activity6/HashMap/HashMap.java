package es.uvigo.esei.aed2.activity6.HashMap;

import es.uvigo.esei.aed2.map.Map;

import java.util.*;

import static java.util.Objects.requireNonNull;

public class HashMap<K, V> implements Map<K, V> {
    private static final int CAPACITY = 50;

    private int size;
    private final List<Pair<K, V>>[] map;

    public HashMap() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    private HashMap(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) throw new IllegalArgumentException("The map size cannot be zero or lower");

        this.size = 0;
        map = (List<Pair<K, V>>[]) new List[capacity];

        for (int i = 0; i < capacity; i++) {
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
        if (key == null) return null;
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
        int index = hashKey(requireNonNull(key, "Key cannot be null"));
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
        return new Iterator<>() {
            private int bucketIndex = 0;
            private Iterator<Pair<K, V>> bucketIterator = map[bucketIndex].iterator();

            /**
             * Ensures that the `bucketIterator` is pointing to a valid bucket with elements.
             * If the current bucket does not have any elements, it advances to the next bucket
             * until it finds one with elements or reaches the end of the map.
             */
            private void iterate() {
                while (!bucketIterator.hasNext() && bucketIndex < map.length - 1) {
                    bucketIndex++; // Move to the next bucket
                    bucketIterator = map[bucketIndex].iterator(); // Update the iterator for the new bucket
                }
            }

            /**
             * Checks if there are more elements to iterate over in the map.
             * This method ensures the iterator is positioned at a valid bucket
             * with elements before checking for the next element.
             *
             * @return true if there are more elements to iterate over, false otherwise
             */
            @Override
            public boolean hasNext() {
                iterate();
                return bucketIterator.hasNext();
            }

            /**
             * Retrieves the next value in the iteration.
             * This method ensures the iterator is positioned at a valid bucket
             * and throws an exception if there are no more elements to iterate over.
             *
             * @return the next value in the iteration
             * @throws NoSuchElementException if there are no more elements to iterate over
             */
            @Override
            public V next() {
                iterate();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return bucketIterator.next().getValue();
            }
        };
    }

    @Override
    public void clear() {
        for (List<Pair<K, V>> bucket : map) {
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
