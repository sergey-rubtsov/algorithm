package my.algorithm.call;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Retrieve {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors
                .newScheduledThreadPool(1);
        System.out.println("Report generation scheduled");
        int att = 0;
        while (true) {
            ScheduledFuture<Attempt> future = scheduler.schedule(
                    new ScheduledReportRequest(), 2, TimeUnit.SECONDS);
            try {
                Attempt attempt = future.get();
                System.out.println(attempt + " Attempt: " + att);
                att++;
                if (attempt.isSuccessful()) break;
            } catch (InterruptedException e) {
                throw new RuntimeException();
            } catch (ExecutionException e) {
                throw new RuntimeException();
            }
        }

        System.out.println("Report generation completed");
        scheduler.shutdown();
    }
}
