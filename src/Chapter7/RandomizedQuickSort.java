package Chapter7;

import java.util.Arrays;

import static Chapter7.QuickSort.partition;
import static Chapter7.QuickSort.quickSort;

/**
 * 随机化的快速排序
 * 复杂度：期望运行时间θ(nlgn)
 * 适用:原址排序，实用，常作为大数据输入下的排序
 */
public class RandomizedQuickSort {

    /**
     * 实现对子数组的原址重排
     * 将数组划分为两个子数组为A[p...q-1]、A[q+1..r]和主元A[q]，
     * 使得前者所有元素都小于A[q]，后者所有元素都大于等于A[q]
     *
     * @return 主元(pivot)的位置
     * 复杂度：θ(n)
     */
    public static int randomizedPartition(int[] A, int p, int r) {
        //  随机取一个元素与即将作为主元的最后一个元素A[r]交换
        int i = (int) (p + Math.random() * (r + 1));
        int tmp = A[r];
        A[r] = A[i];
        A[i] = tmp;
        return partition(A, p, r);
    }

    /**
     * 随机化的快速排序主函数
     * 复杂度：期望运行时间θ(nlgn)
     */
    public static void randomizedQuickSort(int[] A, int p, int r) {
        if (p < r) {
            int q = randomizedPartition(A, p, r);
            randomizedQuickSort(A, p, q - 1);
            randomizedQuickSort(A, q + 1, r);
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 8, 7, 1, 3, 5, 6, 4};
        quickSort(A, 0, A.length - 1);
        System.out.println(Arrays.toString(A));
    }
}
