package blockingQueue;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ProducerConsumerTest {

    @Test
    public void test_one_producer_one_consumer() throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue();
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        IntStream.range(0,10).forEach(count -> {
                        executorService.execute(consumer::consume);
                        executorService.execute(producer::add);});

        executorService.awaitTermination(100, TimeUnit.MILLISECONDS);

        System.out.println("Queue size: " + queue.size());

    }

}