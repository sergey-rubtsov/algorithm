package my.algorithm.schedule.clinic;

/**
 * Created by sergei.rubtcov on 3/27/2017.
 */
public class Schedule {

    private int time;

    private int[] services;



    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int[] getServices() {
        return services;
    }

    public void setServices(int[] services) {
        this.services = services;
    }

    public Schedule(int time, int[] services) {
        this.time = time;
        this.services = services;
    }
}
