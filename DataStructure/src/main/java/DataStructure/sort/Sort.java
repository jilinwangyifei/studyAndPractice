
package  DataStructure.sort;


import  DataStructure.Random;

/**
 * 1.插入排序	insertionSort
 * 2.希尔排序	shellsort
 * 3.堆排序heapsort
 * 4.归并排序	mergeSort
 * 5.快速排序	quicksort
 * 6.快速选择	quickSelect
 * 7.冒泡排序bubbleSort
 */
public final class Sort
{
    /**
     * Simple insertion sort.
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort( AnyType [ ] a )
    {
        int j;

        for( int p = 1; p < a.length; p++ )
        {
            AnyType tmp = a[ p ];
            for( j = p; j > 0 && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
                a[ j ] = a[ j - 1 ];
            a[ j ] = tmp;
        }
    }

    public static <AnyType extends  Comparable<? super AnyType>>
    void insertionSortSeftTest(AnyType [] a){
        int j;
        for (int i = 1; i < a.length ; i++) {
            AnyType tmp = a[i];
            for ( j = i ; tmp.compareTo( a[ j-1 ] ) < 0; j--) {
                a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }

    /**
     * 
     * Shellsort, using Shell's (poor) increments.
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void shellsort( AnyType [ ] a )
    {
        int j;

        //比如长度20 gap 10 5 2 1
        for( int gap = a.length / 2; gap > 0; gap /= 2 )
            for( int i = gap; i < a.length; i++ )
            {
                AnyType tmp = a[ i ];
                for( j = i; j >= gap &&
                            tmp.compareTo( a[ j - gap ] ) < 0; j -= gap )
                    a[ j ] = a[ j - gap ];
                a[ j ] = tmp;
            }
    }


    public static <AnyType extends Comparable<? super AnyType>>
    void shellsortSelfTest( AnyType [ ] a ){
        int j ;
        for (int gap = a.length/2; gap > 0 ; gap /=2)
        {
            for (int i = gap ; i < a.length ; i++)
            {
                AnyType tmp = a[i];
                for ( j = i; j >= gap && tmp.compareTo(a[i-gap]) < 0 ; j -=gap) {
                    a[j] = a[ j - gap ];
                }
                a[j] = tmp;
            }
        }
    }


    /**
     * Internal method for heapsort.
     * @param i the index of an item in the heap.
     * @return the index of the left child.
     */
    private static int leftChild( int i )
    {
        return 2 * i + 1;
    }
    
