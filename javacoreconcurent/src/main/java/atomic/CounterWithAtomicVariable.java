package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterWithAtomicVariable {

    private AtomicInteger counter = new AtomicInteger(0);

    public void incrementOne(){
        counter.getAndIncrement();
    }

    public void decrementOne(){
        counter.getAndDecrement();
    }

    public int getCounter(){
        return counter.get();
    }
}
