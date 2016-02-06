package mx.tec.arq.tdd;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @author jzab
 */
public interface Map<K, V> {

  /**
   * Remove all the elements from the map.
   */
  public void clear();

  /**
   * Returns true if contains the given key
   * <p>
   * @param key Key
   * <p>
   * @return true if key exist
   */
  public boolean containsKey( K key );

  /**
   * Returns true if contains value
   * <p>
   * @param value Value
   * <p>
   * @return true if value is stored
   */
  public boolean containsValue( V value );

  /**
   * Return the Value associated with this key
   * <p>
   * @param key Key
   * <p>
   * @return value
   */
  public V get( K key );

  /**
   * Return the value if exists or the defaulr
   * <p>
   * @param key          Key
   * @param defaultValue Value if not exists
   * <p>
   * @return
   */
  public V getOrDefault( K key, V defaultValue );

  /**
   * Return true if Empty.
   * <p>
   * @return
   */
  public boolean isEmpty();

  /**
   * Put value on map.
   * <p>
   * @param key
   * @param value
   */
  public void put( K key, V value );

  /**
   * Remove element.
   * <p>
   * @param key
   * <p>
   * @return
   */
  public V remove( K key );

  /**
   * Replace element
   * <p>
   * @param key
   * @param value
   * <p>
   * @return
   * @throws NoSuchElementException If the element to replace don't exist
   */
  public boolean replace( K key, V value ) throws NoSuchElementException;

  /**
   * Return the Size.
   * <p>
   * @return
   */
  public int size();

  /**
   * Return all the stored values.
   * <p>
   * @return
   */
  public Collection<V> values();


}
