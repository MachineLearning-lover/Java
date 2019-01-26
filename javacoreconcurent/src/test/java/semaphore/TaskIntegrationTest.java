package semaphore;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(JUnit4.class)
public class TaskIntegrationTest {

    @Test
    public void test_semaphore() throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        IntStream.range(0, 1000).forEach(count-> executorService.execute(new Task(semaphore)));
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }
}