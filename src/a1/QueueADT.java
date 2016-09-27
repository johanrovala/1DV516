package a1;

/**
 * Created by johanrovala on 25/09/16.
 */
public class QueueADT<E> implements A1Queue{

    private E[] array;
    private int front, total;

    public QueueADT(){
        this.total = 10;
        this.array = (E[]) new Object[total];
        this.front = 0;
    }


    @Override
    public void enqueue(Object element) {
        if (array.length == total){
            resize();
        }
        array[front] = (E) element;
        front++;
    }

    @Override
    public Object dequeue() {
        Object element = array[front];
        array[front] = null;
        return element;
    }

    @Override
    public Object peek() {
        return array[front];
    }

    @Override
    public int length() {
        return array.length;
    }

    public void resize() {
        E[] tmp = (E[]) new Object[total * 2];
        for (int i = 0; i < array.length; i++){
            tmp[i] = array[i];
        }
        array = tmp;
    }
}
