package findTheShortestRoute;

public class Graph {

	int vertices[][];

	static final int MAX_VALUE = 10000;
	static final int NO_PARENT = -1;
	int nodes = 0;
	char T[] = null;
	int d[] = null;
	int pred[] = null;

	public Graph(int size) {

		nodes = size;
		T = new char[nodes];
		d = new int[nodes];
		pred = new int[nodes];

		this.vertices = new int[nodes][nodes];

		for (int i = 0; i < vertices.length; i++) {
			for (int j = 0; j < vertices[i].length; j++) {
				vertices[i][j] = 0;
			}
		}

	}

	public void dijkstra(int s) {
		int i;
		for (i = 0; i < nodes; i++)
			/* инициализиране: d[i]=A[s][i], iV, i != s */
			if (0 == vertices[s][i]) {
				d[i] = MAX_VALUE;
				pred[i] = NO_PARENT;
			} else {
				d[i] = vertices[s][i];
				pred[i] = s;
			}
		for (i = 0; i < nodes; i++) {
			T[i] = 1; /* T съдържа всички върхове */
		}
		T[s] = 0;
		pred[s] = NO_PARENT; /* от графа, с изключение на s */
		while (true) { /* докато T съдържа i: d[i] < MAX_VALUE */
			/* избиране на върха j от T, за който d[j] е минимално */
			int j = NO_PARENT;
			int di = MAX_VALUE;
			for (i = 0; i < nodes; i++) {
				
				if (T[i] > 0 && d[i] < di) {
					di = d[i];
					j = i;
				}
			}
				if (NO_PARENT == j)
					break; /* d[i] = MAX_VALUE, за всички i: изход */
				T[j] = 0; /* изключваме j от T */
			
			/* за всяко i от T изпълняваме D[i] = min (d[i], d[j]+A[j][i]) */
			for (i = 0; i < nodes; i++){

				
				if (T[i] > 0 && vertices[j][i] != 0)
					
					if (d[i] > d[j] + vertices[j][i]) {
						d[i] = d[j] + vertices[j][i];
						pred[i] = j;

					}
			}
		}
	}

	void printPath(int s, int j, String[] cities) {
		if (pred[j] != s)
			printPath(s, pred[j], cities);
		System.out.printf("%s ", cities[j]);
	}

	/* Отпечатва намерените минимални пътища */
	void printResult(int s, String[] cities) {
		int i;
		for (i = 0; i < nodes; i++) {
			if (i != s) {
				if ((int)d[i] == MAX_VALUE)
					System.out.printf("Няма път между върховете %s и %s%n",
							cities[s ], cities[i ]);
				else {
					System.out.printf("Минимален път от връх %s до %s: %s ",
							cities[s ], cities[i], cities[s ]);
					printPath(s, i, cities);
					System.out.printf(", дължина на пътя: %s%n", d[i]);
				}
			}
		}
	}

	public void addEdge(int i, int j, int f) {
		vertices[i][j] = f;
		vertices[j][i] = f;
	}

	public void printGraph() {
		System.out
				.println("===========================================================================================");
		for (int i = 0; i < vertices.length; i++) {
			for (int j = 0; j < vertices[i].length; j++) {
				System.out.printf("%10d", vertices[i][j]);
			}

			System.out.println();
		}
		System.out
				.println("===========================================================================================");
	}

}