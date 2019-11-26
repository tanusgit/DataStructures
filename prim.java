package algo1;
import java.util.*;

class MST {
	class Edge implements Comparable<Edge> {
		char source, destination;
		int value;
		Edge (char s, char d) {
			source = s;
			destination = d;
		}
		Edge () {
			value = Integer.MAX_VALUE;
		}
		public int compareTo(Edge e) {
			if (source == e.source) {
				if (destination == e.destination)
					return 0;
				return (destination - e.destination);
			}
			return source - e.source;
		}
	}

	class Visited {
		HashSet<Character> V;
		HashSet<Edge> E;
		Visited() {
			V = new HashSet<Character>();
			E = new HashSet<Edge>();
		}

		void add(Character c) {
			V.add(c);
			// Add visited edges to the cluster.
			for (Edge e : Edges) {
				if (e.source == c || e.destination == c)
					E.add(e);
			}
		}
		boolean contains(Character c) {
			return V.contains(c);
		}
		// Returns true if any of the nodes of these edges
		// are in the cluster.
		boolean contains(Edge e) {
			return E.contains(e);
		}
		Edge minEdge() {
			// Create a fake edge to start comparing,
			// fake edge has the highest weight.
			boolean found = false;
			Edge minEdge = new Edge();
			for (Edge e : E) {
				if (contains(e.source) && contains(e.destination))
					continue;
				if (e.value < minEdge.value) {
					minEdge = e;
					found = true;
				}
			}
			return minEdge;
		}
	}

	int V, E;
	Edge Edges[];
	HashMap<Character, Integer> NodeMap;
	HashMap<Integer, Character> ReverseNodeMap;
	Visited Visited;

	MST(int v, int e, HashMap<Character, Integer> N) {
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
		Visited = new Visited();
	}

	void printMST(ArrayList<Edge> VisitedEdges) {
		System.out.println("Prim's MST");
		for (Edge e : VisitedEdges) {
			System.out.println("(" + e.source + ", " + e.destination + 
					") Value:" + e.value);
		}
	}

	void primMST() {
		Visited.add(Edges[0].source);
		ArrayList<Edge> primMST = new ArrayList<Edge>();
		for (int count = 0; count < V - 1; count++) {
			Edge u = Visited.minEdge();

			Visited.add(u.destination);
			Visited.add(u.source);
			primMST.add(u);
		}
		printMST(primMST);
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
		MST MSTGraph = new MST(V, E, NodeMap);

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

		MSTGraph.primMST();
	}
}
