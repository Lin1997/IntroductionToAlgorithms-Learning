package Chapter6;

import java.util.Arrays;

/**
 * 堆排序
 * 复杂度：θ(nlgn)
 * 适用:原址排序，渐进最优的比较排序算法
 */
public class HeapSort {
    public static int heapSize;    //  因为HeapSort中需要将节点去除，故用变量来存放树中真正的元素数

    /**
     * 最大堆(二叉)堆化，用于维护最大堆性质
     * 复杂度：O(lgn)，即取决于树的高度
     */
    static void MaxHeapify(int[] A, int i) {
        int l = LEFT(i), r = RIGHT(i);
        int largest;
        //  求根节点和两个子节点的最大值
        if (l < heapSize && A[l] > A[i])
            largest = l;
        else
            largest = i;
        if (r < heapSize && A[r] > A[largest])
            largest = r;
        //  根节点不是最大就交换，并递归调用MaxHeapify保证被交换子树保持最大堆性质
        if (largest != i) {
            int tmp = A[i];
            A[i] = A[largest];
            A[largest] = tmp;
            MaxHeapify(A, largest);
        }
    }

    /**
     * 自底向上利用MaxHeapify()把一个数组转换成最大堆
     * 复杂度：O(n)
     */
    static void BuildMaxHeap(int[] A) {
        heapSize = A.length;  //初始化元素数
        for (int i = A.length / 2; i >= 0; i--)
            MaxHeapify(A, i);
    }

    /**
     * 堆排序主函数
     * 复杂度：θ(nlgn)
     */
    public static void HeapSort(int[] A) {
        BuildMaxHeap(A);
        for (int i = A.length - 1; i >= 1; i--) {
            //  将根节点和最后一个叶子节点交换，并将原根节点(现在的叶子节点)从树中去除
            int tmp = A[0];
            A[0] = A[i];
            A[i] = tmp;
            heapSize--;
            //  维护最大堆性质
            MaxHeapify(A, 0);
        }
    }

    /**
     * 在完全二叉树中，求节点i的左孩子节点；RIGHT()，PARENT()同理
     */
    static final int LEFT(int i) {
        return 2 * i;
    }

    static final int RIGHT(int i) {
        return 2 * i + 1;
    }

    static final int PARENT(int i) {
        return i / 2; //向下取整
    }

    public static void main(String[] args) {
        int[] A = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
        System.out.println(Arrays.toString(A));
        HeapSort(A);
        System.out.println(Arrays.toString(A));
    }
}
