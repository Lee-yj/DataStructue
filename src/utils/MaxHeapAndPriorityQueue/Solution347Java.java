package utils.MaxHeapAndPriorityQueue;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution347Java {
	
	private class Freq{
		public int e, freq;
		
		public Freq(int e, int freq){
			this.e = e;
			this.freq = freq;
		}
	}
	//设置PriorityQueue的比较器Comparator (Java的优先队列可以设置Comparator，不用override compareTo()方法)
	private class FreqComparator implements Comparator<Freq>{
		@Override
		public int compare(Freq a, Freq b) {
			return a.freq - b.freq;
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
		
		//JAVA定义的PriorityQueue的底层是最小堆
		PriorityQueue<Freq> queue = new PriorityQueue<>(new FreqComparator());
			//还可以将Comparator类当作PriorityQueue的匿名类
//		PriorityQueue<Freq> queue = new PriorityQueue<>(new Comparator<Freq>() {
//			@Override
//			public int compare(Freq a, Freq b) {
//				return a.freq - b.freq;
//			}
//		});
		for(int key: map.keySet())
			if(queue.size() < k)
				queue.add(new Freq(key, map.get(key)));
			else if(map.get(key) > queue.peek().freq){
				queue.remove();
				queue.add(new Freq(key, map.get(key)));
			}
		
		List<Integer> list = new LinkedList<>();
		while(!queue.isEmpty()){
			list.add(queue.remove().e);
		}
		return list;
    }
	
	public static void main(String[] args) {
		Solution347Java solution = new Solution347Java();
		List<Integer> list = new LinkedList<>();
		int[] nums = {1,1,1,2,2,2,2,3,3,3,3,3,3,4,4,4,4,4,4};
		int k = 3;
		list = solution.topKFrequent(nums, k);
		System.out.println(list);
	}
}
