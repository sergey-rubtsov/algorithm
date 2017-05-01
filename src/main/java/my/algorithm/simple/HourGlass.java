package my.algorithm.simple;

/**
 * Created by Serg on 30.04.2017.
 */
public class HourGlass {

    public static void main(String[] args) {
        //int arr[][] = new int[6][6];
        int arr[][] = {
                {1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 0, 2, 4, 4, 0},
                {0, 0, 0, 2, 0, 0},
                {0, 0, 1, 2, 4, 0}};
        int max = 0;
        for (int i = 1; i < 5; i++){
            for(int j = 1; j < 5; j++){
                int sum = arr[i-1][j-1] +
                arr[i-1][j] +
                arr[i-1][j+1] +
                arr[i][j] +
                arr[i+1][j-1] +
                arr[i+1][j] +
                arr[i+1][j+1];
                if (sum > max) max = sum;
            }

        }
        System.out.println(max);
    }
}
