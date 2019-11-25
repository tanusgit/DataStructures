package algo;
import java.util.*;


public class Graph {
	class Edge implements Comparable<Edge> {
		char source, destination;
		int value;
		public int compareTo(Edge e) {
			return this.value - e.value;
		}
	};

	class Cluster {
		char parent;
		int rank;
	};

	int V, E;
	Edge Edges[];
	HashMap<Character, Integer> NodeMap;
	HashMap<Integer, Character> ReverseNodeMap;

	Graph(int v, int e, HashMap<Character, Integer> N) {
		V = v;
		E = e;
		Edges = new Edge[E];
		for (int i = 0; i < e; ++i)
			Edges[i] = new Edge();
		NodeMap = N;
		ReverseNodeMap = new HashMap<>();
		for(HashMap.Entry<Character, Integer> p : NodeMap.entrySet()) {
			ReverseNodeMap.put(p.getValue(), p.getKey());
		}
	}

	char findRoot(Cluster Clusters[], char c) {
		int i = NodeMap.get(c);
		if (Clusters[i].parent != c)
			Clusters[i].parent = findRoot(Clusters, Clusters[i].parent);

		return Clusters[i].parent;
	}


	void AddEdge(Cluster Clusters[], char N1, char N2) {
		char N1Name = findRoot(Clusters, N1);
		char N2Name = findRoot(Clusters, N2);

		int N1Root = NodeMap.get(N1Name);
		int N2Root = NodeMap.get(N2Name);
		if (Clusters[N1Root].rank < Clusters[N2Root].rank)
			Clusters[N1Root].parent = N2Name;
		else if (Clusters[N1Root].rank > Clusters[N2Root].rank)
			Clusters[N2Root].parent = N1Name;

		else {
			Clusters[N2Root].parent = N1Name;
			Clusters[N1Root].rank++;
		}
	}

	void MSTKruskal() {
		Edge MST[] = new Edge[V];

		// Initialize all edges
		for (int i = 0; i < V; ++i)
			MST[i] = new Edge();

		Arrays.sort(Edges);
		Cluster Clusters[] = new Cluster[V];
		// Construct Clusters for each vertex
		for (int i = 0; i < V; ++i)
			Clusters[i] = new Cluster();

		// Each Cluster initially has itself
		for (int v = 0; v < V; ++v) {
			Clusters[v].parent = ReverseNodeMap.get(v);
			Clusters[v].rank = 0;
		}

		int vdx = 0;
		int idx = 0;
		while (vdx < V - 1) {
			Edge next_edge = new Edge();
			next_edge = Edges[idx++];

			char N1 = findRoot(Clusters, next_edge.source);
			char N2 = findRoot(Clusters, next_edge.destination);
			if (N1 != N2) {
				MST[vdx++] = next_edge;
				AddEdge(Clusters, N1, N2);
			}
		}

		System.out.println("Edges in Kruskal's MST");
		for (int i = 0; i < vdx; ++i)
			System.out.println("(" + MST[i].source + ", "
					+ MST[i].destination + ") Value = "
					+ MST[i].value);
	}

	public static void main(String[] args) {

		HashMap<Character, Integer> NodeMap = new HashMap<>();
		NodeMap.put('a', 0);
		NodeMap.put('b', 1);
		NodeMap.put('c', 2);
		NodeMap.put('d', 3);
		NodeMap.put('e', 4);
		NodeMap.put('f', 5);
		NodeMap.put('g', 6);
		NodeMap.put('h', 7);
		NodeMap.put('i', 8);
		int V = 9;
		int E = 14;
		Graph MSTGraph = new Graph(V, E, NodeMap);

		// a-b
		MSTGraph.Edges[0].source = 'a';
		MSTGraph.Edges[0].destination = 'b';
		MSTGraph.Edges[0].value = 4;

		// b-c
		MSTGraph.Edges[1].source = 'b';
		MSTGraph.Edges[1].destination = 'c';
		MSTGraph.Edges[1].value = 8;

		// c-d
		MSTGraph.Edges[2].source = 'c';
		MSTGraph.Edges[2].destination = 'd';
		MSTGraph.Edges[2].value = 7;

		// d-e
		MSTGraph.Edges[3].source = 'd';
		MSTGraph.Edges[3].destination = 'e';
		MSTGraph.Edges[3].value = 9;

		// e-f
		MSTGraph.Edges[4].source = 'e';
		MSTGraph.Edges[4].destination = 'f';
		MSTGraph.Edges[4].value = 10;

		// f-g
		MSTGraph.Edges[5].source = 'f';
		MSTGraph.Edges[5].destination = 'g';
		MSTGraph.Edges[5].value = 2;

		// g-h
		MSTGraph.Edges[6].source = 'g';
		MSTGraph.Edges[6].destination = 'h';
		MSTGraph.Edges[6].value = 1;

		// h-a
		MSTGraph.Edges[7].source = 'h';
		MSTGraph.Edges[7].destination = 'a';
		MSTGraph.Edges[7].value = 8;

		// f-d
		MSTGraph.Edges[8].source = 'f';
		MSTGraph.Edges[8].destination = 'd';
		MSTGraph.Edges[8].value = 14;
		// f-c
		MSTGraph.Edges[9].source = 'f';
		MSTGraph.Edges[9].destination = 'c';
		MSTGraph.Edges[9].value = 4;
		// c-i
		MSTGraph.Edges[10].source = 'c';
		MSTGraph.Edges[10].destination = 'i';
		MSTGraph.Edges[10].value = 2;

		// i-g
		MSTGraph.Edges[11].source = 'i';
		MSTGraph.Edges[11].destination = 'g';
		MSTGraph.Edges[11].value = 6;
		// i-h
		MSTGraph.Edges[12].source = 'i';
		MSTGraph.Edges[12].destination = 'h';
		MSTGraph.Edges[12].value = 7;
		// b-h
		MSTGraph.Edges[13].source = 'b';
		MSTGraph.Edges[13].destination = 'h';
		MSTGraph.Edges[13].value = 11;

		MSTGraph.MSTKruskal();
	}
}
