package utils.HashTable;

import java.util.TreeMap;

// 哈希表的重点： 是一个长度为M的数组；每个数组索引对应着一个Tree的结构存储哈希冲突的key、value，这里使用的是TreeMap
// 哈希表的数组大小M，最好为一个素数，这样添加元素可以分布均匀
//	     有个小BUG，HashTable<K, V>中K不要求comparable，但是哈希表的地址表示的是TreeM<K, V>，这里K又是comparable，所以还是只能传入一个comparable的类型
// 	  Java8之后，哈希表的地址先是一个链表，在哈希冲突达到一定程度后，若是comparable类型则转成红黑树，否则继续保持链表
@SuppressWarnings("unchecked")
public class HashTable<K, V> {
	
	// 哈希表初始容量M的素数表
	private final int[] capacity= {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
								49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
								12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
	
	private static final int upperTol = 10;
	private static final int lowerTol = 2;
	private int capacityIndex = 0;
	
	private TreeMap<K, V>[] hashTable; 	// 是一个TreeMap类型的数组,每个数组的索引位置都是一个TreeMap<K, V>
	private int M;
	private int size;
	
	public HashTable() {
		this.M = capacity[capacityIndex];
		size = 0;
		hashTable = new TreeMap[M];
		for(int i = 0; i < M; i ++)
			hashTable[i] = new TreeMap<>();		// 给每个数组地址初始化给TreeMap
	}
	
	// 将key值先转成hashCode再消除符号，然后在对M取模，得到这个hashtable的索引
	private int hash(K key){
		return (key.hashCode() & 0xfffffff) % M;  
	}
	
	public void add(K key, V value){
		TreeMap<K, V> map = hashTable[hash(key)];
		if(map.containsKey(key))
			map.put(key, value);
		else{
			map.put(key, value);
			size ++;
			
			if(size >= upperTol * M && capacityIndex + 1 < capacity.length){  // N/M >= upperTol时，扩容，且不能超出素数表的长度
				capacityIndex ++;
				resize(capacity[capacityIndex]);
			}
		}
	}
	
	public V remove(K key) {
		TreeMap<K, V> map = hashTable[hash(key)];
		V ret = null;
		if(map.containsKey(key)){
			ret = map.remove(key);
			size --;
			
			if(size < lowerTol * M && capacityIndex - 1 >= 0) { // N/M < lowerTol时，缩容， 且不能小于初始容积
				capacityIndex --;
				resize(capacity[capacityIndex]);
			}
		}
		return ret;
	}
	// 动态对数组扩缩
	private void resize(int newM) {
		TreeMap<K, V>[] newHashTable = new TreeMap[newM];
		for(int i = 0; i < newM; i ++)
			newHashTable[i] = new TreeMap<>();
		// 需要提前准备M，1是边界值，2是newHashTable的hash方法中的M为新的扩缩容后的值
		int oldM = M;
		this.M = newM;
		for(int i = 0; i < oldM; i ++){		// 循环将原先hashTable中的key取出，重新放入newHashTable
			TreeMap<K, V> map = hashTable[i];
			for(K key : map.keySet())
				newHashTable[hash(key)].put(key, map.get(key));
		}
		this.hashTable = newHashTable;
	}
	
	public void set(K key, V value){
		TreeMap<K, V> map = hashTable[hash(key)];
		if(!map.containsKey(key))
			throw new IllegalArgumentException(key + "doesn't exist!");
		map.put(key, value);
	}
	
	public boolean contains(K key) {
		TreeMap<K, V> map = hashTable[hash(key)];
		return map.containsKey(key);
	}
	
	public V get(K key) {
		TreeMap<K, V> map = hashTable[hash(key)];
		return map.get(key);
	}
	
	public int getSize(){
		return size;
	}
	
}
