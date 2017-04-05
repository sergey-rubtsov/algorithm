package my.algorithm.schedule.clinic;

import java.util.ArrayList;
import java.util.List;

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
        Node next = new Node(nextService);
        for (Node child : begin.getChildren()) {
            int type = next.getService().getId();

        }
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

    private boolean checkParents(Node node, int serviceId, int depth) {
        for (int i = 0; i < depth; i++) {
            if (null == node.getParent()) {
                return true;
            } else {
                node = node.getParent();
                if (null == node.getParent()) {
                    return true;
                } else if (node.getService().getId() == serviceId) {
                    return false;
                }
            }
        }
        return true;
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
}
