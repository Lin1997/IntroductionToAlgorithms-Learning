package Chapter6;

import java.util.Arrays;

/**
 * 快速排序
 * 复杂度：最坏O(n^2),最好θ(nlgn)
 * 适用:原址排序，最好使用随机化的快速排序
 */
public class QuickSort {
    public static void quickSort(int[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            quickSort(A, p, q - 1);
            quickSort(A, q + 1, r);
        }
    }

    /**
     * 实现对子数组的原址重排
     * 将数组划分为两个子数组为A[p...q-1]、A[q+1..r]和主元A[q]，
     * 使得前者所有元素都小于A[q]，后者所有元素都大于等于A[q]
     *
     * @return 主元(pivot)的位置
     * 复杂度：θ(n)
     */
    public static int partition(int[] A, int p, int r) {
        int x = A[r];     //取主元
        int i = p - 1;
        //  遍历数组
        for (int j = p; j <= r - 1; j++) {
            //  如果比主元小就移动至主元前
            if (A[j] <= x) {
                i++;
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        //  将主元换到中间
        int tmp = A[i + 1];
        A[i + 1] = A[r];
        A[r] = tmp;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] A = {2, 8, 7, 1, 3, 5, 6, 4};
        quickSort(A, 0, A.length - 1);
        System.out.println(Arrays.toString(A));
    }
}
