package s6;

public class NullPathTimes {
    public static void main(String[] args) {
    	
        int startSize = 20;
        int increment = 5;
        int maxSize = 100;

        for (int n = startSize; n <= maxSize; n += increment) {
            long totalTime = 0;
            int trials = 100;
            int foundCount = 0;
            boolean verbose = false;
            
            for (int i = 0; i < trials; i++) {
            	NullPath np = new NullPath(n, verbose);
                long startTime = System.currentTimeMillis();
                np.findNullPath();
                long endTime = System.currentTimeMillis();
                totalTime += (endTime - startTime);
            }
            float averageTimeMillis = (totalTime) / (trials);
            System.out.printf("n = %d, Average time: %.2f ms\n", n, averageTimeMillis);
        }
    }
}