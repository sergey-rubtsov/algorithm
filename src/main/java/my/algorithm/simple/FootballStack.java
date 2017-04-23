package my.algorithm.simple;

import java.util.Scanner;

/**
 * Created by sergei.rubtcov on 3/21/2017.
 */
public class FootballStack {

    /**
     * https://www.hackerearth.com/practice/data-structures/stacks/basics-of-stacks/practice-problems/algorithm/the-football-fest-6/
     *
     * Initially, Player having id=23 posses ball. After pass 1,
     * Player having id=86 posses ball. After pass 2, Player having id=63 posses ball.
     * After pass 3, Player having id=60 posses ball. After pass 4,
     * Player having id=63 posses ball. After pass 5, Player having id=47 posses ball.
     * After pass 6, Player having id=63 posses ball. After pass 7,
     * Player having id=99 posses ball. After pass 8, Player having id=9 posses ball.
     * After pass 9, Player having id=99 posses ball. After pass 10,
     * Player having id=9 posses ball.
     *
     1
     10 23
     P 86
     P 63
     P 60
     B
     P 47
     B
     P 99
     P 9
     B
     B
     println output:
     Player 9
     */
    public static void football() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            int passNumber = scanner.nextInt();
            int initPlayerId = scanner.nextInt();
            StackArray stack = new StackArray(passNumber);
            stack.push(initPlayerId);
            for (int j = 0; j < passNumber; j++) {
                String next = scanner.next();
                if (next.equals("B")) {
                    int current = stack.pop();
                    int previous = stack.peek();
                    stack.push(current);
                    stack.push(previous);
                } else if (next.equals("P")) {
                    stack.push(scanner.nextInt());
                }
            }
            System.out.println("Player " + stack.peek());
        }
    }
}
