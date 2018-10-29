package utils.SegmentTree;

@SuppressWarnings("unchecked")
public class SegmentTree<E> {
	
	private E[] data;
	private E[] tree;
	private Merger<E> merger; // 定义节点的定义（求和，最大值等等）
	
	public SegmentTree(E[] arr, Merger<E> merger) {
		this.merger = merger;
		
		data = (E[]) new Object[arr.length];
		for(int i = 0; i < arr.length; i ++)
			data[i] = arr[i];
		
		tree = (E[]) new Object[arr.length * 4]; //线段树SegmentTree用数组存储，需要用4*n的空间
		buildSegmentTree(0, 0, data.length - 1);
	}
	//在treeIndex的索引位置创建区间为[l,r]的线段树，三个参数：根节点的索引，及区间的左右端点
	private void buildSegmentTree(int treeIndex, int l, int r) {
		if(l == r){
			tree[treeIndex] = data[l];
			return;
		}
		
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);
		int mid = (r-l)/2 + l;  //--(l+r)/2
		//[l,r]-->mid-->[l,mid]+[mid+1,r]
		buildSegmentTree(leftTreeIndex, l, mid);
		buildSegmentTree(rightTreeIndex, mid+1, r);
		
		tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
	}

	public E get(int index){
		if(index < 0 || index >= data.length)
			throw new IllegalArgumentException("Index is illegal!");
		return data[index];
	}
	public int getSize(){
		return data.length;
	}
	private int leftChild(int index){
		return 2 * index + 1;
	}
	private int rightChild(int index){
		return 2 * index + 2;
	}
	
	//查询区间[queryL,queryR]的值
	public E query(int queryL, int queryR){
		if(queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
			throw new IllegalArgumentException("Index is illegal!");
		return query(0, 0, data.length-1, queryL, queryR);
	}
	// 在以treeIndex为根的线段树中[L...R]范围里，搜索区间[queryL...queryR]的值
	private E query(int treeIndex, int l, int r, int queryL, int queryR){
		if(l == queryL && r ==queryR)
			return tree[treeIndex];
		int mid = (r-l)/2 + l;  //[l,r]-->[l,mid]+[mid+1,r]
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);
		if(mid+1 <= queryL)
			return query(rightTreeIndex, mid+1, r, queryL, queryR);
		else if(mid >= queryR)
			return query(leftTreeIndex, l, mid, queryL, queryR);
		//否则横跨左右孩子区间
		E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
		E rightResult = query(rightTreeIndex, mid+1, r, mid+1, queryR);
		return merger.merge(leftResult, rightResult);
	}
	
	//将data中的index位置的元素更新为e
	public void set(int index, E e){
		if(index < 0 || index > data.length)
			throw new IllegalArgumentException("index is illegal!");
		data[index] = e;
		set(0, 0, data.length-1, index, e);
	}
	//在treeIndex为根的线段树中更新index索引位置的值为e
	private void set(int treeIndex, int l, int r, int index, E e){
		if(l == r){
			tree[treeIndex] = e;
			return;
		}
		int mid = (r-l)/2 + l;  //[l,r]-->[l,mid]+[mid+1,r]
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);
		if(index >= mid+1)
			set(rightTreeIndex, mid+1, r, index, e);
		else //(index <= mid)
			set(leftTreeIndex, l, mid, index, e);
		
		//从根节点一直找到index叶子节点的位置，将其更新，然后再把父亲节点都重新融合一遍
		tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("[");
		for(int i = 0; i < tree.length; i ++){
			if(tree[i] != null)
				res.append(tree[i]);
			else
				res.append("null");
			if(i != tree.length - 1)
				res.append(", ");
		}
		res.append("]");
		return res.toString();
	}
}
