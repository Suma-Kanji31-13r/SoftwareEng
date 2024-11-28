public class FPACalculation {
    public static void main(String[] args) {
        // Given data
        int[] counts = {60, 25, 35, 20, 25}; // i/p, file, query, i/f, o/p
        double[][] ratios = {
            {1, 6, 3}, // i/p
            {1, 2, 2}, // file
            {0, 1, 0}, // query (avg)
            {0, 0, 1}, // i/f (complex)
            {2, 2, 1}  // o/p
        };
        double[] weights = {0.5, 1.0, 1.5}; // simple, avg, complex
        double reliability = 0.65;
        double complexity = 0.75;

        // Step 1: Calculate UFP
        double UFP = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < ratios[i].length; j++) {
                UFP += counts[i] * (ratios[i][j] / sum(ratios[i])) * weights[j];
            }
        }

        // Step 2: Calculate TDI
        double TDI = reliability + complexity;

        // Step 3: Calculate VAF
        double VAF = (TDI * 0.01) + 0.65;

        // Step 4: Calculate AFP
        double AFP = UFP * VAF;

        // Output results
        System.out.println("Unadjusted Function Point (UFP): " + UFP);
        System.out.println("Total Degree of Influence (TDI): " + TDI);
        System.out.println("Value Adjustment Factor (VAF): " + VAF);
        System.out.println("Adjusted Function Point (AFP): " + AFP);
    }

    // Helper method to calculate the sum of an array
    private static double sum(double[] array) {
        double total = 0;
        for (double value : array) {
            total += value;
        }
        return total;
    }
}
