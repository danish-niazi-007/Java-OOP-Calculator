public class CalculatorApp {

    // ===== Calculator Class (OOP Concepts) =====
    static class Calculator {
        private double num1;
        private double num2;
        public static int operationCount = 0;

        // Constructor
        public Calculator(double a, double b) {
            this.num1 = a;
            this.num2 = b;
        }

        public double add() {
            operationCount++;
            return num1 + num2;
        }

        public double subtract() {
            operationCount++;
            return num1 - num2;
        }

        public double multiply() {
            operationCount++;
            return num1 * num2;
        }

        public double divide() {
            operationCount++;
            if (num2 != 0)
                return num1 / num2;
            else
                return 0;
        }
    }

    // ===== Main Method =====
    public static void main(String[] args) {
        Calculator c1 = new Calculator(10, 5);
        System.out.println("Addition: " + c1.add());
        System.out.println("Subtraction: " + c1.subtract());

        Calculator c2 = new Calculator(20, 4);
        System.out.println("Multiplication: " + c2.multiply());
        System.out.println("Division: " + c2.divide());

        // Using static variable
        System.out.println("Total operations performed: " + Calculator.operationCount);
    }
}
