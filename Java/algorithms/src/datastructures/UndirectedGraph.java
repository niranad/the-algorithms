package datastructures;

import java.util.ArrayList;
import java.util.LinkedList;

public class UndirectedGraph {
	private int v;
	private ArrayList<ArrayList<Integer>> adjLists;

	public UndirectedGraph(int vertices) {
		v = vertices;
		adjLists = new ArrayList<>(v);
		for (int i = 0; i < v; i++) {
			adjLists.add(new ArrayList<>());
		}
	}

	public boolean addEdge(int v1, int v2) {
		if (v1 > 0 && v1 <= v && v2 > 0 && v2 <= v) {
			adjLists.get(v1 - 1).add(v2 - 1);
			adjLists.get(v2 - 1).add(v1 - 1);
			return true;
		}
		return false;
	}
	
	public ArrayList<Integer> depthFirstTraversal() {
		int rootIdx = (int) Math.round(Math.random() * v);
		ArrayList<Integer> output = new ArrayList<>();
		dftHelper(rootIdx, new LinkedList<Integer>(), output);
		return output;
	}

	private void dftHelper(int nodeIdx, LinkedList<Integer> visitedNodes,
		ArrayList<Integer> output) {
		visitedNodes.add(nodeIdx);
		for (Integer edge : adjLists.get(nodeIdx)) {
			if (!visitedNodes.contains(edge)) {
				dftHelper(edge, visitedNodes, output);
			}
		}
		if (!output.contains(visitedNodes.get(visitedNodes.size() - 1))) {
			output.add(visitedNodes.removeLast());
		} else {
			visitedNodes.removeLast();
		}
	}
}
