package a1;

/**
 * Created by johanrovala on 25/09/16.
 */
public class QueueADT<E> implements A1Queue{

    private E[] array;
    private int back, front;

    public QueueADT(){
        this.array = (E[]) new Object[2];
        this.back = 0;
        this.front = this.array.length;
    }


    @Override
    public void enqueue(Object element) {
        
    }

    @Override
    public Object dequeue() {
        return null;
    }

    @Override
    public Object peek() {
        return this.array[front];
    }

    @Override
    public int length() {
        return array.length;
    }
}
