package my.algorithm.simple;

public class RegularExpressionMatching {


    //TODO with recursion
    public static void main(String[] args) {
        RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();
        String s, p;
        s = "ab";
        p = ".*..";
        //System.out.println(regularExpressionMatching.isMatch(s, p)); // true
        s = "ab";
        p = ".*c";
        System.out.println(!regularExpressionMatching.isMatch(s, p)); // false
        s = "a";
        p = "ab*";
        //System.out.println(regularExpressionMatching.isMatch(s, p)); // true
        s = "mississippi";
        p = "mis*is*ip*.";
        //System.out.println(regularExpressionMatching.isMatch(s, p)); // true
/*         s = "mississippi";
        p = "mis*is*p*.";
        System.out.println(!regularExpressionMatching.isMatch(s, p)); // false
        s = "ab";
        p = ".*";
        System.out.println(regularExpressionMatching.isMatch(s, p)); // true
        s = "mis";
        p = "m..";
        System.out.println(regularExpressionMatching.isMatch(s, p)); // true
        s = "a";
        p = ".*..a*";
        System.out.println(!regularExpressionMatching.isMatch(s, p)); // false*/
    }

    private static boolean isMatch(char[] chars, char[][] pattern, int i, int j) {
        if (i >= (chars.length - 1) && j >= (pattern[0].length - 1)) return true;
        if (j >= (pattern[0].length - 1)) return j >= (pattern[0].length - 1);

        char token = pattern[1][j];
        char value = pattern[0][j];
        char current = chars[i];

        boolean first_match = (i < chars.length - 1 && (value == current || token == '.'));

        if (token == 'L') {
            return current == value && isMatch(chars, pattern, ++i, ++j);
        } else if (token == '.') {
            return isMatch(chars, pattern, ++i, ++j);
        } else if (token == 'S') {
            return isMatch(chars, pattern, i, ++j) || first_match && isMatch(chars, pattern, ++i, j);
        }
        return false;
    }

    public boolean isMatch(String s, String p) {
        char[] chars = s.toCharArray();
        char[] ptr = p.toCharArray();
        char[][] pattern = new char[2][0];
        int lit = 0;
        for (int i = 0; i < ptr.length; i++) {
            char next = ptr[i];
            if (next != '*') {
                lit++;
            }
            if (i == ptr.length - 1) {
                pattern = new char[2][lit];
            }
        }
        for (int i = ptr.length - 1, j = lit - 1; i >= 0; i--) {
            char next = ptr[i];
            if (next == '*') {
                pattern[1][j] = 'S';
            } else if (next == '.') {
                if (pattern[1][j] != 'S') {
                    pattern[1][j] = '.';
                }
                pattern[0][j] = '.';
                j--;
            } else {
                if (pattern[1][j] != 'S') {
                    pattern[1][j] = 'L';
                }
                pattern[0][j] = next;
                j--;
            }
        }
        return isMatch(chars, pattern, 0, 0);
    }

}
