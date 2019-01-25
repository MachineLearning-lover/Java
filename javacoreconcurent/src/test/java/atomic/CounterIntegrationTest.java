package atomic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(JUnit4.class)
public class CounterIntegrationTest {

    @Test
    public void test_increment_one_safe_counter() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        CounterWithAtomicVariable counterWithAtomicVariable = new CounterWithAtomicVariable();

        IntStream.range(0, 1000)
                .forEach(count -> service.submit(counterWithAtomicVariable::incrementOne));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals(1000, counterWithAtomicVariable.getCounter());
    }

    @Test
    public void test_increment_one_unsafe_counter() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        CounterUnsafe counterUnsafe = new CounterUnsafe();

        IntStream.range(0, 1000)
                .forEach(count -> service.submit(counterUnsafe::incrementOne));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertNotEquals(1000, counterUnsafe.getCounter());
    }
}
