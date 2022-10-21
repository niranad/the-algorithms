package datastructures;

import java.util.ArrayList;
import java.util.Iterator;

public class GraphImpl1 {
	int v;
	boolean weighted;
	boolean directed;
	ArrayList<ArrayList<Integer>> adjLists;
	ArrayList<ArrayList<Integer>> cost = null;

	/**
	 * Constructs a directed or non-directed, weighted or non-weighted graph. For a
	 * non-weighted graph, the {@code weight} of each edge is assumed to be
	 * {@code 1}.
	 * 
	 * @param vertices number of vertices
	 * @param weighted boolean argument to construct a weighted graph
	 * @param directed boolean argument to construct a directed graph
	 */
	public GraphImpl1(int vertices, boolean weighted, boolean directed) {
		v = vertices;
		this.weighted = weighted;
		this.directed = directed;
		adjLists = new ArrayList<ArrayList<Integer>>();
		
		if (weighted) {
			cost = new ArrayList<>();
		}
		
		for (int i = 0; i < v; i++) {
			adjLists.add(i, new ArrayList<Integer>());
			if (this.weighted) {
				cost.add(i, new ArrayList<Integer>());
			}
		}
	}

	/**
	 * Adds a valid weighted edge between two vertices only if the graph is
	 * constructed as weighted. Vertices number starts from 0 to
	 * ({@code vertices - 1}) inclusive.
	 * 
	 * @param v1 the vertex from which an arrow points
	 * @param v2 the vertex to which an arrow points
	 * @return {@code true} if the operation was successful; {@code false}
	 *         otherwise.
	 */
	public boolean addEdge(int v1, int v2, int weight) {
		if (weighted) {
			if (v1 >= 0 && v1 < v && v2 >= 0 && v2 < v) {
				if (directed) {
					// if edge does not exist already
					if (!adjLists.get(v1).contains(v2)) {
						adjLists.get(v1).add(v2);
						cost.get(v1).add(weight);
						return true;
					}
				} else {
					// if edge does not exist already
					if (!adjLists.get(v1).contains(v2)) {
						adjLists.get(v1).add(v2);
						adjLists.get(v2).add(v1);
						cost.get(v1).add(weight);
						cost.get(v2).add(weight);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Adds a valid unweighted edge between two vertices only if the graph is
	 * constructed as unweighted. Vertices number starts from 0 to
	 * ({@code vertices - 1}) inclusive.
	 * 
	 * @param v1 the vertex from which an arrow points
	 * @param v2 the vertex to which an arrow points
	 * @return {@code true} if the operation was successful; {@code false}
	 *         otherwise.
	 */
	public boolean addEdge(int v1, int v2) {
		if (!weighted) {
			if (v1 >= 0 && v1 < v && v2 >= 0 && v2 < v) {
				if (directed) {
					// if edge does not exist already
					if (!adjLists.get(v1).contains(v2)) {
						adjLists.get(v1).add(v2);
						return true;
					}
				} else {
					// if edge does not exist already
					if (!adjLists.get(v1).contains(v2)) {
						adjLists.get(v1).add(v2);
						adjLists.get(v2).add(v1);
						return true;
					}
				}
			}
		}
		return false;
	}

	private void topologicalSortUtil(int currNode, boolean visited[],
		Stack<Integer> stack) {
		visited[currNode] = true;
		Integer nodeIdx;

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> iter = adjLists.get(currNode).listIterator();
		while (iter.hasNext()) {
			nodeIdx = iter.next();
			if (!visited[nodeIdx]) {
				topologicalSortUtil(nodeIdx, visited, stack);
			}
		}

		// Push the current vertex to the stack which stores result
		stack.push(currNode);
	}

	// Topological sort using depth first traversal. Does not detect cycle in graph.
	public ArrayList<Integer> topologicalSort() {
		if (directed && !isCyclic()) {
			Stack<Integer> stack = new Stack<>();

			// Mark all the vertices as not visited
			boolean visited[] = new boolean[v];

			// Call the recursive helper function to store topological sort
			for (int i = 0; i < v; i++) {
				if (!visited[i]) {
					topologicalSortUtil(i, visited, stack);
				}
			}

			ArrayList<Integer> output = new ArrayList<>();
			while (!stack.isEmpty()) {
				output.add(stack.pop());
			}

			return output;
		}
		return null;
	}

	private boolean isCyclicUtil(int currNode, boolean visited[], boolean recStack[]) {
		visited[currNode] = true;
		recStack[currNode] = true;
		Iterator<Integer> iter = adjLists.get(currNode).listIterator();

		while (iter.hasNext()) {
			Integer edge = iter.next();
			// if vertex v is found in recursion stack of this DFS traversal
			// or if there's an edge from the vertex to itself
			if (recStack[edge] || edge == currNode) {
				return true;
			} else {
				if (isCyclicUtil(edge, visited, recStack)) {
					return true;
				}
			}
		}
		recStack[currNode] = false;
		return false;
	}

	public boolean isCyclic() {
		if (directed) {
			// Array to track vertices already visited
			boolean visited[] = new boolean[v];
			// Array to track vertices in recursion stack of the traversal
			boolean recStack[] = new boolean[v];

			// Check if every vertices have been visited
			for (int i = 0; i < v; i++) {
				if (!visited[i]) {
					// Check if the DFS tree from the vertex contains a cycle
					if (isCyclicUtil(i, visited, recStack)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private int minDistNode(int[] dist, boolean[] shortestPtSet) {
		int min = Integer.MAX_VALUE, minIdx = -1;

		for (int v = 0; v < this.v; v++) {
			if (!shortestPtSet[v] && dist[v] <= min) {
				min = dist[v];
				minIdx = v;
			}
		}

		return minIdx;
	}

	/**
	 * Returns a shortest path mapping of vertices using dijkstra's algorithm.
	 * 
	 * @param source
	 * @return shortest path array
	 */
	public int[] shortestPath(int src) {
		if (weighted) {
			boolean[] shortestPtSet = new boolean[v];
			int[] dist = new int[v];

			for (int i = 0; i < v; i++) {
				dist[i] = Integer.MAX_VALUE;
			}

			dist[src] = 0;

			for (int count = 0; count < v - 1; count++) {
				int node = minDistNode(dist, shortestPtSet);
				shortestPtSet[node] = true;

				for (Integer edge : adjLists.get(node)) {
					if (!shortestPtSet[edge] && dist[node] != Integer.MAX_VALUE
						&& dist[node] + cost.get(node)
							.get(adjLists.get(node).indexOf(edge)) < dist[edge]) {
						dist[edge] = dist[node]
							+ cost.get(node).get(adjLists.get(node).indexOf(edge));
					}
				}
			}

			return dist;
		}
		return null;
	}
}
