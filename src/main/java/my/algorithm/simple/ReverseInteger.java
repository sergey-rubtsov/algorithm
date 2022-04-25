package my.algorithm.simple;

public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        System.out.println(ri.reverse(-1563847412));//0
        System.out.println(ri.reverse(1563847412));//0
        System.out.println(ri.reverse(-2147483648));//0
        System.out.println(ri.reverse(1534236469));//0
        System.out.println(ri.reverse(-123)); //-321
        System.out.println(ri.reverse(120)); //21
        System.out.println(ri.reverse(12000)); //21
        System.out.println(ri.reverse(123)); //321
        System.out.println(ri.reverse(1)); //1
        System.out.println(ri.reverse(-1)); //-1
        System.out.println(ri.reverse(0)); //0
        //System.out.println((int)Math.pow(2, 31) - 1); // 2147483646
        //System.out.println((int)Math.pow(-2, 31)); // -2147483648
        //2147483647
        //1534236469

    }

    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) return 0;
        if (Math.abs(x) < 10) return x;
        while (x % 10 == 0) {
            x = x / 10;
        }
        int y = Math.abs(x);
        int order = 1;
        while (y / 10 > 0) {
            y = y / 10;
            order = order * 10;
        }
        y = 1;
        int result = 0;
        while (order > 0) {
            int a = x - x / 10 * 10;
            if ((double) Math.abs(a) * order >= Integer.MAX_VALUE || result + (double) Math.abs(a) * order >= Integer.MAX_VALUE ) {
                return 0;
            }
            result = result + a * order;
            y = 10 * y;
            order = order / 10;
            x = x / 10;
        }
        return result;
    }
}
