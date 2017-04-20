package my.algorithm.schedule.clinic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serg on 05.04.2017.
 */
public class Branch {

    int[] requestedServices;

    private Node begin;

    private List<Node> index = new ArrayList<>();

    public Branch(Service begin, int[] requestedServices) {
        this.begin = new Node(begin, 0);
        this.begin.setFound(false);
        this.requestedServices = requestedServices;
    }

    public void tryToExtend(Service nextService, int idealTime) {
        Node parent = begin;
        Node next = new Node(nextService);
        recursiveScan(parent, next);
    }

    private void recursiveScan(Node parent, Node next) {
        if (parent.getDepth() >= requestedServices.length) {
            return;
        }
        int type = next.getService().getId();
        if (parent.endsBefore(next) && !parent.hasThisAncestorType(type, requestedServices.length)) {
            next = new Node(next.getService());
            next.setParent(parent);
            parent.addChild(next);
            index.add(next);
        }
        for (Node child : parent.getChildren()) {
            recursiveScan(child, next);
        }
    }

    public List<Node> getFound() {
        List<Node> found = new ArrayList<>();
        for (Node node : index) {
            if (node.isFound() && node.getDepth() >= requestedServices.length - 1) {
                found.add(node);
            }
        }
        return found;
    }
}
