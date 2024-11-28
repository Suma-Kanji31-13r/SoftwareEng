import java.util.Scanner;

public class HKInformationFactor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of modules
        System.out.print("Enter the number of modules: ");
        int n = scanner.nextInt();

        int[] ini = new int[n];    // Information flows into each module
        int[] outi = new int[n];  // Information flows out of each module
        int[] weight = new int[n]; // Weights of each module (default = 1)
        int[] HKi = new int[n];    // HK value for each module

        // Input module data
        System.out.println("Enter data for each module:");
        for (int i = 0; i < n; i++) {
            System.out.println("Module " + (i + 1) + ":");
            System.out.print("  ini: ");
            ini[i] = scanner.nextInt();
            System.out.print("  outi: ");
            outi[i] = scanner.nextInt();
            System.out.print("  weight (default 1): ");
            weight[i] = scanner.nextInt();
        }

        // Calculate HK values for each module
        int totalHK = 0;
        for (int i = 0; i < n; i++) {
            HKi[i] = weight[i] * (int) Math.pow((outi[i] * ini[i]), 2);
            totalHK += HKi[i];
        }

        // Display results
        System.out.println("\nResults:");
        for (int i = 0; i < n; i++) {
            System.out.println("Module " + (i + 1) + ": HKi = " + HKi[i]);
        }
        System.out.println("Total HK Information Factor = " + totalHK);

        scanner.close();
    }
}
