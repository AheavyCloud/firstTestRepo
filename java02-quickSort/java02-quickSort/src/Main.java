import com.zjh.quicksort_.SortSolution;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 2, 3, 6, 10};
        //SortSolution.quickSortWay2(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        int k = 5;
//        int kNum = SortSolution.findKMax(arr, k);
//        System.out.println("第" + k +"大的数为：" + kNum);
        System.out.println("归并排序！");
        SortSolution.mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        for (int i : arr){
            System.out.printf(i + " ");
        }
        System.out.println();
    }
}
