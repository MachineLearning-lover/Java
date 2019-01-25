package blockingQueue;

import java.util.concurrent.BlockingQueue;

public class Consumer {

    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void consume(){
        try {
            System.out.println("Consumed value: " + queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
