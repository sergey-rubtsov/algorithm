package my.algorithm.schedule.clinic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergei.rubtcov on 3/30/2017.
 */
public class Node {

    private Node parent;

    private Service service;

    private int depth;

    private List<Node> children = new ArrayList<>();

    public Node(Service service, int depth) {
        this.service = service;
        this.depth = depth;
    }

    public Node(Service nextService) {
        this.service = nextService;
        this.depth = 0;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Service getService() {
        return service;
    }

    public void addChild(Node child) {
        child.setDepth(getDepth() + 1);
        children.add(child);
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean hasThisAncestorType(int type, int length) {
        if (this.getService().getId() == type) {
            return true;
        }
        Node ancestor = parent;
        if (null == ancestor) {
            return false;
        }
/*        for (int i = 0; i < length - 1; i++) {
            if (null == ancestor) {
                return false;
            }
            if (ancestor.getService().getId() == type) {
                return true;
            } else {
                ancestor = parent.getParent();
            }
        }*/
        return false;
    }

    public boolean endsBefore(Node next) {
        return this.service.getEndTime() <= next.getService().getStartTime();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        if (null != parent) {
            Service parentService = parent.getService();
            if (null != parentService) {
                sb.append("parent=").append(parentService).append(", ");
            }
        }
        sb.append("service=").append(service);
        sb.append('}');
        return sb.toString();
    }
}
