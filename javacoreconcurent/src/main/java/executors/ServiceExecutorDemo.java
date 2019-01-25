package executors;

import java.util.concurrent.ExecutorService;

public class ServiceExecutorDemo {

    private ExecutorService executorService;

    public ServiceExecutorDemo(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
