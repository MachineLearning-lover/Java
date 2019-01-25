package blockingQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer {

    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void add()  {
        try {
            queue.put(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
