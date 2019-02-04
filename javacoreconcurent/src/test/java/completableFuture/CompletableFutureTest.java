package completableFuture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@RunWith(JUnit4.class)
public class CompletableFutureTest {

    Supplier<String> supplier = () -> {
        try {
            Thread.sleep(1_000);
            System.out.println("waited on another thread 1s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ana";
    };

    public static CompletableFuture<Integer> create(int n){
        return CompletableFuture.supplyAsync( () -> n);
    }

    public static CompletableFuture<String> createString(String string){
        return CompletableFuture.supplyAsync(() -> string);
    }

    @Test
    public void create_complete(){
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
    }

    @Test
    public void getValue() throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(supplier);

        System.out.println(completableFuture.get());
    }

    @Test
    public void dontWait() throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(supplier)
                .thenApply(String::toUpperCase);

        System.out.println(completableFuture.get());

    }

    @Test
    public void createPipeline(){
        CompletableFuture<Integer> completableFuture = new CompletableFuture();
        completableFuture.thenApply(x -> x+1)
                .thenApply(x -> x*x)
                .thenAccept(System.out::println);
        completableFuture.complete(10);
    }

    @Test
    public void exception(){
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        completableFuture.exceptionally(throwable -> {System.out.println("error"); return 1;})
                .thenApply(x -> x+1)
                .thenApply(x -> 2*x)
                .thenAccept(System.out::println);

        completableFuture.completeExceptionally(new RuntimeException("smth went wrong"));
    }

    @Test
    public void completeOnTimeout() throws InterruptedException {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        completableFuture.completeOnTimeout(100, 3, TimeUnit.SECONDS);
        completableFuture.exceptionally(throwable -> {System.out.println("error"); return 1;})
                .thenApply(x -> x+1)
                .thenApply(x -> 2*x)
                .thenAccept(System.out::println);

        Thread.sleep(4000);
        completableFuture.completeExceptionally(new RuntimeException("smth went wrong"));
    }

    @Test
    public void combine(){
        create(2).thenCombineAsync(create(5), (x,y)->x*y)
                .thenAccept(System.out::println);

        create(2).thenCombineAsync(createString("mereAreAna"), (x,y) -> x*y.length())
                .thenAccept(System.out::println);
    }

    @Test
    public void compose(){

    }

}
