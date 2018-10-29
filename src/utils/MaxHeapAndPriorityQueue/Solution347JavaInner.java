package utils.MaxHeapAndPriorityQueue;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution347JavaInner {
	
	public List<Integer> topKFrequent(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int num: nums){
			if(map.containsKey(num)){
				map.put(num, map.get(num) + 1);
			}else{
				map.put(num, 1);
			}
		}
		
		//JAVA定义的PriorityQueue的底层是最小堆---将Comparator类当作PriorityQueue的匿名类,同时利用匿名内部类将Freq类用Integer代替
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return map.get(a) - map.get(b);
			}
		});
		//Java8之后，可以使用lambda表达式代替匿名类
//		PriorityQueue<Integer> queue = new PriorityQueue<>(
//				(a, b) -> map.get(a) - map.get(b)
//		);
		
		for(int key: map.keySet())
			if(queue.size() < k)
				queue.add(key);
			else if(map.get(key) > map.get(queue.peek())){
				queue.remove();
				queue.add(key);
			}
		
		List<Integer> list = new LinkedList<>();
		while(!queue.isEmpty()){
			list.add(queue.remove());
		}
		return list;
    }
	
	public static void main(String[] args) {
		Solution347JavaInner solution = new Solution347JavaInner();
		List<Integer> list = new LinkedList<>();
		int[] nums = {1,1,1,2,2,2,2,3,3,3,3,3,3,4,4,4,4,4,4};
		int k = 3;
		list = solution.topKFrequent(nums, k);
		System.out.println(list);
	}
}
