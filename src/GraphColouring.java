import java.util.*;

public class GraphColouring {
    private static final int NUMBER_OF_COLOURS = 5;

    private Stack<RemovedData> removedNodes = new Stack<>(); // S stack.push(i);


    private Graph graph;


    public GraphColouring(Graph graph) {
        this.graph = graph;
        for (Node node : graph.getNodes()) {
            if (node.getDegree() < 5) {
                graph.insertIfDoesNotExist(node, graph.getNodesWithDegreeLessThanFour());
            } else if (node.getDegree() == 5 && !node.getNodesToIdentify(11).isEmpty()) {
                graph.insertIfDoesNotExist(node, graph.getOtherReducibleNodes());
            }

        }
    }

    public void executePhase1() {
        while (!graph.getNodesWithDegreeLessThanFour().isEmpty() || !graph.getOtherReducibleNodes().isEmpty()) {
            if (!graph.getNodesWithDegreeLessThanFour().isEmpty()) {
                Node node = graph.getNodesWithDegreeLessThanFour().poll();
                reduce(node);
            } else {
                Node node = graph.getOtherReducibleNodes().poll();
                reduce(node);
                List<Node> nodesToIdentify = node.getNodesToIdentify(11);
                if (nodesToIdentify.size() == 2) {
                    identify(nodesToIdentify.get(0), nodesToIdentify.get(1));
                }
            }
        }
    }

    public void executePhase2() {
        while (!removedNodes.empty()) {
            RemovedData removedData = removedNodes.pop();
            if (removedData.isIdentified()) {
                Node nodeX = removedData.getNodeX();
                nodeX.addNeighbours(removedData.getOriginalNeighboursOfX());
                nodeX.setColour(removedData.getNodeT().getColour());

                Node nodeY = removedData.getNodeY();
                nodeY.addNeighbours(removedData.getOriginalNeighboursOfY());
                nodeY.setColour(removedData.getNodeT().getColour());

                graph.addNode(nodeX);
                graph.addNode(nodeY);
                graph.removeNode(removedData.getNodeT());

            } else {
                Node node = removedData.getNodeT();
                List<Node> neighbours = removedData.getOriginalNeighboursOfT();

                node.setColourNotInNeighbours(neighbours, NUMBER_OF_COLOURS);
                graph.addNode(node);
            }
        }
    }

    private void consider(Node nodeToConsider) {
        if (nodeToConsider.getDegree() < 5) {
            graph.getOtherReducibleNodes().remove(nodeToConsider);
            graph.insertIfDoesNotExist(nodeToConsider, graph.getNodesWithDegreeLessThanFour());
        }
        if (nodeToConsider.getDegree() == 5) {
            if (nodeToConsider.isReducible()) {
                graph.insertIfDoesNotExist(nodeToConsider, graph.getOtherReducibleNodes());
            } else {
                graph.getOtherReducibleNodes().remove(nodeToConsider);
            }
        }
        if (nodeToConsider.getDegree() == 11) {
            for (Node node : nodeToConsider.getNeighbours()) {//
                if (node.isReducible()) {
                    graph.insertIfDoesNotExist(node, graph.getOtherReducibleNodes());
                }
            }
        }
    }


    private void reduce(Node nodeToReduce) {
        RemovedData removedNode = new RemovedData(nodeToReduce, nodeToReduce.getNeighbours());
        removedNodes.push(removedNode);

        graph.removeNode(nodeToReduce);

        for (Node node : nodeToReduce.getNeighbours()) {
            consider(node);
        }
    }


    private void identify(Node node1, Node node2) {

        List<Node> originalNeighbours1 = node1.getNeighbours();
        List<Node> originalNeighbours2 = node2.getNeighbours();

        RemovedData removedData = new RemovedData(
                node1,
                originalNeighbours1,
                node2,
                originalNeighbours2,
                graph
        );

        graph.removeNode(node1);
        graph.removeNode(node2);

        removedNodes.push(removedData);

        consider(removedData.getNodeT());
        for (Node neighbour : removedData.getOriginalNeighboursOfT()) {
            consider(neighbour);
        }
    }
}