import java.util.Arrays;
import java.util.Comparator;

public class Test {

    public static void main(String... args) {
        Graph graph = buildGraph();
        Graph originalGraph = buildGraph();
        sortGraph(originalGraph);
        System.out.println(originalGraph);

        GraphColouring graphColouring = new GraphColouring(graph);
        graphColouring.executePhase1();
        graphColouring.executePhase2();


        sortGraph(graph);
        System.out.println("Result:");
        System.out.println(graph);

        checkResult(originalGraph, graph);

    }

    static void checkResult(Graph originalGraph, Graph result) {
        verifyGraphsSame(originalGraph, result);
        verifyColoursCorrect(result);
    }

    private static void verifyColoursCorrect(Graph result) {
        for (Node node : result.getNodes()) {
            int colour = node.getColour();
            for (Node neighbour : node.getNeighbours()) {
                assert neighbour.getColour() != colour;
            }
        }
    }

    private static void sortGraph(Graph graph) {
        graph.getNodes().sort(Comparator.comparingInt(Node::getId));
        for (Node node : graph.getNodes()) {
            node.getNeighbours().sort(Comparator.comparingInt(Node::getId));
        }
    }

    private static void verifyGraphsSame(Graph originalGraph, Graph result) {
        assert originalGraph.getNodes().size() == result.getNodes().size();

        for (int i = 0; i < originalGraph.getNodes().size(); i++) {
            Node originalNode = originalGraph.getNodes().get(i);
            Node resultNode = result.getNodes().get(i);

            assert originalNode.getNeighbours().size() == resultNode.getNeighbours().size();

            for (int j = 0; j < originalNode.getNeighbours().size(); j++) {
                assert originalNode.getNeighbours().get(j).getId() == resultNode.getNeighbours().get(j).getId();
            }

        }

    }

    private static Graph buildGraph() {
        Graph graph = new Graph();

        Node node1 = new Node(graph, 1);
        Node node2 = new Node(graph, 2);
        Node node3 = new Node(graph, 3);
        Node node4 = new Node(graph, 4);
        Node node5 = new Node(graph, 5);
        Node node6 = new Node(graph, 6);
        Node node7 = new Node(graph, 7);
        Node node8 = new Node(graph, 8);
        Node node9 = new Node(graph, 9);
        Node node10 = new Node(graph, 10);
        Node node11 = new Node(graph, 11);
        Node node12 = new Node(graph, 12);

        node1.addNeighbours(Arrays.asList(node2, node3, node6, node7, node8));
        node2.addNeighbours(Arrays.asList(node1, node3, node4, node9, node8));
        node3.addNeighbours(Arrays.asList(node1, node2, node4, node5, node6));
        node4.addNeighbours(Arrays.asList(node2, node3, node9, node5, node11));
        node5.addNeighbours(Arrays.asList(node3, node4, node6, node11, node12));
        node6.addNeighbours(Arrays.asList(node1, node3, node5, node7, node12));
        node7.addNeighbours(Arrays.asList(node1, node6, node8, node10, node12));
        node8.addNeighbours(Arrays.asList(node1, node2, node7, node9, node10));
        node9.addNeighbours(Arrays.asList(node2, node4, node8, node10, node11));
        node10.addNeighbours(Arrays.asList(node7, node8, node9, node11, node12));
        node11.addNeighbours(Arrays.asList(node4, node5, node9, node10, node12));
        node12.addNeighbours(Arrays.asList(node5, node6, node7, node10, node11));

        graph.addNodes(Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8, node9, node10, node11, node12));
        return graph;
    }

    private static Graph buildGraphSimple() {
        Graph graph = new Graph();

        Node node1 = new Node(graph, 1);
        Node node2 = new Node(graph, 2);
        Node node3 = new Node(graph, 3);
        Node node4 = new Node(graph, 4);
        Node node5 = new Node(graph, 5);
        Node node6 = new Node(graph, 6);


        node1.addNeighbours(Arrays.asList(node2, node5, node6));
        node2.addNeighbours(Arrays.asList(node1, node3, node6));
        node3.addNeighbours(Arrays.asList(node2, node4, node6));
        node4.addNeighbours(Arrays.asList(node3, node5, node6));
        node5.addNeighbours(Arrays.asList(node1, node4, node6));


        graph.addNodes(Arrays.asList(node1, node2, node3, node4, node5, node6));
        return graph;
    }
}