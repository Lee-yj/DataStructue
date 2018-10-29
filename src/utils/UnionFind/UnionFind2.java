package utils.UnionFind;

//ʹ������洢����ʵ������һ�ֺ���ָ���׵�����������Ԫ��ָͬ��һ��������ô����һ������
public class UnionFind2 implements UF{

	private int[] parent;
	
	public UnionFind2(int size) {
		parent = new int[size];
		for(int i = 0; i < size; i ++) //��ʼ������ÿ���ڵ�ָ����
			parent[i] = i;
	}
	// ���ҷ���������Ԫ��p��Ӧ�ļ��ϱ�ţ�������ڵ�
	// O(h)���Ӷȣ�hΪ���ĸ߶�
	private int find(int p){
		if(p < 0 || p >= parent.length)
			throw new IllegalArgumentException("p is out of bound!");
		while(p != parent[p])
			p = parent[p];
		return p;
	}
	
	// �鿴����Ԫ���Ƿ�����һ������
	// O(h)���Ӷȣ�hΪ���ĸ߶�
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	// �ϲ�Ԫ��p��q�����ļ���
	// O(h)���Ӷȣ�hΪ���ĸ߶�
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
