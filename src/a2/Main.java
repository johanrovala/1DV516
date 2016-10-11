package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by johanrovala on 07/10/16.
 */
public class Main implements A2Main{

    private Scanner scanner;

    /*
     * createItem - Takes the current line in the CSV file and returns it as a Item object.
     */

    private Item createItem(String line){
        String[] values = line.split("\\s*,\\*s");
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

        while(scanner.hasNext()){
            fileOutput = scanner.nextLine();
            currItem = createItem(fileOutput);
            list.add(currItem);
        }

        return list;
    }

    @Override
    public long bubbleSortByTransactionValue(List<A2Item> array) {
        Item temp;

        if(array.equals(null)){
            throw new NullPointerException("Array is null");
        }

        if(array.size() > 1){
            for(int i = 0; i < array.size() - 1; i++){
                for (int j = 0; j < array.size() - 1; j++){
                    if(array.get(i).getTransactionValue() > array.get(i+1).getTransactionValue()){
                        temp = (Item) array.get(i);
                        array.set(i, array.get(i+1));
                        array.set(i+1, temp);
                    }
                }
            }
        }

        return 0;
    }

    @Override
    public long quickSortByTransactionValue(List<A2Item> array) {
        System.out.println(array.toString());
        quickSort(array, array.size(), 0);
        System.out.println(array.toString());
        return 0;
    }

    private void quickSort(List<A2Item> array, int p, int low){
        int q;
        if (p < low){
            q = partition(array, p, low);
            quickSort(array, q - 1, low);
            quickSort(array, p, q + 1);
        }
    }

    private int partition(List<A2Item> array, int high, int low){
          double pivot = array.get(high).getTransactionValue();


        int i = low;
        int j = high - 1;

        Item temp;

        while(j > i){
            double iVal = array.get(i).getTransactionValue();
            double jVal = array.get(j).getTransactionValue();

            if(iVal < pivot){
                i++;
            }
            if(jVal > pivot){
                j++;
            }
            if(iVal > pivot && jVal < pivot){
                temp = (Item) array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
            }
            if(j == i){
                // byt plats på pivot-numret och det aktuella j/i indexet. 
            }
        }
        return high;
    }

    @Override
    public long heapSortByTransactionValue(List<A2Item> array) {
        return 0;
    }

    @Override
    public TreeSet<Map.Entry<String, Long>> compareAlgorithms(List<A2Item> array) {
        return null;
    }

    @Override
    public void printResults(TreeSet<Map.Entry<String, Long>> results) {

    }
}
