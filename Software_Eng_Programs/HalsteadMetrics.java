import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HalsteadMetrics {
    public static void main(String[] args) {
        // Define unique operators and operands along with their counts
        Map<String, Integer> operators = new HashMap<>();
        Map<String, Integer> operands = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        // Input operators
        System.out.println("Enter the number of unique operators: ");
        int numOperators = scanner.nextInt();
        System.out.println("Enter each operator and its count:");
        for (int i = 0; i < numOperators; i++) {
            String operator = scanner.next();
            int count = scanner.nextInt();
            operators.put(operator, count);
        }

        // Input operands
        System.out.println("Enter the number of unique operands: ");
        int numOperands = scanner.nextInt();
        System.out.println("Enter each operand and its count:");
        for (int i = 0; i < numOperands; i++) {
            String operand = scanner.next();
            int count = scanner.nextInt();
            operands.put(operand, count);
        }

        // Calculate Halstead metrics
        int eta1 = operators.size(); // Number of unique operators
        int eta2 = operands.size();  // Number of unique operands
        int N1 = operators.values().stream().mapToInt(Integer::intValue).sum(); // Total operators
        int N2 = operands.values().stream().mapToInt(Integer::intValue).sum();  // Total operands

        int N = N1 + N2; // Program length
        double N_hat = eta1 * (Math.log(eta1) / Math.log(2)) + eta2 * (Math.log(eta2) / Math.log(2)); // Estimated length
        double V = N * (Math.log(eta1 + eta2) / Math.log(2)); // Volume
        double L = (2.0 * eta2) / (eta1 * N2); // Level
        double E = V / L; // Effort
        double T = E / 18; // Time (using Stroud number)

        // Display the results
        System.out.println("Halstead Metrics:");
        System.out.println("Number of unique operators (η1): " + eta1);
        System.out.println("Number of unique operands (η2): " + eta2);
        System.out.println("Total operators (N1): " + N1);
        System.out.println("Total operands (N2): " + N2);
        System.out.println("Program Length (N): " + N);
        System.out.println("Estimated Program Length (N̂): " + N_hat);
        System.out.println("Volume (V): " + V);
        System.out.println("Level (L): " + L);
        System.out.println("Effort (E): " + E);
        System.out.println("Programming Time (T): " + T + " seconds");

        scanner.close();
    }
}
