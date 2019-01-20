package Chapter4;

public class MaxSubArray {
    public static void main(String[] args) {
        int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        StructToRet result = findMaximumSubArray(arr, 0, arr.length - 1);
        System.out.println(result.low + " " + result.high + " " + result.sum);
    }

    static class StructToRet {
        public int low;
        public int high;
        public int sum;

        public StructToRet(int low, int high, int sum) {
            this.low = low;
            this.high = high;
            this.sum = sum;
        }
    }

    /**
     * 分治策略
     * 求解A[low..high]的最大子数组
     * 复杂度:θ(nlogn)
     */
    static StructToRet findMaximumSubArray(int[] A, int low, int high) {
        if (high == low)
            return new StructToRet(low, high, A[low]);    // 基本情况:只有一个元素
        int mid = (low + high) / 2;
        StructToRet left = findMaximumSubArray(A, low, mid);
        StructToRet right = findMaximumSubArray(A, mid + 1, high);
        StructToRet cross = findMaxCrossingSubArray(A, low, mid, high);
        //  返回三者最大值
        if (left.sum >= right.sum && left.sum >= cross.sum)
            return new StructToRet(left.low, left.high, left.sum);
        else if (right.sum >= left.sum && right.sum >= cross.sum)
            return new StructToRet(right.low, right.high, right.sum);
        else
            return new StructToRet(cross.low, cross.high, cross.sum);
    }

    /**
     * 求跨越中点的最大子数组的边界，并返回这个最大子数组的和
     * 复杂度:θ(n)
     */
    static StructToRet findMaxCrossingSubArray(int[] A, int low, int mid, int high) {
        int leftSum = (int) Double.NEGATIVE_INFINITY;
        int sum = 0;
        int maxLeft = 0;
        for (int i = mid; i >= low; i--) {
            sum = sum + A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        int rightSum = (int) Double.NEGATIVE_INFINITY;
        sum = 0;
        int maxRight = 0;
        for (int j = mid + 1; j <= high; j++) {
            sum = sum + A[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }
        return new StructToRet(maxLeft, maxRight, leftSum + rightSum);
    }
}
