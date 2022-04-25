package my.algorithm.simple;

import org.junit.Assert;
import org.junit.Test;

public class RotateImage {

    /*
    * You have to rotate the image in-place, which means
    * you have to modify the input 2D matrix directly.
    * DO NOT allocate another 2D matrix and do the rotation.
    *
    * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    * Output: [[7,4,1],[8,5,2],[9,6,3]]
    *
    * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
    * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
    * */

    @Test
    public void test() {
        RotateImage rotateImage = new RotateImage();

        int[][] input = {{5,1,9,11}, {2,4,8,10}, {13,3,6,7}, {15,14,12,16}};

        int[][] i = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] output = {{15,13,2,5}, {14,3,4,1}, {12,6,8,9}, {16,7,10,11}};
        rotateImage.rotate(input);
        Assert.assertArrayEquals(output, input);
    }

    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i > j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0, k = matrix[i].length - 1; j * 2 < matrix[i].length; j++, k--) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][k];
                matrix[i][k] = temp;
            }
        }
    }

}
