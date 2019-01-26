package forkJoinPool;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class AdderRecursiveTask extends RecursiveTask {

    private static final int MAX_ELEM = 5;

    private List<Long> list;

    public AdderRecursiveTask(List<Long> list) {
        this.list = list;
    }

    @Override
    protected Object compute() {

        if (list.size() <= MAX_ELEM){
           return computeList();
        }
        else
        {
            int mid = list.size() / 2;
            AdderRecursiveTask subtask1 = new AdderRecursiveTask(list.subList(0,mid));
            AdderRecursiveTask subtask2 = new AdderRecursiveTask(list.subList(mid,list.size()));
            subtask1.fork();
            return (long) subtask2.compute() + (long) subtask1.join();
        }
    }

    private long computeList(){
        return list.stream().reduce(0L, (x, y) -> x + y);
    }
}
