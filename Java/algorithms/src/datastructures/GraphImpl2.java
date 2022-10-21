package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Edge {
	int src, dest;
	int weight = 1;

	Edge(int src, int dest) {
		this.src = src;
		this.dest = dest;
	}

	Edge(int src, int dest, int weight) {
		this(src, dest);
		this.weight = weight;
	}
}

public class GraphImpl2 {
	static class Node {
		int val, weight;

		Node(int val, int weight) {
			this.val = val;
			this.weight = weight;
		}
	}

	List<List<Node>> adjList = new ArrayList<>();

	public GraphImpl2(List<Edge> edges) {
		// adjacency list memory allocation
		for (int i = 0; i < edges.size(); i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the graph
		for (Edge e : edges) {
			// allocate new node in adjacency list from src to dest
			adjList.get(e.src).add(new Node(e.dest, e.weight));
		}
	}

//	public boolean isDirected() {
//		for (int v = 0; v < adjList.size(); v++) {
//			for (Node edge : adjList.get(v)) {
//				if (!adjList.get(edge.val).contains(new Node(v, edge.weight))) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	private boolean isCyclic() {
//		return false;
//	}

	// print adjacency list for the graph
	public void printGraph(GraphImpl2 graph) {
		int srcVertex = 0;
		int listSize = graph.adjList.size();

		System.out.println("The contents of the graph:");

		while (srcVertex < listSize) {
			// traverse the adjacency list and print the edges
			for (Node edge : graph.adjList.get(srcVertex)) {
				System.out.print("Vertex:" + srcVertex + " ==> " + edge.val + " ("
					+ edge.weight + ")\t");
			}
			System.out.println();
			++srcVertex;
		}
	}

	public static void main(String[] args) {
		// define edges of the graph
		List<Edge> edges = Arrays.asList(new Edge(0, 1, 2), new Edge(3, 2, 4),
			new Edge(1, 2, 4), new Edge(2, 3, 4), new Edge(1, 0, 2), new Edge(3, 4, 5),
			new Edge(2, 1, 4), new Edge(4, 3, 5));
		GraphImpl2 graph = new GraphImpl2(edges);

		// print the graph as adjacency list
		graph.printGraph(graph);
	}
}
