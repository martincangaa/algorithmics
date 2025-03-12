package algstudent.s3;

/* Params: a=2;b=2;k=0
 * The time complexity is O(n) 
 * and the waste of stack is O(log n)
 * Regardless the growth of n => the stack does not overflow 
 */
public class Division5 {
	public static long rec5(long n)
	{
		long count = 0;
		
		if(n <= 0)
			return count++;
		
		count++;
		
		for(int i = 0; i < 4; i++)
			rec5(n/2);
		
		return count;
		
		
	}
	
	public static void main (String arg []) 
	{
		 long t1,t2,cont = 0;	 
		 for (int n=1000;n<=10000000;n*=2)
		 {
			  t1 = System.currentTimeMillis ();
			   
			  cont = rec5(n);
			      
			  t2 = System.currentTimeMillis ();
			
			  System.out.println(t2-t1);
		 }  // for
	} // main
} //class