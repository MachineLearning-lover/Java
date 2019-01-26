package locks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class AdderLockTest {


    @Test
    public void test_adder() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ReentrantLock lock = new ReentrantLock();
        AdderLock adderLock = new AdderLock(lock);
        IntStream.range(0,1_000).forEach(count -> executorService.submit(adderLock));
        executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
        assertEquals(1_000, adderLock.getCounter().intValue());
    }

}