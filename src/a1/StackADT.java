package a1;


/**
 * Created by johanrovala on 29/09/16.
 *
 * Stack ADT
 */
public class StackADT implements A1Stack{

    private Node first;
    private int size;

    public StackADT(){
        first = null;
        size = 0;
    }

    @Override
    public void push(Object element) {
        Node test = first;
        first = new Node(element);
        first.next = test;
        size++;
    }

    @Override
    public Object pop() {
        Object data = first.data;
        first = first.next;
        size--;
        return data;
    }

    @Override
    public Object peek() {
        return first.data;
    }

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
}
