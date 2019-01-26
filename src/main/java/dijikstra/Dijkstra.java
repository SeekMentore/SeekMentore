package dijikstra;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

public class Dijkstra {

	public static void main(String[] args) { 
    	Node nodeA = new Node("A");
    	Node nodeB = new Node("B");
    	Node nodeC = new Node("C");
    	Node nodeD = new Node("D"); 
    	Node nodeE = new Node("E");
    	Node nodeF = new Node("F");
    	 
    	nodeA.addDestination(nodeB, 10);
    	nodeA.addDestination(nodeC, 15);
    	 
    	nodeB.addDestination(nodeD, 12);
    	nodeB.addDestination(nodeF, 15);
    	 
    	nodeC.addDestination(nodeE, 10);
    	 
    	nodeD.addDestination(nodeE, 2);
    	nodeD.addDestination(nodeF, 1);
    	 
    	nodeF.addDestination(nodeE, 5);
    	 
    	Graph graph = new Graph();
    	 
    	graph.addNode(nodeA);
    	graph.addNode(nodeB);
    	graph.addNode(nodeC);
    	graph.addNode(nodeD);
    	graph.addNode(nodeE);
    	graph.addNode(nodeF);
    	
    	for (Node node : graph.getNodes()) {
    		//System.out.println(node.getName());
    		for (Entry < Node, Integer> adjacencyPair: node.getAdjacentNodes().entrySet()) {
    			System.out.println(node.getName() + " - > " + adjacencyPair.getKey().getName() + "  " + adjacencyPair.getValue());
    		}
    	}
    	
    	System.out.println("------------------------------------------------------------------------------------------------------");
    	graph = Dijkstra.calculateShortestPathFromSource(graph, nodeA);
    	
    	for (Node node : graph.getNodes()) {
    		//System.out.println(node.getName());
    		for (Entry < Node, Integer> adjacencyPair: node.getAdjacentNodes().entrySet()) {
    			System.out.println(node.getName() + " - > " + adjacencyPair.getKey().getName() + "  " + adjacencyPair.getValue());
    		}
    	}
    	System.out.println("------------------------------------------------------------------------------------------------------");
    	for (Node node : nodeA.getShortestPath()) {
    		System.out.println(node.getName());
    	}
	}
	
	public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);
     
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
     
        unsettledNodes.add(source);
     
        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry < Node, Integer> adjacencyPair: 
              currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }
    
    private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
    
    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
	    Integer sourceDistance = sourceNode.getDistance();
	    if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
	        evaluationNode.setDistance(sourceDistance + edgeWeigh);
	        LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
	        shortestPath.add(sourceNode);
	        evaluationNode.setShortestPath(shortestPath);
	    }
	}
}
