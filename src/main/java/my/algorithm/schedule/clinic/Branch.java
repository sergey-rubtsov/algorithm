package my.algorithm.schedule.clinic;

/**
 * Created by Serg on 05.04.2017.
 */
public class Branch {

    int[] requestedServices;

    private Node begin;

    public Branch(Service begin, int[] requestedServices) {
        this.begin = new Node(begin, 0);
        this.requestedServices = requestedServices;
    }

    public void tryToExtend(Service nextService) {
        Node parent = begin;
        Node next = new Node(nextService);
        recursiveScan(parent, next);
    }

    private void recursiveScan(Node parent, Node next) {
        int type = next.getService().getId();
        if (parent.endsBefore(next) && !parent.hasThisAncestorType(type, requestedServices.length)) {
            next.setParent(parent);
            parent.addChild(next);

        }
        for (Node child : parent.getChildren()) {
            recursiveScan(child, next);
        }
    }
}
