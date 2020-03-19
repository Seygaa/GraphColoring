import java.util.LinkedList;
import java.util.List;

public class Node {
    private Graph graph;
    private int id;
    private int colour = -1;
    private List<Node> neighbours;

    public void setColourNotInNeighbours(List<Node> neighbours, int maxColor) {
        int[] colours = new int[maxColor];
        for (Node node : neighbours) {
            colours[node.getColour()]++;
        }
        for (int i = 0; i < maxColor; i++) {
            if (colours[i] == 0) {
                colour = i;
            }
        }
    }

    public boolean isReducible() {
        for (Node node : graph.getNodes()) {
            if (node.getDegree() < 5) {
                return true;
            } else if (node.getDegree() == 5 && !getNodesToIdentify(11).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public List<Node> getNodesToIdentify(int degree) {
        List<Node> nodesWithCorrectDegree = getNeighboursWithCorrectDegree(degree);
        for (Node node1 : nodesWithCorrectDegree) {
            for (Node node2 : nodesWithCorrectDegree) {
                if (node1 != node2 && !node1.getNeighbours().contains(node2)) {
                    return List.of(node1, node2);
                }
            }
        }
        return List.of();
    }

    public List<Node> getNeighboursWithCorrectDegree(int degree) {
        List<Node> result = new LinkedList<>();
        for (Node neighbour : this.getNeighbours()) {
            if (neighbour.getDegree() <= degree) {
                result.add(neighbour);
            }
        }
        return result;
    }

    public int getDegree() {
        return neighbours.size();
    }

    public void removeNodeFromNeighbours(Node node) {
        neighbours.remove(node);
    }

    public Node(Graph graph, int id) {
        this.graph = graph;
        this.id = id;
        this.neighbours = new LinkedList<>();
    }

    public void addNeighbour(Node node) {
        if (!getNeighbours().contains(node)) {
            getNeighbours().add(node);
            node.addNeighbour(this);
        }
    }

    public void addNeighbours(List<Node> neighboursToAdd) {
        neighboursToAdd.forEach(this::addNeighbour);
    }

    public int getId() {
        return id;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    @Override
    public String toString() {
        return String.format("%d(%d)", id, colour);
    }

    public String getPrintedNeighbours() {
        StringBuilder builder = new StringBuilder();

        for (Node node : neighbours) {
            builder.append(node.toString()).append(", ");
        }
        if (builder.length() >= 2) {
            builder.setLength(builder.length() - 2);
        }
        return builder.toString();
    }
}