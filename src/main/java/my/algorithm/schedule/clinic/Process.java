package my.algorithm.schedule.clinic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by sergei.rubtcov on 3/30/2017.
 */
public class Process {

    private int[] requestedServices;

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
        LinkedHashMap<Integer, Service> nodes = new LinkedHashMap<>();
        nodes.put(begin.getId(), begin);
        List<LinkedHashMap<Integer, Service>> branches = new ArrayList<>();
        branches.add(nodes);
        while (!following.isEmpty()) {
            Service next = following.pop();
            tryToExtendBranches(next, branches);
        }
        int count = 0;
        for (LinkedHashMap<Integer, Service> branch : branches) {
            for (Service service : branch.values()) {
                //System.out.print(service.getId() + "-" + service.getStartTime() + "-" + service.getEndTime() + " ");
                System.out.print(service.hashCode() + " ");
                count++;
            }
            System.out.println();
        }
        System.out.println(count);
    }

    private void tryToExtendBranches(Service next, List<LinkedHashMap<Integer, Service>> branches) {
        List<LinkedHashMap<Integer, Service>> newBranches = new ArrayList<>();
        for (LinkedHashMap<Integer, Service> branch : branches) {
            if (!branch.containsKey(next.getId())) {
                branch.put(next.getId(), next);
            } else {
                if (!next.equals(branch.get(next.getId()))) {
                    LinkedHashMap<Integer, Service> newBranch = new LinkedHashMap<>(branch);
                    newBranch.put(next.getId(), next);
                    newBranches.add(newBranch);
                }

            }
            //newBranches.addAll(createNewBranches(branch));
        }
/*        for (LinkedHashMap<Integer, Service> branch : branches) {
            branch.put(next.getId(), next);
        }*/
        branches.addAll(newBranches);
    }

    private List<LinkedHashMap<Integer, Service>> createNewBranches(LinkedHashMap<Integer, Service> branch) {
        List<LinkedHashMap<Integer, Service>> newBranches = new ArrayList<>();
        List<Service> accumulator = new ArrayList<>();
        for (Service service : branch.values()) {
            accumulator.add(service);
            LinkedHashMap<Integer, Service> newBranch = new LinkedHashMap<>();
            for (Service newService : accumulator) {
                newBranch.put(newService.getId(), newService);
            }
            newBranches.add(newBranch);
        }
        return newBranches;
    }

    private LinkedHashMap<Integer, Service> clone(LinkedHashMap<Integer, Service> list) {
        return new LinkedHashMap<Integer, Service>(list);
    }

    private List<Node> tryToAddNode(Node node, List<Node> nodes) {
        List<Node> newNodes = new ArrayList<>();
        for (Node next : nodes) {
            if (checkPedigree(node, next)) {
                next.setParent(node);
                node.addChild(next);
                newNodes.add(next);
            }
        }
        return newNodes;
    }

    private boolean checkPedigree(Node parent, Node child) {
        for (int i = 0; i < requestedServices.length + 1; i++) {
            if (parent == null) {
                return true;
            }
            if (parent.getService().getId() == child.getService().getId()) {
                return false;
            } else {
                parent = parent.getParent();
            }
        }
        return true;
    }

    /*        created.add(new Node(begin));
        while (!services.isEmpty()) {
            Node newNode = new Node(services.pop());
            tryToAddNode(newNode, created);
        }*/

}
