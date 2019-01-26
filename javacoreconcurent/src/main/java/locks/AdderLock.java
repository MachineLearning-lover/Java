package locks;

import java.util.concurrent.locks.ReentrantLock;

public class AdderLock implements Runnable {

    private int counter;

    private ReentrantLock lock;

    public AdderLock(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        counter+=1;
        lock.unlock();
    }

    public Integer getCounter() {
        return counter;
    }
}
