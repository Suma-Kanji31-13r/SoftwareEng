import java.util.Scanner;

public class CyclomaticComplexity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Method 1: Using edges and nodes
        System.out.println("Enter the number of edges (E): ");
        int edges = scanner.nextInt();
        System.out.println("Enter the number of nodes (N): ");
        int nodes = scanner.nextInt();

        int complexityByEdgesNodes = edges - nodes + 2;

        // Method 2: Using predicate nodes
        System.out.println("Enter the number of decision points (P): ");
        int predicateNodes = scanner.nextInt();

        int complexityByPredicates = predicateNodes + 1;

        // Output the results
        System.out.println("\nCyclomatic Complexity Results:");
        System.out.println("Using Edges and Nodes: V(G) = " + complexityByEdgesNodes);
        System.out.println("Using Predicate Nodes: V(G) = " + complexityByPredicates);

        scanner.close();
    }
}
