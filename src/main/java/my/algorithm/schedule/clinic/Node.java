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

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
