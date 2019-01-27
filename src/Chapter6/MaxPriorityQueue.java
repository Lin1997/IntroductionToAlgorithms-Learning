package Chapter6;

import static Chapter6.HeapSort.*;

/**
 * 堆的应用：（最大）优先队列
 * 复杂度：所有优先队列的操作都可以在O(lgn)的时间内完成
 * 其中，maximum() 为θ(1)，extractMax()、insert()、increaseKey() 为O(lgn)
 */
interface PriorityQueue {
    //  把关键字k插入队列
    void insert(int k);

    //  返回具有最大关键字的元素
    int maximum();

    //  去掉并返回队列中具有最大关键字的元素
    int extractMax();

    //  将元素A[i]的关键字增加到k（k不小于x(原关键字值)）
    void increaseKey(int i, int k);
}

public class MaxPriorityQueue implements PriorityQueue {
    private int[] A = new int[20];

    MaxPriorityQueue() {
        heapSize = 0;
    }

    /**
     * 返回具有最大关键字的元素
     * 复杂度：θ(1)
     */
    @Override
    public int maximum() {
        return A[0];
    }

    /**
     * 去掉并返回队列中具有最大关键字的元素
     * 复杂度：O(lgn)
     */
    @Override
    public int extractMax() {
        if (heapSize < 1) {
            System.out.println("heap underflow");
            return (int) Double.NaN;
        }
        int max = A[0];
        A[0] = A[--heapSize];
        MaxHeapify(A, 1);
        return max;
    }

    /**
     * 将元素A[i]的关键字增加到k（k不小于x(原关键字值)）
     * 复杂度：O(lgn)
     */
    @Override
    public void increaseKey(int i, int k) {
        if (k < A[i]) {
            System.out.println("new key is smaller than current key");
            return;
        }
        A[i] = k;
        //  从当前节点往根节点寻找合适的插入位置
        while (i > 0 && A[PARENT(i)] < A[i]) {
            int tmp = A[i];
            A[i] = A[PARENT(i)];
            A[PARENT(i)] = tmp;
            i = PARENT(i);
        }
    }

    /**
     * 把关键字k插入队列
     * 复杂度：O(lgn)
     */
    @Override
    public void insert(int k) {
        A[heapSize] = Integer.MIN_VALUE;
        increaseKey(heapSize, k);
        heapSize++;
    }

    void printQueue() {
        for (int i = 0; i < heapSize; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MaxPriorityQueue queue = new MaxPriorityQueue();
        queue.insert(5);
        queue.insert(1);
        queue.insert(6);
        queue.insert(9);
        queue.insert(4);
        queue.insert(2);
        queue.printQueue();
        queue.increaseKey(6, 10);
        queue.printQueue();
        System.out.print(queue.extractMax() + " ");
        System.out.print(queue.extractMax() + " ");
        System.out.print(queue.extractMax() + " ");
        System.out.print(queue.extractMax() + " ");
        System.out.print(queue.extractMax() + " ");
        System.out.print(queue.extractMax() + " ");
        queue.printQueue();
        queue.extractMax();
    }
}
