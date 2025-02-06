package session01;

import java.util.ArrayList;

public class JavaA1 {
	
	public static void testPrimes(int lowerBound, int upperBound) {
		
		int n = lowerBound;
		
		while (n <= upperBound) {
			
			long startTime = System.currentTimeMillis();
			listPrimesA1(n);
			long estimatedTime = System.currentTimeMillis() - startTime;
			
			System.out.printf("n = %d *** time = %d milliseconds \n", n, estimatedTime);
			
			n = 2 * n;
		}
	}
	
	private static ArrayList<Integer> listPrimesA1(int n) {
		
		ArrayList<Integer> primes = new ArrayList<Integer>();
		
		for(int i = 2; i <= n; i++) {
			if(isPrimeA1(i)) {
				primes.add(i);
			}
		}
		
		return primes;
	}
	
	private static boolean isPrimeA1(int m) {
		
		boolean p = true;
		
		for(int i = 2; i < m; i++) {
			if(m % i == 0) {
				p = false;
			}
		}
		
		return p;
	}
}
