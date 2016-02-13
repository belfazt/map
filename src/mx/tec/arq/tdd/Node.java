/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.tec.arq.tdd;

/**
 *
 * @author dcamargo
 */
public class Node<K, V> {
    
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
