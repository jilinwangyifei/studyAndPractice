package  DataStructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>B-Tree</code> : <p />
 * 假设B树的度为 m（m>=2），则B树满足如下要求：(参考算法导论)
 * <br />
 * (1)每个非根节点至少包含m-1个关键字,m个指向子节点的指针;至多包含2m-1个关键字,2m个指向子女的指针（叶子节点的子女为空）
 * <br />
 * (2)节点的所有key按非降序存放,假设节点的关键字分别为K[1], K[2] … K[n], 指向子女的指针分别为P[1], P[2]…P[n+1],
 * 其中n为节点关键字的个数。则有：P[1] <= K[1] <= P[2] <= K[2] …..<= K[n] <= P[n+1] // 这里P[n]也指其指向的关键字
 * <br />
 * (3)若根节点非空，则根节点至少包含两个子女;
 * <br />
 * (4)所有的叶子节点都在同一层
 * <p/>
 *
 *
 * 查询:<br />
 * 从<code>root</code>出发,对每个节点,找到大于或等于target关键字中最小的K[i]
 *  <br />
 * (1)如果K[i]与target相等,则查找成功,返回一个标识true,和target下标i
 *  <br />
 * (2)否则查找失败, 返回一个标识false,和一个下标指向其子节点位置,递归search新的子节点,如果是叶节点则表示不存在该关键字
 *
 * <p/>
 * 插入:
 * <br />
 * B树的插入需要沿着搜索的路径从<code>root</code>一直到叶节点,根据B树的规则,每个节点的关键字个数在[m-1, 2m-1]之间,
 * 当target要加入到某个叶子时,如果该叶子节点已经有2m-1个关键字,则再加入target就违反了B树的定义,
 * 这时就需要对该叶子节点进行分裂,将叶子以中间节点为界,分成两个包含m-1个关键字的子节点,
 * 同时把中间节点提升到该叶子的父节点中,如果这样使得父节点的关键字个数超过2m-1,
 * 则要继续向上分裂,直到根节点,根节点的分裂会使得树加高一层
 * <br />
 * 关键:在下降的过程中,一旦遇到已满的节点(关键字个数为2m-1),就就对该节点进行分裂,这样就保证在叶子节点需要分裂时,
 * 其父节点一定是非满的,从而不需要再向上回溯
 *
 * <p/>
 * 删除:
 * <br />
 * B树的删除同样需要沿着搜索的路径从<code>root</code>一直到叶节点
 * 1,根节点只有一个实例,且起子节点都只包含<code>minEntrySize</code>个实例时,树降高(树降高的唯一情形)
 * <br />
 * 2,如果在内部节点node中找到关键字key下标i,观察该节点的第i与i+i个子节点,在它俩中找到实例数大于<code>minEntrySize</code>的
 *    记为tmpNode,中转tmpKey到当前node,在tmpNode中递归删除tmpKey,如果上述两个子节点实例数均不大于<code>minEntrySize</code>
 *    则借当前key合并节点
 * <br />
 * 3,与插入时避免回溯的思想一样,保证(2)中合并节点时,借走父节点的 一个实例,而不须继续向上回溯
 */
public class BTree<K extends Comparable<K>, V> {
    private static final int M = 4;
    private int minEntrySize;
    private int maxEntrySize;
    private Node root;
    private int size;
    private int depth;

    public BTree() {
        this(M);
    }

    public BTree(int m) {
        this.minEntrySize = m - 1;
        this.maxEntrySize = (m * 2) - 1;
        this.root = new Node(true);
    }

    /**
     * @return 树中不存在当前key时返回null, 否则返回上次key保存的值
     */
    public V put(K key, V value) {
        if (root.isFull()) {// 创建新root
            Node newRoot = new Node(false);
            newRoot.children.add(root);

            splitFullNode(root, newRoot, 0);
            root = newRoot;
            depth++;
        }
        return insert(root, key, value);
    }

    public V get(K key) {

        return serach(root, key);
    }

