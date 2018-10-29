package utils.Map;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 */
public class LeetCode350 {
	public int[] intersect(int[] nums1, int[] nums2) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for(int i = 0; i < nums1.length; i ++){
			if(!map.containsKey(nums1[i])){
				map.put(nums1[i], 1);
			}else{
				map.put(nums1[i], map.get(nums1[i]) + 1);
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < nums2.length; i ++){
			if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0){
				list.add(nums2[i]);
				map.put(nums2[i], map.get(nums2[i]) - 1);
			}
		}
		int[] res = new int[list.size()];
		for(int i = 0; i < list.size(); i ++){
			res[i] = list.get(i);
		}
		return res;
    }
	
	public static void main(String[] args) {
		LeetCode350 lc = new LeetCode350();
		int[] nums2 = {4,9,5};
		int[] nums1 = {9,4,9,8,4};
		int[] res = lc.intersect(nums1, nums2);
		for(int i : res){
			System.out.print(i + " ");
		}
	}
}
