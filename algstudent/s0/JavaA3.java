package session01;

import java.util.ArrayList;

public class JavaA3 {
	
	public static void testPrimes(int lowerBound, int upperBound) {
		
		int n = lowerBound;
		
		while (n <= upperBound) {
			
			long startTime = System.currentTimeMillis();
			listPrimesA3(n);
			long estimatedTime = System.currentTimeMillis() - startTime;
			
			System.out.printf("n = %d *** time = %d milliseconds \n", n, estimatedTime);
			
			n = 2 * n;
		}
	}
	
	private static ArrayList<Integer> listPrimesA3(int n) {
		
		ArrayList<Integer> primes = new ArrayList<Integer>();
		
		for(int i = 2; i <= n; i++) {
			if(isPrimeA3(i)) {
				primes.add(i);
			}
		}
		
		return primes;
	}
	
	private static boolean isPrimeA3(int m) {
		
		for(int i = 2; i < m/2+1; i++) {
			if(m % i == 0) {
				return false;
			}
		}
		
		return true;
	}
}