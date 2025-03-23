package labs.en._25.algstudent.s5;

//MINIMUM PATHS IN A GRAPH BY FLOYD-WARSHALL
//IT IS A SOLUTION BY DYNAMIC PROGRAMMING
//ITS TIME COMPLEXITY IS CUBIC O(n^3)
public class MinimumPathsExample {
	static String[] v; //node vector
	static int[][] weights; //weight matrix
	static int[][] costs; //Floyd's paths cost matrix
	static int[][] p; //predecessor matrix (steps) in Floyd paths

	public static void main(String arg[]) {
		int n = 5; //nodes of example graph
		v = new String[n];
		for (int i = 0; i < n; i++)
			v[i] = "NODE" + i;

		weights = new int[n][n];
		costs = new int[n][n];
		p = new int[n][n];
		
		initPMatrix(p);
		fillInWeights(weights); //weights for the example
		costs = weights.clone();
		
		System.out.println("WEIGHT MATRIX IS:");
		printMatrix(weights);
		
		floyd(weights, costs, p);

		System.out.println("MINIMUM COST MATRIX IS:");
		printMatrix(costs);

		System.out.println("P MATRIX IS:");
		printMatrix(p);

		System.out.println();
		System.out.println("MINIMUM PATHS IN THE EXAMPLE GRAPH (for every pair of different nodes):");
		System.out.println();
		for (int source = 0; source <= n-1; source++)
			for (int target = 0; target <= n-1; target++)
				if (source != target) {
					System.out.print("FROM " + v[source] + " TO " + v[target] + " = ");
					minimumPath(v, weights, costs, p, source, target);
					if (costs[source][target] < 10000000)
						System.out.println("MINIMUM COST=" + costs[source][target]);
					System.out.println("**************");
				}

	}

	/* ITERATIVE WITH CUBIC COMPLEXITY O(n^3) */
	static void floyd(int[][] weights, int[][] costs, int[][] p) {
		int n = weights.length;
		
		for(int k = 0; k < n; k++) { // intermediate node
	        for(int i = 0; i < n; i++) { // source node
	            for(int j = 0; j < n; j++) { // destination node
	                
	            	if (costs[i][j] > costs[i][k] + costs[k][j] && i != j) {
	            		costs[i][j] = costs[i][k] + costs[k][j];
	            		p[i][j] = k;
	            	}
	            }
	        }
		}
	}

	static void minimumPath(String[] v, int[][] weights, int[][] costs, int[][] p, int i, int j) {
		
		System.out.print(String.format("NODE%d", i));
		
		if(p[i][j] != -1) { // Precheck to init y
			int y = i;
			
			while(p[y][j] != -1) {
				y = p[y][j];
				System.out.print(String.format("--> NODE%d", y));
			}
		}
		
		System.out.print(String.format("--> NODE%d\n", j));
	}

	/* load the example cost matrix */
	static void fillInWeights(int[][] w) {
		for (int i = 0; i < w.length; i++)
			for (int j = 0; j < w.length; j++)
				w[i][j] = 10000000;
		w[0][1] = 19;
		w[0][2]=  10;
		w[1][2]=  20;
		w[2][1] = 19;
		w[2][3] = 14;
		w[3][0] = 27;
		w[3][1] = 67;
	    w[3][2]=  21;
		w[4][1]=  80;
	}
	
	static void initPMatrix(int[][] p) {
		for (int i = 0; i < p.length; i++)
			for (int j = 0; j < p.length; j++)
				p[i][j] = -1;
	}
	
	/* print the cost matrix */
	static void printMatrix(int[][] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(String.format("%10s", a[i][j]));
			System.out.println();
		}
		System.out.println();
	}

}