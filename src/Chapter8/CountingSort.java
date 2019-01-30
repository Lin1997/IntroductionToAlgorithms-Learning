package Chapter8;

import java.util.Arrays;

public class CountingSort {
    /**
     * 计数排序
     * 复杂度：θ(k+n);当k=O(n)时，运行时间为θ(n)
     * 适用:待排序数组的每一个元素大小都是0到k之间的整数
     *
     * @param A 待排序数组
     * @param B 输出的已排序数组
     * @param k 每一个元素大小都是0到k之间的整数
     */
    public static void countingSort(int[] A, int[] B, int k) {
        //新建一个数组C用来计数
        int[] C = new int[k + 1];
        Arrays.fill(C, 0);
        for (int ele : A)
            C[ele] = C[ele] + 1;
        //  现在C[i]表示元素值为i的数量
        for (int i = 1; i < C.length; i++) {
            C[i] = C[i] + C[i - 1];
        }
        //  现在C[i]表示元素值小于等于i的数量，也就是i在有序数组中的最终位置
        //  开始将数组A有序地“分配”到数组B中
        for (int j = A.length - 1; j >= 0; j--) {
            B[C[A[j]] - 1] = A[j];
            C[A[j]]--;
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 5, 3, 0, 2, 3, 0, 3};
        int[] result = new int[A.length];
        countingSort(A, result, 5);
        System.out.println(Arrays.toString(result));
    }
}
