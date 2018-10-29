package utils.UnionFind;

//������UnionFind����������rank(�������)�Ͻ����Ż�������·��ѹ�����Ż��� parent[p] = parent[parent[p]] ��p���׽ڵ�Ϊp���׽ڵ�ĸ��׽ڵ�
//ѡ�񽫽�С�����Ĳ����Ľڵ�ϲ����϶�Ľڵ���
//�������ıȽϣ����������õݹ齫�ڵ�һ���Զ�ָ���˸��ڵ㣬������治��һ���Խ��ڵ㶼ָ����ڵ㣬��Ҫ�Ըýڵ����е��òŻ�����ָ����ڵ�
public class UnionFind5 implements UF{

    private int[] rank;   // rank[i]��ʾ��iΪ���ļ�������ʾ�����Ĳ���
    private int[] parent; // parent[i]��ʾ��i��Ԫ����ָ��ĸ��ڵ�

    // ���캯��
    public UnionFind5(int size){
        rank = new int[size];
        parent = new int[size];

        // ��ʼ��, ÿһ��parent[i]ָ���Լ�, ��ʾÿһ��Ԫ���Լ��Գ�һ������
        for( int i = 0 ; i < size ; i ++ ){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    // ���ҹ���, ����Ԫ��p����Ӧ�ļ��ϱ�ţ���find�������·��ѹ����
    // O(h)���Ӷ�, hΪ���ĸ߶�
    private int find(int p){
        if(p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");
        // ����ȥ��ѯ�Լ��ĸ��׽ڵ�, ֱ��������ڵ�  (���ڵ���ص�: parent[p] == p)
        while(p != parent[p]){
        	parent[p] = parent[parent[p]];  // p���׽ڵ�Ϊp���׽ڵ�ĸ��׽ڵ�
        	p = parent[p];
        }
        return p;
    }

    // �鿴Ԫ��p��Ԫ��q�Ƿ�����һ������
    // O(h)���Ӷ�, hΪ���ĸ߶�
    @Override
    public boolean isConnected( int p , int q ){
        return find(p) == find(q);
    }

    // �ϲ�Ԫ��p��Ԫ��q�����ļ���
    // O(h)���Ӷ�, hΪ���ĸ߶�
    @Override
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if( pRoot == qRoot )
            return;
        // ��������Ԫ����������rank��ͬ�жϺϲ�����
        // ��rank�͵ļ��Ϻϲ���rank�ߵļ�����
        if(rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if(rank[qRoot] < rank[pRoot])
            parent[qRoot] = pRoot;
        else{ // rank[pRoot] == rank[qRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;   // ά��rank��ֵ
        }
    }
}
