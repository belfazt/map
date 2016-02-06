/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tec.arq.tdd;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Diego
 */
public class DiegoCamargoMap <K, V> implements Map <K, V> {
    
    private final static int DEFAULT_SIZE = 999;
    private final V EMPTY_VALUE;
    
    private V[] map;
    private int maxSize;
    private int size;
    
    public DiegoCamargoMap() {
        this(DEFAULT_SIZE);
    }
    
    public DiegoCamargoMap(int size) {
        this.maxSize = size;
        this.map = (V[]) new Object[this.maxSize];
        this.EMPTY_VALUE = (V) new Object();
        this.clear();
    }

    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0 ; i < this.maxSize; i++) {
            this.map[i] = EMPTY_VALUE;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return this.map[this.convertKey(key)] != EMPTY_VALUE;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < this.maxSize; i++) {
            if (value.equals(this.map[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        return this.map[this.convertKey(key)];
    }

    @Override
    public V getOrDefault(K key, V defaultValue) {
        V value = this.map[this.convertKey(key)];
        return value == EMPTY_VALUE ? defaultValue : value;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void put(K key, V value) {
        this.map[this.convertKey(key)] = value; 
        this.size++;
    }

    @Override
    public V remove(K key) {
        V value = this.map[this.convertKey(key)];
        this.map[this.convertKey(key)] = null;
        if (value != EMPTY_VALUE) {
            this.size--;
            return value;
        } else {
            return null;
        }
        
    }

    @Override
    public boolean replace(K key, V value) throws NoSuchElementException {
        if (this.containsKey(key)) {
            this.map[this.convertKey(key)] = value;
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
    public Collection values() {
        return new Collection<V>() {
            @Override
            public int size() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isEmpty() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean contains(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Iterator<V> iterator() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Object[] toArray() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <T> T[] toArray(T[] a) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean add(V e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean remove(Object o) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean addAll(Collection<? extends V> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void clear() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
    private int convertKey(K key) {
        return Math.abs(key.hashCode() % this.maxSize);
    }
    
}
