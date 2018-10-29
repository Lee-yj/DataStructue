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
	//����һ�����ͷ���
	private Node dummyHead;
	private int size;
	
	public LinkedList(){
		dummyHead = new Node();
		size = 0;
	}
	// ��ȡ������Ԫ�ظ���
	public int getSize(){
		return size;
	}
	//�����Ƿ�����Ϊ��
	public boolean isEmpty(){
		return size == 0;
	}
	// ʹ����ٽڵ���������Ԫ��
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
	//������ͷ���Ԫ��
	public void addFirstByDummyHead(E e){
		addByDummyHead(0, e);
	}
	//������β���Ԫ��
	public void addLastByDummyHead(E e){
		addByDummyHead(size, e);
	}
	//��ȡ����ĵ�index(0-based)Ԫ��
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
	//�޸�����ĵ�index(0-based)Ԫ��ΪE e
	public void set(int index, E e){
		if(index < 0 || index > size)
			throw new IllegalArgumentException("Set failed. Illegal index");
		Node cur = dummyHead.next;
		for(int i = 0; i < index; i ++){
			cur = cur.next;
		}
		cur.e = e;
	}
	//�����������Ƿ���Ԫ��E e
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
	//ɾ�������е�Ԫ�أ�����ɾ��Ԫ��
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
	//ɾ�������еĵ�һ��Ԫ�أ�����ɾ��Ԫ��
	public E removeFirst(){
		return remove(0);
	}
	//ɾ�������е����һ��Ԫ�أ�����ɾ��Ԫ��
	public E removeLast(){
		return remove(size-1);
	}
	// ��������ɾ��Ԫ��e
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
	
	// ����ʹ������ͷ���
	//������ͷ���Ԫ��
//	public void addFirst(E e){
//		Node newHead = new Node(e);
//		newHead.next = head;
//		head = newHead;
//		size ++;
//	}
	//�������м�(head֮��)indexλ�����Ԫ��
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
	//������β���Ԫ��
//	public void addLast(E e){
//		addMid(size, e);
//	}
	
}
