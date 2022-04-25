package my.algorithm.simple;

public class MedianTwoSortedArrays {

    public static void main(String[] args) {
        MedianTwoSortedArrays median = new MedianTwoSortedArrays();
        int[] nums1 = {2, 5, 6}, nums2 = {1, 3};
        //int[] nums1 = {1, 2}, nums2 = {3, 4};
        System.out.println(median.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        for (int i = 0, index1 = 0, index2 = 0; i < merged.length; i++) {
            int val1, val2;
            if (index1 < nums1.length) {
                if (index2 < nums2.length) {
                    val1 = nums1[index1];
                    val2 = nums2[index2];
                    if (val1 < val2) {
                        merged[i] = val1;
                        index1++;
                    } else {
                        merged[i] = val2;
                        index2++;
                    }
                } else {
                    merged[i] = nums1[index1];
                    index1++;
                }
            } else {
                merged[i] = nums2[index2];
                index2++;
            }
        }
        if ((nums1.length + nums2.length) % 2 == 0) {
            return (double) (merged[(nums1.length + nums2.length) / 2 - 1] + merged[(nums1.length + nums2.length )/ 2]) / 2;
        } else {
            return merged[(nums1.length + nums2.length) / 2];
        }
    }

}
