import java.util.*;
public class Que3partA {

    public static void quickSort(int[] array,int low,int high){
        if(low < high){
            int partition_index = partition(array,low,high);
            quickSort(array,low,partition_index-1);
            quickSort(array,partition_index+1,high);
        }
    }

        public static int partition(int[] array,int low,int high){
            int pivot = array[high];
            int i = low-1;
            for(int j=low;j<high;j++){
                if(array[j] <= pivot){
                    i=i+1;
                    swap(array,i,j);
                }
            }
            swap(array,i+1,high);
            return i+1;
        }

        public static void swap(int[] array,int i,int j){
            int temp = array[i];
            array[i] = array[j];
            array[j]  = temp;
        }

        public static int[] generateBestCase(int n){
            int[] best_case = new int[n];
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
            	 best_case[i] = rand.nextInt(100000) + 1;
            }
            return best_case;
        }

        public static int[] generateWorstCase(int n){
            int[] worst_case = new int[n];
            for(int i=0;i<n;i++){
                worst_case[i] = n-1-i;
            }
            return worst_case;
        }

        public static void compareTime(int n){
            int[] bestcase = generateBestCase(n);
            int[] worstcase = generateWorstCase(n);

            //calculating best_case time
            long start_time = System.nanoTime();
            quickSort(bestcase,0,bestcase.length-1);
            long end_time = System.nanoTime();
            long bestCaseTime = end_time - start_time;

            //calculating worst_case time
            start_time = System.nanoTime();
            quickSort(worstcase,0,worstcase.length-1);
            end_time = System.nanoTime();
            long worstCaseTime = end_time - start_time;

            System.out.println("Best case time : " + bestCaseTime);
            System.out.println("Worst case time : " + worstCaseTime);
        }


        public static void main(String[] args){
            int n = 500;
            System.out.println("QuickSort time comparison : ");
            compareTime(n);

           
        }
}
