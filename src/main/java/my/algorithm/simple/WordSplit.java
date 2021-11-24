package my.algorithm.simple;

/*
*
* You are given a string consisting of lowercase letters of the English alphabet.
* You must split this string into a minimal number of substrings in such a way
* that no letter occurs more than once in each substring.
* Examples:
* 'abacdec' -> 3, substrings ('a', 'bac', 'dec'), ('a', bacd', 'ec') and ('ab', 'ac', 'dec').
* 'dddd' -> 4, substrings ('d', 'd', 'd', 'd')
* 'cycle' -> 2, substrings ('cy', 'cle') or ('c', 'ycle')
* 'abba' -> 2, substrings ('ab', 'ba')
* N is an integer within the range [1..1,000,000];
* string S consists only of lowercase letters (a−z).
* Correctness 100%
* Performance 71%
* Task score 85%
* * */

import java.util.HashSet;

public class WordSplit {

    public int solution(String S) {
        if (S.length() == 1) {
            return 1;
        }
        int count = 1;
        HashSet<Character> set = new HashSet<>();
        for(Character ch: S.toCharArray()) {
            if(set.contains(ch)) {
                set.clear();
                count++;
            }
            set.add(ch);
        }
        return count;
    }

}
