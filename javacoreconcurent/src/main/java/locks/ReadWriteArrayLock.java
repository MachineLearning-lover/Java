package locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteArrayLock implements Runnable{

    private List<Integer> numbers = new ArrayList<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private ReentrantReadWriteLock.ReadLock readLock = (ReentrantReadWriteLock.ReadLock) lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = (ReentrantReadWriteLock.WriteLock) lock.writeLock();

    public void addNumber(){
        writeLock.lock();
        try{
            int random = new Random().nextInt();
            numbers.add(random);
        } finally {
            writeLock.unlock();
        }

    }

    public void readNumber(){
        readLock.lock();
        try{
            int index = new Random().nextInt(numbers.size());
            System.out.println(numbers.get(index));
        } finally {
            readLock.unlock();
        }

    }

    @Override
    public void run() {
        addNumber();
        readNumber();
    }
}
