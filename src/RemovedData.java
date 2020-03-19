import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RemovedData {
    private boolean identified;

    private Node nodeX;
    private List<Node> originalNeighboursOfX;

    private Node nodeY;
    private List<Node> originalNeighboursOfY;

    private Node nodeT;
    private List<Node> originalNeighboursOfT;

    public RemovedData(Node nodeX, List<Node> originalNeighboursOfX, Node nodeY, List<Node> originalNeighboursOfY, Graph graph) {
        this.identified = true;
        this.nodeX = nodeX;
        this.originalNeighboursOfX = new LinkedList<>(originalNeighboursOfX);
        this.nodeY = nodeY;
        this.originalNeighboursOfY = new LinkedList<>(originalNeighboursOfY);

        this.nodeT = new Node(graph, graph.getNewId());
        this.originalNeighboursOfT = getConcatenatedNeighbours(this.originalNeighboursOfX, this.originalNeighboursOfY);
        nodeT.addNeighbours(this.originalNeighboursOfT);
    }

    private List<Node> getConcatenatedNeighbours(List<Node> neighbours1, List<Node> neighbours2) {
        List<Node> neighboursOfNode1AndNode2 = new LinkedList<>(neighbours1);
        neighboursOfNode1AndNode2.addAll(neighbours2);
        return neighboursOfNode1AndNode2.stream().distinct().collect(Collectors.toList());
    }


    public RemovedData(Node nodeT, List<Node> originalNeighboursOfT) {
        this.identified = false;
        this.nodeT = nodeT;
        this.originalNeighboursOfT = new LinkedList<>(originalNeighboursOfT);
    }

    public boolean isIdentified() {
        return identified;
    }

    public Node getNodeX() {
        return nodeX;
    }

    public List<Node> getOriginalNeighboursOfX() {
        return originalNeighboursOfX;
    }

    public Node getNodeY() {
        return nodeY;
    }

    public List<Node> getOriginalNeighboursOfY() {
        return originalNeighboursOfY;
    }

    public Node getNodeT() {
        return nodeT;
    }

    public List<Node> getOriginalNeighboursOfT() {
        return originalNeighboursOfT;
    }

}