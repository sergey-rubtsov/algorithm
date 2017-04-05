package my.algorithm.schedule.clinic;

import java.util.Deque;
import java.util.List;

public class Main {

    List<Deque<Service>> feeder;

    public static void main(String[] args) {
        Feeder feeder = new Feeder();
        RawData data = feeder.readFile("input.txt");
        List<Service> services = data.getServices();
        Process process = new Process(data.getRequestedServices());
        process.start(services);
/*        for (Node found : process.getFound()) {
            for (int i = 0; i < data.getNumberOfServices(); i++) {
                System.out.print(found.getService().getId() + " " + found.getService().getStartTime()+ " " + found.getService().getEndTime() + ";");
            }
            System.out.println("");
        }*/
    }

}
