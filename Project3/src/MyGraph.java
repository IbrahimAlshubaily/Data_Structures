import java.util.*;
//Rand Faris Almaroof, Chris Jin Kim, Ibrahim Alshubaily.
/**
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */
public class MyGraph implements Graph {
	// you will need some private fields to represent the graph
	private final Collection<Edge> myEdges;
	private final Collection<Vertex> myVertices;

	/**
	 * Creates a MyGraph object with the given collection of vertices and the
	 * given collection of edges.
	 * 
	 * @param v
	 *            a collection of the vertices in this graph
	 * @param e
	 *            a collection of the edges in this graph
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
		// YOUR CODE HERE
		myVertices = v;
		
		//The edges should involve only vertices with labels that are in the vertices of the graph. 
		//That is, there should be no edge from or to a vertex labeled A if there is no vertex with label A. 
		for (Edge edge : e){
			if (!myVertices.contains(edge.getSource()) || !myVertices.contains(edge.getDestination()))
				throw new IllegalArgumentException("No such Vertex (Constructor)");	
			//Edge weights should not be negative. 
			if (edge.getWeight() < 0)
				throw new IllegalArgumentException("Negative edge weight");	
		}
		myEdges = e;
	}

	/**
	 * Return the collection of vertices of this graph
	 * 
	 * @return the vertices as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Vertex> vertices() {
		return myVertices;
	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Edge> edges() {
		return myEdges;
	}

	/**
	 * Return a collection of vertices adjacent to a given vertex v. i.e., the
	 * set of all vertices w where edges v -> w exist in the graph. Return an
	 * empty collection if there are no adjacent vertices.
	 * 
	 * @param v
	 *            one of the vertices in the graph
	 * @return an iterable collection of vertices adjacent to v in the graph
	 * @throws IllegalArgumentException
	 *             if v does not exist.
	 */
	@Override
	public Collection<Vertex> adjacentVertices(Vertex v) {
		// YOUR CODE HERE
		if (!myVertices.contains(v)) {
			throw new IllegalArgumentException("No such Vertex");
		}
		Collection<Vertex> theAdjacentVertices = new ArrayList<>();
		for (Edge e: myEdges) {
			if (e.getSource().equals(v))
				theAdjacentVertices.add(e.getDestination());
		}
		return theAdjacentVertices;
	}

	/**
	 * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed
	 * graph. Assumes that we do not have negative cost edges in the graph.
	 * 
	 * @param a
	 *            one vertex
	 * @param b
	 *            another vertex
	 * @return cost of edge if there is a directed edge from a to b in the
	 *         graph, return -1 otherwise.
	 * @throws IllegalArgumentException
	 *             if a or b do not exist.
	 */
	@Override
	public int edgeCost(Vertex a, Vertex b) {
		for (Edge e: myEdges) {
	 		   if (e.getSource().equals(a) && e.getDestination().equals(b)) 
	 			   return e.getWeight();
	 	   }
	 	   return -1; 
	}

	/**
	 * Returns the shortest path from a to b in the graph, or null if there is
	 * no such path. Assumes all edge weights are nonnegative. Uses Dijkstra's
	 * algorithm.
	 * 
	 * @param a
	 *            the starting vertex
	 * @param b
	 *            the destination vertex
	 * @return a Path where the vertices indicate the path from a to b in order
	 *         and contains a (first) and b (last) and the cost is the cost of
	 *         the path. Returns null if b is not reachable from a.
	 * @throws IllegalArgumentException
	 *             if a or b does not exist.
	 */
	public Path shortestPath(Vertex a, Vertex b) {		
		return new shortestPathFinder(myVertices, myEdges).findShortestPath(a, b);
	}
}
