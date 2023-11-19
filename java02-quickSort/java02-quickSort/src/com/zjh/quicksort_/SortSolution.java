package com.zjh.quicksort_;

public class SortSolution {
    public static void quickSort(int[] arr, int start, int end){
        if(start >= end)
            return;
        // define a middle value
        int middle = arr[start + ((end - start) >> 1)];
        // 处理一个区间 定义两个指针left 和right
        int right = end;
        int left = start;
        // 方法1 是确定中间的数值，然后交换两侧的数据，每次找到left的值和right的值都要进行交换
        // 当left = right的时候，这个值左右两侧的数据是已经相对于left=right处的值确定了的。
        while (left <= right) {
            while (left <= right && arr[left] < middle) {
                left++;
            }
            while (right >= left && arr[right] > middle ) {
                right--;
            }
            if(left <= right) {
//                arr[right] = arr[right] ^ arr[left];
//                arr[left] = arr[right] ^ arr[left];
//                arr[right] = arr[right] ^ arr[left];
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
                left++;
                right--;
            }
        }

        quickSort(arr, start, right);
        quickSort(arr, left, end);



    }
    public static void swtich(int a, int b){
        System.out.println("a = " + a + " b = " + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = " + a + " b = " + b);
    }
    public static void quickSortWay2(int[] arr, int start, int end){
        if(start >= end)
            return;
        int middle = arr[start];
        int left = start;
        int right = end;
        while (left < right){
            while (left < right && arr[right] > middle){
                right--;
            }
            if(left < right){
                arr[left] = arr[right];
                left++;
            }
            while (left < right && arr[left] < middle){
                left++;
            }
            if(left < right){
                arr[right] = arr[left];
                right--;
            }
        }
        // 单独将其取出来的原因是，上面两个while循环，可能会出现两种情况
        // 落到第一个while循环，则right是不变的值，而left++
        // 落到第二个while循环，则left是不变的值(priot应该插入的地方)而right--！
        if(left == right) {
            arr[right] = middle;
            left++;
            right--;
        }
        quickSortWay2(arr, start, right);
        quickSortWay2(arr, left, end);
    }
    private static int searchK(int[] arr, int start, int end, int K) {
        if (start == end)
            return arr[K];
        int priot = arr[start + ((end - start) >> 2)];
        int left = start;
        int right = end;
        while (left <= right) {
            while (left <= right && arr[left] < priot){
                left++;
            }
            while (left <= right && arr[right] > priot){
                right--;
            }
            if (left <= right) {
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
                left++;
                right--;
            }
        }
        // 使用if进行选择的意义在于可以控制递归方向，使其只对某一个成立的区间进行递归！
        if (K <= right)
            return searchK(arr, start, right, K);
        else
            return searchK(arr, left, end, K);
    }

    public static int findKMax(int[] arr, int K){
        if(arr == null || arr.length < K)
            return -1;
        // 第K大的数，其数组索引就是arr.length - K 也就是找索引为arr.length -K 的索引处的值！
        return searchK(arr, 0, arr.length - 1, arr.length - K);
    }

    public static void mergeSort(int[] arr, int start, int end, int[] temp){
        // 当循环到一定的
        if(start >= end)
            return;
        mergeSort(arr, start, (start + ((end - start) >> 1)), temp);
        mergeSort(arr, (start + ((end - start) >> 1)) + 1, end, temp);
        merge(arr, start, end, temp);
    }
    // 此merge函数用来具体排序的实现！
    private static void merge(int[] arr, int start, int end, int[] temp){
        int middle = start + ((end - start) >> 1);
        int left = start;
        int right = middle + 1;
        // 此处的index也是关键！index表明了，这个需要比较的两个子节点是代表的第几个子节点！！
        int index = start;
        // 定义两个数据区间，分别进行比较
        while (left <= middle && right <= end){
            if(arr[left] <= arr[right]){
                temp[index++] = arr[left++];
            }
            else {
                temp[index++] = arr[right++];
            }
        }
        while (left <= middle){
            temp[index++] = arr[left++];
        }
        while (right <= end){
            temp[index++] = arr[right++];
        }
        for(int i = start; i <= end; i++){
            arr[i] = temp[i];
        }
    }
}
