import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    private List<Node> nodes;
    private Queue<Node> nodesWithDegreeLessThanFour = new LinkedList<>(); // Q
    private Queue<Node> otherReducibleNodes = new LinkedList<>(); // R

    private int newIdCounter;

    public void removeNode(Node nodeToRemoveFromGraph) {
        nodes.remove(nodeToRemoveFromGraph);
        List<Node> neighboursOfNodeToRemoveFromGraph = nodeToRemoveFromGraph.getNeighbours();
        neighboursOfNodeToRemoveFromGraph.forEach(neighbour -> neighbour.removeNodeFromNeighbours(nodeToRemoveFromGraph));
        otherReducibleNodes.remove(nodeToRemoveFromGraph);
        nodesWithDegreeLessThanFour.remove(nodeToRemoveFromGraph);
    }

    public void insertIfDoesNotExist(Node element, Queue<Node> queue) {
        if (!queue.contains(element)) {
            queue.add(element);
        }
    }

    public Graph() {
        this.nodes = new LinkedList<>();
        this.newIdCounter = 0;
    }

    public int getNewId() {
        return newIdCounter++;
    }

    public void addNode(Node node) {
        if (!getNodes().contains(node)) {
            getNodes().add(node);
            for (Node neighbour : node.getNeighbours()) {
                neighbour.addNeighbour(node);
            }
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void addNodes(List<Node> nodes) {
        this.nodes.addAll(nodes);
        this.newIdCounter = nodes.size() + 1;
    }

    public Queue<Node> getNodesWithDegreeLessThanFour() {
        return nodesWithDegreeLessThanFour;
    }

    public Queue<Node> getOtherReducibleNodes() {
        return otherReducibleNodes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node node : nodes) {
            builder.append(node.toString()).append(": ").append(node.getPrintedNeighbours()).append("\n");
        }
        return builder.toString();
    }
}