    /**
     * Internal method for heapsort that is used in deleteMax and buildHeap.
     * @param a an array of Comparable items.
     * @index i the position from which to percolate down.
     * @int n the logical size of the binary heap.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void percDown( AnyType [ ] a, int i, int n )
    {
        int child;
        AnyType tmp;

        for( tmp = a[ i ]; leftChild( i ) < n; i = child )
        {
            child = leftChild( i );
            if( child != n - 1 && a[ child ].compareTo( a[ child + 1 ] ) < 0 )
                child++;
            if( tmp.compareTo( a[ child ] ) < 0 )
                a[ i ] = a[ child ];
            else
                break;
        }
        a[ i ] = tmp;
    }
    
    /**
     * 
     * Standard heapsort.
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void heapsort( AnyType [ ] a )
    {
        for( int i = a.length / 2 - 1; i >= 0; i-- )  /* buildHeap */
            percDown( a, i, a.length );
        for( int i = a.length - 1; i > 0; i-- )
        {
            swapReferences( a, 0, i );                /* deleteMax */
            percDown( a, 0, i );
        }
    }


    public static void  heapSortSelfTest(String[] data)
    {
        for (int i = data.length-1 ; i > 0; i--)
        {
            String tmp = data[0];
            data[0] = data[i];
            data[i] = tmp;
            maxHeap(data,i,0);
        }
    }

    public static void maxHeap(String[] data,int heapSize, int index)
    {
        int left = getLeftIndex(index);
        int right = getRightIndex(index);
        int largest = index;

        if(left < heapSize && data[index].compareTo(data[left])<0){
            largest = left;
        }

        if(right < heapSize && data[index].compareTo(data[right])<0){
            largest = right;
        }

        if(largest != index){
            String tmp = data[index];
            data[index] = data[largest];
            data[largest] = tmp;
            maxHeap(data,heapSize,largest);
        }
    }

    public static int getLeftIndex(int index){
        return (index << 1) +1;
    }

    public static int getRightIndex(int index){
        return (index << 1) +2;
    }

    public static int getParentIndex(int index){
        return (index - 1) >> 1;
    }

    /**
     * Mergesort algorithm.
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void mergeSort( AnyType [ ] a )
    { 
        AnyType [ ] tmpArray = (AnyType[]) new Comparable[ a.length ];

        mergeSort( a, tmpArray, 0, a.length - 1 );
    }

    /**
     * Internal method that makes recursive calls.
     * @param a an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort( AnyType [ ] a, AnyType [ ] tmpArray,
               int left, int right )
    {
        if( left < right )
        {
            int center = ( left + right ) / 2;
            mergeSort( a, tmpArray, left, center );
            mergeSort( a, tmpArray, center + 1, right );
            merge( a, tmpArray, left, center + 1, right );
        }
    }

    /**
     * Internal method that merges two sorted halves of a subarray.
     * @param a an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param leftPos the left-most index of the subarray. 左侧开始位置
     * @param rightPos the index of the start of the second half. 中间右侧开始位置
     * @param rightEnd the right-most index of the subarray. 最右侧开始位置
     *   
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void merge( AnyType [ ] a, AnyType [ ] tmpArray, int leftPos, int rightPos, int rightEnd )
    {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while( leftPos <= leftEnd && rightPos <= rightEnd )
            if( a[ leftPos ].compareTo( a[ rightPos ] ) <= 0 )
                tmpArray[ tmpPos++ ] = a[ leftPos++ ];
            else
                tmpArray[ tmpPos++ ] = a[ rightPos++ ];

        while( leftPos <= leftEnd )    //如果左数组还没有结束，将剩余部分拷贝  
            tmpArray[ tmpPos++ ] = a[ leftPos++ ];

        while( rightPos <= rightEnd )  //如果右数组还没结束，将剩余部分拷贝进来  
            tmpArray[ tmpPos++ ] = a[ rightPos++ ];

        // //将临时数组值拷贝回原数组  
        for( int i = 0; i < numElements; i++, rightEnd-- )
            a[ rightEnd ] = tmpArray[ rightEnd ];
    }

    /**
     * Quicksort algorithm.
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort( AnyType [ ] a )
    {
        quicksort( a, 0, a.length - 1 );
    }
 
    private static final int CUTOFF = 3;

    /**
     * Method to swap to elements in an array.
     * @param a an array of objects.
     * @param index1 the index of the first object.
     * @param index2 the index of the second object.
     */
    public static <AnyType> void swapReferences( AnyType [ ] a, int index1, int index2 )
    {
        AnyType tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }

    /**
     * Return median of left, center, and right.
     * Order these and hide the pivot.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3( AnyType [ ] a, int left, int right )
    {
        int center = ( left + right ) / 2;
        if( a[ center ].compareTo( a[ left ] ) < 0 )
            swapReferences( a, left, center );
        if( a[ right ].compareTo( a[ left ] ) < 0 )
            swapReferences( a, left, right );
        if( a[ right ].compareTo( a[ center ] ) < 0 )
            swapReferences( a, center, right );

            // Place pivot at position right - 1
        swapReferences( a, center, right - 1 );
        return a[ right - 1 ];
    }

    /**
     * Internal quicksort method that makes recursive calls.
     * Uses median-of-three partitioning and a cutoff of 10.
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void quicksort( AnyType [ ] a, int left, int right )
    {
        if( left + CUTOFF <= right )
        {
            AnyType pivot = median3( a, left, right );

                // Begin partitioning
            int i = left, j = right - 1;
            for( ; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 ) { }
                while( a[ --j ].compareTo( pivot ) > 0 ) { }
                if( i < j )
                    swapReferences( a, i, j );
                else
                    break;
            }

            swapReferences( a, i, right - 1 );   // Restore pivot

            quicksort( a, left, i - 1 );    // Sort small elements
            quicksort( a, i + 1, right );   // Sort large elements
        }
        else  // Do an insertion sort on the subarray
            insertionSort( a, left, right );
    }

    public static void quickSortSelfTest(Integer [] a, int start, int end){

        if(start >= end){
            return;
        }

        int p = a[start];
        while(start < end){
            while(a[end] >= p && start < end) end--;
            a[start] = a[end];
            while(a[start] < p && start < end) start++;
            a[end] = a[start];
        }
        a[start] = p;
        int mid = start;
        quickSortSelfTest(a, start, mid-1);
        quickSortSelfTest(a, mid+1, end);

    }

    /**
     * Internal insertion sort routine for subarrays
     * that is used by quicksort.
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void insertionSort( AnyType [ ] a, int left, int right )
    {
        for( int p = left + 1; p <= right; p++ )
        { 
            AnyType tmp = a[ p ];
            int j;

            for( j = p; j > left && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
                a[ j ] = a[ j - 1 ];
            a[ j ] = tmp;
        }
    }

    /**
     * Quick selection algorithm.
     * Places the kth smallest item in a[k-1].
     * @param a an array of Comparable items.
     * @param k the desired rank (1 is minimum) in the entire array.
     */     
    public static <AnyType extends Comparable<? super AnyType>>
    void quickSelect( AnyType [ ] a, int k )
    {
        quickSelect( a, 0, a.length - 1, k );
    }
 
    /**
     * Internal selection method that makes recursive calls.
     * Uses median-of-three partitioning and a cutoff of 10.
     * Places the kth smallest item in a[k-1].
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     * @param k the desired index (1 is minimum) in the entire array.
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void quickSelect( AnyType [ ] a, int left, int right, int k )
    {
        if( left + CUTOFF <= right )
        {
            AnyType pivot = median3( a, left, right );

                // Begin partitioning
            int i = left, j = right - 1;
            for( ; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 ) { }
                while( a[ --j ].compareTo( pivot ) > 0 ) { }
                if( i < j )
                    swapReferences( a, i, j );
                else
                    break;
            }

            swapReferences( a, i, right - 1 );   // Restore pivot

            if( k <= i )
                quickSelect( a, left, i - 1, k );
            else if( k > i + 1 )
                quickSelect( a, i + 1, right, k );
        }
        else  // Do an insertion sort on the subarray
            insertionSort( a, left, right );
    }


    /**
     * 冒泡排序
     * @param a
     */
    public void bubbleSort(int[] a ){
        int temp=0;
        for(int i=0;i<a.length-1;i++){
            for(int j=0;j<a.length-1-i;j++){
                if(a[j]>a[j+1]){
                    temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
    }


    private static final int NUM_ITEMS = 1000;
    private static int theSeed = 1;

    private static void checkSort( Integer [ ] a )
    {
        for( int i = 0; i < a.length; i++ )
            if( a[ i ] != i )
                System.out.println( "Error at " + i );
        System.out.println( "Finished checksort" );
    }


    public static void main( String [ ] args )
    {
        Integer [ ] a = new Integer[ NUM_ITEMS ];
        for( int i = 0; i < a.length; i++ )
            a[ i ] = i;

        for( theSeed = 0; theSeed < 20; theSeed++ )
        {
            Random.permute( a );
            Sort.insertionSort( a );
            checkSort( a );

            Random.permute( a );
            Sort.heapsort( a );
            checkSort( a );

            Random.permute( a );
            Sort.shellsort( a );
            checkSort( a );

            Random.permute( a );
            Sort.mergeSort( a );
            checkSort( a );

            Random.permute( a );
            Sort.quicksort( a );
            checkSort( a );

            Random.permute( a );
            Sort.quickSelect( a, NUM_ITEMS / 2 );
            System.out.println( a[ NUM_ITEMS / 2 - 1 ] + " " + NUM_ITEMS / 2 );
        }
        
        
        Integer [ ] b = new Integer[ 10_000_000 ];
        for( int i = 0; i < b.length; i++ )
            b[ i ] = i;
        
        Random.permute( b );
        long start = System.currentTimeMillis( );
        Sort.quickSelect( b, b.length  / 2 );
        long end = System.currentTimeMillis( );
        System.out.println( "Timing for Section 1.1 example: " );
        System.out.println( "Selection for N = " + b.length + " takes " + 
                             ( end - start ) + "ms." );
        System.out.println( b[ b.length / 2 - 1 ] + " " + b.length / 2 );

       /* String[] sort=new String[]{"S","O","R","T","X","A","M","P","L", "E"};
        heapSortSelfTest(sort);
        for (String str : sort){
            System.out.println(str);
        }*/

        Integer[] arr = new Integer[]{12,3,4,1,45,6};
        quickSortSelfTest(arr,0,arr.length-1);
        for (int i : arr){
            System.out.println(i);
        }

        String ss = "";
        int left = 0;
        int right = ss.length() - 1;
        while (left< right && left < ss.length()-1){
            char l = ss.charAt(left);
            if(!((l >= 'a' && l <= 'z') || (l >= '0' && l <= '9'))){
                left ++;
                continue;
            }

        }
    }
}
