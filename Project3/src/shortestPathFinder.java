import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
//Rand Faris Almaroof, Chris Jin Kim, Ibrahim Alshubaily.
public class shortestPathFinder {
	
	private Map<Vertex, Integer> cost;	
	private Map <Vertex, Vertex> thePaths;
	private Collection<Vertex> unknowns;
	private Collection<Vertex>myVertices;
	private Collection<Edge>myEdges;
	
	shortestPathFinder(Collection<Vertex> v, Collection<Edge> e) {
		myVertices = new ArrayList<Vertex>(v);
		myEdges = new ArrayList<Edge>(e);
		cost = new HashMap<Vertex, Integer>();
		unknowns = new HashSet<Vertex>();
		thePaths = new HashMap<Vertex, Vertex>();
	}

//Reference: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
	Path findShortestPath(Vertex theStart, Vertex theDestination){
		for (Vertex v: myVertices) {
			cost.put(v, Integer.MAX_VALUE);
			unknowns.add(v);
		}
		cost.put(theStart, 0);
		while (!unknowns.isEmpty()) {
			Vertex v = getCheapestUnknown();
			unknowns.remove(v);
				
			for (Vertex uv : getAdjUnknown(v)) {
				int c = cost.get(v)+edgeCost(v, uv);
				if (c < cost.get(uv)) {
					cost.put(uv, c);
					thePaths.put(uv, v);
				}
			}
		}
		return buildPath(theStart, theDestination);
	}

	private Vertex getCheapestUnknown() {
		Vertex next = null;
			for (Vertex v: unknowns) {
				
				if (next == null) {
					next = v;
				} else if (cost.get(v) < cost.get(next)) {
 				   next = v;
				}
		   }
		   return next;
	}

	 private List<Vertex> getAdjUnknown(Vertex v) {
		 if (!myVertices.contains(v)) {
				throw new IllegalArgumentException("No such Vertex");
			}
         List<Vertex> adj = new ArrayList<Vertex>();
         for (Edge edge : myEdges) {
                 if (edge.getSource().equals(v)
                                 && unknowns.contains(edge.getDestination())) {
                         adj.add(edge.getDestination());
                 }
         }
         return adj;
	 }
	
    int edgeCost(Vertex a, Vertex b) {
 	   for (Edge e: myEdges) {
 		   if (e.getSource().equals(a) && e.getDestination().equals(b)) 
 			   return e.getWeight();
 	   }
 	   return -1;
    }
    
    
    Path buildPath (Vertex theStart, Vertex Distenation) {
	   	 List <Vertex> theWayBack = new ArrayList<>();
	   	 int totalCost = 0;
	   	 if (theStart.equals(Distenation)){
	   		 theWayBack.add(theStart);
	   		 return new Path(theWayBack , 0);
	   	 }
	   		
	   	 theWayBack.add(Distenation);
	   	 Vertex runner = Distenation;
	   	 while (thePaths.get(runner) != null) {
	   		 totalCost += edgeCost(thePaths.get(runner), runner);
	   		 runner = thePaths.get(runner);
	   		 theWayBack.add(runner);
	   	 }
	   	 List<Vertex> thePath = reverseElements(theWayBack);
	   	 if (!thePath.get(0).equals(theStart) || !thePath.get(thePath.size()-1).equals(Distenation)) {
	   		 return null;
	   	 }
	   	 return new Path (thePath, totalCost);
    }

	private List<Vertex> reverseElements(List<Vertex> theWayBack) {
		List<Vertex> result = new ArrayList<>();
		for (int i = theWayBack.size()-1; i >= 0; i --) {
			result.add(theWayBack.get(i));
		}
		return result;
	}
}
