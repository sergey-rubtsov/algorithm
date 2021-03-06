package my.algorithm.schedule.clinic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sergei.rubtcov on 3/30/2017.
 */
public class Process {

    private int[] requestedServices;

    private int idealTime;

    private int maxTime;

    private static float factor = 1.6f;

    List<Branch> forest = new ArrayList<>();

    public Process(RawData data) {
        this.requestedServices = data.getRequestedServices();
        this.maxTime = data.getNumberOfMinutes();
        this.idealTime = calculateIdealTime(data.getRequestedServices(), data.getServiceDuration());
    }

    private int calculateIdealTime(int[] requestedServices, int[] serviceDuration) {
        if (serviceDuration.length < requestedServices.length) {
            throw new RuntimeException();
        }
        int result = 0;
        for (int service : requestedServices) {
            result = result + serviceDuration[service];
        }
        return result;
    }

    private List<Service> filterByServiceId(int[] requestedServices, List<Service> services) {
        Set<Integer> requiredServicesSet = new HashSet<>();
        for (int serviceId : requestedServices) {
            requiredServicesSet.add(serviceId);
        }
        List<Service> result = new ArrayList<>();
        for (Service service : services) {
            if (requiredServicesSet.contains(service.getId())) {
                result.add(service);
            }
        }
        return result;
    }

    public String process(List<Service> services) {
        services = filterByServiceId(requestedServices, services);
        Collections.sort(services);
        Deque<Service> first = new ArrayDeque<>();
        first.addAll(services);
        while (first.size() > 0) {
            Service begin = first.pop();
            Deque<Service> leftover = new ArrayDeque<>();
            leftover.addAll(first);
            step(begin, leftover);
        }
        List<Node> found = new ArrayList<>();
        for (Branch branch : forest) {
            found.addAll(branch.getFound());
        }
        Node optimal = findOptimal(found);
        return optimal.printServices(requestedServices.length);
    }

    private Node findOptimal(List<Node> found) {
        Node result = found.get(0);
        int time = result.calculateTime(requestedServices.length);
        for (Node node : found) {
            if (node.calculateTime(requestedServices.length) < time) {
                result = node;
            }
        }
        return result;
    }

    private void step(Service begin, Deque<Service> following) {
        Branch branch = new Branch(begin, requestedServices);
        while (!following.isEmpty()) {
            Service next = following.pop();
            for (int i = idealTime; i < maxTime; i = i + Math.round(idealTime * factor)) {
                branch.tryToExtend(next, idealTime);
            }
        }
        forest.add(branch);
    }

}
