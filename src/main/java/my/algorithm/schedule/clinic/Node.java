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

    private boolean found = true;

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
        Node ancestor = this;
        for (int i = 0; i < length; i++) {
            if (ancestor.typeIs(type)) {
                found = false;
                return true;
            } else {
                if (ancestor.hasParent()) {
                    ancestor = ancestor.getParent();
                } else {
                    found = false;
                    return false;
                }
            }
        }
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

    public String printServices(int length) {
        final StringBuilder sb = new StringBuilder();
        List<Node> reversed = new ArrayList<>();
        Node ancestor = this;
        reversed.add(ancestor);
        for (int i = 0; ((i < length - 1) && ancestor.hasParent()); i++) {
            ancestor = ancestor.getParent();
            reversed.add(ancestor);

        }
        appendService(sb, reversed.get(reversed.size() - 1));
        for (int i = reversed.size() - 1; i > 0; i--) {
            sb.append('\n');
            appendService(sb, reversed.get(i - 1));
        }
        return sb.toString();
    }

    private void appendService(StringBuilder sb, Node node) {
        sb.append(node.getService().getId()).append(" ")
                .append(node.getService().getDoctor()).append(" ")
                .append(node.getService().getStartTime());
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean hasParent() {
        return getParent() != null;
    }

    public boolean typeIs(int type) {
        return getService().getId() == type;
    }

    public int calculateTime(int length) {
        Node ancestor = this;
        int end = ancestor.getService().getEndTime();
        for (int i = 0; ((i < length - 1) && ancestor.hasParent()); i++) {
            ancestor = ancestor.getParent();
        }
        int start = ancestor.getService().getStartTime();
        return end - start;
    }
}
