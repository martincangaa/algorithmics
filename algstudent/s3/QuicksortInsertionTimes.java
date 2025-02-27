package algstudent.s2;

public class QuicksortInsertionTimes {
	static int[] v;

	public static void main(String arg[]) {
		long t1, t2;
		
		// QuickSort
		v = new int[16000000];

		Vector.randomSorted(v);

		t1 = System.currentTimeMillis();

		Quicksort.quicksort(v);

		t2 = System.currentTimeMillis();

		System.out.println("\t" + (t2 - t1));
		
		int[] kval = {10,20, 30, 50, 100, 200, 500, 1000};
		//QuickSort with insertion
		for (int n : kval) {
			v = new int[16000000];

			Vector.randomSorted(v);

			t1 = System.currentTimeMillis();

			QuicksortInsertion.quicksort(v, n);

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1));
		}
	}
}
