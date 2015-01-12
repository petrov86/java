package findTheShortestRoute;

public class Graph {

	int graph[][];

	static final int MAX_VALUE = 10000;
	static final int NO_PARENT = -1;
	int nodes = 0;
	int arrAllNodes[] = null;
	int routesForNode[] = null;
	int pred[] = null;

	public Graph(int size) {

		nodes = size;
		arrAllNodes = new int[nodes];
		routesForNode = new int[nodes];
		pred = new int[nodes];

		graph = new int[nodes][nodes];

		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				graph[i][j] = 0;
			}
		}

	}

	public void dijkstra(int startNode) {
		int i;
		for (i = 0; i < nodes; i++)

			if (0 == graph[startNode][i]) {
				routesForNode[i] = MAX_VALUE;
				pred[i] = NO_PARENT;
			} else {
				routesForNode[i] = graph[startNode][i];
				pred[i] = startNode;
			}
		for (i = 0; i < nodes; i++) {
			arrAllNodes[i] = 1;
		}
		arrAllNodes[startNode] = 0;

		pred[startNode] = NO_PARENT;
		while (true) {
			int j = NO_PARENT;
			int di = MAX_VALUE;
			for (i = 0; i < nodes; i++) {

				if (arrAllNodes[i] > 0 && routesForNode[i] < di) {
					di = routesForNode[i];
					j = i;
				}
			}
			if (NO_PARENT == j)
				break;
			
			arrAllNodes[j] = 0;

			for (i = 0; i < nodes; i++) {

				if (arrAllNodes[i] > 0 && graph[j][i] != 0) {
					if (routesForNode[i] > routesForNode[j] + graph[j][i]) {
						routesForNode[i] = routesForNode[j] + graph[j][i];
						pred[i] = j;
					}
				}
			}
		}
	}

	void printPath(int startNode, int j, String[] cities) {
		if (pred[j] != startNode)
			printPath(startNode, pred[j], cities);
		System.out.printf("%s ", cities[j]);
	}

	void printResult(int startNode, String[] cities) {
		int i;
		for (i = 0; i < nodes; i++) {
			if (i != startNode) {
				if (routesForNode[i] == MAX_VALUE)
					System.out.printf("No Route between %s and %s%n",
							cities[startNode], cities[i]);
				else {
					System.out.printf("Min route from %s to %s: %s ",
							cities[startNode], cities[i], cities[startNode]);
					printPath(startNode, i, cities);
					System.out.printf(", lenght: %s km %n",
							routesForNode[i]);
				}
			}
		}
	}

	public void addEdge(int i, int j, int f) {
		graph[i][j] = f;
		graph[j][i] = f;
	}

	public void printGraph() {
		System.out
				.println("===========================================================================================");
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				System.out.printf("%10d", graph[i][j]);
			}

			System.out.println();
		}
		System.out
				.println("===========================================================================================");
	}

}
