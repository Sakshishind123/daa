import java.util.*;

public class Que4 {

    // Merge Sort function
    public static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    // Merge function to combine two sorted halves
    public static void merge(int[] arr, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];

        int arrInd = low;
        for (int i = 0; i < n1; i++) {
            arr1[i] = arr[arrInd++];
        }

        arrInd = mid + 1;
        for (int i = 0; i < n2; i++) {
            arr2[i] = arr[arrInd++];
        }

        int ind1 = 0, ind2 = 0;
        arrInd = low;
        while (ind1 < n1 && ind2 < n2) {
            if (arr1[ind1] <= arr2[ind2]) {
                arr[arrInd++] = arr1[ind1++];
            } else {
                arr[arrInd++] = arr2[ind2++];
            }
        }

        while (ind1 < n1) {
            arr[arrInd++] = arr1[ind1++];
        }

        while (ind2 < n2) {
            arr[arrInd++] = arr2[ind2++];
        }
    }

    // Wrapper function for mergeSort
    public static void performMergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking input for array size
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        scanner.close();

        // Best case: Already sorted array
        int[] bestCaseArray = new int[size];
        for (int i = 0; i < size; i++) {
            bestCaseArray[i] = i;
        }

        long startTime = System.nanoTime();
        performMergeSort(bestCaseArray);
        long endTime = System.nanoTime();
        System.out.println("Merge Sort Best Case Time: " + (endTime - startTime) + " nanoseconds");

        // Worst case: Reverse sorted array
        int[] worstCaseArray = new int[size];
        for (int i = 0; i < size; i++) {
            worstCaseArray[i] = size - i - 1;
        }

        startTime = System.nanoTime();
        performMergeSort(worstCaseArray);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Worst Case Time: " + (endTime - startTime) + " nanoseconds");
    }
}