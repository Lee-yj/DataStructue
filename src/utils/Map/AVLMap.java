package utils.Map;

import utils.AVLTree.AVLTree;
import utils.Map.InterFace.Map;

public class AVLMap<K extends Comparable<K>, V> implements Map<K, V>{

	private AVLTree<K, V> map;
	
	public AVLMap() {
		map = new AVLTree<>();
	}
	
	@Override
	public void add(K key, V value) {
		map.add(key, value);
	}

	@Override
	public V remove(K key) {
		return remove(key);
	}

	@Override
	public boolean contains(K key) {
		return map.contains(key);
	}

	@Override
	public V get(K key) {
		return map.get(key);
	}

	@Override
	public void set(K key, V newValue) {
		map.set(key, newValue);
	}

	@Override
	public int getSize() {
		return map.getSize();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

}
