package my.algorithm.call;

/**
 * Created by Serg on 23.07.2016.
 */
public class Attempt {

    public Attempt() {
        this.successful = false;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    private boolean successful;
    private Report report;

    @Override
    public String toString() {
        return "Attempt{" +
                "successful=" + successful +
                ", report=" + report +
                '}';
    }
}
