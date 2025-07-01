package bmiCalculator;
import java.util.Scanner;
import java.io.Console;
import java.util.Locale;

// I Dessery Lefopane created this BMI Calculator.
// This BMI calculator is a program that takes a person's age, sex, ethnicity, height, and weight as input,
// calculates their Body Mass Index (BMI) using the formula BMI = weight / (height * height),
// and then categorizes the result (e.g., underweight, normal, overweight, or obese).
// It outputs a summary statement combining the user's details with their BMI value and category,
// helping users understand whether their weight is healthy for their height.

public class Bmicalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        char repeat = 0;



        String inputPassword=enterPassword(scanner);

        System.out.println(inputPassword);
        int test=0;
        while(inputPassword !=" "&& test<4){
            System.out.print("Incorrect password,retry:");
            inputPassword=enterPassword(scanner);
            test++;
        }
        if(test==4){
            System.out.println("Access denied,too many Password retry's");
        }else{
        System.out.println();


        do {
            //All Our code
            int unitChoice = getUnitChoice(scanner);

            double weight = (unitChoice == 1) ? getValidInput(scanner, "Enter your weight in kilograms : ", 10,600)
                    : getValidInput(scanner, "Enter your weight in pounds", 22, 13);

            double height = (unitChoice == 1) ? getValidInput(scanner, "Enter your height in meters : ", 0.5, 10.5)
                    : getValidInput(scanner, "Enter your height in inches", 20, 100);

            // Example user input variables
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter your sex (male/female): ");
            String sex = scanner.nextLine().toLowerCase();

            System.out.print("Enter your ethnicity (e.g., Asian, Caucasian, etc.): ");
            String ethnicity = scanner.nextLine().toLowerCase();

            double bmi = calculateBMI(unitChoice, weight, height);
            // Output the result with user details and BMI category
            System.out.println ("Your are a "+ ethnicity + " " + sex + " with "+ age + " years of age and your BMI is" + " "  + bmi+" "+displayCategory(bmi));
            repeat=askToRepeat(scanner);
    

        } while (repeat == 'Y' || repeat == 'y');
    }}
    //Unit - Metric Imperial
    public static int getUnitChoice(Scanner scanner) {
        int choice;
        while(true) {
            System.out.println("Select a preffered unit:\n"
                    + "1.Metric(kg, m)\n"
                    + "2.Imperial (1bs,in)\n"
                    + "Please select either option 1 or option 2");

            if(scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if(choice ==1 || choice == 2) {
                    break;
                }else {
                    System.out.println("Invalid choice. Please enter either 1 or 2");
                }
            }else {
                System.out.println("Invalid input. Please enter a number (1 or 2");
                scanner.next();

            }
        }

        return choice;
    }
    public static double getValidInput(Scanner scanner, String prompt, double min, double max) {
        double value;
        while(true) {
            System.out.println(prompt);
            if(scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if(value >= min && value <= max) {
                    break;
                }else {
                    System.out.printf("Please enter a value between %.1f and %.1f.\n", min, max);
                }
            }else {
                System.out.println("Invalid input. Please enter a value");
                scanner.next();
            }
        }

        return value;
    }
    public static double calculateBMI(int unitChoice, double weight, double height) {
        double totalBMI;

        if(unitChoice == 1) {
            totalBMI = weight / (height * height);
        }else {
            totalBMI = (703 * weight) / (height / height);
        }
        return totalBMI;
    }
    // Method to determine BMI category based on BMI value
    public static String displayCategory(double bmi) {


        if (bmi < 16.0) {
            return "Category: serverly underweight";
        }else if(bmi >=16.0 && bmi <=18.4) {
            return "Category: underweight";
        }else if(bmi >=18.5 && bmi <=24.9) {
            return "Category : normal";}
        else if(bmi >=25.0 && bmi <=29.9) {
            return"Category : Overweight";
        }else if(bmi >=30.0 && bmi <=34.9) {
            return"Category : moderately obese";
        }else if(bmi >=35.0 && bmi <=39.9) {
            return"Category : Serverly Obese";
        }
        else {
            return "Category : Morbidly Obese";}

    }
    public static char askToRepeat( Scanner scanner) {
        char repeat;
        while(true) {
            System.out.println("Do you want to repeat the program again");
            String answer = scanner.next();
            repeat=answer.charAt(0);
            if(repeat=='y'||repeat=='Y') break;
            if(repeat=='n'||repeat=='N') break;
            else System.out.println("Wrong Input");
        }
        return repeat;
    }
    public static String enterPassword(Scanner scanner) {
        final String correctPassword = "nonhle123"; // You can change this

        String inputPassword = null;

        Console console = System.console();
        if (console != null) {
            // Hides password input
            char[] passwordChars = console.readPassword("Enter password to access the BMI calculator: ");
            inputPassword = new String(passwordChars);
        } else {
            // Fallback if console is not available (e.g., in some IDEs)

            System.out.print("Enter password to access the BMI calculator: ");
            inputPassword = scanner.nextLine();
        }

        if (!correctPassword.equals(inputPassword)) {
            System.out.println("Access denied. Incorrect password.");
            return inputPassword;
        }
        return " ";
    }
}

