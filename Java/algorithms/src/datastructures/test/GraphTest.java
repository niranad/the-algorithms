package datastructures.test;

import java.util.Arrays;

import datastructures.GraphImpl1;

public class GraphTest {

	public static void main(String[] args) {
		GraphImpl1 g = new GraphImpl1(6, true, true);
		g.addEdge(5, 2, 3);
		g.addEdge(5, 0, 4);
		g.addEdge(4, 0, 5);
		g.addEdge(4, 1, 3);
		g.addEdge(2, 3, 1);
		g.addEdge(3, 1, 2);

		System.out.printf("Graph1 is cyclic: %b%n", g.isCyclic());
		System.out.println("Topological Sort: ");
		System.out.println(g.topologicalSort());
		System.out.println("Shortest path: ");
		System.out.println(Arrays.toString(g.shortestPath(3)));
		
		g = new GraphImpl1(6, false, true);
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(2, 5);
		g.addEdge(2, 1);
		g.addEdge(4, 2);
		g.addEdge(5, 4);
		
		System.out.printf("%nGraph2 is cyclic: %b%n", g.isCyclic());
		System.out.printf("Topological sort: %s", g.topologicalSort());
	}
}
