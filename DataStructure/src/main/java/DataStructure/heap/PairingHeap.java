
package  DataStructure.heap;

import  DataStructure.UnderflowException;

import java.util.ArrayList;

// PairingHeap class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// Position insert( x )   --> Insert x, return position
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// int size( )            --> Return size of priority queue
// void makeEmpty( )      --> Remove all items
// void decreaseKey( Position p, newVal )
//                        --> Decrease value in node p
// ******************ERRORS********************************
// Exceptions thrown for various operations

/**
 * 
//这里的堆指的都是二叉堆，为了优先队列产生（优先队列，使一些特殊的结点在出队的时候要优先出来。出队入队操作变成了insert和delete）  
//堆是一个完全二叉树，除了最后一层，其余层都是满的。这样的话存储用一个数组就可以，任一个元素位置在i，其左儿子位置是2*i,右儿子位置是2*i+1，父结点是i/2向下取整  
//完全二叉树的高度是logN的下界，所以涉及到完全二叉树的操作，是跟这个高度相关的，就是O(logN)，比如下面的上滤和下滤  
//buildheap是O(N)，它的界就是堆两个结点之间的线的条数，这个可以通过计算堆中所有结点的高度和来得到，这个和是O(N)  
//d堆，二叉堆是2堆，这样就理解d堆是什么了。书上说实践中4堆可以胜过二叉堆......  
//二叉堆和BST（二叉搜索树）的区别，二叉搜索树的左结点<父结点<右结点，堆是父结点小于孩子结点，孩子结点的顺序没有比。大于同理。  
template <typename Comparable>  
class BinaryHeap  
{  
public:  
    expicit BinaryHeap(int capcity = 100);     //构造函数  
    expicit BinaryHeap(const vector<Comparable> & items) :array(items.size() + 10), currentSize(items.size())  //拷贝构造函数  
    {  
        for (int i = 0; i < items.size(); i++)  
            array[i + 1] = items[i];  
        buildHeap();  
    }  
  
    bool isEmpty() const;  
    const Comparable & findMin() const;  
    void insert(const Comparable & x)   //insert相当于入队操作  
    {  
        if (currentSize == array.size() - 1)    //一开始觉得这里写的不对，后来明白了，这个vector，0的位置是空的，第一个元素从1开始，所以vector.size()==1的时候，currentSize是0。  
            array.resize(array.size() * 2);  
        int hole = ++currentSize;  
        for (; hole > 1 && x < array[hole / 2]; hole /= 2)      //这里直接赋值，避免了交换，如果一个元素上滤d层，交换实施的赋值的次数是3d，而这里是d+1  
        {  
            array[hole] = array[hole / 2];  
        }  
        array[hole] = x;  
    }  
  
    void deleteMin()                     //deleteMin相当于出队操作,操作可以迅速执行依赖于堆序性质，最小的在根上，这个规律递归到子堆  
    {  
        if (isEmpty())  
            throw UnderflowEception();  
        array[1] = array[currentSize--];  
        percolateDown(1);  
    }  
  
    void deleteMin(Comparable & minItem)  //和上面的区别，就是把出队的元素存到minItem里了  
    {  
        if (isEmpty)  
            throw UnderflowException();  
        minItem = array[1];  
        array[1] = array[currentSize--];  
        percolateDown(1);  
    }  
  
    void makeEmpty();  
  
private:  
    int currentSize;  
    vector<Comparable> array;  
  
  
    void buildHeap()                 //这个堆（现在还不是堆）先是乱排的，然后从下往上一直下滤，这个堆就是有序的了  
    {  
        for (int i = currentSize / 2; i >= ; i--)  
        {  
            percolateDown(i);  
        }  
    }  
  
    void percolateDown(int hole)     //下滤,上面调用的函数  
    {  
        int child;  
        Comparable tmp = array[hole];  
        for (; hole * 2 <= currentSize; hole = child)  
        {  
            child = hole * 2;  
            if (child != currentSize && array[child] > array[child + 1])  
            {  
                child++;  
            }  
            if (array[child] < tmp)  
            {  
                array[hole] = array[child];  
            }  
            else  
                break;  
        }  
        array[hole] = tmp;  
    }  
};  
  
  
  
  
//左式堆:1.左儿子的零路径长至少与右路径的零路径长一样大；2.任一结点的零路径长比它的诸儿子结点的零路径长的最小值多1。merge用到了这两个性质  
//n个结点的左式树有一条右路经最多含有log(N+1)的下界个结点。对左式堆操作的一般思路是，将所有的工作放到右路径上进行，保证树深短。  
//从树开始，这里面的类定义都有一个技巧，就是用公有的函数调用私有的函数  
template<typename Comparable>  
class LeftistHeap  
{  
public:  
    LeftistHeap();  
    LeftistHeap(const LeftistHeap & rhs);  
    ~LefttistHeap();  
  
    bool isEmpty() const;  
    const Comparable & findMin() const;  
  
    void insert(const Comparable & x)  
    {  
        root = merge(new LefttistNode(x), root);  
    }  
  
    void deleteMin()  
    {  
        if (isEmpty())  
            throw UnderflowException();  
        LeftistNode * oldroot = root;  
        root = merge(root->left, root->right);  
        delete oldRoot;  
    }  
  
    void deleteMin(Comparable & minItem)  
    {  
        minItem = findMin();  
        deleteMin();  
    }  
  
    void makeEmpty();  
    void merge(LeftistHeap & rhs)                   //合并，左式堆的最主要的算法。递归的将具有大的根值的堆和具有小的根值的堆的右子堆合并。所以执行合并的时间与右路径的长的和成正比，合并两个左式堆的时间界O(logN)  
    {  
        if (this == &rhs)  
        {  
            return;  
        }  
        root = merge(root, rhs.root);  
        rhs.root = NULL;  
    }  
  
    const LeftistHeap & operator=(const LiftistHeap & rhs);  
  
private:  
    struct LeftistNode  
    {  
        Comparable element;  
        LeftistHeap *left;  
        LeftistHeap *right;  
        int npl;                    //这个值是零路径长  
  
        LeftistNode(const Comparable & theElement, LeftistNode *lt = NULL, LeftistNode *rt = NULL, int np = 0) :element(theElement), left(lt), right(rt), npl(np){}  
    };  
  
    LeftistNode * root;  
    LeftistNode * merge(LeftistNode *h1, LeftistNode *h2)  
    {  
        if (h1 == NULL)              
            return h2;  
        if (h2 == NULL)  
            return h1;  
        if (h1->element < h2->element)  
            return merge1(h1, h2);  
        else  
            return merge1(h2, h1);  
    }  
  
    LeftistNode * merge1(LeftistNode *h1, LeftistNode *h2);  
    {  
        if (h1->left == NULL)   //左式堆，如果到了这步，那么其余的部分都弄好了，只剩下最下边的了  
        {  
            h1->left = h2;  
        }  
        else  
        {  
            h1->right = merge(h1->right, h2);  
            if ((h1->left->npl) < (h1->right->npl))  
            {  
                swapChildren(h1);  
            }  
            h1->npl = h1->right->npl + 1;  
        }  
        return h1;  
    }  
  
    void swapChildren(LeftistNode *t);  
    void reclainMemory(LeftistNode *t);  
    LeftistNode * clone(LeftistNode *t) const;  
};  
  
  
  
  
//二项队列，是一个二项树的森林，N个结点，用几棵二项树组成  
template<typename Comparable>  
class BinomialQueue  
{  
public:  
    BinomialQueue();  
    BinomialQueue(const Comparable & item);  
    BinomialQueue(const BinomialQueue & rhs);  
    ~BinomialQueue();  
    bool isEmpty() const;  
    const Comparable & findMin() const;  
    void insert(const Comparable & x);  
    void deleteMin();  
    void deleteMin(Comparable & minItem)          //挨个找森林中每棵树的根，最小的在这个根上，然后找到那棵树之后，把根删了，把删掉根之后的子树看成是另一个森林，与之前的合并  
    {  
        if (isEmpty())  
        {  
            throw UnderflowException();  
        }  
        int minIndex = findMinIndex();   //找到最小的是哪棵树的树根，返回坐标  
        minItem = theTrees[minIndex]->element;  
  
        BinomialNode *oldRoot = theTrees[minIndex];  
        BinomialNode *deletedTree = oldRoot->leftChild;  
        delete oldRoot;  
  
        BinomialQueue deletedQueue;       //把找到的那棵树的根删除之后，剩下的变成一个新的森林  
        deletedQueue.theTrees.resize(minIndex + 1);        //为什么我觉得resize(minIndex)也可以......  
        deletedQueue.currentSize = (1 << minIndex) - 1;   //就是2^minIndex-1，就是那棵树去掉根结点之后的结点数  
        for (int j = minIndex - 1; j >= 0; j--)           //这个就是造森林的过程，代码没问题.......二项队列的特点，每一棵树的子树，都是层数从0往上排的.......只是整个森林不一定每棵树都有  
        {  
            deletedQueue.theTrees[j] = deletedTree;  
            deletedTree = deletedTree->nextSibling;  
            deletedQueue.theTree[j]->nextSibling = NULL;  
        }  
        theTrees[minIndex] = NULL;  
        currentSize -= deletedQueue.currentSize + 1;  
        merge(deletedQueue);  
    }  
    void makeEmpty();  
    void merge(BinomialQueue & rhs)  
    {  
        //合并当前和rhs  
        if (this == &rhs)  
            return;  
        currentSize += rhs.currentSize;  
  
        if (currentSize > capacity())      //扩容......  
        {  
            int oldNumTrees = theTrees.size();  
            int newNumTrees = max(theTrees.size(), rhs.theTrees.size()) + 1;       //+1是这样的，比如两个都是最多有高度为4的树，那么合并之后，就会出现高度为5的树（4和4合并是5，不是8，看一下combineTrees的函数，合并只多一层的）  
            theTrees.resize(newNumTrees);  
            for (int i = oldNumTrees; i < newNumTrees; i++)  
                theTrees[i] = NULL;  
        }  
  
        BinomialNode * carry = NULL;            //合并二项队列的过程，有两个森林，然后按照从小往大排每棵树，把两个森林对应的位置相加（combineTrees就是干这个的）。  
                                                //这个carry相当于，一个进位，比如高度为2的两个树合并之后，高度变成了3，carry就要存这个，之前的两棵树2的位置清空；  
                                                //然后carry，两棵树有8种情况，逐个分析。这里combine，也只合并两个高度相同的tree。  
        for (int i = 0, j = 1; j <= currentSize; i++; j *= 2)        //这里的i就是theTrees的标号，j是用来控制这个标号的。i对应这个位置的树的高度，每棵树有2^i个结点，所以如果总共有N个结点，树最大的编号就是logN的下界  
        {  
            BinomialNode *t1 = theTree[i];  
            BinomialNode *t2 = i < rhs.theTrees.size() ? rhs.theTrees[i] : NULL;     //这里两行的区别，theTree因为之前有扩容的操作，但是rhs的没有，所以要这么写  
              
            int whichCase = t1 == NULL ? 0 : 1;  
            whichCase += t2 == NULL ? 0 : 2;  
            whichCase += carry == NULL ? 0 : 4;  
  
            switch (whichCase)  
            {  
            case 0:     //No Trees  
            case 1:     //Only this  
                break;   
            case 2:     //Only rhs  
                theTrees[i] = t2;  
                rhs.theTrees[i] = NULL;  
                break;  
            case 4:     //Only carry  
                theTrees[i] = carry;  
                carry = NULL;  
                break;  
            case 3:     //this and rhs  
                carry = conbineTrees(t1, t2);  
                theTrees[i] = rhs.theTrees[i] = NULL;  
                break;  
            case 5:     //this and carry  
                carry = combineTrees(t1, carry);  
                theTrees[i] = NULL;  
                break;  
            case 6:  
                carry = combineTrees(t2, carry);  
                rhs.theTrees[i] = NULL;  
                break;  
            case 7:  
                theTrees[i] = carry;  
                carry = combineTrees(t1, t2);  
                rhs.theTrees[i] = NULL;  
                break;  
            }  
            for (int k = 0; k < rhs.theTrees.size(); k++)  
                rhs.theTrees[k] = NULL;  
            rhs.currentSize = 0;  
        }  
    }  
  
    const BinomialQueue & operator=(const BinomialQueue & rhs);  
    private:  
    struct BinomialNode  
    {  
        Comparable element;  
        BinomialNode * leftChild;        //每个结点存储数据，大儿子，下一个堂兄弟。二项树中的儿子以递减次序（树由高到低）排列。  
        BinomialNode * nextSibling;  
  
        BinomialNode(const Comparable & theElement, BinomialNode *lt, BinomialNode *rt) :element(theElement), leftChild(lt), nextSibling(rt){}  
    };  
  
    enum(DEFAULT_TREES = 1);  
    //一个二项队列的森林，包括一共有多少个结点，还有森林每棵树根结点的vector(vector的坐标是这棵树的高度，这棵树的结点数是2^i，i是坐标。比如只有一个结点的就是0，如果没有这棵树，就是NULL)  
    int currentSize;                    //总的结点数  
    vector<BinomialNode *> theTrees;    //An array of tree roots  
  
    int findMinIndex() const  
    {  
        int i;  
        int minIndex;  
        for (i = 0; theTrees[i] == NULL; i++)  
            ;  
        for (minIndex = i; i < theTrees.size(); i++)  
        {  
            if (theTrees[i] != NULL && theTrees[i]->element < theTrees[minIndex]->element)  
            {  
                minIndex = i;  
            }  
        }  
        return minIndex;  
    }  
  
  
    int capcity() const;  
    BinomialNode* combineTrees(BinomialNode *t1, BinomialNode *t2)         //这个是把两个大小一样的tree合并  t1和t2是两棵树的root  
    {  
        if (t2->element < t1->element)  
            return combineTrees(t2, t1);  
        t2->nextSibling = t1->leftChild;  
        t1->leftChild = t2;  
        return t1;  
    }  
    void makeEmpty(BinomialNode * & t);  
    BinomialNode * clone(BinomialNode *t) const;  
};  

 * Implements a pairing heap.
 * Supports a decreaseKey operation.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class PairingHeap<AnyType extends Comparable<? super AnyType>>
{    
    /**
     * The Position interface represents a type that can
     * be used for the decreaseKey operation.
     */
    public interface Position<AnyType>
    {
        /**
         * Returns the value stored at this position.
         * @return the value stored at this position.
         */
        AnyType getValue();
    }

    /**
     * Construct the pairing heap.
     */
    public PairingHeap( )
    {
        root = null;
        theSize = 0;
    }

    /**
     * Insert into the priority queue, and return a Position
     * that can be used by decreaseKey.
     * Duplicates are allowed.
     * @param x the item to insert.
     * @return the node containing the newly inserted item.
     */
    public Position<AnyType> insert( AnyType x )
    {
        PairNode<AnyType> newNode = new PairNode<>( x );

        if( root == null )
            root = newNode;
        else
            root = compareAndLink( root, newNode );

        theSize++;
        return newNode;
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item.
     * @throws UnderflowException if pairing heap is empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return root.element;
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item.
     * @throws UnderflowException if pairing heap is empty.
     */
    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        AnyType x = findMin( );
        root.element = null; // null it out in case used in decreaseKey
        if( root.leftChild == null )
            root = null;
        else
            root = combineSiblings( root.leftChild );

        theSize--;
        return x;
    }

    /**
     * Change the value of the item stored in the pairing heap.
     * @param pos any Position returned by insert.
     * @param newVal the new value, which must be smaller
     *    than the currently stored value.
     * @throws IllegalArgumentException if pos is null.
     * @throws IllegalArgumentException if new value is larger than old.
     */
    public void decreaseKey( Position<AnyType> pos, AnyType newVal )
    {
        if( pos == null )
            throw new IllegalArgumentException( "null Position passed to decreaseKey" );

        PairNode<AnyType> p = (PairNode<AnyType>) pos;

        if( p.element == null )
            throw new IllegalArgumentException( "pos already deleted" );
        if( p.element.compareTo( newVal ) < 0 )
            throw new IllegalArgumentException( "newVal/oldval: " + newVal + " /" + p.element );
        p.element = newVal;
        if( p != root )
        {
            if( p.nextSibling != null )
                p.nextSibling.prev = p.prev;
            if( p.prev.leftChild == p )
                p.prev.leftChild = p.nextSibling;
            else
                p.prev.nextSibling = p.nextSibling;

            p.nextSibling = null;
            root = compareAndLink( root, p );
        }
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Returns number of items stored in the priority queue.
     * @return size of the priority queue.
     */
    public int size( )
    {
        return theSize;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
        theSize = 0;
    }

    /**
     * Private static class for use with PairingHeap.
     */
    private static class PairNode<AnyType> implements Position<AnyType>
    {
        /**
         * Construct the PairNode.
         * @param theElement the value stored in the node.
         */
        public PairNode( AnyType theElement )
        {
            element     = theElement;
            leftChild   = null;
            nextSibling = null;
            prev        = null;
        }

        /**
         * Returns the value stored at this position.
         * @return the value stored at this position.
         */
        public AnyType getValue( )
        {
            return element;
        }

            // Friendly data; accessible by other package routines
        public AnyType    element;
        public PairNode<AnyType>   leftChild;
        public PairNode<AnyType>   nextSibling;
        public PairNode<AnyType>   prev;
    }

    private PairNode<AnyType> root;
    private int theSize;

    /**
     * Internal method that is the basic operation to maintain order.
     * Links first and second together to satisfy heap order.
     * @param first root of tree 1, which may not be null.
     *    first.nextSibling MUST be null on entry.
     * @param second root of tree 2, which may be null.
     * @return result of the tree merge.
     */
    private PairNode<AnyType> compareAndLink( PairNode<AnyType> first, PairNode<AnyType> second )
    {
        if( second == null )
            return first;

        if( second.element.compareTo( first.element ) < 0 )
        {
            // Attach first as leftmost child of second
            second.prev = first.prev;
            first.prev = second;
            first.nextSibling = second.leftChild;
            if( first.nextSibling != null )
                first.nextSibling.prev = first;
            second.leftChild = first;
            return second;
        }
        else
        {
            // Attach second as leftmost child of first
            second.prev = first;
            first.nextSibling = second.nextSibling;
            if( first.nextSibling != null )
                first.nextSibling.prev = first;
            second.nextSibling = first.leftChild;
            if( second.nextSibling != null )
                second.nextSibling.prev = second;
            first.leftChild = second;
            return first;
        }
    }

    private PairNode<AnyType> [ ] doubleIfFull( PairNode<AnyType> [ ] array, int index )
    {
        if( index == array.length )
        {
            PairNode<AnyType> [ ] oldArray = array;

            array = new PairNode[ index * 2 ];
            for( int i = 0; i < index; i++ )
                array[ i ] = oldArray[ i ];
        }
        return array;
    }

        // The tree array for combineSiblings
    private PairNode<AnyType> [ ] treeArray = new PairNode[ 5 ];

    /**
     * Internal method that implements two-pass merging.
     * @param firstSibling the root of the conglomerate;
     *     assumed not null.
     */
    private PairNode<AnyType> combineSiblings( PairNode<AnyType> firstSibling )
    {
        if( firstSibling.nextSibling == null )
            return firstSibling;

            // Store the subtrees in an array
        int numSiblings = 0;
        for( ; firstSibling != null; numSiblings++ )
        {
            treeArray = doubleIfFull( treeArray, numSiblings );
            treeArray[ numSiblings ] = firstSibling;
            firstSibling.prev.nextSibling = null;  // break links
            firstSibling = firstSibling.nextSibling;
        }
        treeArray = doubleIfFull( treeArray, numSiblings );
        treeArray[ numSiblings ] = null;

            // Combine subtrees two at a time, going left to right
        int i = 0;
        for( ; i + 1 < numSiblings; i += 2 )
            treeArray[ i ] = compareAndLink( treeArray[ i ], treeArray[ i + 1 ] );

            // j has the result of last compareAndLink.
            // If an odd number of trees, get the last one.
        int j = i - 2;
        if( j == numSiblings - 3 )
            treeArray[ j ] = compareAndLink( treeArray[ j ], treeArray[ j + 2 ] );

            // Now go right to left, merging last tree with
            // next to last. The result becomes the new last.
        for( ; j >= 2; j -= 2 )
            treeArray[ j - 2 ] = compareAndLink( treeArray[ j - 2 ], treeArray[ j ] );

        return (PairNode<AnyType>) treeArray[ 0 ];
    }

        // Test program
    public static void main( String [ ] args )
    {
        PairingHeap<Integer> h = new PairingHeap<>( );
        int numItems = 10000;
        int i = 37;
        int j;

        System.out.println( "Checking; no bad output is good" );
        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
           h.insert( i );
        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );

        ArrayList<Position<Integer>> p = new ArrayList<>( );
        for( i = 0; i < numItems; i++ )
                p.add( null );
        
        for( i = 0, j = numItems / 2; i < numItems; i++, j =(j+71)%numItems )
            p.set( j, h.insert( j + numItems ) );
        for( i = 0, j = numItems / 2; i < numItems; i++, j =(j+53)%numItems )
            h.decreaseKey( p.get( j ), p.get( j ).getValue( ) - numItems );
        i = -1;
        while( !h.isEmpty( ) )
            if( h.deleteMin( ) != ++i )
                System.out.println( "Oops! " + i + " " );
        System.out.println( "Check completed" );
    }
}
