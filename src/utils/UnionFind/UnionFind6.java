package utils.UnionFind;

//�������UnionFind����������rank(�������)�Ͻ����Ż�������·��ѹ�����Ż����ýڵ�ݹ鶼ָ����ڵ�
//ѡ�񽫽�С�����Ĳ����Ľڵ�ϲ����϶�Ľڵ���
public class UnionFind6 implements UF{

    private int[] rank;   // rank[i]��ʾ��iΪ���ļ�������ʾ�����Ĳ���
    private int[] parent; // parent[i]��ʾ��i��Ԫ����ָ��ĸ��ڵ�

    // ���캯��
    public UnionFind6(int size){
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
        // ��ѯp�ĸ��׽ڵ�, �Ƿ�Ϊ���ڵ� (���ڵ���ص�: parent[p] == p)
        // ��p���ڵ㲻�Ǹ��ڵ㣬��ݹ齫p�ĸ��ڵ�ָ����ڵ�-------------���⣺����ѯ·�������нڵ㶼ָ���˸��ڵ㣬����Ľ��������Ĳ�����
        //															   ����Ҫ�ݹ�ʵ��
        if(p != parent[p])
        	parent[p] = find(parent[p]);
        // ��p���ڵ��Ǹ��ڵ㣬��return��p���ڵ�
        return parent[p];
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
