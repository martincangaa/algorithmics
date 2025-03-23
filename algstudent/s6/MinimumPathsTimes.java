package labs.en._25.algstudent.s5;

import java.util.Random;

//MINIMUM PATHS IN A GRAPH BY FLOYD-WARSHALL
//IT IS A SOLUTION BY DYNAMIC PROGRAMMING
//ITS TIME COMPLEXITY IS CUBIC O(n^3)
public class MinimumPathsTimes {
	static String[] v; //node vector
	static int[][] weights; //weight matrix
	static int[][] costs; //Floyd's paths cost matrix
	static int[][] p; //predecessor matrix (steps) in Floyd paths

	public static void main(String arg[]) {
		int n = 200; //starting nodes of the graph
		
		while(true) {
			
			v = new String[n];
			for (int i = 0; i < n; i++)
				v[i] = "NODE" + i;

			weights = new int[n][n];
			costs = new int[n][n];
			p = new int[n][n];
		
			initPMatrix(p);
			fillInWeights(weights, 0.5, 10, 99	); //weights for the example
			costs = weights.clone();
		
			long t1 = System.currentTimeMillis();
		
			floyd(weights, costs, p);
		
			long t2 = System.currentTimeMillis();
		
			System.out.println("n = " + n + ", time = " + (t2 - t1) + " ns");
        
        	n *= 2;
		}
	}

	/* ITERATIVE WITH CUBIC COMPLEXITY O(n^3) */
	static void floyd(int[][] weights, int[][] costs, int[][] p) {
		int n = weights.length;
		
		for (int k = 0; k < n; k++) { // intermediate node
	        for (int i = 0; i < n; i++) { // source node
	            for (int j = 0; j < n; j++) { // destination node
	                
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

	/* IT IS RECURSIVE and WORST CASE is O(n), IT IS O(n) if you write all nodes */
	static void path(String[] v, int[][] steps, int i, int j) {
		
		
		
	}

	/* load the example cost matrix */
	public static void fillInWeights(int[][] matrix, double p1, int minWeight, int maxWeight) {
        
        Random rand = new Random();
        int size = matrix.length;
        
        for (int i = 0; i < size; i++) {
        	matrix[i][i] = 10000000; // No loops
        	
            for (int j = i + 1; j < size; j++) { // Ensure we don't overwrite diagonal or duplicate values
                if (rand.nextDouble() < p1) { // p1 probability of an edge
                    int weight = rand.nextInt(maxWeight - minWeight + 1) + minWeight;
                    matrix[i][j] = weight;
                    matrix[j][i] = weight; // Symmetric for an undirected graph
                } else {
                    matrix[i][j] = 10000000; // No edge
                    matrix[j][i] = 10000000;
                }
            }
        }
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