import java.util.Scanner;

public class StudentReportGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- School Details Input ---");
        System.out.print("Enter School Name: ");
        String schoolName = scanner.nextLine();
        System.out.print("Enter Teacher Name: ");
        String teacherName = scanner.nextLine();
        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();
        System.out.print("Enter Year: ");
        String year = scanner.nextLine();

        final int NUM_STUDENTS = 12;
        final int NUM_SUBJECTS = 6;

        String[] studentNames = new String[NUM_STUDENTS];
        double[][] scores = new double[NUM_STUDENTS][NUM_SUBJECTS];

        double[] totalMarks = new double[NUM_STUDENTS];
        String[] studentRanks = new String[NUM_STUDENTS];

        double[] subjectTotals = new double[NUM_SUBJECTS];
        double[] subjectAverages = new double[NUM_SUBJECTS];

        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        String[] subjects = {"English", "Math", "History", "Geography", "Science", "Programming"};

        System.out.println("\n--- Enter Student Records (12 Students) ---");

        for (int i = 0; i < NUM_STUDENTS; i++) {
            System.out.println("\n--- Student " + (i + 1) + " ---");
            System.out.print("Enter Student Name: ");
            studentNames[i] = scanner.nextLine();

            double currentTotal = 0.0;

            for (int j = 0; j < NUM_SUBJECTS; j++) {
                System.out.printf("Enter %s score for %s: ", subjects[j], studentNames[i]);
                double score = scanner.nextDouble();
                scores[i][j] = score;
                currentTotal += score;
            }
            scanner.nextLine();

            totalMarks[i] = currentTotal;

            if (currentTotal >= 540.0) {
                studentRanks[i] = "A";
                countA++;
            } else if (currentTotal >= 480.0) {
                studentRanks[i] = "B";
                countB++;
            } else if (currentTotal >= 420.0) {
                studentRanks[i] = "C";
                countC++;
            } else if (currentTotal >= 360.0) {
                studentRanks[i] = "D";
                countD++;
            } else {
                studentRanks[i] = "F";
                countF++;
            }
        }

        for (int j = 0; j < NUM_SUBJECTS; j++) {
            double sum = 0.0;
            for (int i = 0; i < NUM_STUDENTS; i++) {
                sum += scores[i][j];
            }
            subjectTotals[j] = sum;
            subjectAverages[j] = sum / NUM_STUDENTS;
        }

        double totalClassMarks = 0.0;
        for(double total : totalMarks) {
            totalClassMarks += total;
        }
        double overallAverage = totalClassMarks / NUM_STUDENTS;


        StringBuilder output = new StringBuilder();
        String SEP_LINE = "--------------------------------------------------------------------------------------------------------------------------------------------------\n";
        String DASH_LINE = "==================================================================================================================================================\n";

        output.append(DASH_LINE);
        output.append(String.format("%70s: %s\n", "School Name", schoolName));
        output.append(String.format("%70s: %s\n", "Teacher", teacherName));
        output.append(String.format("%70s: %s\n", "Grade", grade));
        output.append(String.format("%70s: %s\n", "Year", year));
        output.append(DASH_LINE);

        output.append(String.format("%-19s %9s %7s %9s %10s %9s %12s %9s %9s %s\n",
                "Student Name", "English", "Math", "History", "Geography", "Science", "Programming", "Total", "Rank", ""
        ));
        output.append(SEP_LINE);

        for (int i = 0; i < NUM_STUDENTS; i++) {
            output.append(String.format("%-19s %9.2f %7.2f %9.2f %10.2f %9.2f %12.2f %9.2f %9s %s\n",
                    studentNames[i],
                    scores[i][0], scores[i][1], scores[i][2], scores[i][3], scores[i][4], scores[i][5],
                    totalMarks[i], studentRanks[i], ""
            ));
        }
        output.append(DASH_LINE);

        output.append(String.format("%-19s", ""));
        for (int j = 0; j < NUM_SUBJECTS; j++) {
            output.append(String.format(" %9.2f", subjectTotals[j]));
        }
        output.append(String.format(" %9.2f %9s %s\n", totalClassMarks, "", ""));
        output.append(SEP_LINE);

        output.append(String.format("%-19s", ""));
        for (int j = 0; j < NUM_SUBJECTS; j++) {
            output.append(String.format(" %9.2f", subjectAverages[j]));
        }
        output.append(String.format(" %9.2f %9s %s\n", overallAverage, "", ""));
        output.append(DASH_LINE);

        output.append(String.format("\n%39s %s: %d %3s: %d %3s: %d %3s: %d %3s: %d\n",
                "Ranks", "A's", countA, "B's", countB, "C's", countC, "D's", countD, "F's", countF
        ));

        System.out.println(output.toString());
    }
}