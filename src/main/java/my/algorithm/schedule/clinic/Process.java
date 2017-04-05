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

    List<Branch> forest = new ArrayList<>();

    public Process(int[] requestedServices) {
        this.requestedServices = requestedServices;
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

    public void start(List<Service> services) {
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
    }

    private void step(Service begin, Deque<Service> following) {
        Branch branch = new Branch(begin, requestedServices);
        while (!following.isEmpty()) {
            Service next = following.pop();
            branch.tryToExtend(next);
        }
        forest.add(branch);
    }

}
