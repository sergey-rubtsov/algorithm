package my.algorithm.call;

import java.util.Date;
import java.util.concurrent.Callable;

public class ScheduledReportRequest implements Callable<Attempt> {

    public Attempt call() throws Exception {
        Attempt attempt = new Attempt();
        Date now = new Date();
        if (now.getTime() % 13 == 0) {
            System.out.println("Success, date: " + now.toString());
            attempt.setSuccessful(true);
        } else {
            System.out.println("Unsuccess, date: " + now.toString());
        }
        return attempt;
    }

}
