package utils.UnionFind;

//使用数组存储，但实际上是一种孩子指向父亲的树。若两个元素同指向一个根，那么属于一个集合
public class UnionFind2 implements UF{

	private int[] parent;
	
	public UnionFind2(int size) {
		parent = new int[size];
		for(int i = 0; i < size; i ++) //初始化，让每个节点指向本身
			parent[i] = i;
	}
	// 查找方法，查找元素p对应的集合编号，即其根节点
	// O(h)复杂度，h为树的高度
	private int find(int p){
		if(p < 0 || p >= parent.length)
			throw new IllegalArgumentException("p is out of bound!");
		while(p != parent[p])
			p = parent[p];
		return p;
	}
	
	// 查看两个元素是否所属一个集合
	// O(h)复杂度，h为树的高度
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	// 合并元素p和q所属的集合
	// O(h)复杂度，h为树的高度
	@Override
	public void unionElements(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		
		if(pRoot == qRoot)
			return;
		parent[pRoot] = qRoot;
	}

	@Override
	public int getSize() {
		return parent.length;
	}
}
