package a1;


/**
 * Created by johanrovala on 29/09/16.
 *
 * Stack ADT
 */
public class StackADT<E> implements A1Stack{

    /*
     * Variables
     */

    private Node first;
    private int size;

    /*
     * Constructor
     */

    public StackADT(){
        first = null;
        size = 0;
    }

    /*
     * push() - pushes (adds) an element to the top of the stack (this is the first one to be popped, should no more elements be pushed).
     */

    @Override
    public void push(Object element) {
        Node test = first;
        first = new Node(element);
        first.next = test;
        size++;
    }

    /*
     * pop() - pops (removes) the first element of the stack (the latest added element).
     */

    @Override
    public Object pop() {
        Object data = first.data;
        first = first.next;
        size--;
        return data;
    }

    /*
     * peek() - returns the value of the first element of the stack.
     */

    @Override
    public Object peek() {
        return first.data;
    }

    /*
     * size() - returns the size of the stack.
     */

    @Override
    public int size() {
        return size;
    }

    /*
     * Test
     */

    public static void main(String[] args){
        StackADT stackADT = new StackADT();

        stackADT.push("hej");
        stackADT.push("då");
        stackADT.push("dårå");

        System.out.println(stackADT.peek());
        System.out.println(stackADT.pop());
        System.out.println(stackADT.peek());
    }

    private class Node<E> {
        public Node next;
        public Object data;

        public Node(Object o){
            this.data = o;
        }

    }
}
