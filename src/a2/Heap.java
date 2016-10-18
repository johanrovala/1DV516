package a2;

/**
 * Created by johanrovala on 13/10/16.
 */

import java.util.List;

public class Heap {

    /*
     * Variables
     */

    private List<A2Item> list;
    private int size;
    private int prev;
    private int next;
    private int max;

    /*
     * Constructor - Initializes the list.
     */

    public Heap(List<A2Item> userList){
        this.list = userList;
    }

    /*
     * buildHeap() - Calls maxHeap with the amout of 'levels' that the heap should have,
     * predetermined by the size of the list / 2.
     */

    public void buildHeap(){
        size = list.size() - 1;

        for (int i = (size/2); i >= 0; i--){
            maxHeap(i);
        }
    }

    /*
     * maxHeap - Builds a max heap.
     */

    private void maxHeap(int index){
        prev = (2 * index);
        next = (2 * index) + 1;

        if (prev <= size && list.get(prev).getTransactionValue() > list.get(index).getTransactionValue()){
            max = prev;
        }else{
            max = index;
        }

        if (next <= size && list.get(next).getTransactionValue() > list.get(max).getTransactionValue()){
            max = next;
        }

        if (max != index){
            exchange(index, max);
            maxHeap(max);
        }
    }

    /*
     * exchange - Acts as a type of 'removal' for the heap (deletemax) although this is not exactly what this method does.
     * It simply exchanges two predetermined items in the list. Explanation in the method below.
     */

    private void exchange(int i, int j){
        A2Item temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);

    }

    /*
     * sort - Starts of by building a max heap from the provided list.
     * After that it sorts the list by performing a dumbed down version of a max delete where it places the biggest item
     * at the end of the list, then creating a new max heap from the rest of the items. By doing this for the entire length
     * of the list all items will be sorted, since the max item in the new max heap will be placed ('deleted') and placed in the
     * correct order of the list.
     */

    public void sort(){
        buildHeap();

        for (int i = size; i > 0; i--){
            exchange(0, i);
            size--;
            maxHeap(0);
        }
    }
}