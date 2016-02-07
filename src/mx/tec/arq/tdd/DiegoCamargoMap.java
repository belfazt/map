/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tec.arq.tdd;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Diego
 * @param <K> Key Type
 * @param <V> Value Type
 */
public class DiegoCamargoMap <K, V> implements Map <K, V> {
    
    private final static int DEFAULT_SIZE = 9999;
    private final V EMPTY_VALUE;
    
    private V[] map;
    private int size;
    
    public DiegoCamargoMap() {
        this(DEFAULT_SIZE);
    }
    
    public DiegoCamargoMap(int size) {
        this.map = (V[]) new Object[size];
        this.EMPTY_VALUE = (V) new Object();
        this.clear();
    }

    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0 ; i < this.map.length; i++) {
            this.map[i] = EMPTY_VALUE;
        }
    }

    @Override
    public boolean containsKey(K key) {
        requireNonNull(key, "Key can't be null");
        return this.map[convertKey(key, this.map.length)] != EMPTY_VALUE;
    }

    @Override
    public boolean containsValue(V value) {
        for (V map1 : this.map) {
            if (value.equals(map1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        requireNonNull(key, "Key can't be null");
        return this.map[convertKey(key, this.map.length)];
    }

    @Override
    public V getOrDefault(K key, V defaultValue) {
        requireNonNull(key, "Key can't be null");
        V value = this.map[convertKey(key, this.map.length)];
        return value == EMPTY_VALUE ? defaultValue : value;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void put(K key, V value) {
        requireNonNull(key, "Key can't be null");
        this.map[convertKey(key, this.map.length)] = value; 
        this.size++;
    }

    @Override
    public V remove(K key) {
        requireNonNull(key, "Key can't be null");
        V value = this.map[convertKey(key, this.map.length)];
        this.map[convertKey(key, this.map.length)] = EMPTY_VALUE;
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
            this.map[convertKey(key, this.map.length)] = value;
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
        V[] values = (V[]) new Object[this.size];
        int position = 0;
        for (V value : this.map) {
            if (value != EMPTY_VALUE) {
                values[position++] = value;
            }
        }
        return new HashSet<>(Arrays.asList(values));
    }
    
    public Collection<V> values2() {
        return new Collection<V>() {
            
            private int len = 0;
            private final V[] values;
            
            {
                values = (V[]) new Object[map.length];
                for (V value : map) {
                    if (value != EMPTY_VALUE) {
                        values[this.len++] = value;
                    }
                }
            }
            
            
            @Override
            public int size() {
                return this.len;
            }

            @Override
            public boolean isEmpty() {
                return this.len == 0;
            }

            @Override
            public boolean contains(Object o) {
                for (int i = 0; i < this.len; i++) {
                    if (o.equals(this.values[i])) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Iterator iterator() {
                return new Iterator() {
                    
                    private final int length;
                    private final Object[] itValues;
                    private int position;
                    
                    {
                        length = len;
                        itValues = values;
                        position = 0;
                    }
                    
                    @Override
                    public boolean hasNext() {
                        return position < length;
                    }

                    @Override
                    public Object next() {
                        return itValues[position++];
                    }
                };
            }

            @Override
            public Object[] toArray() {
                V[] resultingArray = (V[]) new Object[this.len];
                System.arraycopy(this.values, 0, resultingArray, 0, this.len);
                return resultingArray;
            }

            @Override
            public Object[] toArray(Object[] a) {
                Object[] existingArray = this.toArray();
                Object[] resultingArray = new Object[existingArray.length];
                for (int i = 0; i < existingArray.length; i++) {
                    resultingArray[i] = a.getClass().getComponentType().cast(existingArray[i]);
                }
                return resultingArray;
            }

            @Override
            public boolean add(Object e) {
                if (this.len < this.values.length) {
                    this.values[this.len++] = (V) e;
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public boolean remove(Object o) {
                for (int i = 0; i < this.values.length; i++) {
                    if (o.equals(this.values[i])) {
                        this.values[i] = EMPTY_VALUE;
                        this.len--;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean containsAll(Collection c) {
                for (Object value : c.toArray()){
                    if (!this.contains(value)) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public boolean addAll(Collection c) {
                boolean addedAll = true;
                for (Object value : c.toArray()) {
                    if(!this.add(value)) {
                        addedAll = false;
                    }
                }
                return addedAll;
            }

            @Override
            public boolean removeAll(Collection c) {
                boolean removedAll = true;
                for (Object value : c.toArray()) {
                    if(!this.remove(value)) {
                        removedAll = false;
                    }
                }
                return removedAll;
            }

            @Override
            public boolean retainAll(Collection c) {
                boolean retainedAll = true;
                for (Object value : c.toArray()) {
                    
                }
                return retainedAll;
            }

            @Override
            public void clear() {
                for (int i = 0; i < this.values.length; i++) {
                    this.values[i] = EMPTY_VALUE;
                }
                this.len = 0;
            }
        };
    }
    
    private static int convertKey(Object key, int clamp) {
        return Math.abs(key.hashCode() % clamp);
    }
    
    private static void requireNonNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
