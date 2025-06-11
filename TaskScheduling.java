import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
public class TaskScheduling {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Queue<String> queue=new ArrayDeque<>();
        System.out.println("Enter the number of tasks:");
        int n=sc.nextInt();
        for(int i=0;i<=n;i++){
            System.out.print("Enter task"+i+":");
            String task=sc.nextLine();
            queue.offer(task);
            if(queue.size()>1){
                String removed=queue.poll();
                System.out.println("Removed previous task:"+removed);
            }
            System.out.println("Current task in queue:"+queue.peek());

        }
        System.out.println("Final task remaining in queue:"+queue.peek());
        sc.close();
    }
    
}
