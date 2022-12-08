/*
 * AUTHOR: Merle Crutchfield
 * FILE: MyHashMap.java
 * ASSIGNMENT: PA8 - Generic HashMap
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This assignment was meant to gain more practice with generics and 
 * hashTables, which involves us creating out own HashMap. We use the 
 * HashNode file from a previous drill in this project. I created an
 * ArrayList of HashNodes, which represents an ArrayList of LinkedLists.
 * This allowed me to deal with overflow and not worry about generics with
 * an array instead. The number of buckets is set to 8, which is used in our
 * hash method for finding the index of where the key-val pairs go. Our 
 * clear method clears the ArrayList, and containsKey and containsValue
 * return true or false if they have the corresponding key or value passed.
 * We have a get method that returns value to the entered key, as well as 
 * isEmpty which does what the name suggests. Our printTable prints out 
 * the number of collisions in an accessible way. The put and remove methods
 * add the key-value pair or remove based on the input. Finally the size
 * method returns the number of key-value pairs.
 * 
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K, V> {
	// Private fields
	private int numBuckets;
	private ArrayList<HashNode> list = new ArrayList<HashNode>(8);
	private int size = 0;
	
	/*
	 * Constructs an empty HashMap with the default initial capacity
	 * of 8.
	 */
	public MyHashMap() {
		this.numBuckets = 8;
		for (int i = 0; i < 8; i ++)
			list.add(null);
	}
	
	/*
	 * Hash function given to us through the PA spec. Returns the 
	 * index that was passed.
	 */
	private int hash(K key) {
		int hashCode = key.hashCode();
		int index = hashCode % numBuckets;
		return Math.abs(index);
	}
	
	/*
	 * Clears the map by removing all the mappings
	 * from the map. Sets the size back to 0.
	 */
	public void clear() {
		list = new ArrayList<HashNode>(8);
		size = 0;
		for (int i = 0; i < 8; i ++)
			list.add(null);
	}
	
	/*
	 * Returns true if the key entered by the
	 * user is in the map. The input is the 
	 * key, and the output is true or false
	 * if the key is found.
	 */
	public boolean containsKey(K key) {
		int index = hash(key);
		HashNode<K, V> temp = list.get(index);
		while (!(temp == null)) {
			if (temp.getKey().equals(key))
				return true;
			temp = temp.getNext();
		}
		return false;
	}
	
	/*
	 * Returns true if the value entered by the
	 * user is in the map. The input is the 
	 * value, and the output is true or false
	 * if the value is found.
	 */
	public boolean containsValue(V val) {
		for (int i = 0; i < 8; i++) {
			HashNode<K, V> temp = list.get(i);
			while (!(temp == null)) {
				if (temp.getValue().equals(val))
					return true;
				temp = temp.getNext();
			}
		}
		return false;
	}
	
	/*
	 * Returns the value associated with the key
	 * that was passed. The input is the key,
	 * and it returns the value. It will return
	 * null if there is no key-value pair.
	 */
	public V get(K key) {
		int index = hash(key);
		HashNode<K, V> temp = list.get(index);
		while (!(temp == null)) {
			if (temp.getKey().equals(key))
				return temp.getValue();
			temp = temp.getNext();
		}
		return null;
	}
	
	/*
	 * Returns true if there are no key-value pairs,
	 * and false if there are.
	 */
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}
	
	/*
	 * Returns a set of the keys that were in the
	 * map. No arguments are passed but iterates through
	 * each key-value pair and adds the keys to the
	 * set before returning it.
	 */
	public java.util.Set<K> keySet() {
		Set<K> keySet = new HashSet<K>();
		for (int i = 0; i < 8; i++) {
			HashNode<K, V> temp = list.get(i);
			while (!(temp == null)) {
				keySet.add(temp.getKey());
				temp = temp.getNext();
			}
		}
		return keySet;
	}
	
	/*
	 * Prints out a table that shows how many conflicts
	 * occur at each of the buckets. It shows each index
	 * and the conflicts along with the keys.
	 */
	public void printTable() {
		int total = 0;
        for (int i = 0; i < 8; i++) {
        	int collision = -1;
        	HashNode<K, V> temp = list.get(i);
        	String ans = "[";
    		while (!(temp == null)) {
    			collision += 1;
    			ans += temp.getKey().toString();
    			ans += ", ";
    			temp = temp.getNext();
    		}
    		if (collision == -1)
    			collision = 0;
            total += collision;
            System.out.println("Index " + i + ": (" + collision
                    + " conflicts), " + ans + "]");
        }
        System.out.println("Total # of conflicts: " + total);
	}
	
	/*
	 * Adds the key-value mapping entered by the user
	 * into the map. The input is they key and value
	 * passed by the user, and it returns the value associated
	 * with the key.
	 */
	public V put(K key, V val) {
		if (containsKey(key)) {
			int index = hash(key);
			HashNode<K, V> temp = list.get(index);
			while (!(temp == null)) {
				if (temp.getKey().equals(key))
					temp.setValue(val);
				temp = temp.getNext();
			}
			return val;
		}
		else {
			HashNode<K, V> node = new HashNode(key, val);
			int index = hash(key);
			HashNode<K, V> temp = list.get(index);
			node.setNext(temp);
			list.set(index, node);
			size += 1;
			return val;
		}
	}
	
	/*
	 * Removees the key-value mapping for the specified
	 * key if it is present. The input is the key to
	 * be removed and it returns the previous value associated
	 * with the key, and null if there was no mapping.
	 */
	public V remove(K key) {
		int index = hash(key);
		HashNode<K, V> temp = list.get(index);
		if (temp == null)
			return null;
		if (temp.getKey().equals(key)) {
			V val = temp.getValue();
			HashNode<K, V> tail = temp.getNext();
			list.set(index, tail);
			size -= 1;
			return val;
		}
		else {
			while (!(temp.getNext() == null)) {
				if (temp.getNext().getKey().equals(key)) {
					V val = temp.getNext().getValue();
					HashNode<K, V> tail = temp.getNext().getNext();
					temp.setNext(tail);
					size -= 1;
					return val;
				}
				temp = temp.getNext();
			}
			return null;
		}
	}
	
	/*
	 * Returns the number of key-value mappings
	 * in this map.
	 */
	public int size() {
		return size;
	}
}