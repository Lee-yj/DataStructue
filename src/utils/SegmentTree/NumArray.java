package utils.SegmentTree;

//LeetCode-303  区域求和-不变性
public class NumArray {
	
	private SegmentTree<Integer> segmentTree;
	
	public NumArray(int[] nums) {
		if(nums.length > 0){
			Integer[] data = new Integer[nums.length];
			for(int i = 0; i < data.length; i ++){
				data[i] = nums[i];
			}
			segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
		}
	}
	public int sumRange(int i, int j) {
		if(segmentTree == null)
			throw new IllegalArgumentException("segmentTree is empty!");
	    return segmentTree.query(i, j);
 	}
}
