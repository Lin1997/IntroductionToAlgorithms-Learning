package Chapter2;

import java.util.Arrays;

/**
 * 归并排序
 * 复杂度:θ(nlgn)
 * 适用:(比较模型下)排序算法中复杂度最小的，效率较高。
 * [(比较模型下)排序算法的最低复杂度(下界)就是θ(nlgn)]
 * 但是从实际计算机系统的缓存机制来看，快速排序的效率更高
 */
public class MergeSort {
    /**
     * 完成两个已排序子数组的合并,arr[p..q]为子数组1,arr[q+1..r]为子数组2
     * 复杂度:O(n)
     */
    static void Merge(int[] arr, int p, int q, int r) {
        //  计算子数组长度
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];
        //  初始化新子数组
        for (int i = 0; i < n1; i++)
            L[i] = arr[p + i];
        for (int i = 0; i < n2; i++)
            R[i] = arr[q + 1 + i];
        //  设置哨兵
        L[n1] = (int) Double.POSITIVE_INFINITY;
        R[n2] = (int) Double.POSITIVE_INFINITY;
        //  开始合并
        int indexL = 0, indexR = 0;
        for (int k = p; k <= r; k++) {
            //  取两数组小的一个值,替换原数组
            if (L[indexL] <= R[indexR]) {
                arr[k] = L[indexL];
                indexL++;
            } else {
                arr[k] = R[indexR];
                indexR++;
            }
        }
    }

    /**
     * 排序数组
     */
    static void beginMergeSort(int[] arr, int p, int r) {
        if (p < r) {    //否则就只有一个元素
            //  分解为两个子数组
            int q = (p + r) / 2;
            beginMergeSort(arr, p, q);
            beginMergeSort(arr, q + 1, r);
            //  合并结果
            Merge(arr, p, q, r);
        }
    }

    public static void main(String[] args) {
        int[] testArr = {5, 2, 4, 7, 1, 3, 2, 6};
        beginMergeSort(testArr, 0, testArr.length - 1);
        System.out.println(Arrays.toString(testArr));
    }
}
