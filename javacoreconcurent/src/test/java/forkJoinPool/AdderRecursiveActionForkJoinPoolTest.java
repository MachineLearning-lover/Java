package forkJoinPool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import static java.util.stream.Collectors.toList;

@RunWith(JUnit4.class)
public class AdderRecursiveActionForkJoinPoolTest {

    @Test
    public void test_adder_fork_join_pool(){
        Random random = new Random();

        List<Long> data = random
                .longs(100_000_000, 1, 5)
                .boxed()
                .collect(toList());

        Instant start = Instant.now();
        System.out.println(data.stream().reduce(0L, (x,y) -> x+y));
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end));


        start = Instant.now();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new AdderRecursiveAction(data));
        end = Instant.now();
        System.out.println(Duration.between(start,end));

        start = Instant.now();
        System.out.println(forkJoinPool.invoke(new AdderRecursiveTask(data)));
        end = Instant.now();
        System.out.println(Duration.between(start,end));

    }

}