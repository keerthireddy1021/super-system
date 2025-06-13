import java.util.Scanner;

public class FibonacciRecursion {
    
    // Recursive function to find nth Fibonacci number
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n; // base cases: fib(0)=0, fib(1)=1
        }
        return fibonacci(n - 1) + fibonacci(n - 2); // recursive relation
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of terms: ");
        int terms = sc.nextInt();
        
        System.out.println("Fibonacci Series up to " + terms + " terms:");
        for (int i = 0; i < terms; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        
        sc.close();
    }
}
