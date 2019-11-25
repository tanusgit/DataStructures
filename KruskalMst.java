package algo;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Graph {
	class Edge implements Comparable<Edge> {
		char src, dest;
		int weight;
		public int compareTo(Edge compareEdge) {
			return this.weight - compareEdge.weight;
		}
	};

	class subset {
		int parent, rank;
	};

	int V, E;
	Edge edge[];

	Graph(int v, int e) {
		V = v;
		E = e;
		edge = new Edge[E];
		for (int i = 0; i < e; ++i)
			edge[i] = new Edge();
	}

	int find(subset subsets[], int i) {
		if (subsets[i].parent != i)
			subsets[i].parent = find(subsets, subsets[i].parent);

		return subsets[i].parent;
	}


	void Union(subset subsets[], int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);


		if (subsets[xroot].rank < subsets[yroot].rank)
			subsets[xroot].parent = yroot;
		else if (subsets[xroot].rank > subsets[yroot].rank)
			subsets[yroot].parent = xroot;

		else {
			subsets[yroot].parent = xroot;
			subsets[xroot].rank++;
		}
	}

	void KruskalMST() {
		Edge result[] = new Edge[V];
		int e = 0;
		int i = 0;
		for (i = 0; i < V; ++i)
			result[i] = new Edge();

		Arrays.sort(edge);
		subset subsets[] = new subset[V];
		for (i = 0; i < V; ++i)
			subsets[i] = new subset();


		for (int v = 0; v < V; ++v) {
			subsets[v].parent = v;
			subsets[v].rank = 0;
		}

		i = 0;


		while (e < V - 1) {
			Edge next_edge = new Edge();
			next_edge = edge[i++];

			int x = find(subsets, next_edge.src);
			int y = find(subsets, next_edge.dest);
			if (x != y) {
				result[e++] = next_edge;
				Union(subsets, x, y);
			}
		}

		System.out.println("Following are the edges in "
				+ "the constructed MST");
		for (i = 0; i < e; ++i)
			System.out.println(result[i].src + " -- " + result[i].dest + " == "
					+ result[i].weight);
	}

	public static void main(String[] args) {

		int V = 9;
		int E = 14;
		Graph graph = new Graph(V, E);

		// a-b
		graph.edge[0].src = 0;
		graph.edge[0].dest = 1;
		graph.edge[0].weight = 4;

		// b-c
		graph.edge[1].src = 1;
		graph.edge[1].dest = 2;
		graph.edge[1].weight = 8;

		// c-d
		graph.edge[2].src = 2;
		graph.edge[2].dest = 3;
		graph.edge[2].weight = 7;

		// d-e
		graph.edge[3].src = 3;
		graph.edge[3].dest = 4;
		graph.edge[3].weight = 9;

		// e-f
		graph.edge[4].src = 4;
		graph.edge[4].dest = 5;
		graph.edge[4].weight = 10;

		// f-g
		graph.edge[5].src = 5;
		graph.edge[5].dest = 6;
		graph.edge[5].weight = 2;

		// g-h
		graph.edge[6].src = 6;
		graph.edge[6].dest = 7;
		graph.edge[6].weight = 1;

		// h-a
		graph.edge[7].src = 7;
		graph.edge[7].dest = 0;
		graph.edge[7].weight = 8;

		// f-d
		graph.edge[8].src = 5;
		graph.edge[8].dest = 3;
		graph.edge[8].weight = 14;
		// f-c
		graph.edge[9].src = 5;
		graph.edge[9].dest = 2;
		graph.edge[9].weight = 4;
		// c-i
		graph.edge[10].src = 2;
		graph.edge[10].dest = 8;
		graph.edge[10].weight = 2;

		// i-g
		graph.edge[11].src = 8;
		graph.edge[11].dest = 6;
		graph.edge[11].weight = 6;
		// i-h
		graph.edge[12].src = 8;
		graph.edge[12].dest = 7;
		graph.edge[12].weight = 7;
		// b-h
		graph.edge[13].src = 1;
		graph.edge[13].dest = 7;
		graph.edge[13].weight = 11;

		graph.KruskalMST();
	}
}
