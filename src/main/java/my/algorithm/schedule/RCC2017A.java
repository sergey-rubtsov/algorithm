package my.algorithm.schedule;

import java.util.Arrays;

/**
 * Created by sergei.rubtcov on 3/20/2017.
 */
public class RCC2017A {

    public static void main(String[] args) {
        int count = 3;
        int[] force = {2, 5, 3};
        int[] result = getOrder(count, force);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println("");
        count = 5;
        int[] force2 = {-1, 1, 0, 1, -1};
        result = getOrder(count, force2);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    /**
     * count = 3
     * force = 2 5 3
     * result is 2 3 5
     *
     * count = 5
     * force = -1 1 0 1 -1
     * result is -1 1 1 -1 0
     * @param count count of enemies
     * @param forces force of each enemy
     * @return order of enemies to destroy
     */
    public static int[] getOrder(int count, int[] forces) {
        Arrays.sort(forces);
        int balance = 0;
        for (int force : forces) {
            balance = balance + force;
        }
        if (balance == 0) {
            processBalanced(forces);
        }
        return forces;
    }

    private static void processBalanced(int[] forces) {
        for (int i = forces.length - 1; i > 0; i--) {
            if (forces[i] == 0) {
                int swap = forces[forces.length - 1];
                forces[i] = swap;
                forces[forces.length - 1] = 0;
                break;
            }
        }
    }

}
