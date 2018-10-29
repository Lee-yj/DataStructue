package utils.UnionFind;

//第三版的UnionFind，在树的size上进行优化
//选择将元素较少的集合合并到元素较多的集合中
public class UnionFind3 implements UF{

	private int[] parent;
	private int[] sz;		//sz[i]表示以i为根的集合中元素的个数
	
	public UnionFind3(int size) {
		parent = new int[size];
		sz = new int[size];
		for(int i = 0; i < size; i ++){ //初始化，让每个节点指向本身
			parent[i] = i;
			sz[i] = 1;
		}
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
		//元素较少的根节点去指向较多元素的根节点
		//将元素较少的集合合并到元素较多的集合中
		if(sz[pRoot] < sz[qRoot]){ 
			parent[pRoot] = qRoot; 
			sz[qRoot] += sz[pRoot];
		}else{
			parent[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
	}

	@Override
	public int getSize() {
		return parent.length;
	}
}
