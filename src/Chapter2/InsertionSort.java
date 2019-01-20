package Chapter2;

/**
 * 插入排序
 * 复杂度：θ(n^2)
 * 适用:少量元素，原址排序
 */
public class InsertionSort {
    InsertionSort(int[] A) {
        for (int j = 1; j < A.length; j++) {
            int key = A[j];
            //  Insert A[j] into the sorted sequence A[1..j-1]
            int i = j - 1;
            while (i >= 0 && A[i] > key) {
                A[i + 1] = A[i];
                i = i - 1;
            }
            A[i + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] test = {3, 9, 8, 7, -1, 6, 7, 0, -50, 100};
        new InsertionSort(test);
        for (int a : test) {
            System.out.print(a + "\t");
        }
    }
}

