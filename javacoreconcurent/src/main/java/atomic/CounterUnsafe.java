package atomic;

public class CounterUnsafe {

    private int counter;

    public int getCounter(){
        return counter;
    }

    public void incrementOne(){
        counter++;
    }

    public void decrementOne(){
        counter--;
    }
}
