package my.algorithm.simple;

public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray rdfsa = new RemoveDuplicatesFromSortedArray();
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(rdfsa.removeDuplicates(nums));
        int[] nums2 = {1, 1, 2};
        System.out.println(rdfsa.removeDuplicates(nums2));
        int[] nums3 = {0, 0, 0};
        System.out.println(rdfsa.removeDuplicates(nums3));
        int[] nums4 = {0, 0};
        System.out.println(rdfsa.removeDuplicates(nums4));
        int[] nums5 = {-100, 0, 100};
        System.out.println(rdfsa.removeDuplicates(nums5));
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int current = nums[0];
        int j = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > current) {
                current = nums[i];
                nums[j] = current;
                j++;
            }
        }
        return j;
    }

}
