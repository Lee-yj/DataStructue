package utils.MaxHeapAndPriorityQueue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution347 {
	
	private class Freq implements Comparable<Freq>{
		public int e, freq;
		
		public Freq(int e, int freq){
			this.e = e;
			this.freq = freq;
		}
		
		@Override
		public int compareTo(Freq another) {
			if(this.freq < another.freq)
				return 1;					//基于MaxHeap,频次低的比较为高，先被从堆中取出
			else if(this.freq > another.freq)
				return -1;
			else
				return 0;
		}
		
	}
	
	public List<Integer> topKFrequent(int[] nums, int k) {
		
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int num: nums){
			if(map.containsKey(num)){
				map.put(num, map.get(num) + 1);
			}else{
				map.put(num, 1);
			}
		}
		
		PriorityQueue<Freq> queue = new PriorityQueue<>();
		for(int key: map.keySet())
			if(queue.getSize() < k)
				queue.enqueue(new Freq(key, map.get(key)));
			else if(map.get(key) > queue.getFront().freq){
//				queue.dequeue();
//				queue.enqueue(new Freq(key, map.get(key)));
				queue.replace(new Freq(key, map.get(key)));
			}
		
		List<Integer> list = new LinkedList<>();
		while(!queue.isEmpty()){
			list.add(queue.dequeue().e);
		}
		return list;
    }
	
	public static void main(String[] args) {
		Solution347 solution = new Solution347();
		List<Integer> list = new LinkedList<>();
		int[] nums = {1,1,1,2,2,2,2,3,3,3,3,3,3,4,4,4,4,4,4};
		int k = 3;
		list = solution.topKFrequent(nums, k);
		System.out.println(list);
	}
}
