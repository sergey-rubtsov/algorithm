package my.algorithm.simple;

public class SearchInsertPosition {

    public static void main(String[] args) {
        SearchInsertPosition searchInsertPosition = new SearchInsertPosition();
        int target;
        int[] num5 = {1,3,5,6};
        target = 5;
        System.out.println(searchInsertPosition.searchInsert(num5, target)); // 2

        target = 1;
        int[] num0 = {1};
        System.out.println(searchInsertPosition.searchInsert(num0, target)); // 0
        target = 1;
        int[] num13 = {1, 3};
        System.out.println(searchInsertPosition.searchInsert(num0, target)); // 0

        int[] nums = {1,3,5,6};
        target = 0;
        System.out.println(searchInsertPosition.searchInsert(nums, target));//0
        target = 2;
        System.out.println(searchInsertPosition.searchInsert(nums, target));
        target = 5;
        System.out.println(searchInsertPosition.searchInsert(nums, target));
        target = 7;
        System.out.println(searchInsertPosition.searchInsert(nums, target));

        int[] nums0 = {0};
        target = 7;
        System.out.println(searchInsertPosition.searchInsert(nums0, target));
        int[] nums1 = {8};
        target = 7;
        System.out.println(searchInsertPosition.searchInsert(nums1, target));
    }

    public int searchInsert(int[] nums, int target) {
        if (nums.length == 1) {
            if (nums[0] >= target) {
                return 0;
            } else return 1;
        }
        if (nums[0] > target) {
            return 0;
        } else if (nums[nums.length - 1] < target) {
            return nums.length;
        }
        int end = nums.length - 1;
        for (int i = 0; i < end; ) {
            if (nums[i] == target) {
                return i;
            } else if (nums[end] == target) {
                return end;
            }
            int middle = (end - i) / 2 + i;
            if (target > nums[(end - i) / 2 + i]) {
               i = middle;
            } else {
                end = middle;
            }
            if (end == i || end == i + 1) {
                return end;
            }
        }
        return 0;
    }


}
