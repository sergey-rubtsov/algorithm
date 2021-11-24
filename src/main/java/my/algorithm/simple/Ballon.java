package my.algorithm.simple;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
* How many BALLONs into string
* Correctness test cases Passed 2 out of 4
* Performance test cases Passed 3 out of 6
*
* */

public class Ballon {

    public static void main(String[] args) {
        System.out.println(solution("BALLO"));
        System.out.println(solution("BALLONX"));
        System.out.println(solution("BALLONBALLONX"));
    }

    public static int solution(String S) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('B', 0);
        map.put('A', 0);
        map.put('L', 0);
        map.put('O', 0);
        map.put('N', 0);
        for (char c: S.toCharArray()) {
            if (map.containsKey(c)) {
                int value = map.get(c) + 1;
                map.put(c, value);
            }
        }
        int l = map.get('L') / 2;
        map.put('L', l);
        return Collections.min(map.values());
    }

}
