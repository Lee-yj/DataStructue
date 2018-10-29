package utils.UnionFind;

//�������UnionFind��������size�Ͻ����Ż�
//ѡ��Ԫ�ؽ��ٵļ��Ϻϲ���Ԫ�ؽ϶�ļ�����
public class UnionFind3 implements UF{

	private int[] parent;
	private int[] sz;		//sz[i]��ʾ��iΪ���ļ�����Ԫ�صĸ���
	
	public UnionFind3(int size) {
		parent = new int[size];
		sz = new int[size];
		for(int i = 0; i < size; i ++){ //��ʼ������ÿ���ڵ�ָ����
			parent[i] = i;
			sz[i] = 1;
		}
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
		//Ԫ�ؽ��ٵĸ��ڵ�ȥָ��϶�Ԫ�صĸ��ڵ�
		//��Ԫ�ؽ��ٵļ��Ϻϲ���Ԫ�ؽ϶�ļ�����
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
