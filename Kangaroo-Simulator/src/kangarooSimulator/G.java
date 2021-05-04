package kangarooSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class G {
    
    List<Position> positions = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();
        

    public static G randomGraph(int numberOfVertices) {
	G graph = new G();
	Random random = new Random();
	for (int i = 0; i < numberOfVertices; i++) {
		graph.addVertex(random.nextInt(JumpyGroof.WIDTH + 1), random.nextInt(JumpyGroof.HEIGHT + 1));
	}

	for (int i = 0; i < numberOfVertices; i++){
            for (int j = 0; j < numberOfVertices; j++) {
		if (j <= i) //continue;
                    graph.connectVertices(i, j);
                                
                                
		}
            }
        return graph;
    }

    public G() {
    }
        //add new point with coordinate
	public void addVertex(int x, int y) {
            positions.add(new Position(x, y));
	}
        //connect line between 2 point
	public void connectVertices(int vertex1, int vertex2) {
            if (getEdge(vertex1, vertex2) == null) 
                edges.add(new Edge(vertex1, vertex2));
	}
        //return vertex
	public Position getPosition(int vertex) {
            return positions.get(vertex);
	}
        //total of all point
	public int getNumberOfVertices() {
            return positions.size();
	}
        //sum of all line
	public int getNumberOfEdges() {
            return edges.size();
	}
        //return edge
	public List<Edge> getEdges() {
            return edges;
	}
        //
	public Edge getEdge(int vertex1, int vertex2) {
            for (Edge edge : edges) {
                if ((edge.getFromVertex() == vertex1 && edge.getToVertex() == vertex2)|| (edge.getToVertex() == vertex1 && edge.getFromVertex() == vertex2))
                    return edge;
            }
            return null;
	}
        //check if the line exist or not
        
	public boolean edgeExists(int vertex1, int vertex2) {
            return getEdge(vertex1, vertex2) != null;
	}
        
	public List<Edge> getEdgesConnectedTo(int vertex) {
            List<Edge> edges = new ArrayList<>();

            for (Edge edge : this.edges) {
		if (edge.getFromVertex() == vertex)
                    edges.add(edge);
		else if (edge.getToVertex() == vertex) 
                    edges.add(edge);
            }

            return edges;
	}

	public class Position {

            private int x;
            private int y;
            //get coordinate x & y
            public Position(int x, int y) {
		this.x = x;
		this.y = y;
            }
            //get coordinate x
            public int getX() {
		return x;
            }
            //get coordinate y
            public int getY() {
		return y;
            }

	}

	public class Edge {

            private int fromVertex;
            private int toVertex;
            private double weight;

            public Edge(int fromVertex, int toVertex) {
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;

		Position p1 = getPosition(fromVertex);
		Position p2 = getPosition(toVertex);
		int x1 = p1.getX();
		int y1 = p1.getY();
		int x2 = p2.getX();
		int y2 = p2.getY();
                        
            }

            public int getFromVertex() {
		return fromVertex;
            }

            public int getToVertex() {
		return toVertex;
            }

            public double getWeight() {
		return weight;
            }

	}
}
