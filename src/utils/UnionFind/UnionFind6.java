package utils.UnionFind;

//第六版的UnionFind，根据树的rank(树的深度)上进行优化，再在路径压缩上优化，让节点递归都指向根节点
//选择将较小的树的层数的节点合并到较多的节点上
public class UnionFind6 implements UF{

    private int[] rank;   // rank[i]表示以i为根的集合所表示的树的层数
    private int[] parent; // parent[i]表示第i个元素所指向的父节点

    // 构造函数
    public UnionFind6(int size){
        rank = new int[size];
        parent = new int[size];

        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for( int i = 0 ; i < size ; i ++ ){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    // 查找过程, 查找元素p所对应的集合编号（在find中添加上路径压缩）
    // O(h)复杂度, h为树的高度
    private int find(int p){
        if(p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");
        // 查询p的父亲节点, 是否为根节点 (根节点的特点: parent[p] == p)
        // 若p父节点不是根节点，则递归将p的父节点指向根节点-------------问题：将查询路径的所有节点都指向了根节点，极大的降低了树的层数，
        //															   但需要递归实现
        if(p != parent[p])
        	parent[p] = find(parent[p]);
        // 若p父节点是根节点，则return回p父节点
        return parent[p];
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    @Override
    public boolean isConnected( int p , int q ){
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    @Override
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if( pRoot == qRoot )
            return;
        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到rank高的集合上
        if(rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if(rank[qRoot] < rank[pRoot])
            parent[qRoot] = pRoot;
        else{ // rank[pRoot] == rank[qRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;   // 维护rank的值
        }
    }
}
