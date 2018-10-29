package utils.Recursion;
/**
 * LeetCode 203Ã‚
 */
public class Solution203 {
	
	public ListNode removeElement(ListNode head, int val){
		if(head == null){
			return null;
		}
		ListNode newHead = removeElement(head.next, val);
		if(head.val == val){
			return newHead;
		}else{
			head.next = newHead;
			return head;
		}
	}
	
	
	public static void main(String[] args) {
		Solution203 solution = new Solution203();
		int []arr = {1,1,2,3,4,1,5};
		ListNode head = new ListNode(arr);
		System.out.println(head);
		System.out.println(solution.removeElement(head, 1));
	}
}
