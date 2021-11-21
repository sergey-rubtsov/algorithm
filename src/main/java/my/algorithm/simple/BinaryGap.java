package my.algorithm.simple;

public class BinaryGap {

    public static void main(String[] args) {
        System.out.println(binaryGap(16961));
        System.out.println(binaryGap(16673));
        System.out.println(binaryGap(8));
        System.out.println(binaryGap(5));
        System.out.println(binaryGap(3));
    }

    //This is for gaps between ones
    //16961 or 100001001000001 - returns 6
    //16673 or 100000100100001 - returns 6
    //8 or 1000 - returns 0
    //5 or 101 - returns 2
    //3 or 11 - returns 1
    public static int binaryGap(int n) {
        int ones = 0;
        int zeroes = 1;
        int max = 1;
        boolean started = false;
        while (n > 0) {
            if (n % 2 == 0) {
                if (started) {
                    zeroes++;
                    if (max < zeroes) {
                        max = zeroes;
                    }
                }
            } else {
                ones++;
                started = true;
                zeroes = 1;
            }
            n = n / 2;
        }
        if (ones == 1) {
            return 0;
        }
        return max;
    }

}
