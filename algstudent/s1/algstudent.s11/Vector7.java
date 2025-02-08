package session1;

/**
 * This program serves to measure times automatically increasing 
 * the size of the problem. In addition, we use a repetition value 
 * determined by nTimes, an argument of the program
 */
public class Vector7 {
	static int[]v;
	static int[]w;
	
	public static void main(String arg []) {
		long repetitions = Integer.parseInt(arg[0]);
		long t1,t2;
		
		for (int n=1000; n<=Integer.MAX_VALUE; n*=2){ //n is increased *5   
			  v = new int[n];
			  Vector1.fillIn(v);
			  w = new int[n];
			  Vector1.fillIn(w);
			  
			  t1 = System.currentTimeMillis();
			  //We have to repeat the whole process to be measured
			  for (int repetition=1; repetition<=repetitions; repetition++){    	
			     Vector1.matches2(w, v);
			  }
			  t2 = System.currentTimeMillis();
			  System.out.printf("SIZE=%d TIME=%.5f milliseconds NTIMES=%d\n", n, ((double)(t2-t1) / repetitions), repetitions);	
		}//for 
		
	}//main

}
