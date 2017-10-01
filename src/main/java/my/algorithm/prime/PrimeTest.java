package my.algorithm.prime;

import java.util.Scanner;

public class PrimeTest {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T = sc.nextInt();
        int[] numbers = new int[T];
        for (int i = 0; i < T; i++) {
            numbers[i] = sc.nextInt();
        }
        for (int i = 0; i < T; i++) {
            if (isPrime(numbers[i])) {
                System.out.println("Prime");
            } else {
                System.out.println("Not prime");
            }
        }
    }

    public static boolean isPrime(int number) {
        return true;
    }


}
