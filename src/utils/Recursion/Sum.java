package utils.Recursion;

/**
 * ������array �ĺ�
 */
public class Sum {
	
	public static int sum(int[] arr){
		return sum(arr, 0);
	}
	
//	indexΪ����arr����߽磬����[index��n]�����
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
