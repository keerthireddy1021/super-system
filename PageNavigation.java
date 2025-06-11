import java.util.Scanner;
import java.util.Stack;

public class PageNavigation {
    static Stack<String> backStack = new Stack<>();
    static Stack<String> forwardStack = new Stack<>();
    static String currentPage = "Page 1";
    static int pageCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- You are on: " + currentPage + " ---");
            System.out.println("Options: 1. Next Page  2. Back  3. Forward  4. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Next Page
                    backStack.push(currentPage);
                    forwardStack.clear();
                    pageCounter++;
                    currentPage = "Page " + pageCounter;
                    break;

                case 2: // Back
                    if (!backStack.isEmpty()) {
                        forwardStack.push(currentPage);
                        currentPage = backStack.pop();
                    } else {
                        System.out.println("No previous page.");
                    }
                    break;

                case 3: // Forward
                    if (!forwardStack.isEmpty()) {
                        backStack.push(currentPage);
                        currentPage = forwardStack.pop();
                    } else {
                        System.out.println("No next page.");
                    }
                    break;

                case 4: // Exit
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}