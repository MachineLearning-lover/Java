package executors;

import atomic.CounterWithAtomicVariable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.annotation.Repeatable;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ServiceExecutorIntegrationTest {

    @Test
    public void test_fixed_pool_executor() throws InterruptedException {
        Instant start = Instant.now();
        ExecutorService executorService =  Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CounterWithAtomicVariable counterWithAtomicVariable = new CounterWithAtomicVariable();
        IntStream.range(0,1000).forEach(count -> executorService.execute(counterWithAtomicVariable::incrementOne));
        executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
        Instant end = Instant.now();
        assertEquals(1000, counterWithAtomicVariable.getCounter());
        System.out.println(Duration.between(start, end));

    }

    @Test
    public void test_cached_pool_executor() throws InterruptedException {
        Instant start = Instant.now();
        ExecutorService executorService = Executors.newCachedThreadPool();
        CounterWithAtomicVariable counterWithAtomicVariable = new CounterWithAtomicVariable();
        IntStream.range(0,1000).forEach(count -> executorService.execute(counterWithAtomicVariable::incrementOne));
        executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
        Instant end = Instant.now();
        assertEquals(1000, counterWithAtomicVariable.getCounter());
        System.out.println(Duration.between(start, end));
    }

    @Test
    public void test_single_thread_executor() throws InterruptedException {
        Instant start = Instant.now();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CounterWithAtomicVariable counterWithAtomicVariable = new CounterWithAtomicVariable();
        IntStream.range(0,1000).forEach(count -> executorService.execute(counterWithAtomicVariable::incrementOne));
        executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
        Instant end = Instant.now();
        assertEquals(1000, counterWithAtomicVariable.getCounter());
        System.out.println(Duration.between(start, end));
    }

}
