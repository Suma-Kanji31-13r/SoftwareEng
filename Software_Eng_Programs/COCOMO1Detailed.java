public class COCOMO1Detailed {
    public static void main(String[] args) {
        // Constants from the problem
        double complexity = 1.25;         // Given complexity
        double efficiency = 0.75;         // Efficiency: 75%
        double reliability = 0.80;        // Reliability: 80%
        double ethnicity = 1.50;          // Ethnicity factor
        double developmentCostPerDay = 2000; // INR/day
        int totalLOC = 500 + 250 + 200 + 600 + 300 + 150; // Total LOC
        double interestRate = 0.05;       // Interest rate (5%)

        // Calculate KLOC
        double KLOC = totalLOC / 1000.0;

        // Determine project type based on KLOC
        String projectType;
        double a = 0, b = 0, c = 0, d = 0;

        if (KLOC <= 5) {
            projectType = "Organic";
            a = 3.2; b = 1.05; c = 2.5; d = 3.8;
        } else if (KLOC <= 300) {
            projectType = "Semi-Detached";
            a = 3.0; b = 1.12; c = 2.5; d = 3.5;
        } else {
            projectType = "Embedded";
            a = 2.8; b = 1.20; c = 2.5; d = 3.2;
        }

        // Calculate Initial Effort (Ei)
        double Ei = a * Math.pow(KLOC, b);

        // Calculate Effort Adjustment Factor (EAF)
        double EAF = complexity * efficiency * reliability * ethnicity;

        // Calculate Final Effort (Ef)
        double Ef = EAF * Ei;

        // Calculate Development Time (td)
        double td = c * Math.pow(Ef, d);

        // Calculate Staffing Size
        double staffingSize = Ef / td;

        // Calculate Total Development Cost
        double totalDevelopmentCost = td * developmentCostPerDay;

        // Calculate Productivity
        double productivity = KLOC / Ef;

        // Calculate Future Value (FVA) and Present Value (PVA)
        double n = td / 12.0; // Convert development time to years
        double futureValue = totalDevelopmentCost * Math.pow((1 + interestRate), n);
        double presentValue = totalDevelopmentCost / Math.pow((1 + interestRate), n);

        // Calculate Equivalent Function Points (FP)
        double FP = totalLOC / 128.0; // LOC:FP ratio for C language

        // Calculate Effort Variation Index (EVI)
        double EVI = Math.abs(Ef - Ei) / Ei * 100;

        // Display Results
        System.out.println("COCOMO-I Project Metrics:");
        System.out.println("Project Type: " + projectType);
        System.out.println("KLOC: " + KLOC);
        System.out.println("Initial Effort (Ei): " + Ei + " PM");
        System.out.println("Effort Adjustment Factor (EAF): " + EAF);
        System.out.println("Final Effort (Ef): " + Ef + " PM");
        System.out.println("Development Time (td): " + td + " months");
        System.out.println("Staffing Size: " + staffingSize + " persons");
        System.out.println("Total Development Cost: INR " + totalDevelopmentCost);
        System.out.println("Productivity: " + productivity + " KLOC/PM");
        System.out.println("Future Value (FVA): INR " + futureValue);
        System.out.println("Present Value (PVA): INR " + presentValue);
        System.out.println("Equivalent Function Points (FP): " + FP);
        System.out.println("Effort Variation Index (EVI): " + EVI + "%");
    }
}
