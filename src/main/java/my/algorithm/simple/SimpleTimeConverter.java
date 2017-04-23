package my.algorithm.simple;

/**
 * Created by Serg on 23.04.2017.
 */
public class SimpleTimeConverter {

    public static void main(String[] args) {
        String time = "11:00:00PM";
        if ("12:00:00AM".equals(time)) {
            System.out.print("00:00:00");
        } else if ("12:00:00PM".equals(time)) {
            System.out.print("12:00:00");
        } else {
            char[] array = time.toCharArray();
            int hour = Integer.parseInt(array[0] + "" + array[1]);
            if (array[array.length - 2] == 'P') {
                if (hour != 12) {
                    hour = hour + 12;
                    array[0] = Integer.toString(hour).toCharArray()[0];
                    array[1] = Integer.toString(hour).toCharArray()[1];
                }
            } else {
                if (hour == 12) {
                    array[0] = '0';
                    array[1] = '0';
                }
            }
            for (int i = 0; i < 8; i++) {
                System.out.print(array[i]);
            }
        }
    }
}
