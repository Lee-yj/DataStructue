package utils.SegmentTree;

//LeetCode-307  区域求和-可变性
public class NumArray2 {
	
	private SegmentTree<Integer> segmentTree;
	
	public NumArray2(int[] nums) {
		if(nums.length > 0){
			Integer[] data = new Integer[nums.length];
			for(int i = 0; i < data.length; i ++){
				data[i] = nums[i];
			}
			segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
		}
	}
	public void update(int index, int val){
		if(segmentTree == null)
			throw new IllegalArgumentException("segmentTree is empty!");
		segmentTree.set(index, val);
	}
	public int sumRange(int i, int j) {
		if(segmentTree == null)
			throw new IllegalArgumentException("segmentTree is empty!");
	    return segmentTree.query(i, j);
 	}
}
