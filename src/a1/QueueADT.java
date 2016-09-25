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
        this.front = array.length;
    }


    @Override
    public void enqueue(Object element) {
        E[] temp = (E[]) new Object[array.length * 2];
        for (int i = 0; i < array.length; i++){
            
        }

    }

    @Override
    public Object dequeue() {
        return null;
    }

    @Override
    public Object peek() {
        return array[front];
    }

    @Override
    public int length() {
        return array.length;
    }
}
