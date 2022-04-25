package my.algorithm.simple;

import java.util.HashMap;
import java.util.Map;

public class DivideArray {

    public static void main(String[] args) {
        int[] nums0 = {3, 2, 3, 2, 2, 2};
        int[] nums = {-1, -1};
        DivideArray divideArray = new DivideArray();
        System.out.println(divideArray.divideArray(nums0));
        System.out.println(divideArray.divideArray(nums));
    }

    public boolean divideArray(int[] nums) {
        if (nums.length % 2 != 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int new_val = map.get(nums[i]);
                new_val++;
                map.put(nums[i], new_val);
            } else {
                map.put(nums[i], 1);
            }
        }
        for (int val: map.values()) {
            if (val % 2 != 0) {
                return false;
            }
        }
        return true;
    }

}
