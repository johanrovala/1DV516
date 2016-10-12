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

    @Override
    public long bubbleSortByTransactionValue(List<A2Item> array) {
        Item temp;

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

        return 0;
    }

    @Override
    public long quickSortByTransactionValue(List<A2Item> array) {
        quickSort(array, array.size()-1, 0);
        return 0;
    }

    private void quickSort(List<A2Item> array, int p, int low){
        int q;
        if (p > low){
            System.out.println("run");
            q = partition(array, p, low);
            quickSort(array, q - 1, low);
            quickSort(array, p, q + 1);
        }
    }

    private int partition(List<A2Item> array, int high, int low){
        Item pivot = (Item) array.get(high);


        int i = low;
        int j = high - 1;

        System.out.println("i at start: " + i);
        System.out.println("j at start: " + j);

        Item temp;

        while(j >= i){
            System.out.println("inside while");
            double iVal = array.get(i).getTransactionValue();
            double jVal = array.get(j).getTransactionValue();

            if(j == i && iVal > pivot.getTransactionValue()){
                System.out.println("swap pivot");
                temp = (Item) array.get(i);
                array.set(i, pivot);
                array.set(high, temp);
                for (int k = 0; k < array.size(); k++){
                    System.out.println("After partioning: " + array.get(k).toString());
                }
                return j;
            }

            if(iVal < pivot.getTransactionValue()){
                i++;
                System.out.println("i: " + i);
            }
            if(jVal > pivot.getTransactionValue()){
                j--;
                System.out.println("j: " + j);
            }
            if(iVal >= pivot.getTransactionValue() && jVal <= pivot.getTransactionValue()){
                System.out.println("swap");
                temp = (Item) array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
                i++;
            }
        }
        return j;
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
