package semaphore;

import java.util.concurrent.Semaphore;

public class Task  implements Runnable{

    private Semaphore semaphore;

    public Task(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            SlowService.callMe();
            System.out.println(semaphore.availablePermits());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
