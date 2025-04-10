package s6;	

import java.util.Random;
import java.util.ArrayList;

public class NullPath {
    private int[][] weightMatrix;
    private int n;
    private int tolerance = 99;
    private boolean found = false;
    private boolean verbose;

    public NullPath(int n, boolean verbose) {
        this.n = n;
        this.weightMatrix = new int[n][n];
        this.verbose = verbose;
        generateMatrix();
    }

    private void generateMatrix() {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    weightMatrix[i][j] = 0;
                } else {
                    if (rand.nextDouble() < 0.5) {
                        weightMatrix[i][j] = rand.nextInt(90) + 10;
                    } else {
                        weightMatrix[i][j] = -(rand.nextInt(90) + 10);
                    }
                }
            }
        }
    }

    public void findNullPath() {
        ArrayList<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[n];
        path.add(0); // starting node is 0
        visited[0] = true;
        search(path, visited, 0);
        if (!found) {
            System.out.println("No NullPath found.");
        }
    }

    private void search(ArrayList<Integer> path, boolean[] visited, int currentCost) {
        if (found) return; // stop if a valid path has been found
        if (path.size() == n) {
            if (path.get(path.size() - 1) == n - 1 && Math.abs(currentCost) <= tolerance) {
            	if(verbose) {
            		System.out.println("NullPath found: " + path);
                	System.out.println("Total cost: " + currentCost);
            	}
                found = true;
            }
            return;
        }
        int currentNode = path.get(path.size() - 1);
        
        for (int nextNode = 0; nextNode < n; nextNode++) {
            if (!visited[nextNode]) {
                // If we are at the last step
            	if (path.size() != n - 1 || nextNode == n - 1) {
                    int newCost = currentCost + weightMatrix[currentNode][nextNode];
                    visited[nextNode] = true;
                    path.add(nextNode);
                    search(path, visited, newCost);
                    path.remove(path.size() - 1);
                    visited[nextNode] = false;
                }
            }
        }
    }
}
