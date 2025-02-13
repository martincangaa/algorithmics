package session3;

public class Loop5 {

	public static long loop4(int n) {
		long cont = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= i; j++)
				for (int k = j; k > 1;	k = k/2)
					for (int m = k; m > 1;	m = m/2)
						cont++;
		return cont;

	}

	public static void main(String arg[]) {
		long c = 0;
		long t1, t2;

		int nTimes = Integer.parseInt(arg[0]);

		System.out.println("n\ttime\trepetions\tcounter");

		for (int n = 100; n <= 819200; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repetitions = 1; repetitions <= nTimes; repetitions++)
				c = loop4(n);

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (float)(t2 - t1) / nTimes + "\t" + nTimes + "\t\t" + c);
		} // for
	} // main

} 