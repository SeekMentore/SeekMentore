package dijikstra;

import java.util.HashSet;
import java.util.Set;

public class Graph {
	 
    private Set<Node> nodes = new HashSet<>();
    
    public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }
 
    // getters and setters 
}
