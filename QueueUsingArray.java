public class QueueUsingArray{
    private int[] queue;
    private int front,rear,size,capacity;
    public QueueUsingArray(int capacity){
        this.capacity=capacity;
        queue=new int[capacity];
        front=0;
        rear=0;
        size=0;
    }
    public void enqueue(int value){
        if(isFull()){
            System.out.println("Queue if full cannot enqueue");
            return;
        }
        rear++;
        queue[rear]=value;
        size++;
        System.out.println(value+"enqueued.");
        public int dequeue(){
            if(isEmpty()){
                System.out.println("Queue is empty cannot dequeued");
                return -1;
            }
            int removed=queue[front];
            front++;
            size--;
            System.out.println(removed+"dequeued");
            return removed;
        }
        public int peek(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            return queue[front];
        }
        public boolean isFull(){
            return size==capacity;
        }
        public void display(){
            if(isEmpty()){
                System.out.println("queue is empty");
                return;
            }
            System.out.println
        }
    }
}