package a1;


/**
 * Created by johanrovala on 25/09/16.
 *
 * Queue ADT.
 */
public class QueueADT<E> implements A1Queue{

    /*
     * Variables
     */

    private Node first;
    private Node last;
    private int size;

    /*
     * Constructor
     */

    public QueueADT(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /*
     * enqueue - Add element to the Queue.
     */

    @Override
    public void enqueue(Object element) {
        if(isEmpty()){
            last = first = new Node(element);
        }else{
            last.next = new Node(element);
            last = last.next;
        }
        size++;
    }

    /*
     * dequeue - Remove and return first added element of the Queue.
     */

    @Override
    public Object dequeue() {
        if (isEmpty()) throw new IllegalArgumentException();
        Object val = first.data;
        first = first.next;
        size--;
        if(isEmpty()) first = null;
        return val;
    }

    /*
     * peek - Returns the first element of the Queue.
     */

    @Override
    public Object peek() {
        if (isEmpty()) throw new IllegalArgumentException();
        return first.data;
    }

    /*
     * length - Returns length of the Queue.
     */

    @Override
    public int length() {
        return size;
    }

    /*
     * isEmpty - Private helper method, checks if the queue is empty.
     */

    private boolean isEmpty() {
        return first == null;
    }

    /*
     * testPrint - Test method, checks if objects are ordered and added in a correct way.
     */

    public void testPrint() {
        Node tmp = first;
        while (tmp != null){
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }

    /*
     * Test
     */

    public static void main(String[] args){
        QueueADT queueADT = new QueueADT();

        queueADT.enqueue("yo");
        queueADT.enqueue("yo2");
        queueADT.enqueue("yo3");
        queueADT.enqueue("yo4");
        queueADT.enqueue("yo5");

        System.out.println(queueADT.dequeue());
        System.out.println(queueADT.dequeue());
        System.out.println(queueADT.dequeue());
        System.out.println(queueADT.dequeue());
        System.out.println(queueADT.dequeue());


        queueADT.enqueue("yo");
        queueADT.enqueue("yo2");
        queueADT.enqueue("yo3");
        queueADT.enqueue("yo4");
        queueADT.enqueue("yo5");

        queueADT.testPrint();
    }

    private class Node<E> {
        public Node next;
        public Object data;

        public Node(Object o){
            this.data = o;
        }

    }
}
