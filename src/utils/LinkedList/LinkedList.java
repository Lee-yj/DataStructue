package utils.LinkedList;

public class LinkedList<E> {
	
	private class Node{
		public E e;
		public Node next;
		
		public Node(E e, Node next){
			this.e = e;
			this.next = next;
		}
		public Node(E e){
			this(e, null);
		}
		public Node(){
			this(null, null);
		}
		@Override
		public String toString(){
			return e.toString();
		}
	}
	
//	private Node head;
	//设置一个虚假头结点
	private Node dummyHead;
	private int size;
	
	public LinkedList(){
		dummyHead = new Node();
		size = 0;
	}
	// 获取链表中元素个数
	public int getSize(){
		return size;
	}
	//返回是否链表为空
	public boolean isEmpty(){
		return size == 0;
	}
	// 使用虚假节点给链表添加元素
	public void addByDummyHead(int index, E e){
		if(index < 0 || index > size)
			throw new IllegalArgumentException("Add failed. Illegal index");
		Node prev = dummyHead;
		for(int i = 0; i < index; i ++){
			prev = prev.next;
		}
		Node node = new Node(e);
		node.next = prev.next;
		prev.next = node;
		size ++;
	}
	//在链表头添加元素
	public void addFirstByDummyHead(E e){
		addByDummyHead(0, e);
	}
	//在链表尾添加元素
	public void addLastByDummyHead(E e){
		addByDummyHead(size, e);
	}
	//获取链表的第index(0-based)元素
	public E get(int index){
		if(index < 0 || index > size)
			throw new IllegalArgumentException("Get failed. Illegal index");
		Node cur = dummyHead.next;
		for(int i = 0; i < index; i ++){
			cur = cur.next;
		}
		return cur.e;
	}
	public E getFirst(){
		return get(0);
	}
	public E getLast(){
		return get(size - 1);
	}
	//修改链表的第index(0-based)元素为E e
	public void set(int index, E e){
		if(index < 0 || index > size)
			throw new IllegalArgumentException("Set failed. Illegal index");
		Node cur = dummyHead.next;
		for(int i = 0; i < index; i ++){
			cur = cur.next;
		}
		cur.e = e;
	}
	//查找链表中是否有元素E e
	public boolean contains(E e){
		Node cur = dummyHead.next;
		while(cur != null){
			if(cur.e.equals(e)){
				return true;
			}
			cur = cur.next;
		}
		return false;
	}
	//删除链表中的元素，返回删除元素
	public E remove(int index){
		if(index < 0 || index > size)
			throw new IllegalArgumentException("Set failed. Illegal index");
		Node prev = dummyHead;
		for(int i = 0; i < index; i ++)
			prev = prev.next;
		Node delNode = prev.next;
		prev.next = delNode.next; 
		delNode.next = null;
		size --;
		return delNode.e;
	}
	//删除链表中的第一个元素，返回删除元素
	public E removeFirst(){
		return remove(0);
	}
	//删除链表中的最后一个元素，返回删除元素
	public E removeLast(){
		return remove(size-1);
	}
	// 从链表中删除元素e
    public void removeElement(E e){
        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
//		for(Node cur = dummyHead.next; cur != null; cur = cur.next)
//			res.append(cur + "->");
		Node cur = dummyHead.next;
		while(cur != null){
			res.append(cur + "->");
			cur = cur.next;
		}
		res.append("NULL");
		return res.toString();
	}
	
	// 不是使用虚拟头结点
	//在链表头添加元素
//	public void addFirst(E e){
//		Node newHead = new Node(e);
//		newHead.next = head;
//		head = newHead;
//		size ++;
//	}
	//在链表中间(head之后)index位置添加元素
//	public void addMid(int index, E e){
//		if(index < 0 || index > size)
//			throw new IllegalArgumentException("Add failed. Illegal index");
//		if(index == 0)
//			addFirst(e);
//		else {
//			Node prev = head;
//			for(int i = 0; i < index - 1; i ++){
//				prev = prev.next;
//			}
//			Node node = new Node(e);
//			node.next = prev.next;
//			prev.next = node;
//			size ++;
//		}
//	}
	//在链表尾添加元素
//	public void addLast(E e){
//		addMid(size, e);
//	}
	
}
