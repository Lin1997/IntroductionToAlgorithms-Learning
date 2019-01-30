package Chapter8;

import java.util.Arrays;

/**
 * 基数排序
 * 复杂度：O( d(n+k) ) = O(bn/lgn) = O(dn)
 * 其中d表示将一个b位(bit)的无符号整数拆分为d个r位无符号整数，有d=b/r，(取r=lgn)
 * 当d=(1)时，即能拆分位常数个整数时，有复杂度为O(dn)=O(n)
 */
public class RadixSort {
    static int[] result; //用来保存每次调用radixSort()的输出结果
    final static int bitOfInt = 32;
    static int r;

    /**
     * 基数排序函数：计算d、r并调用基数排序
     */
    public static int[] radixSort(int[] A) {
        int size = A.length;
        //  取r=lgn=logeN/loge2
        r = (int) (Math.log(size) / Math.log(2));
        int d = Math.min(bitOfInt / r, bitOfInt);
        result = A;
        //  对于拆分成的d个r bit无符号整数，进行计数排序
        for (int i = 0; i < d; i++) {
            int[] input = result;
            result = new int[A.length];
            maskCountingSort(input, result, i);
        }
        return result;
    }

    /**
     * 带掩码的计数排序，通过掩码来比较32位的其中r位
     *
     * @param A 待排序数组
     * @param B 输出结果数组
     * @param i 从最低位到最高位，第几批数据（每批r位）
     */
    public static void maskCountingSort(int[] A, int[] B, int i) {
        final int k = 2 << r - 1;     //数组元素值范围[0,2^r-1](假设输入都为无符号整数)
        final int mask = 0xffffffff >>> (bitOfInt - r);   //  掩码111...111共r个1
        int bitMask = mask << (i * r);                    //  掩码，用来求第i批r bit无符号整数的值
        //新建一个数组C用来计数
        int[] C = new int[k + 1];
        Arrays.fill(C, 0);
        for (int ele : A) {
            int index = (ele & bitMask) >>> (i * r);    //求第i批r bit无符号整数的值
            C[index] = C[index] + 1;
        }
        //  现在C[i]表示元素值为i的数量
        for (int j = 1; j < C.length; j++) {
            C[j] = C[j] + C[j - 1];
        }
        //  现在C[i]表示元素值小于等于i的数量，也就是i在有序数组中的最终位置
        //  开始将数组A有序地“分配”到数组B中
        for (int j = A.length - 1; j >= 0; j--) {
            int index = (A[j] & bitMask) >>> (i * r);
            B[C[index] - 1] = A[j];
            C[index]--;
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 505, 3000, 0, 2565, 35, 98, 666};
        int[] result = radixSort(A);
        System.out.println(Arrays.toString(result));
    }
}
