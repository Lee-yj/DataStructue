package utils.UnionFind;

public class UnionFind1 implements UF{

	private int[] id;
	
	public UnionFind1(int size) {
		id = new int[size];
		for(int i = 0; i < id.length; i ++)
			id[i] = i;					   	
	}
	
	//查找元素p所对应的集合编号
	private int find(int p){
		if(p < 0 || p >= id.length)
			throw new IllegalArgumentException("p is out of bound!");
		return id[p];
	}
	
	@Override //查看元素p、q是否同一个集合 
	public boolean isConnected(int p, int q) {
		return  find(p) == find(q);
	}

	@Override // 合并元素p、q元素所属的集合
	public void unionElements(int p, int q) {
		int pID = find(p);
		int qID = find(q);
		
		if(pID == qID)
			return;
		for(int i = 0; i < id.length; i ++)
			if(id[i] == pID)
				id[i] = qID;
	}

	@Override
	public int getSize() {
		return id.length;
		
	}
}
