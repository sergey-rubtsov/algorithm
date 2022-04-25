package my.algorithm.simple;

public class RomanToInt {

/*    Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
*/

    public static void main(String[] args) {
        RomanToInt romanToInt = new RomanToInt();
        String test0 = "MDCCCLXXVI"; //1876
        System.out.println(romanToInt.romanToInt(test0));
        String test = "MMMDCCXXIV"; //3724
        System.out.println(romanToInt.romanToInt(test));
        String one = "I";
        System.out.println(romanToInt.romanToInt(one));
        String five = "V";
        System.out.println(romanToInt.romanToInt(five));
        String nine = "IX";
        System.out.println(romanToInt.romanToInt(nine));
        String twentySeven = "XXVII";
        System.out.println(romanToInt.romanToInt(twentySeven));

    }

    public int romanToInt(String s) {
        int[] numbers = new int[s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < numbers.length; i++) {
            char c = chars[i];
            if (c == 'I') {
                numbers[i] = 1;
            } else if (c == 'V') {
                numbers[i] = 5;
            } else if (c == 'X') {
                numbers[i] = 10;
            } else if (c == 'L') {
                numbers[i] = 50;
            } else if (c == 'C') {
                numbers[i] = 100;
            } else if (c == 'D') {
                numbers[i] = 500;
            } else if (c == 'M') {
                numbers[i] = 1000;
            }
        }
        for (int i = 1, n = numbers[0]; i < numbers.length; i++) {
            if (n < numbers[i]) {
                numbers[i - 1] = -numbers[i - 1];
            }
            n = numbers[i];
        }
        int result = 0;
        for (int i = 0; i < numbers.length; i++) {
            result = result + numbers[i];
        }
        return result;
    }

}
