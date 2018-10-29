package utils.HashTable;

import java.util.TreeMap;

// ��ϣ����ص㣺 ��һ������ΪM�����飻ÿ������������Ӧ��һ��Tree�Ľṹ�洢��ϣ��ͻ��key��value������ʹ�õ���TreeMap
// ��ϣ��������СM�����Ϊһ���������������Ԫ�ؿ��Էֲ�����
//	     �и�СBUG��HashTable<K, V>��K��Ҫ��comparable�����ǹ�ϣ��ĵ�ַ��ʾ����TreeM<K, V>������K����comparable�����Ի���ֻ�ܴ���һ��comparable������
// 	  Java8֮�󣬹�ϣ��ĵ�ַ����һ�������ڹ�ϣ��ͻ�ﵽһ���̶Ⱥ�����comparable������ת�ɺ���������������������
@SuppressWarnings("unchecked")
public class HashTable<K, V> {
	
	// ��ϣ���ʼ����M��������
	private final int[] capacity= {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
								49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
								12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
	
	private static final int upperTol = 10;
	private static final int lowerTol = 2;
	private int capacityIndex = 0;
	
	private TreeMap<K, V>[] hashTable; 	// ��һ��TreeMap���͵�����,ÿ�����������λ�ö���һ��TreeMap<K, V>
	private int M;
	private int size;
	
	public HashTable() {
		this.M = capacity[capacityIndex];
		size = 0;
		hashTable = new TreeMap[M];
		for(int i = 0; i < M; i ++)
			hashTable[i] = new TreeMap<>();		// ��ÿ�������ַ��ʼ����TreeMap
	}
	
	// ��keyֵ��ת��hashCode���������ţ�Ȼ���ڶ�Mȡģ���õ����hashtable������
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
			
			if(size >= upperTol * M && capacityIndex + 1 < capacity.length){  // N/M >= upperTolʱ�����ݣ��Ҳ��ܳ���������ĳ���
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
			
			if(size < lowerTol * M && capacityIndex - 1 >= 0) { // N/M < lowerTolʱ�����ݣ� �Ҳ���С�ڳ�ʼ�ݻ�
				capacityIndex --;
				resize(capacity[capacityIndex]);
			}
		}
		return ret;
	}
	// ��̬����������
	private void resize(int newM) {
		TreeMap<K, V>[] newHashTable = new TreeMap[newM];
		for(int i = 0; i < newM; i ++)
			newHashTable[i] = new TreeMap<>();
		// ��Ҫ��ǰ׼��M��1�Ǳ߽�ֵ��2��newHashTable��hash�����е�MΪ�µ������ݺ��ֵ
		int oldM = M;
		this.M = newM;
		for(int i = 0; i < oldM; i ++){		// ѭ����ԭ��hashTable�е�keyȡ�������·���newHashTable
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
