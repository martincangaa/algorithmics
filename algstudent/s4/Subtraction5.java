package algstudent.s3;

/* Class that models T(n)=2 T(n-1)+O(1)
 * Params: a=2;b=1;k=0
 * The time complexity is quadratic O(2^n) 
 * and the waste of stack is O(n)
 * In this case => the stack does not overflow because 
 * long before the execution time is untreatable 
 */
public class Subtraction5 {
	public static long rec5(int n) {
		
		int cont = 0;

		if(n <= 0)
			return cont++;
		
		cont++;
		
		rec5(n-2);
		rec5(n-2);
		rec5(n-2);
		
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2, cont = 0;
		for (int n = 30; n <= 100; n++) {
			t1 = System.currentTimeMillis();

			cont = rec5(n);

			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main
} // class