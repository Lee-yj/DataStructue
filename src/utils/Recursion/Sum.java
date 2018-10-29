package utils.Recursion;

/**
 * 求数组array 的和
 */
public class Sum {
	
	public static int sum(int[] arr){
		return sum(arr, 0);
	}
	
//	index为数组arr的左边界，计算[index，n]的求和
	private static int sum(int[] arr, int index){
		if(index == arr.length){
			return 0;
		}
		int sum = arr[index] + sum(arr, index + 1);
		return sum;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6};
		System.out.println(sum(arr));
	}
	
}