    private V serach(Node current, K key) {
        SerachResult sr = current.serach(key);
        if (sr.serached) {
            return current.entrys.get(sr.wudindex).value;
        } else {
            if (!current.isLeaf) {
                return serach(current.children.get(sr.wudindex), key);
            }
            //递归到叶节点中仍没有查询到
            return null;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int depth() {
        return depth;
    }

    private V insert(Node current, K key, V value) {
        if (current.isFull()) {
            throw new UnsupportedOperationException("只能为非满节点插入实例...");
        }
        SerachResult sr = current.serach(key);

        if (sr.serached) {
            V oldValue = current.entrys.get(sr.wudindex).value;
            current.entrys.get(sr.wudindex).value = value;
            return oldValue;
        }
        if (!current.isLeaf) {
            Node wudNode = current.children.get(sr.wudindex);
            if (wudNode.isFull()) {
                // 回溯过程中每遇到一个满实例节点就立刻分裂
                // 保证在叶节点需要分裂时,其父节点一定是非满的,从而不需要再向上回溯
                splitFullNode(wudNode, current, sr.wudindex);

                if (key.compareTo(current.entrys.get(sr.wudindex).key) > 0) {//需要插入到分裂出的兄弟节点中
                    wudNode = current.children.get(sr.wudindex + 1);
                }
            }

            return insert(wudNode, key, value);
        } else {//所有节点都在叶节点加入
            current.entrys.add(sr.wudindex, new Entry(key, value));
            size++;
        }
        return null;
    }

    /**
     * 将一个满实例节点分割, 中间实例提取至父节点中, 分隔出的部分((m - 1)个)实例放入新生成的兄弟节点中
     * @param parent 父节点
     * @param fullNode 满实例节点
     * @param index 满实例节点在父节点中的位置(提取出中间实例应在父节点中插入的位置)
     */
    private void splitFullNode(Node fullNode, Node parent, int index) {
        if (!fullNode.isFull()) {
            throw new UnsupportedOperationException("不能对非满节点经行分裂!");
        }
        int middleIndex = (fullNode.entrys.size() - 1) / 2;
        Entry middleEntry = fullNode.entrys.get(middleIndex);
        //此处已保证parent加入新节点后仍满足B-tree特性
        parent.entrys.add(index, middleEntry);

        Node sibling = new Node(fullNode.isLeaf);
        //采用向右分割
        for (int i = maxEntrySize - 1; i > middleIndex; i--) {
            sibling.entrys.add(0, fullNode.entrys.get(i));
            fullNode.entrys.remove(i);
        }
        fullNode.entrys.remove(middleIndex);
        parent.children.add(index + 1, sibling);

        //如果分割的不是叶节点, 则fullNode的后半部分(m个)子节点将成为sibling的子节点
        if (!fullNode.isLeaf) {
            int cs = fullNode.children.size();
            for (int i = cs - 1; i >= (cs / 2); i--) {
                sibling.children.add(0, fullNode.children.get(i));
                fullNode.children.remove(i);
            }
        }
    }

    private class Node {
        List<Entry> entrys;
        List<Node> children;
        boolean isLeaf;

        Node(boolean isLeaf) {
            this.entrys = new ArrayList<Entry>();
            this.children = new ArrayList<Node>();
            this.isLeaf = isLeaf;
        }

        /**
         * 在当前节点中查询
         * @param key 搜索关键字
         * @return {@link SerachResult}
         */
        SerachResult serach(K key) {
            int left = 0;
            int right = entrys.size() - 1;
            int middle = 0;

            while (left <= right) {
                middle = (left + right) / 2;

                K ck = entrys.get(middle).key;
                if (key.compareTo(ck) < 0) {
                    right = middle - 1;
                } else if(key.compareTo(ck) > 0) {
                    left = middle + 1;
                } else {
                    break;
                }
            }

            boolean flag = true;
            if (left > right) {//未找到
                flag = false;
            }

            return new SerachResult(flag, left);
        }

        boolean isFull() {
            return entrys.size() == maxEntrySize;
        }
    }

    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{key:" + key + ", value:" + value + "}";
        }
    }

    /**
     * 在节点中单次查询结果
     */
    private class SerachResult {
        /**
         * 是否查询到
         */
        boolean serached;
        /**
         * 查到时为在node实例集中的位置
         * 未查到时表示在node子节点集对应节点中
         */
        int wudindex;

        SerachResult(boolean serached, int wudindex) {
            this.serached = serached;
            this.wudindex = wudindex;
        }
    }
}