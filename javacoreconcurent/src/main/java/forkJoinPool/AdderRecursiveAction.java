package forkJoinPool;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class AdderRecursiveAction extends RecursiveAction {

    private static final int MAX_ELEM = 5;

    private List<Long> list;

    public AdderRecursiveAction(List<Long> list) {
        this.list = list;
    }

    @Override
    protected void compute() {
        if (list.size() <= MAX_ELEM){
//            System.out.println(computeList());
            computeList();
        }
            else
        {
            int mid = list.size() / 2;
            AdderRecursiveAction subtask1 = new AdderRecursiveAction(list.subList(0,mid));
            AdderRecursiveAction subtask2 = new AdderRecursiveAction(list.subList(mid,list.size()));
            invokeAll(subtask1, subtask2);
        }

    }

    private long computeList(){
        return list.stream().reduce(0L, (x, y) -> x + y);
    }
}
