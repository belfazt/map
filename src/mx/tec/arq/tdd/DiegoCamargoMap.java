/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tec.arq.tdd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @author Diego
 * @param <K> Key Type
 * @param <V> Value Type
 */
public class DiegoCamargoMap<K, V> implements Map<K, V> {

    private static final String CANT_BE_NULL = "Key can't be null";
    private static final int DEFAULT_SIZE = 9999;
    private final Node<K, V> EMPTY;

    private Node<K, V>[] map;
    private int size;

    public DiegoCamargoMap() {
        this(DEFAULT_SIZE);
    }

    public DiegoCamargoMap(int size) {
        this.map = new Node[size];
        this.EMPTY = new Node((K) new Object(), (V) new Object());
        this.clear();
    }

    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.map.length; i++) {
            this.map[i] = EMPTY;
        }
    }

    @Override
    public boolean containsKey(K key) {
        requireNonNull(key, CANT_BE_NULL);
        return this.map[avoidCollision(key)].getKey() != EMPTY.getKey();
    }

    @Override
    public boolean containsValue(V value) {
        for (Node node : this.map) {
            if (value.equals(node.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        requireNonNull(key, CANT_BE_NULL);
        V value = this.map[avoidCollision(key)].getValue();
        return value == EMPTY.getValue() ? null : value;
    }

    @Override
    public V getOrDefault(K key, V defaultValue) {
        requireNonNull(key, CANT_BE_NULL);
        V value = this.map[avoidCollision(key)].getValue();
        return value == EMPTY.getValue() ? defaultValue : value;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void put(K key, V value) {
        requireNonNull(key, CANT_BE_NULL);
        if (this.resizeNeeded()) {
            this.resize();
        }
        if (!this.containsKey(key)) {
            this.size++;
        }
        this.map[avoidCollision(key)] = new Node<>(key, value);
    }

    @Override
    public V remove(K key) {
        requireNonNull(key, CANT_BE_NULL);
        int position = avoidCollision(key);
        V value = this.map[position].getValue();
        this.map[position] = EMPTY;
        if (value != EMPTY.getValue()) {
            this.size--;
            return value;
        } else {
            return null;
        }
    }

    @Override
    public boolean replace(K key, V value) throws NoSuchElementException {
        if (this.containsKey(key)) {
            this.put(key, value);
            return true;
        } else {
            throw new NoSuchElementException("Key does not exist, cannot replace");
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> values = new ArrayList<>(this.size);
        for (Node node : this.map) {
            if (node != EMPTY) {
                values.add((V) node.getValue());
            }
        }
        return values;
    }

    @Override
    public String toString() {
        String result = "";
        for (Node node : this.map) {
            if (node != EMPTY) {
                result += node + "\n";
            }
        }
        return result;
    }

    private int convertKey(Object key) {
        return Math.abs(key.hashCode()) % this.map.length;
    }

    private boolean resizeNeeded() {
        return (double) this.size / this.map.length > 0.75;
    }

    private void resize() {
        Node[] tempMap = this.map;
        this.map = new Node[this.map.length * 2 + 1];
        this.clear();
        for (Node node : tempMap) {
            if (node != EMPTY) {
                this.put((K) node.getKey(), (V) node.getValue());
            }
        }
    }

    private int avoidCollision(K key) {
        int proposedKey = convertKey(key);
        int count = 1;
        while (this.map[proposedKey] != EMPTY && !this.map[proposedKey]
                .getKey().equals(key)) {
            proposedKey += (int) Math.pow(count++, 2) % this.map.length;
        }
        return proposedKey;
    }

    private static void requireNonNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }
}


class Node<K, V> {
    
    private final K key;
    private final V value;
    
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return this.key;
    }
    
    public V getValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
        return this.getKey() + " : " + this.getValue();
    }
}
