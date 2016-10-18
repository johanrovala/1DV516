package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by johanrovala on 07/10/16.
 */
public class Main implements A2Main{

    /*
     * Variables
     */

    private Scanner scanner;

    /*
     * createItem - Takes the current line in the CSV file and returns it as a Item object.
     */

    private Item createItem(String line){
        String[] values = line.split(",");
        Item item = new Item();
        item.setPerformer(values[0]);
        item.setTransactionValue(Double.valueOf(values[1]));
        item.setDate(values[2]);
        return item;
    }

    /*
     * readCSVFile - Creates a scanner and reads the File. For each new line a new Item object is created and added in the
     * returned list.
     */

    @Override
    public List<A2Item> readCSVFile(String filename) {
        String fileOutput = "";
        List<A2Item> list = new ArrayList<>();
        A2Item currItem;

        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(scanner.hasNextLine()){
            fileOutput = scanner.nextLine();
            currItem = createItem(fileOutput);
            list.add(currItem);
        }

        return list;
    }

    /*
     * bubbleSort - basic implementation of bubblesort
     */

    @Override
    public long bubbleSortByTransactionValue(List<A2Item> array) {
        Item temp;

        // For time measuring

        long starttime = System.nanoTime();

        if(array.equals(null)){
            throw new NullPointerException("Array is null");
        }

        if(array.size() > 1){
            for(int i = 0; i < array.size() - 1; i++){
                for (int j = 0; j < array.size() - 1; j++){
                    if(array.get(j).getTransactionValue() > array.get(j+1).getTransactionValue()){
                        temp = (Item) array.get(j);
                        array.set(j, array.get(j+1));
                        array.set(j+1, temp);
                    }
                }
            }
        }


        // Time spent

        long timeDif = System.nanoTime() - starttime;


        return timeDif;
    }

    /*
     * quickSortByTransactionValue - Calls quickSort with the list, pivot and start point.
     */


    @Override
    public long quickSortByTransactionValue(List<A2Item> array) {

        // For time measuring

        long starttime = System.nanoTime();

        quickSort(array, array.size()-1, 0);

        // Time spent

        long timeDif = System.nanoTime() - starttime;

        return timeDif;
    }

    /*
     * quickSort - calls partition and gets the true position of the pivot. The list is
     * divided into three parts. [0..q-1], [q] and [q+1...array.size()]
     *
     * After the first partition is done, the same procedure is done on the two other lists. This
     * keeps going until p (the high val) is greater than the low val.
     */

    private void quickSort(List<A2Item> array, int p, int low){
        int q;
        if (p > low){
            q = partition(array, p, low);
            quickSort(array, q - 1, low);
            quickSort(array, p, q + 1);
        }
    }


    /*
     * partition - Splits the list into three sublists. [0..q-1], [q] and [q+1...array.size()].
     */

    private int partition(List<A2Item> array, int high, int low){
        Item pivot = (Item) array.get(high);

        int i = low;
        int j = high - 1;

        Item temp;

        while(j >= i){
            double iVal = array.get(i).getTransactionValue();
            double jVal = array.get(j).getTransactionValue();

            if(j == i && iVal > pivot.getTransactionValue()){
                temp = (Item) array.get(i);
                array.set(i, pivot);
                array.set(high, temp);
                return j;
            }

            if(iVal < pivot.getTransactionValue()){
                i++;
            }
            if(jVal > pivot.getTransactionValue()){
                j--;
            }
            if(iVal >= pivot.getTransactionValue() && jVal <= pivot.getTransactionValue()){
                temp = (Item) array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
                i++;
            }
        }
        return j;
    }

    /*
     * heapSortByTransactionValue - Creates a Heap out of the inputed array and calls sort.
     */

    @Override
    public long heapSortByTransactionValue(List<A2Item> array) {

        // For time measuring

        long starttime = System.nanoTime();

        Heap heap = new Heap(array);
        heap.sort();

        // Time spent

        long timeDif = System.nanoTime() - starttime;

        return timeDif;
    }

    /*
     * compareAlgorithms - Adds the name and time gathered from the different sorting algorithms to a Treeset.
     * The sorted Treeset is then returned.
     */

    @Override
    public TreeSet<Map.Entry<String, Long>> compareAlgorithms(List<A2Item> array) {
        long BStime = bubbleSortByTransactionValue(array);
        long QStime = quickSortByTransactionValue(array);
        long HPsort = heapSortByTransactionValue(array);

        Result bsResult = new Result("BubbleSort", BStime);
        Result qsResult = new Result("QuickSort", QStime);
        Result hpResult = new Result("HeapSort", HPsort);

        TreeSet set = new TreeSet();
        set.add(bsResult);
        set.add(qsResult);
        set.add(hpResult);

        return set;
    }

    /*
     * Result - In order to add custom Objects to the TreeSet they have to implement Map.Entry as well as
     * Comparable considering the fact that TreeSet uses Comparable.
     */

    class Result implements Map.Entry<String, Long>, Comparable<Map.Entry<String, Long>>{

        private String name;
        private long time;

        public Result(String name, long time){
            this.name = name;
            this.time = time;
        }

        @Override
        public String getKey() {
            return this.name;
        }

        @Override
        public Long getValue() {
            return this.time;
        }

        @Override
        public Long setValue(Long value) {
            return this.time;
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public int compareTo(Map.Entry<String, Long> o) {
            if (this.getValue() > o.getValue()){
                return 1;
            }
            else if (this.getValue() < o.getValue()){
                return -1;
            }
            return 0;
        }

        @Override
        public String toString(){
            return this.name + " " + this.getValue();
        }
    }

    /*
     * printResults - Simply calls println on the results.toString();
     */

    @Override
    public void printResults(TreeSet<Map.Entry<String, Long>> results) {
        System.out.println(results.toString());
    }

}
